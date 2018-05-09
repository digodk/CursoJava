package lista03;
import java.util.Arrays;

/*
 * Desenvolva um algoritmo que terá dois vetores, 
 * no primeiro vetor serão pedidos dez números, 
 * em seguida serão pedidos mais dez números e armazenados no segundo vetor.
 * Após armazenados os vetores deverá ser pedido para o usuário informar um cálculo a 
 * ser executado, as opções de cálculo serão:
 * a) Somar
 * b) Subtrair
 * c) Multiplicar
 * d) Dividir
 * Após informado o tipo de cálculo deverá ser realizado o mesmo com a 
 * posição de cada vetor.
 */
import javax.swing.*;

public class Ex01 {

	public static void main(String[] args) {
		String mensagem = "", entrada = "";
		int operacao=-1;
		boolean sair = false, terminar=false;
		double[] valores1=new double[10], valores2=new double[10], resultado = new double[10];
		Object[] opcoesCalculos = {"Somar", "Subtrair","Multiplicar","Dividir"};
		
		for(int ix = 0; ix<valores1.length;ix++){
			sair=false;
            while(!terminar && !sair){
                mensagem = "Operação " + (ix+1) + ", digite o primeiro número da operação";
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    terminar = true;              
                }else{
                    try{
                        valores1[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Digite um número inteiro!");
                    }
                }
            }
            sair=false;
            while(!terminar && !sair){
                mensagem = "Operação " + (ix+1) + ", digite o segundo número da operação";
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    terminar = true;              
                }else{
                    try{
                        valores2[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Digite um número inteiro!");
                    }
                }
            }
		}
		
		if(!terminar){
			try{
		        mensagem = "Informe a operação.";
		        entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
		        null,opcoesCalculos, 1).toString();
		        if (entrada == null || (entrada!= null && entrada.equals(""))){
		            terminar = true;              
		        }else{
		            operacao = Arrays.asList(opcoesCalculos).indexOf(entrada);
		        }
			}catch(Exception ex){
				terminar = true;
			}
        }
		
		if(!terminar){
			switch(operacao){
				case 0:
					for(int ix = 0;ix<valores1.length;ix++){
						resultado[ix] = valores1[ix]+valores2[ix];
					}
					break;
				case 1:
					for(int ix = 0;ix<valores1.length;ix++){
						resultado[ix] = valores1[ix]-valores2[ix];
					}
					break;
				case 2:
					for(int ix = 0;ix<valores1.length;ix++){
						resultado[ix] = valores1[ix]*valores2[ix];
					}
					break;
				case 3:
					for(int ix = 0;ix<valores1.length;ix++){
						resultado[ix] = valores1[ix]/valores2[ix];
					}				
			}
			
			mensagem = "Resultado de cada operação: \n\n";
			for(int ix = 0;ix<valores1.length;ix++){
				mensagem += String.format("Operação %1$s, resultado: %2$.2f. \n",ix,resultado[ix]);
			}
			JOptionPane.showMessageDialog(null,mensagem);
					
		}
	}

}
