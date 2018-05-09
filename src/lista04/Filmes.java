package lista04;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Filmes {
  static final AuxInput input = new AuxInput();
  Map<String,Filme> listaFilmes = new HashMap<>();
  Map<String, Filme> listaFilmesDisponiveis = new HashMap<>();
  Map<String, Filme> listaFilmesLocados = new HashMap<>();
  
  boolean cadastrarFilme() {
    Filme novoFilme = new Filme();
    boolean cancelar=false, sair=false;
    
    while(!sair) {
      cancelar=!novoFilme.cadastrarNome();
      if(cancelar) {
        return false;
      }
      if(listaFilmes.containsKey(novoFilme.nome)) {
        String mensagem = "Esse filme já está cadastrado.";
        JOptionPane.showMessageDialog(null, mensagem);
      } else {
        sair=true;
      }
    }
    
    if(!novoFilme.cadastrarGenero()) {
      return false;
    }
    if(!novoFilme.cadastrarPreco()) {
      return false;
    }
    listaFilmes.put(novoFilme.nome, novoFilme);
    listaFilmesDisponiveis.put(novoFilme.nome, novoFilme);
    return true;    
  }
  
  boolean excluirFilme(String nome) {      
    listaFilmesDisponiveis.remove(nome);
    return listaFilmes.remove(nome)!=null;
  }
  
  boolean existeFilme(String nome) {
    return listaFilmes.containsKey(nome);
  }
  
  boolean filmeDisponivel(String nome) {
    if(listaFilmes.containsKey(nome)) {
      return listaFilmes.get(nome).emEstoque;
    } else {
      return false;
    }
  }
  
  Filme pegarFilme(String nome) {
    return listaFilmes.get(nome);
  }
  
  boolean locarFilme(String nome) {
    if(listaFilmes.containsKey(nome)) {
      Filme filme = listaFilmes.get(nome);
      filme.alocar();
      listaFilmes.put(nome, filme);
      listaFilmesLocados.put(nome, filme);
      listaFilmesDisponiveis.remove(nome);
      return true;
    } else {
      return false;
    }
  }
  
  boolean retornarFilme(String nome) {
    if(listaFilmes.containsKey(nome)) {
      Filme filme = listaFilmes.get(nome);
      filme.retornar();
      listaFilmes.put(nome, filme);
      listaFilmesLocados.remove(nome);
      listaFilmesDisponiveis.put(nome, filme);
      return true;
    } else {
      return false;
    }
  }
  
  class Filme {
    String nome="", genero="";
    double preco=0;
    boolean emEstoque=true;
    
    boolean cadastrarNome() {
      String mensagem="Digite o nome do filme";
      nome=input.strInput(mensagem);
      if(nome.equals("")) {
        return false;
      }
      return true;
    }
    
    boolean cadastrarGenero() {
      String mensagem="Digite o nome do filme";
      genero=input.strInput(mensagem);
      if(genero.equals("")) {
        return false;
      }
      return true;
    }
    
    boolean cadastrarPreco() {
      String mensagem="Digite o preço do filme";
      preco=input.dblToDbl(mensagem, true, 1, (double) -1);
      if(preco<0) {
        return false;
      }
      return true;
    }
    
    void alocar() {
      emEstoque=false;
    }
    
    void retornar() {
      emEstoque=true;
    }
  }
}

