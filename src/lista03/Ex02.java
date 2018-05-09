package lista03;
/*
 * Crie um algoritmo onde o usuário irá informar dez números distintos, 
 * após informados deverão ser exibidos os números informados, 
 * só que na ordem contrária.
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
                mensagem = "Informe o número " + (numeros.length-ix);
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    cancelar = true;              
                }else{
                    try{
                        numeros[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Insira um valor numérico!");
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
