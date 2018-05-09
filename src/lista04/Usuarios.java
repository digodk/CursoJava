package lista04;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Usuarios {
  static final AuxInput input = new AuxInput();
  Map<String, Usuario> listaUsuarios = new HashMap<>();
  
  boolean cadastrarUsuario() {
    Usuario usuario = new Usuario();
    
    if(usuario.cadastrar()) {
      listaUsuarios.put(usuario.nome, usuario);
      return true;
    } else
      return false;
  }
  
  boolean excluirUsuario(String nome) {
    return listaUsuarios.remove(nome)!=null;
  }
  
  boolean existeUsuario(String nome) {
    return listaUsuarios.containsKey(nome);
  }
  
  Usuario pegarUsuario(String nome) {
    return listaUsuarios.get(nome);
  }
  
  boolean cadastrarLocacao(String nomeUsuario, String nomeFilme, double valor) {
    if(listaUsuarios.containsKey(nomeUsuario)) {
      Usuario usuario = listaUsuarios.get(nomeUsuario);
      usuario.locar(nomeFilme, valor);
      listaUsuarios.put(nomeUsuario, usuario);
      return true;
    } else {
    return false;
    }
  }
  
  int numLocacoes(String nome) {
    if(listaUsuarios.containsKey(nome)) {
      return listaUsuarios.get(nome).numLocacoes();
    } else {
      return 0;
    }
  }
  
  class Usuario {
    String nome="", endereco="", email="";
    double valorAluguel=0;
    int idade=0, telefone=0;
    Map<String, Double> Locacoes = new HashMap<>();
    
    boolean cadastrar() {
      String mensagem="";
      boolean sair=false;
      
      while(!sair) {
        mensagem="Informe o nome completo do usuário.";
        nome=input.strInput(mensagem);
        if(nome.equals("")) {
          return false;
        }
        if(listaUsuarios.containsKey(nome)) {
          mensagem="Esse usuário já está cadastrado.";
          JOptionPane.showMessageDialog(null, mensagem);
        } else {
          sair=true;
        }
      }
      mensagem="Digite a idade do usuário";
      idade=input.intToInt(mensagem, false, 1, -1);
      if(idade<0) {
        return false;
      }
      mensagem="Digite o endereço do usuário";
      endereco=input.strInput(mensagem);
      if(endereco.equals("")) {
        return false;
      }
      mensagem="Insira o telefone do usuário";
      telefone=input.intToInt(mensagem, false, 1, -1);
      if(telefone<0) {
        return false;
      }
      mensagem="Insira o email do usuário.";
      email = input.strInput(mensagem);
      if(email.equals("")) {
        return false;
      }
      return true;
    }
    
    int numLocacoes() {
      return Locacoes.size();
    }
    
    void locar(String nome, double valor) {
      Locacoes.put(nome, valor);
      valorAluguel += valor;
    }
    
    boolean devolver(String nome) {
      if(Locacoes.containsKey(nome)){
        
        return true;
      } else {
        return false;
      }
    }
  }
}
