package lista03;
/*
 * Crie um algoritmo onde o usu�rio ir� informar dez n�meros distintos, 
 * ap�s informados dever�o ser exibidos os n�meros informados, 
 * s� que na ordem contr�ria.
 */

import javax.swing.*;

public class Ex02 {

	public static void main(String[] args) {
		String mensagem="",entrada="";
		boolean sair=false, cancelar=false;
		double[] numeros= new double[10];
		
		for(int ix=numeros.length-1;ix>=0;ix--){
			sair=false;
		    while(!sair && !cancelar){
                mensagem = "Informe o n�mero " + (numeros.length-ix);
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    cancelar = true;              
                }else{
                    try{
                        numeros[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Insira um valor num�rico!");
                    }
                }
		    }
		}
		if(!cancelar){
			mensagem = "Lista invertida: \n";
			for(double ix: numeros){
				mensagem += ix + "\n";
			}
			JOptionPane.showMessageDialog(null,mensagem);
		}

	}

}
