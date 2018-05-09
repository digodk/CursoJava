package lista04;
/*
 * Classe auxiliar para resolver Ex07
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Supermercado {
  int numCompras = 0;
  String[] listaNomes = {"1","2"};
  double[] listaPrecos = {1,2};
  String[] listaProdutos;
  AuxInput input = new AuxInput();
  Map<Integer, Integer> listaCompras = new HashMap<>();
  
  public Supermercado() {
    this.listarProdutos();
  }
  
  void listarProdutos() {
    ArrayList<String> lista = new ArrayList<>();
    String mensagem="%1$s - %2$s - R$%3$.2f";
    for (int ix=0;ix<listaNomes.length;ix++) {
      lista.add(String.format(mensagem, ix+1,listaNomes[ix],listaPrecos[ix]));
      listaCompras.put(ix, 0);
    }
    lista.add("Sair");
    listaProdutos=lista.toArray(new String[0]);
  }
  
  void funcionar() {
    String mensagem = "";
    boolean continua=true;
    
    while(continua) {
      continua = this.cadastrarCompra();
    }
    
    if(this.numCompras>0) {
      mensagem = this.relatarCompras();
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }
  
  boolean cadastrarCompra() {
    String mensagem="";
    int escolha, quantidade;
    mensagem = "Selecione o produto desejado ou clique em sair";
    escolha = input.optionToInt(mensagem, listaProdutos);
    if (escolha==-1||escolha==listaNomes.length) {
      return false;
    }
    
    mensagem = "Digite a quandidade desejada do item";
    quantidade = input.intToInt(mensagem, false, 1, 0);
    if (quantidade==0) {
      return false;
    }
    listaCompras.put(escolha, listaCompras.get(escolha)+quantidade);
    numCompras++;
    return true;
  }
  
  String relatarCompras() {
    String relatorio="", produto="";
    String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
    String linhaRelatorio = "-Item: %1$s, total comprado: %2$s, valor unit�rio: R$%3$.2f, valor total: R$%4$.2f\n";
    double preco=0, valorCompra=0, valorTotal=0;
    int totalCompra=0;
    
    relatorio = "Relat�rio de compras:\n"+linha;
    for(int codigo=0; codigo<listaNomes.length;codigo++) {
      produto = listaNomes[codigo];
      preco = listaPrecos[codigo];
      totalCompra = listaCompras.get(codigo);
      valorCompra = totalCompra*preco;
      valorTotal += valorCompra;
      relatorio += String.format(linhaRelatorio, produto, totalCompra,preco, valorCompra);
    }
    
    relatorio +=linha+ "Total das compras: R$" + valorTotal;
    
    return relatorio;
  }

}
