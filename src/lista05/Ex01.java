package lista05;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * O usuário irá informar uma palavra ou uma frase.
 * Após informada deverá aparecer o que foi digitado alternando entre maiúsculas e minúsculas.
 */
public class Ex01 {

  public static void main(String[] args) {
    AuxInput input = new AuxInput();
    String entrada, mensagem = "Digite uma palavra  ";
    String saida = "";

    entrada = input.strInput(mensagem);
    if (entrada == null) {
      return;
    }
    boolean maiuscula = false;
    for (char letra : entrada.toCharArray()) {
      saida += maiuscula? Character.toUpperCase(letra): Character.toLowerCase(letra);
      maiuscula = maiuscula? false:true;
    }
    JOptionPane.showMessageDialog(null, saida);
  }

}
