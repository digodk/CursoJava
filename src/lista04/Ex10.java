package lista04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * Desenvolver um sistema para cadastrar at� 30 produtos. Antes de realizar as fun��es dever� ser 
 * pedido um usu�rio e uma senha. Deixe pr�-cadastrado um array com usu�rio e senha,
 * se o usu�rio e a senha informados pertencerem ao mesmo �ndice ser� liberada a �rea contendo
 * as fun��es do sistema, caso contr�rio ir� pedir o usu�rio e a senha novamente. 
 * Fun��es do sistema:
 * a. Cadastrar produtos.
 * b. Alterar produtos.
 * c. Excluir produtos.
 * d. Listar produtos.
 * e. Cadastrar clientes.
 * f. Alterar clientes.
 * g. Excluir clientes.
 * h. Listar clientes.
 * i. Realizar venda.
 * j. Excluir venda.
 * k. Verificar estoque.
 * l. Extrato.
 * 
 * a. Pedir o nome do produto, fabricante, quantidade em estoque e valor unit�rio.
 * b. Para alterar produtos dever� ser pedido o nome do produto.
 * Caso o produto exista ser� pedido para recadastrar o nome, quantidade,
 * fabricante e valor unit�rio.
 * c. Para excluir o produto basta pedir o nome e realizar a exclus�o.
 * d. Exibir o nome do produto, fabricante, quantidade em estoque e valor unit�rio.
 * e. Cadastro de clientes consiste em: Nome, sobrenome, email, telefone e endere�o.
 * f. Procurar pelo nome do cliente e alterar todos os campos.
 * g. Excluir clientes atrav�s do nome.
 * h. Exibir a lista contendo todos os clientes cadastrados.
 * i. Pedir o c�digo da venda, nome do produto, quantidade e se deseja continuar o la�o ou n�o.
 * Quando sair do la�o pedir o nome do cliente e vincular os produtos adquiridos em um vetor.
 * Quando realizada uma compra valide se a quantidade em estoque seja maior ou igual a pedida,
 * caso contr�rio n�o poder� ser efetuada a compra.
 * N�o esque�a de subtrair da quantidade em estoque ap�s realizar a compra dos produtos.
 * O c�digo da venda � referente a compra de todos os produtos daquela venda. Isso vai auxiliar
 * a excluir os produtos.
 * Voc� pode digitar um c�digo para a venda ou deixar que o sistema crie automaticamente uma
 * sequ�ncia num�rica.
 * j. Atrav�s do c�digo da venda ser� exclu�do todos os produtos vendidos naquela transa��o. 
 * Incremente o estoque quando realizar uma exclus�o de produtos.
 * k. Verificar estoque servir� para exibir o nome dos produtos e a quantidade do estoque.
 * l. Para o extrato exibir todos os produtos vendidos, juntamente com o nome do cliente, 
 * valor unit�rio, quantidade e total da compra.
 *  */
public class Ex10 {

  public static void main(String[] args) {
    Deposito deposito = new Deposito();
    deposito.funcionar();

  }

}

class Deposito {
  final static int CADASTRARPRODUTO = 0, ALTERARPRODUTO = 1, EXCLUIRPRODUTO = 2, LISTARPRODUTO = 3,
      CADASTRARCLIENTE = 4, ALTERARCLIENTE = 5, EXCLUIRCLIENTE = 6, LISTARCLIENTE = 7, CADASTRARVENDA = 8,
      EXCLUIRVENDA = 9, VERIFICARESTOQUE = 10, EXTRATO = 11;
  String linhaTexto = new String(new char[20]).replace("\0", "-") + "\n \n";
  int codigoDisponivel = 1;
  String[] opcoes = { "a. Cadastrar produtos", "b. Alterar produtos", "c. Excluir produtos", "d. Listar produtos",
      "e. Cadastrar clientes", "f. Alterar clientes", "g. Excluir clientes", "h. Listar clientes", "i. Realizar venda",
      "j. Excluir venda", "k. Verificar estoque", "l. Extrato" };
  static AuxInput input = new AuxInput();
  Map<String, Produto> listaProdutos = new HashMap<>();
  Map<String, Cliente> listaClientes = new HashMap<>();
  Map<String, Produto> produtosDisponiveis = new HashMap<>();
  Map<Integer, ArrayList<Venda>> listaVendas = new HashMap<>();

