package lista03;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/*
 * Crie um algoritmo de perguntas e respostas.
 * Deverá ser pedido o nome e realizada dez perguntas contendo SIM ou NÃO.
 * Após informadas as dez respostas exibir quantos acertos e erros foram feitos.
 * Exiba a pergunta e a resposta correta daquelas que foram erradas.
 * Após informado a quantidade de erros e acertos pergunte se mais alguém queira jogar.
 * Poderá participar até dez jogadores, o jogo finaliza após dez pessoas jogarem
 * ou não quiser mais jogar.
 * Assim que finalizar o jogo exiba um ranking com o nome do jogador e a quantidade de acertos.
 * Ordene esse ranking pela quantidade de acertos.
 */
public class Ex06 {

	public static void main(String[] args) {
		String mensagem ="", mensagemResultado = "", entrada="", nome="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		int ix=0, numAcertos=0, numNomes=0;
		boolean sair=false, cancelar=false;
		String[] gabarito = new String[10];
		String[] questoes = new String[10];
		Object[] opcoesRespostas = {"Sim","Não"};
		List<String> listaNomes = new ArrayList<>();
		List<Integer> listaAcertos = new ArrayList<>();
		
		while(!cancelar&&ix<gabarito.length){
			mensagem = "Insira o enunciado da questão " + (ix+1);
			try{
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoesRespostas, 1).toString();
				if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;
				}else{questoes[ix] = entrada;}
			}catch(Exception exc){
				cancelar=true;
			}
			mensagem = "Insira a resposta para a questão " + (ix+1);
			try{
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoesRespostas, 1).toString();
				gabarito[ix] = entrada;
			}catch(Exception exc){
				cancelar=true;
			}
			ix++;
		}

		while(!cancelar&&!sair){
			mensagem = "Insira o nome do participante";
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
			mensagemResultado = "";
			while(!cancelar&&ix<gabarito.length){
				sair=false;
				mensagem = "Questão " + (ix+1) + ": " + questoes[ix+1] + ".\n" +
						"Qual a sua resposta?";
				try{
					entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
					        null,opcoesRespostas, 1).toString();
					if(entrada.equals(gabarito[ix])){
						numAcertos++;
					}else{
						mensagemResultado += String.format("-Questão %1$s: %2$s./n" + "Resposta correta: %3$s.\n",
							(ix+1),questoes[ix],gabarito[ix]);
					}
				}catch(Exception exc){
					cancelar=true;
				}
				ix++;
			}
			if(!cancelar){
				sair=false;
				numNomes++;
				if(listaAcertos.size()>0){
					for(int jx=0;jx<listaAcertos.size();jx++){
						if(listaAcertos.get(jx)< numAcertos){
							listaAcertos.add(jx, numAcertos);
							listaNomes.add(jx, nome);
							sair=true;
							break;
						}
						
					}
				}
				
				if(!sair){
					listaAcertos.add(numAcertos);
					listaNomes.add(nome);
				}else{
					sair=false;
				}
				mensagem = nome + ", você acertou " + numAcertos + " \n" + 
						"Abaixo segue uma lista das questões que você errou." + linha;
				mensagem += mensagemResultado;
				JOptionPane.showMessageDialog(null, mensagem);
				if(numNomes>=10){
					sair=true;
				}else{
					mensagem = "Deseja jogar novamente?";
					if (JOptionPane.showConfirmDialog(null, mensagem, "",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
						sair=true;
					}
				}
			}
		}
		
		if(!cancelar){
			mensagem = "Resumo do desempenho dos participantes: \n" + linha;
			for(ix=0;ix<listaAcertos.size();ix++){
				nome = listaNomes.get(ix);
				mensagem += String.format("Participante: %1$s, acertos: %2$s \n", 
						nome, listaAcertos.get(ix));
			}
			JOptionPane.showMessageDialog(null, mensagem);
		}

	}

}
