package lista07;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Ex01 {

  public static void main(String[] args) {
    final int DEPOSITAR = 0, SACAR = 1, EXTRATO = 2, SALDO = 3;

    AuxInput input = new AuxInput();
    String mensagem = "";
    int escolha;
    double saldo = 0, entrada;

    ArrayList<String> operacoes = new ArrayList<>();
    ArrayList<Double> valorOperacoes = new ArrayList<>();
    String[] opcoes = { "Depositar", "Sacar", "Extrato", "Saldo" };

    while (true) {
      mensagem = "Escolha a operação que deseja fazer. Cancelar para sair.";
      escolha = input.optionToInt(mensagem, opcoes);
      if (escolha < 0) {
        return;
      }
      switch (escolha) {
      case DEPOSITAR:
        mensagem = "Digite o valor a ser depositado";
        entrada = input.dblToDbl(mensagem, false, 1, (double) -1);
        if (entrada < 0) {
          break;
        }
        saldo += entrada;
        operacoes.add("Depósito");
        valorOperacoes.add(entrada);
        JOptionPane.showMessageDialog(null, "Depósito realizado");
        break;
      case SACAR:
        if (saldo == 0) {
          JOptionPane.showMessageDialog(null, "Você não tem saldo para fazer saque");
          break;
        }
        mensagem = "Digite o valor a ser sacado, máximo de R$" + saldo;
        entrada = input.dblToDbl(mensagem, false, 1, (double) -1);
        if (entrada < 0) {
          break;
        } else if (entrada > saldo) {
          JOptionPane.showMessageDialog(null, "Você não tem saldo para esse saque");
          break;
        }
        saldo -= entrada;
        operacoes.add("Saque");
        valorOperacoes.add(-entrada);
        JOptionPane.showMessageDialog(null, "Saque realizado");
        break;
      case EXTRATO:
        if (operacoes.size() == 0) {
          JOptionPane.showMessageDialog(null, "Você não realizou nenhuma operação até agora");
          break;
        }
        mensagem = "Abaixo está um extrato das operações realizadas: \n\n";
        double saldoExtrato = 0;
        for (int ix = 0; ix < operacoes.size(); ix++) {
          saldoExtrato += valorOperacoes.get(ix);
          mensagem += String.format("-Operação: %1$s, valor: R$%2$.2f, saldo: R$%3$.2f\n",
              operacoes.get(ix), valorOperacoes.get(ix), saldoExtrato);
        }
        JOptionPane.showMessageDialog(null, mensagem);
        break;
      case SALDO:
        mensagem = "Seu saldo é de R$" + saldo;
      }
    }

  }

}
