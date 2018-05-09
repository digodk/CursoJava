package lista03;

import javax.swing.*;
import java.lang.Math;

/*
 * Implemente um algoritmo onde o usu�rio ir� informar quinze n�meros inteiros. Ap�s informados os
 * quinze n�meros informe os seguintes dados:
 * a. Soma dos n�meros
 * b. M�dia dos n�meros
 * c. Quantidade de n�meros maiores ou iguais a m�dia
 * d. Quantidade de n�meros negativos, neutros e positivos
 * e. Maior n�mero
 * f. Menor n�mero
 * g. N�meros pares
 * h. N�meros �mpares
 */
public class Ex05 {

	public static void main(String[] args) {
		String mensagem="", entrada = "";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		int soma=0, cont=0, numAcimaMedia=0, numNegativos=0, numNeutros=0, numPositivos=0, maior=0;
		int menor=0, numPares=0, numImpares=0, numero=0;
		double media=0;
		int[] numeros=new int[15];
		int[] numerosPares = new int[15];
		int[] numerosImpares = new int[15];
		boolean sair=false, cancelar=false;
		
		for(int ix=0; ix<numeros.length;ix++){
			sair = false;
			while(!sair&&!cancelar){
				mensagem = "Insira o n�mero " +(ix+1)+"/"+(numeros.length);
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;
				}else{
					try{
						numero = Integer.parseInt(entrada);
						numeros[ix] = numero;
						soma += numero;
						if(cont==0){
							maior = numero;
							menor = numero;
						}else{
							maior = Math.max(maior, numero);
							menor = Math.min(menor, numero);
						}
						cont++;
						sair=true;
						if(numero>0){
							numPositivos++;
							if(numero%2==0){
								numerosPares[numPares]=numero;
								numPares++;
							}else{
								numerosImpares[numImpares] = numero;
								numImpares++;
							}
						}else if(numero<0){
							numNegativos++;
						}else{
							numNeutros++;
						}
					}catch(Exception exc){
						JOptionPane.showMessageDialog(null, "Insira um n�mero inteiro!");
					}
				}
			}
			
			if(cancelar){
				break;
			}
		}
		
		if(!cancelar){
			media = soma/cont;
			for(int ix = 0; ix<numeros.length;ix++){
				if(numeros[ix]>=media){
					numAcimaMedia++;
				}
			}
			mensagem = "Resumo dos n�meros apresentados: \n" + linha;
			mensagem += String.format("-Soma: %1$s \n" +
					"-M�dia: %2$.2f \n" + 
					"-Quantidade de n�meros maiores ou iguais � m�dia: %3$s \n" + 
					"-Quantidade de n�meros positivos: %4$s \n" +
					"-Quantidade de n�meros neutros: %5$s \n" +
					"-Quantidade de n�meros negativos: %6$s \n" +
					"-Maior n�mero: %7$s \n" +
					"-Menor n�mero: %8$s \n" + linha +
					"Clique em OK para continuar.", 
					soma,media,numAcimaMedia, numPositivos,numNeutros, numNegativos,maior, menor);
			JOptionPane.showMessageDialog(null, mensagem);
			
			if(numPares>0){
				mensagem = "N�meros pares :" + linha;
				for(int ix = 0;ix<numPares;ix++){
					mensagem += "-" + numerosPares[ix] + "\n";
				}
			}else{
				mensagem = "N�o foram inseridos n�meros pares";
			}
			JOptionPane.showMessageDialog(null, mensagem);
			
			if(numImpares>0){
				mensagem = "N�meros �mpares :" + linha;
				for(int ix = 0;ix<numImpares;ix++){
					mensagem += "." + numerosImpares[ix] + "\n";
				}
			}else{
				mensagem = "N�o foram inseridos n�meros �mpares";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

}
