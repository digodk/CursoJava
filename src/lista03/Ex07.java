package lista03;

import java.util.Arrays;

import javax.swing.JOptionPane;

/*
 * Peça sete números inteiros e armazene no vetor.
 * O vetor não poderá aceitar números repetidos.
 * Após informados os sete números exiba cada um deles.
 */
public class Ex07 {

	public static void main(String[] args) {
		String mensagem, entrada="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		int ix = 0;
		double numero=0;
		boolean repetido = false, cancelar=false;
		double[] vetorNumeros = new double[7];

		mensagem = "Insira um número não repetido";
		while(ix < vetorNumeros.length && !cancelar){
			
			try{
				entrada = JOptionPane.showInputDialog(mensagem);
				
				if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;
	                
				}else{
					numero = Double.parseDouble(entrada);
					
					for(int jx = 0; ix< vetorNumeros.length;ix++){
						if(numero == vetorNumeros[jx]){
							JOptionPane.showMessageDialog(null, "Esse valor já foi digitado!");
							repetido = true;
						}
					}
					if(!repetido){vetorNumeros[ix] = numero;}
				}
			}catch (NumberFormatException exc){
				JOptionPane.showMessageDialog(null, "Digite um valor numérico!");
				
			}catch(Exception exc){
				exc.printStackTrace();
				cancelar=true;
			}
			if(!repetido){ix++;}
		}
		
		if(!cancelar){
			Arrays.sort(vetorNumeros);
			mensagem = "Aqui está a lista de números digitada: \n" + linha;
			for(ix = 0;ix<vetorNumeros.length;ix++){
				mensagem += vetorNumeros[ix];
			}
		}
	}

}
