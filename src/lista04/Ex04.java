package lista04;

import java.util.concurrent.ThreadLocalRandom;

/*
 * Implementar uma matriz 5X15 onde deverá ser exibida da seguinte maneira:
 * ***************
 * *             *
 * *      5      *
 * *             *
 * ***************
 * A primeira linha e última linha deverão aparecer apenas asteriscos.
 * A primeira coluna e a última coluna deverão aparecer apenas asteriscos.
 * A terceira linha 8 coluna deverá ser exibido um número entre 1 e 9 que deve ser gerado
 * automaticamente.
 * As demais posições precisam estar vazias.
 */
public class Ex04 {

  public static void main(String[] args) {
    String mensagem="";
    String linhaAsterisco = new String(new char[15]).replace("\0", "*");
    String linhaVazia = "*"+ new String(new char[13]).replace("\0", " ") + "*" ; 
    int numAleat = ThreadLocalRandom.current().nextInt(1, 10);
    String linhaNumero = "*" +  new String(new char[6]).replace("\0", " ") + numAleat + new String(new char[6]).replace("\0", " ") + "*";
    char[][] matriz = new char[5][15];
    
    matriz[0] = linhaAsterisco.toCharArray();
    mensagem += linhaAsterisco + "\n";
    matriz[1] = linhaVazia.toCharArray();
    mensagem += linhaVazia + "\n";
    matriz[2] = linhaNumero.toCharArray();
    mensagem += linhaNumero + "\n";
    matriz[3] = linhaVazia.toCharArray();
    mensagem += linhaVazia + "\n";
    matriz[4] = linhaAsterisco.toCharArray();
    mensagem += linhaAsterisco + "\n";
    
    System.out.println(mensagem);
  }

}
