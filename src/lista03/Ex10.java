package lista03;
/*
 * Criar uma agenda. Nessa agenda deverá ter as seguintes opções:
 * a. Cadastrar contatos
 * b. Alterar contatos
 * c. Excluir contatos
 * d. Pesquisar contatos
 * e. Cadastrar categoria
 * f. Alterar categoria
 * g. Excluir categoria
 * h. Lembrete de aniversário
 * 
 * a. Para o cadastro será pedido: 
 * Nome, sobrenome, data de nascimento (separe em três vetores), e-mail, celular, 
 * endereço e categoria. Valide os campos, é obrigatório tudo estar preenchido.
 * 
 * b. Através do nome do contato serão pedidos novamente os dados para realizar a alteração.
 * 
 * c. Através do nome será pedido para excluir o contato. 
 * Peça para confirmar se realmente deseja alterar o contato.
 * Valida os campos, pois tudo deve estar preenchido.
 * 
 * d. Pesquisar com contatos através dos nomes.
 * 
 * e. Cadastrar categoria permite que você crie uma categoria nova.
 * Cada contato obrigatoriamente precisa ter uma categoria. O nome da categoria deve ser
 * diferente de vazio.
 * 
 * f. Para alterar a categoria será pedido o nome, em seguida pedido o novo nome. 
 * O nome da categoria precisa ser diferente de vazio.
 * 
 * g. Excluir categoria será pedido o nome da categoria. 
 * Caso exista alguém cadastrado nessa categoria você não conseguirá alterar o nome dela. 
 * Em outras palavras vc não pode ter nenhum contato cadastrado para realizar a exclusão da categoria, 
 * pois os contatos não podem ficar sem categoria.
 * h. Para o lembrete de aniversário será pedido o mês que a pessoa quer saber os aniversariantes. Exiba o nome completo, dia, mês e ano de nascimento.
 */

 /*
 * Usa duas classes: 
 * -Agenda armazena todas as informações de contatos fornecidas pelo usuário e realiza as operações
 * como buscar nome de um contato, excluir contato, etc.
 * -Contato é uma classe auxiliar apenas para estruturar os dados em um nome só.
 */

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import lista03.Agenda;
import lista03.Contato;

public class Ex10 {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();
		Contato contato = new Contato();
		String mensagem="", entrada="";
		String nome="", sobrenome="", email="", endereco="", categoria="", celular="",
				diaAniver = "", mesAniver="", anoAniver="";
		String linha = new String(new char[20]).replace("\0", "-") + "\n \n"; //Auxiliar para inserir uma linha no texto
		String listaOpcoes = "";
		int escolha = 0;
		boolean sair = false, terminar=false;
		Object[] opcoes = {"a. Cadastrar contatos","b. Alterar contatos","c. Excluir contatos",
				 "d. Pesquisar contatos","e. Cadastrar categoria","f. Alterar categoria",
				 "g. Excluir categoria","h. Lembrete de aniversário"};
		Object[] meses = {"1","2","3","4","5","6","7","8","10","11","12"};
		ArrayList<Contato> aniversariantes = new ArrayList<>();
		
		//String com lista de opções para caixa de seleção de operação
		for(int ix=0;ix<opcoes.length;ix++){
			listaOpcoes += opcoes[ix].toString() + "\n";
		}
		