  void funcionar() {
    String mensagem = "Selecione a op��o que deseja executar";
    int escolha = 0;
    while (escolha >= 0) {
      escolha = input.optionToInt(mensagem, opcoes);
      switch (escolha) {
      case CADASTRARPRODUTO:
        cadastrarProdutos();
        break;
      case ALTERARPRODUTO:
        alterarProduto();
        break;
      case EXCLUIRPRODUTO:
        excluirProduto();
        break;
      case LISTARPRODUTO:
        listarProdutos();
        break;
      case CADASTRARCLIENTE:
        cadastrarClientes();
        break;
      case ALTERARCLIENTE:
        alterarCliente();
        break;
      case EXCLUIRCLIENTE:
        excluircliente();
        break;
      case LISTARCLIENTE:
        listarClientes();
        break;
      case CADASTRARVENDA:
        cadastrarVendas();
        break;
      case EXCLUIRVENDA:
        excluirVenda();
        break;
      case VERIFICARESTOQUE:
        verificarEstoque();
        break;
      case EXTRATO:
        listarCompras();
      }
    }
  }

  void cadastrarProdutos() {
    Produto produto = new Produto();
    if (produto.cadastroCompleto()) {
      listaProdutos.put(produto.nome, produto);
      if (produto.qtdEmEstoque > 0) {
        produtosDisponiveis.put(produto.nome, produto);
      }
      String mensagem = "Cadastro realizado";
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }

  void alterarProduto() {
    String mensagem = "", entrada = "";
    if (listaProdutos.size() == 0) {
      mensagem = "N�o h� produtos cadastrados";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o produto que deseja alterar.";
      entrada = input.optionToString(mensagem, listaProdutos.keySet().toArray());
      if (!entrada.equals("")) {
        Produto produto = listaProdutos.get(entrada);
        if (produto.cadastroCompleto()) {
          listaProdutos.remove(entrada);
          produtosDisponiveis.remove(entrada);
          listaProdutos.put(produto.nome, produto);
          if (produto.qtdEmEstoque > 0) {
            produtosDisponiveis.put(produto.nome, produto);
          }
          if(listaVendas.size()>0) {
            for(ArrayList<Venda> Vendas : listaVendas.values()) {
              for(Venda venda : Vendas) {
                if(venda.nomeProduto().equals(entrada)) {
                  venda.produto = produto;
                }
              }
            }
          }
          mensagem = "Produto alterado.";
          JOptionPane.showMessageDialog(null, mensagem);
        }
      }
    }
  }

  void excluirProduto() {
    String mensagem = "", entrada = "";
    if (listaProdutos.size() == 0) {
      mensagem = "N�o h� produtos cadastrados";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o produto que deseja excluir.";
      entrada = input.optionToString(mensagem, listaProdutos.keySet().toArray());
      if (entrada != null) {
        listaProdutos.remove(entrada);
        produtosDisponiveis.remove(entrada);
        mensagem = "Produto removido.";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void listarProdutos() {
    String mensagem = "";

    if (listaProdutos.size() == 0) {
      mensagem = "N�o h� produtos cadastrados";
    } else {
      String linhaProduto = "Nome: %1$s - Fabricante: %2$s - Quantidade em Estoque: %3$s - Pre�o: R$%4$.2f. \n";
      mensagem = "Lista de produtos cadastrados: \n" + linhaTexto;
      for (Produto produto : listaProdutos.values()) {
        mensagem += String.format(linhaProduto, produto.nome, produto.fabricante, produto.qtdEmEstoque, produto.preco);
      }
    }

    JOptionPane.showMessageDialog(null, mensagem);
  }

  void cadastrarClientes() {
    Cliente cliente = new Cliente();
    if (cliente.cadastroCompleto()) {
      listaClientes.put(cliente.nomeCompleto, cliente);
      String mensagem = "Cadastro realizado.";
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }

  void alterarCliente() {
    String mensagem = "";
    if (listaClientes.size() == 0) {
      mensagem = "N�o h� clientes cadastrados.";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o cliente que deseja alterar.";
      String entrada = input.optionToString(mensagem, listaClientes.keySet().toArray());
      if (!entrada.equals("")) {
        Cliente cliente = listaClientes.get(entrada);
        if (cliente.cadastroCompleto()) {
          listaClientes.remove(entrada);
          listaClientes.put(cliente.nomeCompleto, cliente);
          if(listaVendas.size()>0) {
            for(ArrayList<Venda> Vendas : listaVendas.values()) {
              for(Venda venda : Vendas) {
                if(venda.nomeCliente().equals(entrada)) {
                  venda.cliente = cliente;
                }
              }
            }
          }
          mensagem = "Cadastro alterado.";
          JOptionPane.showMessageDialog(null, mensagem);
        }
      }
    }
  }

  void excluircliente() {
    String mensagem = "";
    if (listaClientes.size() == 0) {
      mensagem = "N�o h� clientes cadastrados.";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione o cliente que deseja alterar.";
      String entrada = input.optionToString(mensagem, listaClientes.keySet().toArray());
      if (!entrada.equals("")) {
        listaClientes.remove(entrada);
        mensagem = "Cadastro removido.";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void listarClientes() {
    String mensagem = "";
    if (listaClientes.size() == 0) {
      mensagem = "N�o h� clientes cadastrados.";
    } else {
      String linhaCliente = "Nome completo: %1$s - Email: %2$s - Telefone: %3$s - Endere�o: %4$s. \n";
      mensagem = "Lista de clientes cadastrados: \n" + linhaTexto;
      for (Cliente cliente : listaClientes.values()) {
        mensagem += String.format(linhaCliente, cliente.nomeCompleto, cliente.email, cliente.telefone,
            cliente.endereco);
      }
    }
    JOptionPane.showMessageDialog(null, mensagem);
  }

  void cadastrarVendas() {
    String mensagem = "", entrada = "";
    int entradaNum = 0, codigoVenda = 0;
    Venda venda = new Venda();
    ArrayList<Venda> vendas = new ArrayList<>();
    boolean sair = false;

    if (produtosDisponiveis.size() == 0) {
      mensagem = "N�o h� produtos dispon�veis para venda.";
      JOptionPane.showMessageDialog(null, mensagem);
      return;
    } else if (listaClientes.size() == 0) {
      mensagem = "N�o h� clientes cadastrados para realizar a venda.";
      JOptionPane.showMessageDialog(null, mensagem);
      return;
    }
    while (!sair) {
      mensagem = "Selecione o ID das vendas a serem cadastradas.";
      entradaNum = input.intToInt(mensagem, false, 1, -1, Integer.toString(codigoDisponivel));
      if (entradaNum < 0) {
        return;
      }
      if (listaVendas.containsKey(entradaNum)) {
        mensagem = "Essa ID j� est� associada a uma venda";
        JOptionPane.showMessageDialog(null, mensagem);
      } else {
        codigoVenda = entradaNum;
        sair = true;
      }
    }
    sair = false;
    while (!sair) {
      if (codigoVenda >= codigoDisponivel) {
        codigoDisponivel = codigoVenda + 1;
      }

      if (!venda.cadastrarProduto()) {
        break;
      }

      if (!venda.cadastrarQuantidade()) {
        break;
      }
      venda.produto.qtdEmEstoque -= venda.quantidade;
      vendas.add(venda);
      listaProdutos.put(venda.produto.nome, venda.produto);
      if (venda.produto.qtdEmEstoque == 0) {
        produtosDisponiveis.remove(venda.produto.nome);
      } else {
        produtosDisponiveis.put(venda.produto.nome, venda.produto);
      }
      venda = new Venda();
      mensagem = "Deseja cadastrar outro produto?";
      sair = !input.simNao(mensagem);
    }

    if (vendas.size() > 0) {
      mensagem = "Selecione o cliente ao qual deseja atribuir essas vendas.";
      entrada = input.optionToString(mensagem, listaClientes.keySet().toArray());
      if (!entrada.equals("")) {
        Cliente cliente = listaClientes.get(entrada);
        for (Venda item : vendas) {
          item.cliente = cliente;
        }
        listaVendas.put(codigoVenda, vendas);
        mensagem = "Venda cadastrada.";
        JOptionPane.showMessageDialog(null, mensagem);
      }
    }
  }

  void excluirVenda() {
    String mensagem = "", entrada = "";
    Integer codigo;
    if (listaVendas.size() == 0) {
      mensagem = "N�o h� vendas cadastradas.";
      JOptionPane.showMessageDialog(null, mensagem);
    } else {
      mensagem = "Selecione a venda a ser cancelada.";
      entrada = input.optionToString(mensagem, listaVendas.keySet().toArray());
      if (entrada.equals("")) {
        return;
      }
      codigo = Integer.valueOf(entrada);
      ArrayList<Venda> vendas = listaVendas.get(codigo);
      Produto produto = new Produto();
      for (Venda venda : vendas) {
        produto = listaProdutos.get(venda.produto.nome);
        produto.qtdEmEstoque += venda.quantidade;
        listaProdutos.put(produto.nome, produto);
        produtosDisponiveis.put(produto.nome, produto);
      }
      listaVendas.remove(codigo);
      mensagem = "Venda exclu�da.";
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }

  void verificarEstoque() {
    String mensagem = "";
    String linhProduto = "Nome: %1$s - Em estoque: %2$s \n";
    if (listaProdutos.size() == 0) {
      mensagem = "N�o h� produtos cadastrados.";
    } else {
      mensagem = "Abaixo est� a lista de produtos cadastrados: \n" + linhaTexto;
      for (Produto produto : listaProdutos.values()) {
        mensagem += String.format(linhProduto, produto.nome, produto.qtdEmEstoque);
      }
    }
    JOptionPane.showMessageDialog(null, mensagem);
  }

  void listarCompras() {
    String mensagem = "";
    if (listaVendas.size() == 0) {
      mensagem = "N�o h� vendas cadastradas";
    } else {
      String linhaVenda = "C�digo: %1$s - Item: %2$s - Cliente: %3$s - Quantidade: %4$s"
          + " - Pre�o unit�rio: R$%5$.2f - Valor total: R$%6$.2f \n";
      mensagem = "Abaixo est� uma lista das vendas realizadas: \n" + linhaTexto;
      for (Entry<Integer, ArrayList<Venda>> Vendas : listaVendas.entrySet()) {
        for (Venda venda : Vendas.getValue()) {
          mensagem += String.format(linhaVenda, Vendas.getKey(), venda.nomeProduto(), venda.nomeCliente(),
              venda.quantidade, venda.precoProduto(), venda.valor);
        }
      }
    }
    JOptionPane.showMessageDialog(null, mensagem);
  }

  class Venda {

    Produto produto = new Produto();
    Cliente cliente = new Cliente();
    double valor = 0;
    int quantidade = 0;

    String nomeProduto() {
      return produto.nome;
    }

    String nomeCliente() {
      return cliente.nomeCompleto;
    }

    double precoProduto() {
      return produto.preco;
    }

    boolean cadastrarProduto() {
      String mensagem = "", entrada = "";
      mensagem = "Selecione o produto que deseja vender.";
      entrada = input.optionToString(mensagem, produtosDisponiveis.keySet().toArray());
      if (entrada.equals("")) {
        return false;
      }
      produto = produtosDisponiveis.get(entrada);
      return true;
    }

    boolean cadastrarQuantidade() {
      String mensagem = "";
      int entrada = 0;
      mensagem = "Informe a quantidade a ser vendida. \nO total em estoque desse item �: " + produto.qtdEmEstoque;
      entrada = input.intToIntMax(mensagem, false, 1, -1, Integer.toString(produto.qtdEmEstoque), produto.qtdEmEstoque);
      if (entrada < 0) {
        return false;
      }
      quantidade = entrada;
      valor = produto.preco * quantidade;
      return true;
    }
  }

  class Produto {
    String nome = "", fabricante = "";
    int qtdEmEstoque = 0;
    double preco;

    boolean cadastroCompleto() {
      if (!cadastrarNome()) {
        return false;
      }
      if (!cadastrarFabricante()) {
        return false;
      }
      if (!cadastrarSaldo()) {
        return false;
      }
      if (!cadastrarPreco()) {
        return false;
      }
      return true;
    }

    boolean cadastrarNome() {
      String mensagem = "", entrada = "";
      boolean sair = false;
      while (!sair) {
        mensagem = "Digite o nome do produto.";
        entrada = input.strInput(mensagem, nome);
        if (entrada == null) {
          return false;
        }
        if (listaProdutos.containsKey(entrada)&&!entrada.equals(nome)) {
          mensagem = "Esse produto j� existe.";
          JOptionPane.showMessageDialog(null, mensagem);
        } else {
          nome = entrada;
          sair = true;
        }
      }

      return true;
    }

    boolean cadastrarFabricante() {
      String mensagem = "", entrada = "";
      mensagem = "Digite o nome do fabricante.";
      entrada = input.strInput(mensagem, fabricante);
      if (entrada == null) {
        return false;
      }
      fabricante = entrada;
      return true;
    }

    boolean cadastrarSaldo() {
      String mensagem = "";
      mensagem = "Digite a quantidade desse produto em estoque.";

      qtdEmEstoque = input.intToInt(mensagem, true, 1, -1, Integer.toString(qtdEmEstoque));
      if (qtdEmEstoque < 0) {
        return false;
      }
      return true;
    }

    boolean cadastrarPreco() {
      String mensagem = "Cadastre o valor unit�rio do produto.";
      preco = input.dblToDbl(mensagem, true, 1, (double) -1, Double.toString(preco));
      if (preco < 0) {
        return false;
      }
      return true;
    }

  }

  class Cliente {
    String nome = "", sobrenome = "", nomeCompleto = "", email = "", endereco = "";
    int telefone = 0;

    boolean cadastroCompleto() {
      if (!cadastrarNome()) {
        return false;
      }

      if (!cadastrarEmail()) {
        return false;
      }

      if (!cadastrarTelefone()) {
        return false;
      }

      if (!cadastrarEndereco()) {
        return false;
      }

      return true;
    }

    boolean cadastrarNome() {
      String mensagem = "", entradaNome = "", entradaSobrenome = "";
      boolean sair = false;
      while (!sair) {
        mensagem = "Digite o nome do cliente";
        entradaNome = input.strInput(mensagem, nome);
        if (entradaNome == null) {
          return false;
        }
        mensagem = "Digite o sobrenome do cliente";
        entradaSobrenome = input.strInput(mensagem, sobrenome);
        if (entradaSobrenome == null) {
          return false;
        }
        if (listaClientes.containsKey(nome + " " + sobrenome)&&!nomeCompleto.equals(nome + " " + sobrenome)) {
          mensagem = "Esse nome j� est� cadastrado!";
          JOptionPane.showMessageDialog(null, mensagem);
        } else {
          nome = entradaNome;
          sobrenome = entradaSobrenome;
          nomeCompleto = nome + " " + sobrenome;
          sair = true;
        }
      }
      return true;
    }

    boolean cadastrarEmail() {
      String mensagem = "Digite o email", entrada = "";
      entrada = input.strInput(mensagem, email);
      if (entrada == null) {
        return false;
      }
      email = entrada;
      return true;
    }

    boolean cadastrarTelefone() {
      String mensagem = "Digite o n�mero de telefone";
      String valorPadrao = telefone > 0 ? Integer.toString(telefone) : "";
      int entrada = 0;
      entrada = input.intToInt(mensagem, false, 1, -1, valorPadrao);
      if (entrada == -1) {
        return false;
      }
      telefone = entrada;
      return true;
    }

    boolean cadastrarEndereco() {
      String mensagem = "Digite o endereco", entrada = "";
      entrada = input.strInput(mensagem, endereco);
      if (entrada == null) {
        return false;
      }
      endereco = entrada;
      return true;
    }
  }
}
