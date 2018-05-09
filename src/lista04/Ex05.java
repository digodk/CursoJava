package lista04;

import java.util.Arrays;

import javax.swing.JOptionPane;

/*
 * Criar um vetor 20x6 onde o usuário irá informar os seguintes dados:
 * a. Nome
 * b. Sexo
 * c. Altura
 * d. Peso
 * Nome, sexo, altura e peso ficarão nas colunas de posição 0,1,2 e 3, a coluna de posição 4 deverá ser
 * calculado o IMC (peso / altura^2) e a posição 5 deverá conter a situação de cada indivíduo.
 * Faça um laço para que sejam cadastrados um determinado número de pessoas (a quantidade pode
 * variar).
 * Ao término exiba:
 * a) Nome, imc e situação de todos os participantes.
 * b) Quantidade e percentual de homens e mulheres que participaram
 * c) Quantidade de cada situação (quantidade de pesos normais, abaixo, sobrepeso…)
 * IMC Situações
 * Abaixo de 17 Muito abaixo do peso
 * Entre 17 e 18,49 Abaixo do peso
 * Entre 18,5 e 24,99 Peso normal
 * Entre 25 e 29,99 Acima do peso
 * Entre 30 e 34,99 Obesidade I
 * Entre 35 e 39,99 Obesidade II (severa)
 * Acima de 40 Obesidade III (mórbida)
 */
public class Ex05 {

  public static void main(String[] args) {
    String mensagem = "", entrada = "", nome = "", sexo = "", situacao="";
    String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
    double altura=0, peso=0, imc=0;
    int numCadastros=0, escolha=0;
    boolean sair=false, terminar=false;
    String[][] dados = new String[20][6];   
    Object[] sexos = {"Feminino", "Masculino","Outro"};
    double[] valoresSituacoes = {0,17,18.5,25.5,30,35.5,40};
    String[] textoSituacoes ={"Muito abaixo do peso","Abaixo do peso","Peso normal","Acima do peso",
        "Obesidade I","Obesidade II (severa)","Obesidade III (mórbida)"};
    int[] contSituacoes = new int[textoSituacoes.length];
    int[] contSexos = new int[sexos.length];
    
    while(numCadastros<= dados.length){
      mensagem = String.format("Cadastro %1$s/%2$s. \nInsira o nome. Pressione cancelar para terminar.",numCadastros+1,dados.length);
      nome = strInput(mensagem);
      if(nome==null){
        break;
      }
      mensagem = "Insira o sexo";
      escolha = optInput(mensagem, sexos);
      if(escolha==-1){
        break;
      }else{
        contSexos[escolha]++;
        sexo = sexos[escolha].toString();
      }
      sair=false;
      while(!sair&&!terminar){
        mensagem = "Digite a altura";
        entrada = dblInput(mensagem, false, 1);
        if(entrada == null){
          terminar=true;
          break;
        }else{
          altura = Double.parseDouble(entrada);
          sair=true;
        }
      }
      if(terminar){break;}
      
      sair=false;
      while(!sair&&!terminar){
        mensagem = "Digite o peso";
        entrada = dblInput(mensagem, false, 1);
        if(entrada == null){
          terminar=true;
          break;
        }else{
          peso = Double.parseDouble(entrada);
          sair=true;
        }
      }
      if(terminar){break;}
      imc = peso/Math.pow(altura,2);
      for(int jx=textoSituacoes.length-1;jx>-1;jx--){
        if(imc>=valoresSituacoes[jx]){
          situacao = textoSituacoes[jx];
          contSituacoes[jx]++;
          break;
        }
      }
      
      dados[numCadastros][0] = nome;
      dados[numCadastros][1] = sexo;
      dados[numCadastros][2] = Double.toString(altura);
      dados[numCadastros][3] = Double.toString(peso);
      dados[numCadastros][4] = Double.toString(imc);
      dados[numCadastros][5] = situacao;
      numCadastros++;
    }
    
    if(numCadastros>0){
      if(numCadastros>dados.length){
        mensagem = "Limite de cadastros atingido. Será apresentado o resumo dos dados.";
        JOptionPane.showMessageDialog(null, mensagem);
      }
      mensagem = "Cadastros realizados: \n" + linha;
      for(int ix=0;ix<numCadastros;ix++){
        String[] cadastro = dados[ix];
        mensagem += String.format("Nome: %1$s, sexo: %2$s, peso: %3$s, altura: %4$s, imc: %5$s, situação: %6$s \n",
            (Object[])cadastro);
      }
      mensagem += "Pressione ok para visualizar o resumo dos sexos.";
      JOptionPane.showMessageDialog(null, mensagem);
      
      mensagem = "Distribuição por sexo:\n" + linha;
      for(int ix=0;ix<sexos.length;ix++){
        mensagem+=String.format("Sexo: %1$s, Cadastros: %2$s\n",sexos[ix].toString(),contSexos[ix]); 
      }
      mensagem += "Pressione ok para visualizar o resumo dos dados.";
      JOptionPane.showMessageDialog(null, mensagem);
      
      mensagem = "Resumo dos cadastros: \n" + linha;
      for(int ix=0;ix<textoSituacoes.length;ix++){
        mensagem+=String.format("Situação: %1$s, Cadastros: %2$s\n",textoSituacoes[ix], contSituacoes[ix]);
      }
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }
  
  static int optInput(String mensagem, Object[] opcoes){
    String entrada="";
    try{
      entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
              null,opcoes, 1).toString();
      return Arrays.asList(opcoes).indexOf(entrada);
    }catch(Exception exc){
      return -1;
    }
  }
  
  static String strInput(String mensagem){
    String entrada="";
    entrada = JOptionPane.showInputDialog(mensagem);
    if (entrada == null || (entrada!= null && entrada.equals(""))){
      return null;
    }else{
      return entrada;
    }
  }
  
  static String dblInput(String mensagem, boolean zero, int positivo){
    String entrada="", msgErro="";
    double teste=0;
    boolean sair=false, erro = false;
    
    if(Math.abs(positivo)>1){
      positivo = 0;
    }
    
    if(!zero){
      if(positivo==1){
        msgErro = "Digite um número positivo!";
      }else if(positivo==-1){
        msgErro = "Digite um número negativo!";
      }else{
        msgErro = "Digite um número diferente de zero!";
      }
    }else{
      if(positivo==1){
        msgErro = "Digite um número maior ou igual a zero!";
      }else if(positivo==-1){
        msgErro = "Digite um número menor ou igual a zero!";
      }
    }
      
    while(!sair){
      try{
        entrada = JOptionPane.showInputDialog(mensagem);
        if (entrada == null || (entrada!= null && entrada.equals(""))){
                  entrada = null;
        }else{
          teste = Double.parseDouble(entrada);
          erro = (teste==0 && !zero) ||((teste>0 && positivo == -1) || (teste<0 && positivo == 1));
          if(erro){
            JOptionPane.showMessageDialog(null, msgErro);
          }else{
            sair=true;
          }
        }
      }catch (Exception exc){
        JOptionPane.showMessageDialog(null, msgErro);
      }
    }
    return entrada;
  }
  
  String dblInput(String mensagem){
    return dblInput(mensagem, true, 0);
  }
}
