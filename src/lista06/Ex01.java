package lista06;

import javax.swing.JOptionPane;

import recursos.AuxInput;

/*
 * Utilizando os conceitos vistos desenvolva uma calculadora que irá pedir:
 * a. Uma operação;
 * b. Um número (ou dois dependendo da operação escolhida).
 * As operações a disposição do usuário serão:
 *  Soma;
 *  Subtração;
 *  Multiplicação;
 *  Divisão;
 *  Seno;
 *  Cosseno;
 *  Tangente;
 *  Log;
 *  Raiz Quadrada;
 *  Raiz Cúbica.
 */
public class Ex01 {

  public static void main(String[] args) {
    Calculadora calculadora = new Calculadora();

    while (!calculadora.terminar) {
      calculadora.operar();
    }

  }

}

class Calculadora {
  static AuxInput input = new AuxInput();
  static final String[] opcoes = { "Soma", "Subtração", "Multiplicação", "Divisão", "Seno",
      "Cosseno", "Tangente", "Log", "RaIz Quadrada", "Raiz Cúbica" };
  static final int SOMA = 0, SUBTRACAO = 1, MULTIPLICACAO = 2, DIVISAO = 3, SENO = 4, COSSENO = 5,
      TANGENTE = 6, LOG = 7, RAIZQUAD = 8, RAIZCUB = 9;
  boolean terminar = false;

  void operar() {
    int escolha;
    Double num1, resultado;
    String mensagem = "Digite a operação desejada";
    escolha = input.optionToInt(mensagem, opcoes);
    if (escolha < 0) {
      terminar = true;
      return;
    }

    mensagem = "Digite o primeiro número da operação";
    boolean zeroPermitido = escolha != LOG;
    num1 = input.dblToDbl(mensagem, zeroPermitido, null);
    if (num1 == null) {
      terminar = true;
      return;
    }
    mensagem = "O resultado da operação de ";
    switch (escolha) {
    case SOMA:
      resultado = somar(num1);
      if (resultado == null) {
        terminar = true;
        return;
      }
      mensagem += "soma é de " + resultado;
      break;
    case SUBTRACAO:
      resultado = subtrair(num1);
      if (resultado == null) {
        terminar = true;
        return;
      }
      mensagem += "subtração é de " + resultado;
      break;
    case MULTIPLICACAO:
      resultado = multiplicar(num1);
      if (resultado == null) {
        terminar = true;
        return;
      }
      mensagem += "multiplicação é de " + resultado;
      break;
    case DIVISAO:
      resultado = dividir(num1);
      if (resultado == null) {
        terminar = true;
        return;
      }
      mensagem += "divisão é de " + resultado;
      break;
    case SENO:
      resultado = Math.sin(num1);
      mensagem += "seno é de " + resultado;
      break;
    case COSSENO:
      resultado = Math.cos(num1);
      mensagem += "cosseno é de " + resultado;
      break;
    case TANGENTE:
      resultado = Math.tan(num1);
      mensagem += "tangente é de " + resultado;
      break;
    case LOG:
      resultado = Math.log(num1);
      mensagem += "logaritmo na base e é de " + resultado;
      break;
    case RAIZQUAD:
      resultado = Math.sqrt(num1);
      mensagem += "raiz quadrada é de " + resultado;
      break;
    case RAIZCUB:
      resultado = Math.cbrt(num1);
      mensagem += "raiz cúbica é de " + resultado;
      break;
    }
    JOptionPane.showMessageDialog(null, mensagem);
    return;
  }

  Double somar(Double num1) {
    String mensagem = "Digite o segundo número";
    Double num2 = input.dblToDbl(mensagem, null);
    if (num2 == null) {
      return null;
    }
    return num1 + num2;
  }

  Double subtrair(Double num1) {
    String mensagem = "Digite o segundo número";
    Double num2 = input.dblToDbl(mensagem, null);
    if (num2 == null) {
      return null;
    }
    return num1 - num2;
  }

  Double multiplicar(Double num1) {
    String mensagem = "Digite o segundo número";
    Double num2 = input.dblToDbl(mensagem, null);
    if (num2 == null) {
      return null;
    }
    return num1 * num2;
  }

  Double dividir(Double num1) {
    String mensagem = "Digite o segundo número";
    Double num2 = input.dblToDbl(mensagem, false, null);
    if (num2 == null) {
      return null;
    }
    return num1 / num2;
  }
}