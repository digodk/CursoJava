package lista04;
/*
 * Desenvolver um algoritmo para supermercado
 * nela deverá conter uma lista de produtos já cadastrados.
 * O algoritmo com esses produtos pré-definidos deverá perguntar o código do produto a ser comprado 
 * e a quantidade, enquanto o código for diferente de zero deverá perguntar o código 
 * e a quantidade.
 * Após digitado zero deve ser exibido: código, nome do produto, quantidade comprada, 
 * valor unitário e total de cada produto (quantidade * produto) de cada pedido adicionado.
 * Juntamente com o total a ser pago pelo cliente.
 * Informe também qual é o total da compra (soma de todos os totais).
 */
public class Ex07 {

  public static void main(String[] args) {
    Supermercado supermercado = new Supermercado();
    supermercado.funcionar();

  }

}
