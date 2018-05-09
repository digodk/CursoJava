package lista06;

import java.util.Random;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Ex02 {

  public static void main(String[] args) {
    AuxInput input = new AuxInput();
    Random gerador = new Random(101);
    String mensagem = "";
    int entrada, oEscolhido, diff, numTentativas = 0;
    boolean acertou = false;
    oEscolhido = gerador.nextInt();
    mensagem = "Informe um número e vamos ver se você acerta. \n"
        + "Para facilitar a sua vida, só serão aceitos chutes maiores ou iguais a 0";
    while (!acertou) {
      entrada = input.intToInt(mensagem, true, 1, -1);
      if (entrada < 0) {
        return;
      }
      diff = Math.abs(entrada - oEscolhido);
      if (diff == 0) {
        acertou = true;
      } else if (diff <= 10) {
        mensagem = "Você está perto!";
        JOptionPane.showMessageDialog(null, mensagem);
        mensagem = "Essa foi quase! Vamos de novo =)";
      } else if (diff < 20) {
        mensagem = "Essa foi longe, sabe...";
        JOptionPane.showMessageDialog(null, mensagem);
        mensagem = "Vamos de novo, dá um número aí:";
      } else {
        mensagem = "Vish cara, essa foi muito longe bicho.";
        JOptionPane.showMessageDialog(null, mensagem);
        mensagem = "Tenta um chute melhor dessa vez:";
      }
      numTentativas++;

    }
    mensagem = "KOROI VC ACERTOU LEK!!!11";
    JOptionPane.showMessageDialog(null, mensagem);

    if (numTentativas == 1) {
      mensagem = "E de primeira! Que largo!";
    } else if (numTentativas <= 5) {
      mensagem = "Cê é bom hein?";
    } else if (numTentativas <= 10) {
      mensagem = "É... até que adivinhou rápido.";
    } else if (numTentativas <= 20) {
      mensagem = "Podia ter ido melhor hein?";
    } else {
      mensagem = "É véi, cê é meio pra trás né bicho?";
    }
    
    JOptionPane.showMessageDialog(null, mensagem);
  }

}
