package provas;

import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

/*
 * Desenvolver um algoritmo de perguntas e respostas. Haverá trinta questões já definidas em um vetor.
 * Cada questão possui quatro alternativas e apenas uma resposta correta.
 * O jogador terá que informar seu nome e responder dez questões.
 * 
 * Além de ter as quatro opções de resposta o jogador terá uma opção chamada pular que poderá ser
 * utilizada até três vezes por jogador cadastrado. Quando utilizada a opção pular uma nova questão é exibida.
 * Não contabilize a questão pulada.
 * 
 * As questões deverão ser aleatórias e não podem ser repetidas.
 * Crie um vetor para armazenar treze questões (10 que mínimas necessárias e 3 extras caso alguma seja pulada.)
 * Quando as dez questões forem devidamente respondidas informe a quantidade de acertos e erros
 * (informe o enunciado da questão que foi errada, a resposta dada pelo jogador e a resposta correta).
 * Após obter a quantidade de acertos e erros pedidos no parágrafo anterior perguntar
 * se deseja continuar a jogar.
 * Caso afirmativo peça um nome e realize novamente as perguntas,
 * caso contrário exibir um ranking contendo o nome dos jogadores e a quantidade de acertos.
 * 
 * Classifique da maior quantidade de acertos para a menor.
 * Valide o nome informado, pois não serão aceitos nomes iguais no vetor.
 * Sendo assim antes de realizar as perguntas o nome informado não pode conter no vetor.
 * Poderão jogar até cinco pessoas.
 */
public class Prova02Diogo {

