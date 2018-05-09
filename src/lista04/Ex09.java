package lista04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * Criar um sistema onde o usu�rio ter� as seguintes op��es:
 * e. Cadastrar filme
 * f. Cadastrar usu�rio
 * g. Locar filme
 * h. Excluir filme
 * i. Excluir usu�rio
 * j. Filmes locados
 * a. Cadastrar filme: Dever� ser pedido o nome do filme, g�nero e valor do filme alugado.
 * b. Cadastrar usu�rio: Dever� ser pedido, nome, idade, endere�o, telefone e e-mail.
 * N�o poder� ter nomes repetidos.
 * c. Locar filme: Dever� ser pedido o nome do usu�rio e o nome do filme,
 * especificar a quantidade de dias que ser� alugado. Cadastre em uma matriz o nome do usu�rio,
 * filme, dias alugados, valor do filme e o total a pagar.
 * d. Excluir usu�rio: Poder� ser exclu�do o usu�rio apenas se n�o houver nenhum filme locado.
 * e. Excluir filme: Poder� ser exclu�do o filme, caso n�o esteja sendo locado.
 * f. Filmes locados: Exibir os filmes locados, juntamente com o nome do usu�rio,
 * quantidade de dias e total a pagar.
 * Os filmes e usu�rios exclu�dos n�o dever�o aparecer nessa listagem.
 */
public class Ex09 {

  public static void main(String[] args) {
    Locadora locadora = new Locadora();
    locadora.funcionar();
  }

}

class Locadora {
  static final AuxInput input = new AuxInput();
  final static int NOVOFILME = 0, NOVOUSUARIO = 1, LOCARFILME = 2, RETORNARFILME = 3, EXCLUIRFILME = 4,
      EXCLUIRUSUARIO = 5, LISTARLOCACOES = 6;
  double valorTotal = 0;
  String[] opcoes = { "a. Cadastrar filme", "b. Cadastrar usu�rio", "c. Locar filme", "d. Devolver filme",
      "e. Excluir filme", "f. Excluir usu�rio", "g. Filmes locados" };
  Map<String, Filme> listaFilmes = new HashMap<>();
  Map<String, Filme> listaFilmesDisponiveis = new HashMap<>();
  Map<String, Filme> listaFilmesLocados = new HashMap<>();
  Map<String, Usuario> listaUsuarios = new HashMap<>();
  Map<String, Locacao> listaLocacoes = new HashMap<>();
  ArrayList<String> usuariosSemPendencia = new ArrayList<>();

  void funcionar() {
    String mensagem = "Escolha a a��o desejada.";
    int escolha = -2;
    while (escolha != -1) {
      escolha = input.optionToInt(mensagem, opcoes);
      switch (escolha) {
      case NOVOFILME:
        cadastrarFilme();
        break;
      case NOVOUSUARIO:
        cadastrarUsuario();
        break;
      case LOCARFILME:
        locarFilme();
        break;
      case RETORNARFILME:
        retornarFilme();
        break;
      case EXCLUIRFILME:
        excluirFilme();
        break;
      case EXCLUIRUSUARIO:
        excluirUsuario();
        break;
      case LISTARLOCACOES:
        listarLocacoes();
        break;
      }
    }
  }

  void cadastrarFilme() {
    String mensagem = "";
    Filme novoFilme = new Filme();
    boolean sair = false;

    while (!sair) {
      if (!novoFilme.cadastrarNome()) {
        return;
      }
      if (listaFilmes.containsKey(novoFilme.nome)) {
        mensagem = "Esse filme j� est� cadastrado.";
        JOptionPane.showMessageDialog(null, mensagem);
      } else {
        sair = true;
      }
    }

    if (!novoFilme.cadastrarGenero()) {
      return;
    }
    if (!novoFilme.cadastrarPreco()) {
      return;
    }
    listaFilmes.put(novoFilme.nome, novoFilme);
    listaFilmesDisponiveis.put(novoFilme.nome, novoFilme);
    mensagem = "Cadastro realizado.";
    JOptionPane.showMessageDialog(null, mensagem);
  }

