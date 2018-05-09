package lista03;

/*
 * Criar um sistema de login e senha.
 * Crie um vetor contendo três usuários e um vetor contendo três senhas.
 * Enquanto não for digitado o usuário e a senha correspondentes ao índice dos dois vetores
 * deverá aparecer a mensagem: Usuário ou senha incorretos,
 * caso contrário exiba: Login realizado com sucesso!.
 */

import javax.swing.JOptionPane;

public class Ex08 {

	public static void main(String[] args) {
		String mensagem = "", entrada = "", usuario="", senha="";
		boolean login = false, cancelar=false;
		String[] usuarios = {"Diogo", "Alice", "Bob"};
		String[] senhas = {"111","222","333"};
		
		while(!login && !cancelar){
			mensagem = "Informe seu usuário";
			try{
				entrada = JOptionPane.showInputDialog(mensagem);
				
				if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;
	                
				}else{
					usuario = entrada;
				}
			}catch(Exception exc){
				exc.printStackTrace();
				cancelar=true;
			}
			if(!cancelar){
				mensagem = "Informe sua senha";
				try{
					entrada = JOptionPane.showInputDialog(mensagem);
					
					if (entrada == null || (entrada!= null && entrada.equals(""))){
		                cancelar = true;
		                
					}else{
						senha = entrada;
					}
				}catch(Exception exc){
					cancelar=true;
				}
				
				for(int ix = 0; ix<usuarios.length;ix++){
					if(usuario.toLowerCase().equals(usuarios[ix].toLowerCase())){
						if(senha.equals(senhas[ix])){
							login = true;
						}
					}
				}
			}
			if(!cancelar){
				
				if(!login){
					mensagem = "Usuário ou senha incorretos.";
				}else{
					mensagem = "Login realizado com sucesso!";
				}
				JOptionPane.showMessageDialog(null, mensagem);
			}
		}
	}
}
