package lista03;
import javax.swing.*;
/*
 * Elabore um algoritmo onde deverão ser solicitados cinco números distintos.
 * Após informados os cinco números deverá ser retornado em qual posição do vetor
 * se encontra algum número que seja igual a dez,
 * caso não haja nenhum número dez deverá retornar:
 * Não foi encontrado nenhum número 10.
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
	            mensagem = "Informe um número único\n";
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
	                    	JOptionPane.showMessageDialog(null, "Esse número já foi cadastrado!");
	                    }else{
	                    	numeros[ix]=numero;
	                    	sair = true;
	                    }
	                }catch(Exception exc){
	                    JOptionPane.showMessageDialog(null, "Insira um valor numérico!");
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
				mensagem = "A posição do número 10 nessa lista é: " + posicao;
			}else{
				mensagem = "Não foi encontrado nenhum número 10.";
			}
			JOptionPane.showMessageDialog(null,mensagem);
		}
	}
}
