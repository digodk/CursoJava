package lista03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

/*
 * Crie um algoritmo com as seguintes op��es:
 * a. Cadastrar produto � Pedir o nome do produto, valor e quantidade em estoque.
 * b. Listar produtos � Exibir o nome do produto, valor e quantidade
 * c. Alterar produto � Pedir o nome do produto, ap�s informado pedir um nome, 
 * valor e estoque para atualizar.
 * d. Excluir produto � Atrav�s do nome excluir o produto do vetor.
 * Fa�a com que sejam cadastrados at� 20 produtos no vetor.
 * Caso haja mais de 20 produtos n�o poder� ser cadastrado.
 */
public class Ex09 {
	public static void main(String[] args) {
		final int maxProdutos = 20;
		String mensagem = "", entrada = "", nomeProduto= "";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n";
		double valorProduto=0;
		int opcaoEscolhida=-1, numProdutos=0, qtdProduto=0, indice=0;
		boolean sair=false,cancelar=false;
		List<String> nomesProdutos = new ArrayList<>();
		List<Double> valoresProdutos = new ArrayList<>();
		List<Integer> qtdsProdutos = new ArrayList<>();
		Object[] opcoes = {"Cadastrar produto", "Listar Produtos", "Alterar produto", "Excluir produto"};
		
		while(!cancelar){
			mensagem = "Selecione uma op��o";
			try{
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoes, 1).toString();
				if (entrada == null || (entrada!= null && entrada.equals(""))){
	                cancelar = true;
				}else{
					opcaoEscolhida = Arrays.asList(opcoes).indexOf(entrada);
				}
			}catch(Exception exc){
				cancelar=true;
			}
			
			if(!cancelar){
				switch(opcaoEscolhida){
				case 0:
					if(numProdutos == maxProdutos){
						mensagem = "Limite de produtos cadastrados atingido," + 
								" n�o � poss�vel realizar um novo cadastro sem antes remover algum item.";
						break;
					}
					sair = false;
					while(!cancelar && !sair){
						mensagem = "Insira o nome do novo produto";
						entrada = JOptionPane.showInputDialog(mensagem);
						if (entrada == null || (entrada!= null && entrada.equals(""))){
			                cancelar = true;
						}else{
							indice = nomesProdutos.indexOf(entrada);
							if(indice>=0){
								mensagem = "Esse produto j� est� cadastrado!";
								JOptionPane.showMessageDialog(null, mensagem);
							}else{
								nomeProduto = entrada;
								sair=true;
							}
						}
					}
					sair=false;
					while(!sair && !cancelar){
						mensagem = "Insira o pre�o (R$) do novo produto";
						entrada = JOptionPane.showInputDialog(mensagem);
						if (entrada == null || (entrada!= null && entrada.equals(""))){
			                cancelar = true;
						}else{
							try{
								valorProduto = Double.parseDouble(entrada);
								if(valorProduto < 0){
									mensagem = "Insira um valor maior ou igual a 0!";
									JOptionPane.showMessageDialog(null, mensagem);
								}else{
									sair = true;
								}
							}catch(NumberFormatException exc){
								mensagem = "Insira um valor num�rico!";
								JOptionPane.showMessageDialog(null, mensagem);
							}
						}
					}
					sair=false;
					while(!sair && !cancelar){
						mensagem = "Insira a quantidade em estoque do novo produto";
						entrada = JOptionPane.showInputDialog(mensagem);
						if (entrada == null || (entrada!= null && entrada.equals(""))){
			                cancelar = true;
						}else{
							try{
								qtdProduto = Integer.parseInt(entrada);
								if(qtdProduto < 0){
									mensagem = "Insira um valor maior ou igual a 0!";
									JOptionPane.showMessageDialog(null, mensagem);
								}else{
									sair = true;
								}
							}catch(NumberFormatException exc){
								mensagem = "Insira um valor inteiro!";
								JOptionPane.showMessageDialog(null, mensagem);
							}
						}
					}
					if(!cancelar){
						qtdsProdutos.add(qtdProduto);
						valoresProdutos.add(valorProduto);
						nomesProdutos.add(nomeProduto);
						nomeProduto = new String("");
						numProdutos++;
					}
					break;
				case 1:
					if(numProdutos==0){
						mensagem = "N�o h� produtos cadastrados!";
					}else{
						mensagem = "Abaixo est� a lista de produtos cadastrados at� o momento: \n" + linha;
						for(int ix = 0;ix<numProdutos;ix++){
							mensagem += String.format("-Nome: %1$s; Valor: %2$.2f R$; Quantidade: %3$s \n",
									nomesProdutos.get(ix), valoresProdutos.get(ix), qtdsProdutos.get(ix));
						}
					}
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				case 2:
					if(numProdutos==0){
						mensagem = "N�o h� produtos cadastrados!";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{
						sair = false;
						while(!sair && ! cancelar){
							mensagem = "Insira o nome do produto a alterar\n" +
									"Abaixo est� uma lista dos nomes cadastrados at� o momento \n" + linha +
									nomesProdutos.toString();
							entrada = JOptionPane.showInputDialog(mensagem);
							if (entrada == null || (entrada!= null && entrada.equals(""))){
				                cancelar = true;
							}else{
								indice = nomesProdutos.indexOf(entrada);
								if(indice>=0){
									sair = true;
								}else{
									mensagem = "Insira um nome v�lido!";
									JOptionPane.showMessageDialog(null, mensagem);
								}
							}
						}
						sair=false;
						while(!sair && !cancelar){
							mensagem = "Insira o novo pre�o (R$) do produto";
							entrada = JOptionPane.showInputDialog(mensagem, valoresProdutos.get(indice));
							if (entrada == null || (entrada!= null && entrada.equals(""))){
				                cancelar = true;
							}else{
								try{
									valorProduto = Double.parseDouble(entrada);
									if(valorProduto < 0){
										mensagem = "Insira um valor maior ou igual a 0!";
										JOptionPane.showMessageDialog(null, mensagem);
									}else{
										sair = true;
									}
								}catch(NumberFormatException exc){
									mensagem = "Insira um valor num�rico!";
									JOptionPane.showMessageDialog(null, mensagem);
								}
							}
						}
						sair=false;
						while(!sair && !cancelar){
							mensagem = "Insira a nova quantidade em estoque do produto";
							entrada = JOptionPane.showInputDialog(mensagem, qtdsProdutos.get(indice));
							if (entrada == null || (entrada!= null && entrada.equals(""))){
				                cancelar = true;
							}else{
								try{
									qtdProduto = Integer.parseInt(entrada);
									if(qtdProduto < 0){
										mensagem = "Insira um valor maior ou igual a 0!";
										JOptionPane.showMessageDialog(null, mensagem);
									}else{
										
										sair = true;
									}
								}catch(NumberFormatException exc){
									mensagem = "Insira um valor inteiro!";
									JOptionPane.showMessageDialog(null, mensagem);
								}
							}
						}
						if(!cancelar){
							valoresProdutos.set(indice, valorProduto);
							qtdsProdutos.set(indice, qtdProduto);
						}
					}
					break;
				case 3:
					if(numProdutos==0){
						mensagem = "N�o h� produtos cadastrados!";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{
						sair = false;
						while(!sair && ! cancelar){
							mensagem = "Insira o nome do produto a ser removido\n" +
									"Abaixo est� uma lista dos nomes cadastrados at� o momento \n" + linha +
									nomesProdutos.toString();
							entrada = JOptionPane.showInputDialog(mensagem);
							if (entrada == null || (entrada!= null && entrada.equals(""))){
				                cancelar = true;
							}else{
								indice = nomesProdutos.indexOf(entrada);
								if(indice>=0){
									sair = true;
									nomesProdutos.remove(indice);
									valoresProdutos.remove(indice);
									qtdsProdutos.remove(indice);
								}else{
									mensagem = "Insira um nome v�lido!";
									JOptionPane.showMessageDialog(null, mensagem);
								}
							}
						}
					}
					break;
				}
			}
		}
	}
}
