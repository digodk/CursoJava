package lista05;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * O usuário poderá informar uma palavra ou uma frase.
 * Após informado deverá ser exibida a quantidade de vogais encontradas.
 */
public class Ex02 {

  public static void main(String[] args) {
    AuxInput input = new AuxInput();
    String entrada;
    String vogais = "aeiou";
    int numVogais = 0;
    
    entrada = input.strInput("Digite uma palavra ou frase.");
    if(entrada==null) {
      return;
    }
    for(char letra:entrada.toCharArray()) {
      if(vogais.indexOf(letra)>=0) {
        numVogais++;
      }
    }
    String mensagem = "";
    if(numVogais>0) {
      mensagem = "Há " + numVogais + " vogais.";
    } else {
      mensagem = "Não foram encontradas vogais";
    }
    
    JOptionPane.showMessageDialog(null, mensagem);
  }

}
