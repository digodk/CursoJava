package lista04;
/*
 * Desenvolver um algoritmo para supermercado
 * nela dever� conter uma lista de produtos j� cadastrados.
 * O algoritmo com esses produtos pr�-definidos dever� perguntar o c�digo do produto a ser comprado 
 * e a quantidade, enquanto o c�digo for diferente de zero dever� perguntar o c�digo 
 * e a quantidade.
 * Ap�s digitado zero deve ser exibido: c�digo, nome do produto, quantidade comprada, 
 * valor unit�rio e total de cada produto (quantidade * produto) de cada pedido adicionado.
 * Juntamente com o total a ser pago pelo cliente.
 * Informe tamb�m qual � o total da compra (soma de todos os totais).
 */
public class Ex07 {

  public static void main(String[] args) {
    Supermercado supermercado = new Supermercado();
    supermercado.funcionar();

  }

}
