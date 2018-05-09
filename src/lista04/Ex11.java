package lista04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Ex11 {

  public static void main(String[] args) {
    Jogo jogo = new Jogo(3, true);
    while (!jogo.acabou) {
      jogo.jogar();
    }

  }

}

class Jogo {
  private static AuxInput input = new AuxInput();
  private static String linhaTexto = new String(new char[20]).replace("\0", "-") + "\n \n";
  int tamanhoJogo = 3;
  int numJogadas = 0;
  boolean acabou = false;
  boolean contraComputador = false;
  private int jogadorAtivo = 1;
  private int jogadorPC = 2;
  private Random geradorJogadas = new Random();
  private String[] jogadores = { "\u25A0", "X", "O" };
  private ArrayList<Integer> linhas = new ArrayList<>();
  private ArrayList<Integer> colunasValidas = new ArrayList<>();
  // Para cada linha há um intervalo diferente de colunas disponíveis. O dicionário faz a
  // indexação de cada lista de colunas disponível por linha.
  private Map<Integer, ArrayList<Integer>> colunas = new HashMap<>();
  String tabuleiro = " ";
  int[][] jogo;

  Jogo(int tamanhoJogo, boolean contraComputador) {
    this.tamanhoJogo = tamanhoJogo;
    this.contraComputador = contraComputador;
    iniciarJogo();
  }

  Jogo() {
    iniciarJogo();
  }

  void jogar() {
    String jogador = jogadores[jogadorAtivo];
    String mensagemJogada = "Jogador %1s, selecione a %2s da sua jogada.\n" + linhaTexto
        + tabuleiro;
    String entrada;
    Object[] opcoes;
    String mensagem = String.format(mensagemJogada, jogador, "linha");
    int linha, coluna;

    // Se a jogada for do PC então determina linha e coluna aleatoriamente
    if (contraComputador && jogadorAtivo == jogadorPC) {
      linha = linhas.get(geradorJogadas.nextInt(linhas.size()))-1;
      colunasValidas = colunas.get(linha+1);
      coluna = colunasValidas.get(geradorJogadas.nextInt(colunasValidas.size()))-1;
    } else {
      // Caso onde é a vez do usuário jogar
      // Registra a linha da jogada
      opcoes = linhas.toArray();
      entrada = input.optionToString(mensagem, opcoes);
      if (entrada.equals("")) {
        acabou = true;
        return;
      } else {
        linha = Integer.parseInt(entrada) - 1;
        colunasValidas = colunas.get(linha + 1);
        // Registra a coluna da jogada
        opcoes = colunasValidas.toArray();
        mensagem = String.format(mensagemJogada, jogador, "coluna");
        entrada = input.optionToString(mensagem, opcoes);
        if (entrada.equals("")) {
          acabou = true;
          return;
        }
        coluna = Integer.parseInt(entrada) - 1;
      }
    }
    jogo[linha][coluna] = jogadorAtivo;
    numJogadas++;
    desenharTabuleiro();
    if (jogadaVencedora(linha, coluna)) {
      fimJogo(false);
      return;
    } else if (jogoEmpatou()) {
      fimJogo(false);
      return;
    }
    validarLinhasColunas(linha, coluna);
    trocarJogador();
  }

  boolean jogoEmpatou() {
    return numJogadas == tamanhoJogo * tamanhoJogo;
  }

  boolean jogadaVencedora(int linha, int coluna) {
    int jogador = jogo[linha][coluna];
    boolean check = true, check2 = true;
    // Confere diagonal principal
    if (linha == coluna) {
      for (int ix = 0; ix < tamanhoJogo; ix++) {
        check &= jogo[ix][ix] == jogador;
      }
      if (check) {
        return true;
      } else {
        check = true;
      }
    }
    // Confere diagonal secundária
    if (linha + coluna == tamanhoJogo - 1) {
      for (int ix = 0; ix < tamanhoJogo; ix++) {
        check &= jogo[ix][tamanhoJogo - ix - 1] == jogador;
      }
      if (check) {
        return true;
      } else {
        check = true;
      }
    }
    // Confere linhas e colunas
    for (int ix = 0; ix < tamanhoJogo; ix++) {
      check &= jogo[linha][ix] == jogador;
      check2 &= jogo[ix][coluna] == jogador;
    }
    check |= check2;
    return check;
  }

  void validarLinhasColunas(int linha, int coluna) {
    List<Integer> colunaAtiva = colunas.get(linha+1);
    colunaAtiva.remove(colunaAtiva.indexOf(coluna+1));
    if (colunaAtiva.size()==0) {
      linhas.remove(linhas.indexOf(linha+1));
    }
  }

  void fimJogo(boolean empate) {
    String mensagem = "  ";
    if (!empate) {
      mensagem = "Fim de jogo! O jogador vencedor é: " + jogadores[jogadorAtivo] + "\n";
    } else {
      mensagem = "Ah, o jogo terminou empatado! Que pena =( \n";
    }
    mensagem += linhaTexto + tabuleiro;
    JOptionPane.showMessageDialog(null, mensagem);
    acabou = true;
  }

  void trocarJogador() {
    jogadorAtivo = jogadorAtivo == 1 ? 2 : 1;
  }

  void desenharTabuleiro() {
    String espacamento = "  ";
    int jogador = 0;
    tabuleiro = "\u25E4"; // ◤
    for (int ix = 1; ix <= tamanhoJogo - 1; ix++) {
      tabuleiro += ix + " \u25AA "; // ▪
    }
    tabuleiro += tamanhoJogo + "\n";
    for (int ix = 0; ix < tamanhoJogo; ix++) {
      tabuleiro += (ix + 1) + espacamento;
      for (int jx = 0; jx < tamanhoJogo; jx++) {
        jogador = jogo[ix][jx];
        tabuleiro += jogadores[jogador] + espacamento;
      }
      tabuleiro += "\n";
    }
  }

  @SuppressWarnings("unchecked")
  void iniciarJogo() {
    jogo = new int[tamanhoJogo][tamanhoJogo];
    // Cria um intervalo de inteiros entre 1 e o tamanho do jogo
    for(int ix = 1; ix<= tamanhoJogo;ix++) {
      linhas.add(ix);
    }
    for (int ix = 1; ix <= tamanhoJogo; ix++) {;
      colunas.put(ix, (ArrayList<Integer>) linhas.clone());
    }
    desenharTabuleiro();
  }
}