  void cadastrarUsuario() {
    Usuario usuario = new Usuario();

    if (usuario.cadastrar()) {
      listaUsuarios.put(usuario.nome, usuario);
      usuariosSemPendencia.add(usuario.nome);
      String mensagem = "Usu�rio cadastrado.";
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }

  void locarFilme() {
    String mensagem = "";
    Filme filme = new Filme();
    Usuario usuario = new Usuario();
    if (listaFilmesDisponiveis.size() == 0) {
      mensagem = "N�o h� filmes dispo�veis para alocar!";
      JOptionPane.showMessageDialog(null, mensagem);
    } else if (listaUsuarios.size() == 0) {
      mensagem = "N�o h� usu�rio cadastrados para alocar!";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o nome do filme que deseja alocar";
      String escolha = input.optionToString(mensagem, listaFilmesDisponiveis.keySet().toArray());
      if (!escolha.equals("")) {
        filme = listaFilmesDisponiveis.get(escolha);
      } else {
        return;
      }
      mensagem = "Selecione o usu�rio que vai locar o filme.";
      escolha = input.optionToString(mensagem, listaUsuarios.keySet().toArray());
      if (!escolha.equals("")) {
        usuario = listaUsuarios.get(escolha);
      } else {
        return;
      }
      mensagem = "Digite o n�mero de dias que o filme ser� alocado.";
      int numDias = input.intToInt(mensagem, false, 1, -1);
      if (numDias > 0) {
        Locacao locacao = new Locacao(filme, usuario, numDias);
        listaLocacoes.put(locacao.nomeFilme() + "-" + usuario.nome, locacao);
        filme.alocar();
        usuario.locar(locacao);
        listaFilmesDisponiveis.remove(filme.nome);
        listaFilmesLocados.put(filme.nome, filme);
        valorTotal += locacao.valor;
        mensagem = "Filme alugado. Valor total: R$%.2f";
        JOptionPane.showMessageDialog(null, String.format(mensagem, locacao.valor));
        if (usuariosSemPendencia.contains(usuario.nome)) {
          usuariosSemPendencia.remove(usuario.nome);
        }
      }
    }
  }

  void retornarFilme() {
    String mensagem = "";
    if (listaLocacoes.size() == 0) {
      mensagem = "N�o h� filmes locados.";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o filme que deseja retornar";
      String entrada = input.optionToString(mensagem, listaLocacoes.keySet().toArray());
      if (!entrada.equals("")) {
        Locacao locacao = listaLocacoes.get(entrada);
        valorTotal -= locacao.valor;
        Filme filme = locacao.filme;
        Usuario usuario = locacao.usuario;
        listaLocacoes.remove(entrada);
        filme.retornar();
        usuario.devolver(filme.nome);
        listaFilmesDisponiveis.put(filme.nome, filme);
        listaFilmesLocados.remove(filme.nome);
        if (usuario.numLocacoes() == 0) {
          usuariosSemPendencia.add(usuario.nome);
        }
        mensagem = "Filme devolvido";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void excluirFilme() {
    String mensagem = "";
    if (listaFilmesDisponiveis.size() == 0) {
      if (listaFilmesLocados.size() == 0) {
        mensagem = "N�o h� filmes cadastrados para exclus�o";
      } else {
        mensagem = String.format(
            "N�o h� filmes liberados para exclus�o.\nH� %s filmes que est�o alugados e n�o podem ser exclu�dos",
            listaFilmesLocados.size());
      }
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o filme que deseja excluir";
      String nome = input.optionToString(mensagem, listaFilmesDisponiveis.keySet().toArray());
      if (!nome.equals("")) {
        listaFilmesDisponiveis.remove(nome);
        mensagem = "Filme removido.";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void excluirUsuario() {
    String mensagem = "";
    if (usuariosSemPendencia.size() == 0) {
      if (listaUsuarios.size() == 0) {
        mensagem = "N�o h� usu�rios cadastrados.";
      } else {
        mensagem = String.format(
            "N�o h� usu�rios sem pend�ncias.\nH� %s usu�rios com pend�ncias que n�o podem ser exclu�dos.",
            listaUsuarios.size());
      }
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o usu�rio que deseja excluir";
      String entrada = input.optionToString(mensagem, usuariosSemPendencia.toArray());
      if (!entrada.equals("")) {
        listaUsuarios.remove(entrada);
        usuariosSemPendencia.remove(entrada);
        mensagem = "Usu�rio removido";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void listarLocacoes() {
    String mensagem = "", linhaRelatorio = "Usu�rio: %1$s, Filme: %2$s, Dias: %3$s, Valor: R$%4$.2f. \n";
    String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
    if (listaLocacoes.size() == 0) {
      mensagem = "N�o h� filmes locados para mostrar.";
    } else {
      mensagem = "Resumo das loca��es: \n" + linha;
      for (Locacao locacao : listaLocacoes.values()) {
        mensagem += String.format(linhaRelatorio, locacao.filme.nome, locacao.usuario.nome, locacao.numDias,
            locacao.valor);
      }
      mensagem += linha;
      mensagem += String.format("Valor total: R$%.2f", valorTotal);
    }
    JOptionPane.showMessageDialog(null, mensagem);
  }

  class Locacao {
    Filme filme;
    Usuario usuario;
    int numDias;
    double valor = 0;

    public Locacao(Filme filme, Usuario usuario, int numDias) {
      this.filme = filme;
      this.usuario = usuario;
      this.numDias = numDias;
      valor = numDias * filme.preco;
    }

    String nomeFilme() {
      return filme.nome;
    }
  }

  class Filme {
    String nome = "", genero = "";
    double preco = 0;
    boolean emEstoque = true;

    boolean cadastrarNome() {
      String mensagem = "Digite o nome do filme.";
      nome = input.strInput(mensagem);
      if (nome == null) {
        return false;
      }
      return true;
    }

    boolean cadastrarGenero() {
      String mensagem = "Digite o nome do g�nero do filme.";
      genero = input.strInput(mensagem);
      if (genero == null) {
        return false;
      }
      return true;
    }

    boolean cadastrarPreco() {
      String mensagem = "Digite o pre�o do filme.";
      preco = input.dblToDbl(mensagem, true, 1, (double) -1);
      if (preco < 0) {
        return false;
      }
      return true;
    }

    void alocar() {
      emEstoque = false;
    }

    void retornar() {
      emEstoque = true;
    }
  }

  class Usuario {
    String nome = "", endereco = "", email = "";
    double valorAluguel = 0;
    int idade = 0, telefone = 0;
    Map<String, Double> locacoes = new HashMap<>();

    boolean cadastrar() {
      String mensagem = "";
      boolean sair = false;

      while (!sair) {
        mensagem = "Informe o nome completo do usu�rio.";
        nome = input.strInput(mensagem);
        if (nome == null) {
          return false;
        }
        if (listaUsuarios.containsKey(nome)) {
          mensagem = "Esse usu�rio j� est� cadastrado.";
          JOptionPane.showMessageDialog(null, mensagem);
        } else {
          sair = true;
        }
      }
      mensagem = "Digite a idade do usu�rio";
      idade = input.intToInt(mensagem, false, 1, -1);
      if (idade < 0) {
        return false;
      }
      mensagem = "Digite o endere�o do usu�rio";
      endereco = input.strInput(mensagem);
      if (endereco == null) {
        return false;
      }
      mensagem = "Insira o telefone do usu�rio";
      telefone = input.intToInt(mensagem, false, 1, -1);
      if (telefone < 0) {
        return false;
      }
      mensagem = "Insira o email do usu�rio.";
      email = input.strInput(mensagem);
      if (email == null) {
        return false;
      }
      return true;
    }

    int numLocacoes() {
      return locacoes.size();
    }

    void locar(Locacao locacao) {
      double valor = locacao.valor;
      locacoes.put(locacao.nomeFilme(), valor);
      valorAluguel += valor;
    }

    boolean devolver(String nome) {
      if (locacoes.containsKey(nome)) {
        double valor = locacoes.get(nome);
        valorAluguel -= valor;
        locacoes.remove(nome);
        return true;
      } else {
        return false;
      }
    }
  }
}
