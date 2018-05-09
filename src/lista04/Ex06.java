package lista04;
/*
 * Criar um jogo da velha, onde duas pessoas ir�o jogar.
 */

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Ex06 {
  static String[] jogadores = {"-","O","X"};
  static AuxInput input = new AuxInput();
  public static void main(String[] args) {
    String textoTabuleiro = "", jogador="X", mensagem="", vencedor="";
    String linhaTexto = new String(new char[20]).replace("\0", "-") + "\n \n";
    int linha=0,coluna=0, gameSize = 3, mult;
    boolean terminar=false;
    int[][] jogo = new int[3][3];
    Object[] opcoes;
    ArrayList<String> colunasDisponiveis;
    ArrayList<String> Linhas;
    ArrayList<String[]> Colunas;
    
    //Default input message for choosing row and column to mark
    String mensagemJogada = "Jogador \"%1$S\" selecione a %2$s onde deseja marcar.\n" + linhaTexto + "%3$s";
    while (!terminar) {
          //Updates available options for rows and columns
            Linhas = new ArrayList<>();
            Colunas = new ArrayList<>();
            for(int ix=0;ix<gameSize;ix++) {
              colunasDisponiveis = new ArrayList<>();
              mult=1;
              for(int jx:jogo[ix]) {
                mult*=jx;
              }
              if (mult==0) {
                Linhas.add(Integer.toString(ix+1));
                for(int jx=0;jx<gameSize;jx++) {
                  if (jogo[ix][jx]==0) {
                    colunasDisponiveis.add(Integer.toString(jx+1));
                  }
                }
              }
              Colunas.add( colunasDisponiveis.toArray (new String[colunasDisponiveis.size()]) );
            }
            //If there are no more possible plays, end the game
            if (Linhas.size()==0) {
              terminar=true;
              break;
            }
            //Switch player and asks for row and column to be marked
      jogador = jogador.equals("O") ? "X" : "O";
      textoTabuleiro = montarTabuleiro(jogo);
      //Get row
      mensagem =String.format(mensagemJogada, jogador, "linha", textoTabuleiro);
      opcoes=Linhas.toArray();
      linha = input.optionToInt(mensagem, opcoes);
      if((linha+1)==0) {
              break;
            }
      linha = Integer.parseInt(opcoes[linha].toString())-1;
      //Get column
      opcoes=Colunas.get(linha);
      mensagem =String.format(mensagemJogada, jogador, "coluna", textoTabuleiro);
      coluna = input.optionToInt(mensagem, opcoes);
      if((coluna+1)==0) {
        break;
      }
      coluna = Integer.parseInt(opcoes[coluna].toString())-1;
            //Updates the game and checks if there is a winner
      jogo[linha][coluna]=Arrays.asList(jogadores).indexOf(jogador);
      terminar = fimJogo(jogo, linha, coluna);
      if (terminar) {
        vencedor = jogador;
        textoTabuleiro = montarTabuleiro(jogo);
      }
    }
    //If game was not canceled then shows final message
    if (terminar) {
      //Checks if there is a winner
      if (!vencedor.equals("")) {
        mensagem = "Jogo conclu�do!\n" + "O jogador vencedor �: %1$s \n " + linha + "%2$s";
        mensagem = String.format(mensagem,jogador,textoTabuleiro);
      } else {
        mensagem = "N�o h� mais jogadas dispon�veis, o jogo terminou em empate!";
      }
      JOptionPane.showMessageDialog(null,mensagem);
    }
  }
  
  static String montarTabuleiro(int[][] jogo) {
    String espacamento = new String(new char[10]).replace("\0", " ");
    String tabuleiro=" " + espacamento +"1" + espacamento + "2" + espacamento +  "3\n\n";
    int linha=1;
    for(int[] ix :jogo) {
      tabuleiro += linha + espacamento;
      for(int jx:ix) {
        tabuleiro+=jogadores[jx] + espacamento;
      }
      tabuleiro += "\n\n" ;
      linha++;
    }
    return tabuleiro;
  }
  
  static boolean fimJogo(int[][] jogo, int i ,int j){
    int jogador = jogo[i][j];
    int gameSize = jogo.length;
    boolean endGame;
    
    //If the play was on main diagonal, checks diagonal
    if (i==j) {
      endGame = true;
      for (int ix=0; ix<gameSize; ix++) {
        endGame &= jogo[ix][ix]==jogador;
      }
      if (endGame) {
        return endGame;
      }
    }
    //If the play was on sexondary diagonal, checks diagonal
    if (i+j==gameSize-1) {
      endGame = true;
      for (int ix=0; ix<gameSize; ix++) {
            endGame &= jogo[ix][gameSize-ix-1]==jogador;
          }
      if (endGame) {
            return endGame;
          }
    }
    
    //Checks row and colum
    endGame = true;
    for(int ix=0;ix<gameSize;ix++) {
      endGame &= jogo[i][ix]==jogador;
    }
    if (endGame) {
          return endGame;
        }
    for(int ix=0;ix<gameSize;ix++) {
          endGame &= jogo[ix][j]==jogador;
        }
    return endGame;
  }
  
}
