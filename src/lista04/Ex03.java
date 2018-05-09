package lista04;

import java.util.Arrays;

import javax.swing.JOptionPane;

/*
 * Crie uma matriz 3X2, peça para que o usuário escreva os seis números e exiba nas proporções: 3X2 e 2X3. 
 * Chamamos isso de matriz transposta.
 */
public class Ex03 {

  public static void main(String[] args) {
    String mensagem="", entrada="", matrizTexto="", transpostaTexto="";
    String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
    int numero=0;
    boolean cancelar = false, sair=false;
    int[][] matriz = new int[3][2], transposta = new int[2][3];
    
    for(int ix = 0; ix< matriz.length;ix++){
      for(int jx = 0; jx< matriz[0].length;jx++){
        if(cancelar){break;}
        mensagem = String.format("Insira o número para a linha %1$s e coluna %2$s da matriz.",ix+1,jx+1);
        sair=false;
        while(!sair&&!cancelar){
          try{
            entrada = JOptionPane.showInputDialog(mensagem);
            if (entrada == null || (entrada!= null && entrada.equals(""))){
                      cancelar = true;
            }else{
              numero = Integer.parseInt(entrada);
              matriz[ix][jx] = numero;
              transposta[jx][ix] = numero;
              sair=true;
            }
          }catch (Exception exc){
            JOptionPane.showMessageDialog(null, "Digite um valor inteiro!");
            
          }
        }
      }
      matrizTexto += Arrays.toString(matriz[ix]) + "\n";
    }
    
    if(!cancelar){
      for(int[] ix:transposta){
        transpostaTexto += Arrays.toString(ix) + "\n";
      }
      mensagem = "Matriz original: \n" + linha;
      mensagem += matrizTexto +"\n"; 
      mensagem += "Pressione OK para visualizar a transposta.";
      JOptionPane.showMessageDialog(null, mensagem);
      mensagem = "Matriz transposta: \n" + linha;
      mensagem += transpostaTexto;
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }

}
