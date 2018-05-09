package lista04;
/*
 * Crie uma matriz 5X6 onde o usu�rio ir� informar n�meros inteiros. Ap�s informados dever� ser exibido:
 * a. Quantidade de N�meros Pares.
 * b. Quantidade de N�meros �mpares.
 * c. M�dia dos N�meros Informados.
 * d. Quantidade de N�meros Maiores ou Iguais a M�dia.
 * e. Exibir a matriz
 */

import java.util.Arrays;

import javax.swing.*;
public class Ex01 {

	public static void main(String[] args) {
		String mensagem="", entrada="", matrizTexto="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		double media=0;
		int soma=0, numPares=0, numImpares=0, numero=0, numMaior=0, numMenor=0, numMedia=0;
		boolean cancelar = false, sair=false;
		int[][] matriz = new int[5][6];
		
		for(int ix = 0; ix< matriz.length;ix++){
			for(int jx = 0; jx< matriz[0].length;jx++){
				if(cancelar){break;}
				mensagem = String.format("Insira o n�mero para a linha %1$s e coluna %2$s da matriz.",ix+1,jx+1);
				sair=false;
				while(!sair&&!cancelar){
					try{
						entrada = JOptionPane.showInputDialog(mensagem);
						if (entrada == null || (entrada!= null && entrada.equals(""))){
			                cancelar = true;
			                break;
						}else{
							numero = Integer.parseInt(entrada);
							if(numero>0){
								if(numero % 2 ==0){
									numPares++;
								}else{
									numImpares++;
								}
							}
							soma+=numero;
							matriz[ix][jx]=numero;
							sair=true;
						}
					}catch (Exception exc){
						JOptionPane.showMessageDialog(null, "Digite um valor inteiro!");
						
					}
				}
			}
		}
		
		if(!cancelar){
			mensagem = "Resumo dos numeros apresentados: \n" + linha;
			media = soma/(matriz.length*matriz[0].length);
			for(int[] ix: matriz){
				matrizTexto += Arrays.toString(ix) + "\n";
				for(int jx : ix){
					if(jx>media){
						numMaior++;
					}else if(jx<media){
						numMenor++;
					}else{
						numMedia++;
					}
				}
				
			}
			mensagem += "Quantidade de pares: " + numPares + "\n";
			mensagem += "Quantidade de �mpares: " + numImpares + "\n";
			mensagem += "Quantidade de n�meros: \n";
			mensagem += "-Maiores que a m�dia: " + numMaior + "\n";
			mensagem += "-Menores que a m�dia: " + numMenor + "\n";
			mensagem += "-Iguais � m�dia: " + numMedia + "\n";
			mensagem += "Pressione ok para visiualizar a matriz";
			JOptionPane.showMessageDialog(null,mensagem);
			JOptionPane.showMessageDialog(null, matrizTexto);
		}

	}

}
