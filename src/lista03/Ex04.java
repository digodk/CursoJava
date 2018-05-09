package lista03;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/*
 * Criar um algoritmo para cria��o de provas. 
 * De imediato ser� pedido o gabarito da prova que ser� composta por dez quest�es, 
 * as respostas de cada quest�o poder�o ser A, B, C e D, 
 * se por ventura for digitado algum termo diferente dessas quatro letras dever� ser 
 * pedido novamente a quest�o. 
 * Assim que o gabarito estiver dispon�vel informe o nome de um aluno, 
 * enquanto o nome desse aluno for diferente de SAIR dever� ser pedido o nome 
 * e as dez quest�es respondidas pelo aluno 
 * (valide as quest�es caso n�o sejam informados A, B, C, D). 
 * Assim que obter as dez respostas do aluno informe quantas ele acertou e quantas errou 
 * (apenas quantidade). 
 * Quando digitado SAIR dever� informar o nome e a quantidade de acertos de cada aluno, 
 * fa�a com que a ordem seja da maior quantidade de acertos at� a menor.
 */
public class Ex04 {
	public static void main(String[] args) {
		String mensagem ="", entrada="", nome="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		int ix=0, numAcertos=0;
		boolean sair=false, cancelar=false;
		String[] gabarito = new String[10];
		String[] aluno = new String[2];
		Object[] opcoesRespostas = {"A","B","C","D"};
		List<String[]> listaAlunos = new ArrayList<>();
		
		while(!cancelar&&ix<gabarito.length){
			mensagem = "Insira a resposta para a quest�o " + (ix+1);
			try{
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoesRespostas, 1).toString();
				gabarito[ix] = entrada;
			}catch(Exception exc){
				cancelar=true;
			}
			ix++;
		}
		sair=false;
		while(!cancelar&&!sair){
			mensagem = "Insira o nome do aluno";
			entrada = JOptionPane.showInputDialog(mensagem);
			if (entrada == null || (entrada!= null && entrada.equals(""))){
                cancelar = true;
			}else if(entrada.toLowerCase().equals("sair")){
				cancelar=true;
			}else{
				nome=entrada;
				numAcertos =0;
			}
			ix=0;
			while(!cancelar&&ix<gabarito.length){
				sair=false;
				mensagem = "Insira a resposta do aluno para a quest�o " + (ix+1);
				try{
					entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
					        null,opcoesRespostas, 1).toString();
					if(entrada.equals(gabarito[ix])){
						numAcertos++;
					}
				}catch(Exception exc){
					cancelar=true;
				}
				ix++;
			}
			if(!cancelar){
				sair=false;
				aluno = new String[2];
				aluno[0]=nome;
				aluno[1]=Integer.toString(numAcertos);
				if(listaAlunos.size()>=1){
					for(int jx=0;jx<listaAlunos.size();jx++){
						if(Integer.parseInt(listaAlunos.get(jx)[1])<numAcertos){
							listaAlunos.add(jx, aluno);
							sair=true;
							break;
						}
					}
					if(!sair){
						listaAlunos.add(aluno);
					}
				}else{
					listaAlunos.add(aluno);
				}
			}
			sair=false;
			if(!cancelar){
				mensagem = "Deseja cadastrar outro aluno?";
				if (JOptionPane.showConfirmDialog(null, mensagem, "",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					sair=true;
				}
			}
		}
		
		if(!cancelar){
			mensagem = "Resumo do desempenho dos alunos: \n" + linha;
			for(ix=0;ix<listaAlunos.size();ix++){
				aluno = listaAlunos.get(ix);
				mensagem += String.format("Aluno: %1$s, acertos: %2$s \n", aluno[0], Integer.parseInt(aluno[1]));
			}
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
