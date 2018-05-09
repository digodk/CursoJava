package lista03;

import javax.swing.*;
import java.lang.Math;

/*
 * Implemente um algoritmo onde o usuário irá informar quinze números inteiros. Após informados os
 * quinze números informe os seguintes dados:
 * a. Soma dos números
 * b. Média dos números
 * c. Quantidade de números maiores ou iguais a média
 * d. Quantidade de números negativos, neutros e positivos
 * e. Maior número
 * f. Menor número
 * g. Números pares
 * h. Números ímpares
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
				mensagem = "Insira o número " +(ix+1)+"/"+(numeros.length);
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
						JOptionPane.showMessageDialog(null, "Insira um número inteiro!");
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
			mensagem = "Resumo dos números apresentados: \n" + linha;
			mensagem += String.format("-Soma: %1$s \n" +
					"-Média: %2$.2f \n" + 
					"-Quantidade de números maiores ou iguais à média: %3$s \n" + 
					"-Quantidade de números positivos: %4$s \n" +
					"-Quantidade de números neutros: %5$s \n" +
					"-Quantidade de números negativos: %6$s \n" +
					"-Maior número: %7$s \n" +
					"-Menor número: %8$s \n" + linha +
					"Clique em OK para continuar.", 
					soma,media,numAcimaMedia, numPositivos,numNeutros, numNegativos,maior, menor);
			JOptionPane.showMessageDialog(null, mensagem);
			
			if(numPares>0){
				mensagem = "Números pares :" + linha;
				for(int ix = 0;ix<numPares;ix++){
					mensagem += "-" + numerosPares[ix] + "\n";
				}
			}else{
				mensagem = "Não foram inseridos números pares";
			}
			JOptionPane.showMessageDialog(null, mensagem);
			
			if(numImpares>0){
				mensagem = "Números ímpares :" + linha;
				for(int ix = 0;ix<numImpares;ix++){
					mensagem += "." + numerosImpares[ix] + "\n";
				}
			}else{
				mensagem = "Não foram inseridos números ímpares";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

}