		while(true){
			//Tela inicial. Seleciona uma opção. Caso o usuário clicar em cancelar, termina o programa.
			try{
				mensagem = "Selecione a opção desejada: \n" + linha + listaOpcoes;
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoes, 1).toString();
				//escolha é usada para determinar a opção selecionada pelo usuário
				escolha = Arrays.asList(opcoes).indexOf(entrada);
			}catch(Exception exc){
				break;
			}
			terminar=false;
			switch(escolha){
			case 0:
				//Adicionar contato. Caso não hajam categorias a função não é executada.
				if(agenda.NumCategorias()==0){
					mensagem = "Atenção! \nNão existem categorias cadastradas! \n"+ 
							"É necessário cadastrar uma categoria antes de cadastrar um contato!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				
				mensagem = "Insira o nome do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					nome = entrada;
				}
				
				mensagem = "Insira o sobrenome do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					sobrenome = entrada;
				}
				//Datas de nascimento são armazenadas em três variáveis diferentes (em texto).
				//Não é feito validação de número.
				mensagem = "Insira o dia de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					diaAniver = entrada;
				}
				//O mês do aniversário é selecionado uma lista.
				mensagem = "Insira o mês de nascimento do Contato";
				try{
					mensagem = "Selecione a opção desejada: \n" + linha + listaOpcoes;
					entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
					        null,meses, 1).toString();
				}catch(Exception exc){
					break;
				}
					
				mensagem = "Insira o ano de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					anoAniver = entrada;
				}
				
				mensagem = "Insira o e-mail do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					email = entrada;
				}
				
				mensagem = "Insira o endereço do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					endereco = entrada;
				}
				//Também não é realizada uma validação para o celular
				mensagem = "Insira o celular do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					celular = entrada;
				}
				//Entrada da categoria. Repete a solicitação até informar uma categoria
				//cadastrada ou a operação seja cancelada.
				//Se a operação foi cancelada, retorna à tela inicial.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Insira a categoria do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else if(agenda.ExisteCategoria(entrada)){
						categoria = entrada;
						sair=true;
					}else{
						mensagem = "Essa categoria não está cadastrada!";
						JOptionPane.showMessageDialog(null, mensagem);
					}
				}
				if(terminar){break;}
				agenda.Cadastrar(nome, sobrenome, email, endereco, diaAniver, mesAniver, anoAniver, categoria, celular);
				mensagem = "Cadastro realizado.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 1:
				//Alteração de cadastros. Caso não haja contato cadastrado a operação é cancelada.
				if(agenda.NumContatos()==0){
					mensagem = "Atenção! \nNão existem contatos cadastrados! \n"+ 
							"É necessário ao menos um contato antes de realizar uma alteração!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Repete a solicitação do nome do contato enquanto o nome fornecido não estiver cadastrado.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Insira o nome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
						break;
					}else{
						nome = entrada;
					}
					
					mensagem = "Insira o sobrenome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
						break;
					}else{
						sobrenome = entrada;
					}
					
					if(!agenda.ExisteContato(nome, sobrenome)){
						mensagem = "Não há um contato cadastrado com esse nome.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{sair=true;}
				}
				if(terminar){break;}
					
				mensagem = "Insira o dia de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					diaAniver = entrada;
				}
				
				mensagem = "Insira o mês de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					mesAniver = entrada;
				}
				
				mensagem = "Insira o ano de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					anoAniver = entrada;
				}
				
				mensagem = "Insira o e-mail do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					email = entrada;
				}
				
				mensagem = "Insira o endereço do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					endereco = entrada;
				}
				
				mensagem = "Insira o celular do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					celular = entrada;
				}
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Insira o celular do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else if(agenda.ExisteCategoria(entrada)){
						categoria = entrada;
						sair=true;
					}else{
						mensagem = "Essa categoria não está cadastrada!";
						JOptionPane.showMessageDialog(null, mensagem);
					}
				}
				if(terminar){break;}
					
				agenda.AlterarContato(nome, sobrenome, email, endereco, diaAniver, mesAniver, anoAniver, categoria, celular);
				mensagem = "Novos dados gravados com sucesso.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
				
			case 2:
				//Exclusão de contatos. Caso não hajam contatos cadastrados a operção é cancelada.
				if(agenda.NumContatos()==0){
					mensagem = "Atenção! \nNão existem contatos cadastrados! \n"+ 
							"É necessário ao menos um contato antes de excluir algum!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Repete a solicitação do nome do contato enquanto o nome fornecido não estiver cadastrado.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Insira o nome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
						break;
					}else{
						nome = entrada;
					}
					
					mensagem = "Insira o sobrenome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
						break;
					}else{
						sobrenome = entrada;
					}
					
					if(!agenda.ExisteContato(nome, sobrenome)){
						mensagem = "Não há um contato cadastrado com esse nome.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{sair=true;}
				}
				if(terminar){break;}
					
				agenda.ExcluirContato(nome, sobrenome);
				mensagem = "Contato excluído.";
				JOptionPane.showMessageDialog(null, mensagem);
			case 3:
				//Pesquisa de contato.
				if(agenda.NumContatos()==0){
					mensagem = "Atenção! \nNão existem contatos cadastrados! \n"+ 
							"É necessário ao menos um contato antes de pesquisar algum!";
					JOptionPane.showMessageDialog(null, mensagem);
					terminar=true;
				}
					
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Insira o nome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else{
						nome = entrada;
					}
					
					mensagem = "Insira o sobrenome do Contato";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else{
						sobrenome = entrada;
					}
					
					if(!agenda.ExisteContato(nome, sobrenome)){
						mensagem = "Não há um contato cadastrado com esse nome.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{sair=true;}
				}
				if(terminar){break;}
					
				contato = agenda.EncontrarContato(nome, sobrenome);
				mensagem = "Informações do contato: \n" + linha;
				mensagem += String.format("Nome completo: %1$s \n \n Nascimento: %2$s \n e-mail: %3$s \n",
						contato.nome + " " + contato.sobrenome,
						contato.diaAniver + "\\" + contato.mesAniver + "\\" + contato.anoAniver,
						contato.email);
				mensagem += String.format("Endereço: %1$s \n Categoria: %2$s", contato.endereco, contato.categoria);
				JOptionPane.showMessageDialog(null, mensagem);				
				break;
			case 4:
				//Cadastro de categoria de contato
				mensagem = "Digite o nome da categoria a ser adicionada.";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					categoria = entrada;
				}
				
				if(agenda.ExisteCategoria(categoria)){
					mensagem = "Essa categoria já existe.";	
				}else{
					agenda.CadastrarCategoria(categoria);
					mensagem = "Cadastro realizado.";
				}
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 5:
				//Alteração de categoria. Caso não hajam categorias cadastradas cancela a operação.
				if(agenda.NumCategorias()==0){
					mensagem = "Não há categorias cadastradas!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Solicita a categoria a ser alterada.
				//Caso a categoria informada não esteja cadastrada então repete a operação.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Digite o nome da categoria a ser alterada.";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar=true;
						break;
					}else{
						categoria = entrada;
						if(!agenda.ExisteCategoria(categoria)){
							mensagem = "Essa categoria não está cadastrada.";
							JOptionPane.showMessageDialog(null, mensagem);
						}else{
							//Solicita o novo nome na categoria
							//Pensando bem, deveria haver uma checagem se já existe uma categoria
							//com o novo nome antes de fazer a alteração.
							mensagem = "Digite o novo nome da categoria";
							entrada = JOptionPane.showInputDialog(mensagem);
							if (entrada == null || (entrada!= null && entrada.equals(""))){
								terminar=true;
							}else{
								agenda.AlterarCategoria(categoria, entrada);
								sair=true;
							}
						}
					}
				}
				if(terminar){break;}
				mensagem = "Alteração realizada.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 6:
				//Exclusão de categoria, caso não hajam categorias cadastradas, cancela a operação.
				if(agenda.NumCategorias()==0){
					mensagem = "Não há categorias cadastradas!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Solicita o nome da categoria a ser excluída. Se não for uma categoria válida
				//repete a operação.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Digite o nome da categoria a ser excluída.";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else if(!agenda.ExisteCategoria(categoria)){
						mensagem = "Essa categoria não existe.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{
						categoria = entrada;
						sair=true;
					}
				}
				if(terminar){break;}

				agenda.RemoverCategoria(categoria);
				mensagem = "Categoria removida.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 7:
				//Lista todos os aniversariantes de um mês. A lista de aniversariantes é
				//Salva em uma coleção de objetos Contato, que são usados para criar
				//a lista.
				try{
					mensagem = "Selecione o mês para listar os aniversariantes.";
					entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
					        null,meses, 1).toString();
					aniversariantes = agenda.ListarAniversarios(entrada);
				}catch(Exception exc){
					break;
				}
				
				if(aniversariantes.size()==0){
					mensagem = "Não há aniversariantes nesse mês";
				}else{
					mensagem = "Aniversariantes nesse mês: \n" + linha;
					for(Contato c : aniversariantes){
						mensagem += String.format("Nome completo : %1$s \nAniversário: %2$s",
							c.nome + " " + c.sobrenome, c.diaAniver + "\\" + c.mesAniver + "\\" + c.anoAniver);
					}
				}
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			}
		}
	}

}