package lista03;
  /*
  * Classe para ser utilizar no exercício 10 da lista 03.
  * Armazena uma coleção de contatos e realiza uma série de operações com a coleção.
  * A coleção é armazenada como um ArrayList estático dentro da classe.
  */
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Agenda {
	static ArrayList<Contato> AgendaContatos = new ArrayList<>();
	static ArrayList<String> Nomes = new ArrayList<>();
	static Map<String, Integer> Categorias = new HashMap<String, Integer>();
	static ArrayList<String> MesesAniversario = new ArrayList<>();
	static ArrayList<Contato> Aniversariantes;
	
	public void Cadastrar(String nome, String sobrenome, String email, 
			String endereco,String diaAniver, String mesAniver, String anoAniver, String categoria, String celular){
			
		if(Categorias.containsKey(categoria)){
			Contato c= new Contato();
			c.nome = nome;
			c.sobrenome = sobrenome;
			c.email = email;
			c.endereco = endereco;
			c.diaAniver = diaAniver;
			c.mesAniver = mesAniver;
			c.anoAniver = anoAniver;
			c.categoria = categoria;
			c.celular = celular;
			
			MesesAniversario.add(mesAniver);
			Categorias.put(categoria, Categorias.get(categoria)+1);
			AgendaContatos.add(c);
			Nomes.add(nome + " " + sobrenome);
		}
	}
	
	public ArrayList<Contato> AgendaCompleta(){
		return AgendaContatos;
	}
	
	public void OrdenarNomes(){
		
	} 
	
	public Contato EncontrarContato(String nome, String sobrenome){
		int pos;
		pos = Nomes.indexOf(nome + " " + sobrenome);
		
		if(pos>-1){
			return AgendaContatos.get(pos);
		}else{
			return null;
		}
	}
	
	public ArrayList<Contato> ListarAniversarios(String mesAniver){
		Aniversariantes = new ArrayList<>();
		for(int ix = 0; ix< MesesAniversario.size();ix++){
			if(mesAniver.equals(MesesAniversario.get(ix))){
				Aniversariantes.add(AgendaContatos.get(ix));
			}
		}
		return Aniversariantes;
	}
	
	public boolean ExisteCategoria(String categoria){
		return Categorias.containsKey(categoria);
	}
	
	public boolean ExisteContato(String nome, String sobrenome){
		int pos;
		pos = Nomes.indexOf(nome + " " + sobrenome);
		
		return pos != -1;
	}
	
	public int NumContatosCategoria(String categoria){
		if(Categorias.containsKey(categoria)){
			return Categorias.get(categoria);
		}else{
			return 0;
		}
	}
	
	public int NumCategorias(){
		return Categorias.size();
	}
	
	public int NumContatos(){
		return AgendaContatos.size();
	}
	
	public void AlterarContato (String nome, String sobrenome, String email, 
			String endereco,String diaAniver, String mesAniver, String anoAniver, String categoria, String celular){
			int pos;
			pos = Nomes.indexOf(nome);
			if(pos>-1 && Categorias.containsKey(categoria)){
				Contato c= AgendaContatos.get(pos);
				
				String antigaCategoria = c.categoria;
				c.nome = nome;
				c.sobrenome = sobrenome;
				c.email = email;
				c.endereco = endereco;
				c.diaAniver = diaAniver;
				c.mesAniver = mesAniver;
				c.anoAniver = anoAniver;
				c.categoria = categoria;
				c.celular = celular;
				
				MesesAniversario.set(pos, mesAniver);
				Categorias.put(antigaCategoria, Categorias.get(antigaCategoria)-1);
				Categorias.put(categoria, Categorias.get(categoria)+1);
			}
	}
	
	public void ExcluirContato (String nome, String sobrenome){
		int pos = AgendaContatos.indexOf(nome);
		
		if(pos>-1){
			AgendaContatos.remove(pos);
		}
	}
	
	public void CadastrarCategoria (String categoria){
		Categorias.put(categoria,0);
	}
	
	public void AlterarCategoria (String antigoNome, String novoNome){
		if(Categorias.containsKey(antigoNome)){
			Categorias.put(novoNome, Categorias.get(antigoNome));
			Categorias.remove(antigoNome);
			for(Contato c:AgendaContatos){
				if(antigoNome.equals(c.categoria)){c.categoria = novoNome;}
			}
		}
	}
	
	public void RemoverCategoria(String categoria){
		if(Categorias.get(categoria)==0){
			Categorias.remove(categoria);
		}
	}
}
