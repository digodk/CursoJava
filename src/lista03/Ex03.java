package lista03;
import javax.swing.*;
/*
 * Elabore um algoritmo onde dever�o ser solicitados cinco n�meros distintos.
 * Ap�s informados os cinco n�meros dever� ser retornado em qual posi��o do vetor
 * se encontra algum n�mero que seja igual a dez,
 * caso n�o haja nenhum n�mero dez dever� retornar:
 * N�o foi encontrado nenhum n�mero 10.
 */
public class Ex03 {
	public static void main(String[] args) {
		int[] numeros = new int[5];
		String mensagem ="", entrada="";
		int numero=0;
		int posicao = -1;
		boolean sair = false, cancelar=false;
		
		for(int ix=0;ix<numeros.length;ix++){
			sair=false;
		    while(!sair && !cancelar){
	            mensagem = "Informe um n�mero �nico\n";
	            entrada = JOptionPane.showInputDialog(mensagem);
	            if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;              
	            }else{
	                try{
	                	posicao=-1;
	                    numero = Integer.parseInt(entrada);
	                    for(int jx=0;jx<numeros.length;jx++){
	                    	if(numeros[jx]==numero){
	                    		posicao=jx;
	                    		break;
	                    	}
	                    }
	                    if(posicao>=0){
	                    	JOptionPane.showMessageDialog(null, "Esse n�mero j� foi cadastrado!");
	                    }else{
	                    	numeros[ix]=numero;
	                    	sair = true;
	                    }
	                }catch(Exception exc){
	                    JOptionPane.showMessageDialog(null, "Insira um valor num�rico!");
	                }
	            }
		    }
		}
		if(!cancelar){
			posicao=-1;
            numero = Integer.parseInt(entrada);
            for(int jx=0;jx<numeros.length;jx++){
            	if(numeros[jx]==10){
            		posicao=jx;
            		break;
            	}
            }
			if(posicao>=0){
				mensagem = "A posi��o do n�mero 10 nessa lista �: " + posicao;
			}else{
				mensagem = "N�o foi encontrado nenhum n�mero 10.";
			}
			JOptionPane.showMessageDialog(null,mensagem);
		}
	}
}