  public static void main(String[] args) {
    Random geradorRandom = new Random();
    String mensagem = "", entrada = "", enunciado = "", nomeJogador = "";
    String linhaTexto = new String(new char[20]).replace("\0", "-") + "\n \n";
    int questaoAtiva = 0, numJogadores = 0, respostaCorreta = 0, respostaSelecionada = 0,
        numAcertos = 0, numErros = 0, numPulos;
    boolean sair = false, terminar = false;
    Object[] opcoesResposta = new Object[5];
    String[] jogadores = new String[5];
    int[] questoesUsadas = new int[13];
    int[][] erros = new int[13][3];
    int[][] resumoJogadores = new int[13][2];

    String[][] questoes = new String[30][6];
    // Inicialização das questões
    questoes[0][0] = "Quem era o homem mais sedutor do mundo?";
    questoes[0][1] = "a)Dom Juan";
    questoes[0][2] = "b)Dom Antônio";
    questoes[0][3] = "c)Dom Marco";
    questoes[0][4] = "d)Dom Carlos";
    questoes[0][5] = "0";

    questoes[1][0] = "De quantos anos é constituído um século?";
    questoes[1][1] = "a)50";
    questoes[1][2] = "b)100";
    questoes[1][3] = "c)1000";
    questoes[1][4] = "d)1500";
    questoes[1][5] = "1";

    questoes[2][0] = "Qual é o coletivo de cães?";
    questoes[2][1] = "a)Matilha";
    questoes[2][2] = "b)Rebanho";
    questoes[2][3] = "c)Cardume";
    questoes[2][4] = "d)Manada";
    questoes[2][5] = "0";

    questoes[3][0] = "Segundo a Bíblia, em que rio Jesus foi batizado por João Batista?";
    questoes[3][1] = "a)Rio Nilo";
    questoes[3][2] = "b)Rio Sena";
    questoes[3][3] = "c)Rio Reno";
    questoes[3][4] = "d)Rio Jordão";
    questoes[3][5] = "3";

    questoes[4][0] = "Qual é a maior floresta do planeta?";
    questoes[4][1] = "a)Negra";
    questoes[4][2] = "b)Sherwood";
    questoes[4][3] = "c)Tijuca";
    questoes[4][4] = "d)Amazônica";
    questoes[4][5] = "3";

    questoes[5][0] = "Qual é o naipe do baralho que tem o desenho de coração?";
    questoes[5][1] = "a)Ouros";
    questoes[5][2] = "b)Paus";
    questoes[5][3] = "c)Copas";
    questoes[5][4] = "d)Espadas";
    questoes[5][5] = "2";

    questoes[6][0] = "Qual atriz estrelou no filme: 'A lagoa azul'?";
    questoes[6][1] = "a)Alicia Silverstone";
    questoes[6][2] = "b)Brooke Shields";
    questoes[6][3] = "c)Julia Roberts";
    questoes[6][4] = "d)Jessica Lange";
    questoes[6][5] = "1";

    questoes[7][0] = "Qual casal foi expulso do Paraíso?";
    questoes[7][1] = "a)Sansão de Dalila";
    questoes[7][2] = "b)José e Maria";
    questoes[7][3] = "c)Sara e Abraão";
    questoes[7][4] = "d)Adão e Eva";
    questoes[7][5] = "3";

    questoes[8][0] = "Segundo os contos, qual animal ao ser beijado se transforma em príncipe?";
    questoes[8][1] = "a)Cavalo";
    questoes[8][2] = "b)Cão";
    questoes[8][3] = "c)Gato";
    questoes[8][4] = "d)Sapo";
    questoes[8][5] = "3";

    questoes[9][0] = "Qual é o santo que só acreditou vendo?";
    questoes[9][1] = "a)Santo Antônio";
    questoes[9][2] = "b)São Judas Tadeu";
    questoes[9][3] = "c)São Pedro";
    questoes[9][4] = "d)São Tomé";
    questoes[9][5] = "3";

    questoes[10][0] = "Qual é o fruto conhecido no nordeste do Brasil como 'jerimum'?";
    questoes[10][1] = "a)Caju";
    questoes[10][2] = "b)Abóbora";
    questoes[10][3] = "c)Chuchu";
    questoes[10][4] = "d)Coco";
    questoes[10][5] = "1";

    questoes[11][0] = "Em qual parte do corpo humano são implantadas as 'pontes de safena'?";
    questoes[11][1] = "a)Estômago";
    questoes[11][2] = "b)Intestino";
    questoes[11][3] = "c)Pulmão";
    questoes[11][4] = "d)Coração";
    questoes[11][5] = "3";

    questoes[12][0] = "Como é conhecido o jogador Edmundo?";
    questoes[12][1] = "a)Tigrão";
    questoes[12][2] = "b)Gatinho";
    questoes[12][3] = "c)Animal";
    questoes[12][4] = "d)Cobra";
    questoes[12][5] = "2";

    questoes[13][0] = "Na literatura, quem foi o criador da boneca Emília?";
    questoes[13][1] = "a)Monteiro Lobato";
    questoes[13][2] = "b)Maurício de Souza";
    questoes[13][3] = "c)Walt Disney";
    questoes[13][4] = "d)Jorge Amado";
    questoes[13][5] = "0";

    questoes[14][0] = "Quem é o primeiro substituto do Presidente?";
    questoes[14][1] = "a)Veriador";
    questoes[14][2] = "b)Vice-presidente";
    questoes[14][3] = "c)Senador";
    questoes[14][4] = "d)Deputado";
    questoes[14][5] = "1";

    questoes[15][0] = "Segundo o romance, que animal era 'Moby Dick'?";
    questoes[15][1] = "a)Tubarão";
    questoes[15][2] = "b)Golfinho";
    questoes[15][3] = "c)Polvo";
    questoes[15][4] = "d)Baleia";
    questoes[15][5] = "3";

    questoes[16][0] = "Que animal é o Pateta?";
    questoes[16][1] = "a)Cavalo";
    questoes[16][2] = "b)Burro";
    questoes[16][3] = "c)Cachorro";
    questoes[16][4] = "d)Raposa";
    questoes[16][5] = "2";

    questoes[17][0] = "Em qual cidade está o Cristo Redentor do Corcovado?";
    questoes[17][1] = "a)Rio de Janeiro";
    questoes[17][2] = "b)São Paulo";
    questoes[17][3] = "c)Curitiba";
    questoes[17][4] = "d)Belo Horizonte";
    questoes[17][5] = "0";

    questoes[18][0] = "Quem é conhecido como o 'playboy brasileiro'?";
    questoes[18][1] = "a)Joãozinho Trinta";
    questoes[18][2] = "b)Zeca Pagodinho";
    questoes[18][3] = "c)Chiquinho Scarpa";
    questoes[18][4] = "d)Chico Buarque";
    questoes[18][5] = "2";

    questoes[19][0] = "Quantos centímetros equivalem a um metro?";
    questoes[19][1] = "a)10";
    questoes[19][2] = "b)100";
    questoes[19][3] = "c)1000";
    questoes[19][4] = "d)10000";
    questoes[19][5] = "1";

    questoes[20][0] = "Qual é o nome do macho da abelha?";
    questoes[20][1] = "a)Zangão";
    questoes[20][2] = "b)Abelhão";
    questoes[20][3] = "c)Rufião";
    questoes[20][4] = "d)Pulgão";
    questoes[20][5] = "0";

    questoes[21][0] = "Qual é o inseto que transmite a doença de Chagas?";
    questoes[21][1] = "a)Abelha";
    questoes[21][2] = "b)Rato";
    questoes[21][3] = "c)Barbeiro";
    questoes[21][4] = "d)Pulga";
    questoes[21][5] = "2";

    questoes[22][0] = "Qual é o nome do conjunto de dados sobre uma população?";
    questoes[22][1] = "a)Censo";
    questoes[22][2] = "b)Montagem";
    questoes[22][3] = "c)Marcação";
    questoes[22][4] = "d)Registro";
    questoes[22][5] = "0";

    questoes[23][0] = "Qual ator imortalizou o personagem 'Zé Bonitinho'?";
    questoes[23][1] = "a)Roni Cócegas";
    questoes[23][2] = "b)Jorge Loredo";
    questoes[23][3] = "c)Davi Pinheiro";
    questoes[23][4] = "d)Arnaud Rodrigues";
    questoes[23][5] = "1";

    questoes[24][0] = "Qual a cantora conhecida como 'A rainha dos caminhoneiros'?";
    questoes[24][1] = "a)Roberta Miranda";
    questoes[24][2] = "b)Maria Bethânia";
    questoes[24][3] = "c)Sula Miranda";
    questoes[24][4] = "d)Rita Cadillac";
    questoes[24][5] = "2";

    questoes[25][0] = "Qual é o nome da missa rezada no Natal?";
    questoes[25][1] = "a)Campal";
    questoes[25][2] = "b)Do Galo";
    questoes[25][3] = "c)Do Vaticano";
    questoes[25][4] = "d)Do Sétimo Dia";
    questoes[25][5] = "1";

    questoes[26][0] = "Qual é a orixá conhecida como 'A rainha do mar'?";
    questoes[26][1] = "a)Mamãe Oxum";
    questoes[26][2] = "b)Iemanjá";
    questoes[26][3] = "c)Axé";
    questoes[26][4] = "d)Iansã";
    questoes[26][5] = "1";

    questoes[27][0] = "No julgamento de Jesus, quem disse a frase: 'Eu lavo minhas mãos'?";
    questoes[27][1] = "a)Barrabás";
    questoes[27][2] = "b)Tibério";
    questoes[27][3] = "c)Pôncio Pilatos";
    questoes[27][4] = "d)Judas";
    questoes[27][5] = "2";

    questoes[28][0] = "O que está no centro da Terra?";
    questoes[28][1] = "a)Manto";
    questoes[28][2] = "b)Núcleo";
    questoes[28][3] = "c)Hidrosfera";
    questoes[28][4] = "d)Litosfera";
    questoes[28][5] = "1";

    questoes[29][0] = "Qual é o animal que representa o signo de áries?";
    questoes[29][1] = "a)Touro";
    questoes[29][2] = "b)Leão";
    questoes[29][3] = "c)Caneiro";
    questoes[29][4] = "d)Bode";
    questoes[29][5] = "2";

    mensagem = "Bem vindo ao jogo do Ralf!\n Vamos começar?";
    JOptionPane.showMessageDialog(null, mensagem);
    // Início do jogo
    while (true) {
      numAcertos = 0;
      numPulos = 0;
      numErros = 0;
      erros = new int[13][3];
      // Pergunta o nome do jogador e checa se já existe algum cadastrado
      mensagem = "Para começar, digite seu nome:";
      sair = false;
      while (!sair) {
        entrada = JOptionPane.showInputDialog(null, mensagem, "Ralf Lindo");
        if (entrada == null || (entrada != null && entrada.equals(""))) {
          terminar = true;
          break;
        } else {
          sair = true;
          for (int ix = 0; ix < jogadores.length; ix++) {
            if (entrada.equals(jogadores[ix])) {
              mensagem = "Esse jogador já foi cadastrado ô cabeção!";
              JOptionPane.showMessageDialog(null, mensagem);
              mensagem = "Vamos tentar de novo. Seu nome:";
              sair = false;
            }
          }
        }

      }
      if (terminar) {
        break;
      }
      sair = false;
      nomeJogador = entrada;

      mensagem = "Muito bem, vamos começar as perguntas misteriosas."
          + "\nLembrando que você tem direito a pular três vezes!";
      JOptionPane.showMessageDialog(null, mensagem);
      // Reinicia variáveis do jogo
      int numQuestao = 1;
      questoesUsadas = new int[13];
      // Loop de perguntas
      while (true) {
        // Gera uma pergunta aleatoriamente, seleciona novamente caso ela já tenha sido usada
        sair = false;
        while (!sair) {
          sair = true;
          questaoAtiva = geradorRandom.nextInt(questoes.length);
          for (int ix = 0; ix < questoesUsadas.length; ix++) {
            if (questoesUsadas[ix] == questaoAtiva) {
              sair = false;
            }
          }
        }
        questoesUsadas[numQuestao + numPulos - 1] = questaoAtiva;
        enunciado = questoes[questaoAtiva][0];
        respostaCorreta = Integer.parseInt(questoes[questaoAtiva][5]);
        for (int ix = 0; ix < opcoesResposta.length - 1; ix++) {
          opcoesResposta[ix] = questoes[questaoAtiva][ix + 1];
        }
        if (numPulos < 3) {
          opcoesResposta[opcoesResposta.length - 1] = "Pular";
        } else {
          opcoesResposta[opcoesResposta.length - 1] = "Você não pode mais pular questões";
        }
        // Faz a pergunta
        mensagem = String.format("Pergunta de número %1$s, você ainda têm direito a %2$s pulos.\n",
            numQuestao, 3 - numPulos);
        mensagem += enunciado;
        sair = false;
        while (!sair) {
          try {
            entrada = JOptionPane.showInputDialog(null, mensagem, "", JOptionPane.PLAIN_MESSAGE,
                null, opcoesResposta, 1).toString();
          } catch (Exception exc) {
            terminar = true;
            break;
          }

          respostaSelecionada = Arrays.asList(opcoesResposta).indexOf(entrada);
          if (respostaSelecionada == 4) {
            if (numPulos < 3) {
              numPulos++;
              sair = true;
            } else {
              JOptionPane.showMessageDialog(null, "Você não pode mais pular questões cabeção!");
            }
          } else {
            numQuestao++;
            sair = true;
            if (respostaSelecionada == respostaCorreta) {
              ;
              numAcertos++;
            } else {
              erros[numErros][0] = questaoAtiva;
              erros[numErros][1] = respostaSelecionada;
              erros[numErros][2] = respostaCorreta;
              numErros++;
            }
          }
        }
        if (terminar) {
          break;
        }
        // Confere se já foram feitas dez perguntas
        if (numQuestao > 10) {
          break;
        }

      }
      if (terminar) {
        break;
      }

      jogadores[numJogadores] = nomeJogador;
      // Apresenta resumo do jogo
      mensagem = "Fim de jogo! Vamos ao resumo:\n" + linhaTexto;
      mensagem += String.format("Número de acertos: %1$s, número de pulos: %2$s", numAcertos + "\n",
          numPulos);
      if (numAcertos < 10) {
        mensagem += "Eis o que você errou:\n" + linhaTexto;

        for (int ix = 0; ix < numErros; ix++) {
          mensagem += "\n\nQuestão: \n" + questoes[erros[ix][0]][0];
          mensagem += "\n\nSua resposta: \n" + questoes[erros[ix][0]][erros[ix][1] + 1];
          mensagem += "\n\nResposta correta: \n" + questoes[erros[ix][0]][erros[ix][2]+1];
        }
      }
      JOptionPane.showMessageDialog(null, mensagem);
      numJogadores++;
      // Registra a matriz de acertos já na ordem correta
      sair = false;
      for (int ix = 0; ix < resumoJogadores.length - 1; ix++) {
        if (numAcertos > resumoJogadores[ix][1]) {
          // Somente faz o sort se eu não tiver atingido o final dos dados
          if (resumoJogadores[ix][0] != 0) {
            for (int jx = resumoJogadores.length - 1; jx > ix; jx--) {
              resumoJogadores[jx][0] = resumoJogadores[jx-1][0];
              resumoJogadores[jx][1] = resumoJogadores[jx-1][1];
            }
          }
          resumoJogadores[ix][0] = numJogadores;
          resumoJogadores[ix][1] = numAcertos;
          sair = true;
          break;
        } else if (resumoJogadores[ix][0] == 0) {
          resumoJogadores[ix][0] = numJogadores;
          resumoJogadores[ix][1] = numAcertos;
        }
      }
      // Coloca o valor no final da matriz caso seja o último jogador E ele tem o menor acerto
      if (!sair) {
        resumoJogadores[resumoJogadores.length - 1][0] = numJogadores;
        resumoJogadores[resumoJogadores.length - 1][1] = numAcertos;
      }
      mensagem = "Você terminou seu ritual de iniciação, existe outra pessoa que deseja jogar?";
      if (JOptionPane.showConfirmDialog(null, mensagem, "",
          JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
        break;
      }
    }
    // Resumo do jogo se algum jogador chegou a completar as perguntas
    if (numJogadores > 0) {
      mensagem = "Vamos ao resumo do jogo! \n" + linhaTexto;
      mensagem += "Numero de almas sugadas - quer dizer - jogadores: " + numJogadores + "\n";
      mensagem += linhaTexto;
      for (int ix = 0; ix < numJogadores; ix++) {
        mensagem += String.format("-Jogador %1$s, acertos: %2$s\n",
            jogadores[resumoJogadores[ix][0] - 1], resumoJogadores[ix][1]);
      }
      JOptionPane.showMessageDialog(null, mensagem);

    }

  }

}
