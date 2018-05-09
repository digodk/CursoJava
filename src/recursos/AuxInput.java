package recursos;

import java.awt.Font;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class AuxInput {

  JOptionPane painel = new JOptionPane();
  Font font = new Font(Font.MONOSPACED, Font.PLAIN, 10);
  
  public int optionToInt(String mensagem, Object[] opcoes) {
    String entrada = "";
    try {
      entrada = JOptionPane
          .showInputDialog(null, mensagem, "", JOptionPane.PLAIN_MESSAGE, null, opcoes, 1)
          .toString();
      return Arrays.asList(opcoes).indexOf(entrada);
    } catch (Exception exc) {
      return -1;
    }
  }

  public String optionToString(String mensagem, Object[] opcoes) {
    String entrada = "";
    try {
      entrada = JOptionPane
          .showInputDialog(null, mensagem, "", JOptionPane.PLAIN_MESSAGE, null, opcoes, 1)
          .toString();
      return entrada;
    } catch (Exception exc) {
      return "";
    }
  }

  public String strInput(String mensagem, String valorPadrao) {
    String entrada = "";
    entrada = JOptionPane.showInputDialog(null, mensagem, valorPadrao);
    if (entrada == null || (entrada != null && entrada.equals(""))) {
      return null;
    } else {
      return entrada;
    }
  }

  public String strInput(String mensagem) {
    return strInput(mensagem, "");
  }

  public String dblToStr(String mensagem, boolean zero, int positivo, String valorPadrao) {
    String entrada = "", msgErro = "";
    double teste = 0;
    boolean sair = false, erro = false;

    if (Math.abs(positivo) > 1) {
      positivo = 0;
    }

    if (!zero) {
      if (positivo == 1) {
        msgErro = "Digite um número positivo!";
      } else if (positivo == -1) {
        msgErro = "Digite um número negativo!";
      } else {
        msgErro = "Digite um número diferente de zero!";
      }
    } else {
      if (positivo == 1) {
        msgErro = "Digite um número maior ou igual a zero!";
      } else if (positivo == -1) {
        msgErro = "Digite um número menor ou igual a zero!";
      }
    }

    while (!sair) {
      try {
        entrada = JOptionPane.showInputDialog(null, mensagem, valorPadrao);
        if (entrada == null || (entrada != null && entrada.equals(""))) {
          entrada = null;
          break;
        } else {
          teste = Double.parseDouble(entrada);
          erro = (teste == 0 && !zero)
              || ((teste > 0 && positivo == -1) || (teste < 0 && positivo == 1));
          if (erro) {
            JOptionPane.showMessageDialog(null, msgErro);
          } else {
            sair = true;
          }
        }
      } catch (Exception exc) {
        JOptionPane.showMessageDialog(null, msgErro);
      }
    }
    return entrada;
  }

  public String dblToStr(String mensagem, boolean zero, int positivo) {
    return dblToStr(mensagem, zero, positivo, "");
  }

  public String intToStr(String mensagem, boolean zero, int positivo, String valorPadrao) {
    String entrada = "", msgErro = "";
    int retorno = 0;
    boolean sair = false, erro = false;

    if (Math.abs(positivo) > 1) {
      positivo = 0;
    }

    if (!zero) {
      if (positivo == 1) {
        msgErro = "Digite um inteiro positivo!";
      } else if (positivo == -1) {
        msgErro = "Digite um inteiro negativo!";
      } else {
        msgErro = "Digite um inteiro diferente de zero!";
      }
    } else {
      if (positivo == 1) {
        msgErro = "Digite um inteiro maior ou igual a zero!";
      } else if (positivo == -1) {
        msgErro = "Digite um inteiro menor ou igual a zero!";
      }
    }

    while (!sair) {
      try {
        entrada = JOptionPane.showInputDialog(null, mensagem, valorPadrao);
        if (entrada == null || (entrada != null && entrada.equals(""))) {
          entrada = null;
          break;
        } else {
          retorno = Integer.parseInt(entrada);
          erro = (retorno == 0 && !zero)
              || ((retorno > 0 && positivo == -1) || (retorno < 0 && positivo == 1));
          if (erro) {
            JOptionPane.showMessageDialog(null, msgErro);
          } else {
            sair = true;
          }
        }
      } catch (Exception exc) {
        JOptionPane.showMessageDialog(null, msgErro);
      }
    }
    return entrada;
  }

  public int intToInt(String mensagem, boolean zero, int positivo, int cancelar, String valorPadrao) {
    String entrada = intToStr(mensagem, zero, positivo, valorPadrao);
    if (entrada == null || entrada.equals("")) {
      return cancelar;
    } else {
      return Integer.parseInt(entrada);
    }
  }

  public int intToInt(String mensagem, boolean zero, int positivo, int cancelar) {
    return intToInt(mensagem, zero, positivo, cancelar, "");
  }

  public int intToInt(String mensagem, boolean zero, int cancelar) {
    return intToInt(mensagem, zero, 1, cancelar);
  }

  public int intToInt(String mensagem, int cancelar) {
    return intToInt(mensagem, true, 1, cancelar);
  }

  public int intToIntMax(String mensagem, boolean zero, int positivo, int cancelar,
      String valorPadrao, int maximo) {
    int entrada = 0;
    while (true) {
      entrada = intToInt(mensagem, zero, positivo, cancelar, valorPadrao);
      if (entrada == cancelar) {
        return cancelar;
      }
      if(entrada>maximo) {
        String msg = "Digite um valor menor que " + maximo;
        JOptionPane.showMessageDialog(null, msg);
      } else {
        return entrada;
      }
    }

  }

  public String dblToStr(String mensagem) {
    return dblToStr(mensagem, true, 0);
  }

  public Double dblToDbl(String mensagem, boolean zero, int positivo, Double cancelar, String valorPadrao) {
    String entrada = dblToStr(mensagem, zero, positivo, valorPadrao);
    if (entrada == null || entrada.equals("")) {
      return cancelar;
    } else {
      return Double.parseDouble(entrada);
    }
  }

  public Double dblToDbl(String mensagem, boolean zero, int positivo, Double cancelar) {
    return dblToDbl(mensagem, zero, positivo, cancelar, "");
  }

  public Double dblToDbl(String mensagem, boolean zero, Double cancelar) {
    return dblToDbl(mensagem, zero, 0, cancelar, "");
  }

  public Double dblToDbl(String mensagem, Double cancelar) {
    return dblToDbl(mensagem, true, 0, cancelar, "");
  }

  public boolean simNao(String mensagem) {
    int escolha = JOptionPane.showConfirmDialog (null, mensagem,"",JOptionPane.YES_NO_OPTION);
    return escolha == JOptionPane.YES_OPTION;
  }
}
