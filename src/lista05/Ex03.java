package lista05;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * O usuário irá informar um verbo
 * (caso ele não informe um verbo o programa deverá informar que o termo informado não é um verbo).
 * Esse verbo deverá terminar em AR, quando digitado o verbo você deverá conjugá-lo.
 */
public class Ex03 {

  public static void main(String[] args) {
    AuxInput input = new AuxInput();
    String entrada = "", radical;
    String[] tempos = { "Presente", "Pretérito Imperfeito", "Pretérito Perfeito",
        "Pretérito Mais-que-perfeito", "Futuro do presente", "Futuro do pretérito" };
    String[] pessoas = { "Eu", "Tu", "Ele/Ela", "Nós", "Vós", "Eles" };
    String[][] sufixos = { { "o", "as", "a", "amos", "ais", "am" },
        { "ava", "avas", "ava", "ávamos", "áveis", "avam" },
        { "ei", "aste", "ou", "amos", "astes", "aram" },
        { "ara", "aras", "ara", "áramos", "áreis", "aram" },
        { "arei", "arás", "ará", "aremos", "areis", "arão" },
        { "aria", "arias", "aria", "aríamos", "aríeis", "ariam" } };
    boolean ehVerbo = false;
    String mensagem = "Digite um verbo";
    while (!ehVerbo) {
      entrada = input.strInput("Digite um verbo");
      if (entrada == null) {
        return;
      }
      if (entrada.length() < 3
          || !entrada.substring(entrada.length() - 2).toLowerCase().equals("ar")) {
        mensagem = "Hmm, isso não parece um verbo";
        JOptionPane.showMessageDialog(null, mensagem);
        mensagem = "Vamos tentar de novo. Insira um verbo";
      } else {
        ehVerbo = true;
      }
    }
    radical = entrada.substring(0, entrada.length() - 2);
    mensagem = "Conjugações do indicativo para o verbo " + entrada + ":\n\n";
    for (int tempo = 0; tempo < tempos.length; tempo++) {
      mensagem += " - " + tempos[tempo] + ": \n";
      for (int pessoa = 0; pessoa < pessoas.length; pessoa++) {
        mensagem += pessoas[pessoa] + " " + radical + sufixos[tempo][pessoa] + ";\n";
      }
      mensagem += "\n\n";
    }
    JOptionPane.showMessageDialog(null, mensagem);
  }

}
