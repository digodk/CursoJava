package lista03;
import java.util.Arrays;

/*
 * Desenvolva um algoritmo que ter� dois vetores, 
 * no primeiro vetor ser�o pedidos dez n�meros, 
 * em seguida ser�o pedidos mais dez n�meros e armazenados no segundo vetor.
 * Ap�s armazenados os vetores dever� ser pedido para o usu�rio informar um c�lculo a 
 * ser executado, as op��es de c�lculo ser�o:
 * a) Somar
 * b) Subtrair
 * c) Multiplicar
 * d) Dividir
 * Ap�s informado o tipo de c�lculo dever� ser realizado o mesmo com a 
 * posi��o de cada vetor.
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
                mensagem = "Opera��o " + (ix+1) + ", digite o primeiro n�mero da opera��o";
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    terminar = true;              
                }else{
                    try{
                        valores1[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Digite um n�mero inteiro!");
                    }
                }
            }
            sair=false;
            while(!terminar && !sair){
                mensagem = "Opera��o " + (ix+1) + ", digite o segundo n�mero da opera��o";
                entrada = JOptionPane.showInputDialog(mensagem);
                if (entrada == null || (entrada!= null && entrada.equals(""))){
                    terminar = true;              
                }else{
                    try{
                        valores2[ix] = Double.parseDouble(entrada);
                        sair = true;
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null, "Digite um n�mero inteiro!");
                    }
                }
            }
		}
		
		if(!terminar){
			try{
		        mensagem = "Informe a opera��o.";
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
			
			mensagem = "Resultado de cada opera��o: \n\n";
			for(int ix = 0;ix<valores1.length;ix++){
				mensagem += String.format("Opera��o %1$s, resultado: %2$.2f. \n",ix,resultado[ix]);
			}
			JOptionPane.showMessageDialog(null,mensagem);
					
		}
	}

}
