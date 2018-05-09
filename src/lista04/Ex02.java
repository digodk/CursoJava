package lista04;

import java.util.Arrays;

import javax.swing.JOptionPane;

/*
 * Desenvolva um algoritmo onde o usu�rio ir� informar 25 n�meros, essa matriz ser� 5X5. 
 * Ap�s informado os n�meros dever� retornar:
 * a. A soma dos n�meros informados na quarta linha.
 * b. A soma dos n�meros informados na segunda coluna.
 * c. A soma da diagonal principal.
 * d. A soma da diagonal secund�ria.
 * e. A soma de todos os elementos da matriz.
 * f. Escrever todos os elementos que comp�em a matriz.
 */
public class Ex02 {

	public static void main(String[] args) {
		String mensagem="", entrada="", matrizTexto="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		int somaLinha4=0, somaColuna2=0, somaDiagPrincipal=0, somaDiagSec=0, somaTotal=0, numero=0;
		boolean cancelar = false, sair=false;
		int[][] matriz = new int[5][5];
		
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
						}else{
							numero = Integer.parseInt(entrada);
							somaTotal+=numero;
							if(ix==jx){
								somaDiagPrincipal +=numero;
							}
							if(ix+jx==matriz[0].length+1){
								somaDiagSec+=numero;
							}
							if(ix==4){
								somaLinha4 += numero;
							}
							if(jx==2){
								somaColuna2 += numero;
							}
							matriz[ix][jx]=numero;
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
			mensagem = "Resumo dos n�meros inseridos: \n" + linha;
			mensagem += String.format("-Soma dos elementos da quarta linha: %1$s.\n" + 
					"-Soma dos elementos da segunda coluna: %2$s.\n" + 
					"-Soma da diagonal principal: %3$s.\n" +
					"-Soma da diagonal secund�ria: %4$s.\n" +
					"-Soma total da matriz: %5$s.\n",
					somaLinha4,somaColuna2, somaDiagPrincipal, somaDiagSec, somaTotal);
			mensagem += "Pressione ok para visualizar a matriz.";
			JOptionPane.showMessageDialog(null, mensagem);
			JOptionPane.showMessageDialog(null, matrizTexto);
			
		}

	}

}
