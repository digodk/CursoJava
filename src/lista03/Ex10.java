package lista03;
/*
 * Criar uma agenda. Nessa agenda dever� ter as seguintes op��es:
 * a. Cadastrar contatos
 * b. Alterar contatos
 * c. Excluir contatos
 * d. Pesquisar contatos
 * e. Cadastrar categoria
 * f. Alterar categoria
 * g. Excluir categoria
 * h. Lembrete de anivers�rio
 * 
 * a. Para o cadastro ser� pedido: 
 * Nome, sobrenome, data de nascimento (separe em tr�s vetores), e-mail, celular, 
 * endere�o e categoria. Valide os campos, � obrigat�rio tudo estar preenchido.
 * 
 * b. Atrav�s do nome do contato ser�o pedidos novamente os dados para realizar a altera��o.
 * 
 * c. Atrav�s do nome ser� pedido para excluir o contato. 
 * Pe�a para confirmar se realmente deseja alterar o contato.
 * Valida os campos, pois tudo deve estar preenchido.
 * 
 * d. Pesquisar com contatos atrav�s dos nomes.
 * 
 * e. Cadastrar categoria permite que voc� crie uma categoria nova.
 * Cada contato obrigatoriamente precisa ter uma categoria. O nome da categoria deve ser
 * diferente de vazio.
 * 
 * f. Para alterar a categoria ser� pedido o nome, em seguida pedido o novo nome. 
 * O nome da categoria precisa ser diferente de vazio.
 * 
 * g. Excluir categoria ser� pedido o nome da categoria. 
 * Caso exista algu�m cadastrado nessa categoria voc� n�o conseguir� alterar o nome dela. 
 * Em outras palavras vc n�o pode ter nenhum contato cadastrado para realizar a exclus�o da categoria, 
 * pois os contatos n�o podem ficar sem categoria.
 * h. Para o lembrete de anivers�rio ser� pedido o m�s que a pessoa quer saber os aniversariantes. Exiba o nome completo, dia, m�s e ano de nascimento.
 */

 /*
 * Usa duas classes: 
 * -Agenda armazena todas as informa��es de contatos fornecidas pelo usu�rio e realiza as opera��es
 * como buscar nome de um contato, excluir contato, etc.
 * -Contato � uma classe auxiliar apenas para estruturar os dados em um nome s�.
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
				 "g. Excluir categoria","h. Lembrete de anivers�rio"};
		Object[] meses = {"1","2","3","4","5","6","7","8","10","11","12"};
		ArrayList<Contato> aniversariantes = new ArrayList<>();
		
		//String com lista de op��es para caixa de sele��o de opera��o
		for(int ix=0;ix<opcoes.length;ix++){
			listaOpcoes += opcoes[ix].toString() + "\n";
		}
		
		while(true){
			//Tela inicial. Seleciona uma op��o. Caso o usu�rio clicar em cancelar, termina o programa.
			try{
				mensagem = "Selecione a op��o desejada: \n" + linha + listaOpcoes;
				entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
				        null,opcoes, 1).toString();
				//escolha � usada para determinar a op��o selecionada pelo usu�rio
				escolha = Arrays.asList(opcoes).indexOf(entrada);
			}catch(Exception exc){
				break;
			}
			terminar=false;
			switch(escolha){
			case 0:
				//Adicionar contato. Caso n�o hajam categorias a fun��o n�o � executada.
				if(agenda.NumCategorias()==0){
					mensagem = "Aten��o! \nN�o existem categorias cadastradas! \n"+ 
							"� necess�rio cadastrar uma categoria antes de cadastrar um contato!";
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
				//Datas de nascimento s�o armazenadas em tr�s vari�veis diferentes (em texto).
				//N�o � feito valida��o de n�mero.
				mensagem = "Insira o dia de nascimento do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					diaAniver = entrada;
				}
				//O m�s do anivers�rio � selecionado uma lista.
				mensagem = "Insira o m�s de nascimento do Contato";
				try{
					mensagem = "Selecione a op��o desejada: \n" + linha + listaOpcoes;
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
				
				mensagem = "Insira o endere�o do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					endereco = entrada;
				}
				//Tamb�m n�o � realizada uma valida��o para o celular
				mensagem = "Insira o celular do Contato";
				entrada = JOptionPane.showInputDialog(mensagem);
				if (entrada == null || (entrada!= null && entrada.equals(""))){
					break;
				}else{
					celular = entrada;
				}
				//Entrada da categoria. Repete a solicita��o at� informar uma categoria
				//cadastrada ou a opera��o seja cancelada.
				//Se a opera��o foi cancelada, retorna � tela inicial.
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
						mensagem = "Essa categoria n�o est� cadastrada!";
						JOptionPane.showMessageDialog(null, mensagem);
					}
				}
				if(terminar){break;}
				agenda.Cadastrar(nome, sobrenome, email, endereco, diaAniver, mesAniver, anoAniver, categoria, celular);
				mensagem = "Cadastro realizado.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 1:
				//Altera��o de cadastros. Caso n�o haja contato cadastrado a opera��o � cancelada.
				if(agenda.NumContatos()==0){
					mensagem = "Aten��o! \nN�o existem contatos cadastrados! \n"+ 
							"� necess�rio ao menos um contato antes de realizar uma altera��o!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Repete a solicita��o do nome do contato enquanto o nome fornecido n�o estiver cadastrado.
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
						mensagem = "N�o h� um contato cadastrado com esse nome.";
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
				
				mensagem = "Insira o m�s de nascimento do Contato";
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
				
				mensagem = "Insira o endere�o do Contato";
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
						mensagem = "Essa categoria n�o est� cadastrada!";
						JOptionPane.showMessageDialog(null, mensagem);
					}
				}
				if(terminar){break;}
					
				agenda.AlterarContato(nome, sobrenome, email, endereco, diaAniver, mesAniver, anoAniver, categoria, celular);
				mensagem = "Novos dados gravados com sucesso.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
				
			case 2:
				//Exclus�o de contatos. Caso n�o hajam contatos cadastrados a oper��o � cancelada.
				if(agenda.NumContatos()==0){
					mensagem = "Aten��o! \nN�o existem contatos cadastrados! \n"+ 
							"� necess�rio ao menos um contato antes de excluir algum!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Repete a solicita��o do nome do contato enquanto o nome fornecido n�o estiver cadastrado.
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
						mensagem = "N�o h� um contato cadastrado com esse nome.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{sair=true;}
				}
				if(terminar){break;}
					
				agenda.ExcluirContato(nome, sobrenome);
				mensagem = "Contato exclu�do.";
				JOptionPane.showMessageDialog(null, mensagem);
			case 3:
				//Pesquisa de contato.
				if(agenda.NumContatos()==0){
					mensagem = "Aten��o! \nN�o existem contatos cadastrados! \n"+ 
							"� necess�rio ao menos um contato antes de pesquisar algum!";
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
						mensagem = "N�o h� um contato cadastrado com esse nome.";
						JOptionPane.showMessageDialog(null, mensagem);
					}else{sair=true;}
				}
				if(terminar){break;}
					
				contato = agenda.EncontrarContato(nome, sobrenome);
				mensagem = "Informa��es do contato: \n" + linha;
				mensagem += String.format("Nome completo: %1$s \n \n Nascimento: %2$s \n e-mail: %3$s \n",
						contato.nome + " " + contato.sobrenome,
						contato.diaAniver + "\\" + contato.mesAniver + "\\" + contato.anoAniver,
						contato.email);
				mensagem += String.format("Endere�o: %1$s \n Categoria: %2$s", contato.endereco, contato.categoria);
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
					mensagem = "Essa categoria j� existe.";	
				}else{
					agenda.CadastrarCategoria(categoria);
					mensagem = "Cadastro realizado.";
				}
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 5:
				//Altera��o de categoria. Caso n�o hajam categorias cadastradas cancela a opera��o.
				if(agenda.NumCategorias()==0){
					mensagem = "N�o h� categorias cadastradas!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Solicita a categoria a ser alterada.
				//Caso a categoria informada n�o esteja cadastrada ent�o repete a opera��o.
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
							mensagem = "Essa categoria n�o est� cadastrada.";
							JOptionPane.showMessageDialog(null, mensagem);
						}else{
							//Solicita o novo nome na categoria
							//Pensando bem, deveria haver uma checagem se j� existe uma categoria
							//com o novo nome antes de fazer a altera��o.
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
				mensagem = "Altera��o realizada.";
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			case 6:
				//Exclus�o de categoria, caso n�o hajam categorias cadastradas, cancela a opera��o.
				if(agenda.NumCategorias()==0){
					mensagem = "N�o h� categorias cadastradas!";
					JOptionPane.showMessageDialog(null, mensagem);
					break;
				}
				//Solicita o nome da categoria a ser exclu�da. Se n�o for uma categoria v�lida
				//repete a opera��o.
				sair=false;
				while(!sair&&!terminar){
					mensagem = "Digite o nome da categoria a ser exclu�da.";
					entrada = JOptionPane.showInputDialog(mensagem);
					if (entrada == null || (entrada!= null && entrada.equals(""))){
						terminar = true;
					}else if(!agenda.ExisteCategoria(categoria)){
						mensagem = "Essa categoria n�o existe.";
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
				//Lista todos os aniversariantes de um m�s. A lista de aniversariantes �
				//Salva em uma cole��o de objetos Contato, que s�o usados para criar
				//a lista.
				try{
					mensagem = "Selecione o m�s para listar os aniversariantes.";
					entrada = JOptionPane.showInputDialog(null, mensagem,"",JOptionPane.PLAIN_MESSAGE,
					        null,meses, 1).toString();
					aniversariantes = agenda.ListarAniversarios(entrada);
				}catch(Exception exc){
					break;
				}
				
				if(aniversariantes.size()==0){
					mensagem = "N�o h� aniversariantes nesse m�s";
				}else{
					mensagem = "Aniversariantes nesse m�s: \n" + linha;
					for(Contato c : aniversariantes){
						mensagem += String.format("Nome completo : %1$s \nAnivers�rio: %2$s",
							c.nome + " " + c.sobrenome, c.diaAniver + "\\" + c.mesAniver + "\\" + c.anoAniver);
					}
				}
				JOptionPane.showMessageDialog(null, mensagem);
				break;
			}
		}
	}

}