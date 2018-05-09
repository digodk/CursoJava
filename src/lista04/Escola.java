package lista04;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import recursos.AuxInput;

public class Escola {
  static final int CADASTRAR=0, EXCLUIR=1, ESTATISTICAS=2, SAIR=3, CANCELAR=-1;
  AuxInput input = new AuxInput();
  Map<String, Aluno> alunos = new HashMap<>();
  Map<String, Integer> estatSituacoes = new HashMap<>();
  Map<String, Integer> estatSexos = new HashMap<>();
  String[] opcoes = {"a. Cadastrar alunos","b. Excluir alunos", "c. Estatísticas","d. Sair do sistema"};
  
  public void funcionar() {
    String mensagem = "Selecione uma opção";
    int escolha=-2;
    while(escolha!=SAIR && escolha!=CANCELAR) {
      escolha = input.optionToInt(mensagem, opcoes);
      switch(escolha) {
      case CADASTRAR:
        cadastrarAluno();
        break;
      case EXCLUIR:
        removerAluno();
        break;
      case ESTATISTICAS:
        exibirEstatisticas();
        break;
      }
    }
  }
  
  public void cadastrarAluno() {
    Aluno aluno = new Aluno();
    boolean cancelar=false;
    while(true){
      cancelar = !aluno.cadastrarNome();
      if(cancelar) {
        break;
      }
      if(alunos.containsKey(aluno.nome)) {
        String mensagem = "Esse aluno já existe!";
        JOptionPane.showMessageDialog(null, mensagem);
      } else {
        break;
      }
    }
    
    if(!cancelar) {
      cancelar=!aluno.cadastrarSexo();
    }
    
    if(!cancelar) {
      cancelar=!aluno.cadastrarNotas();
    }
    
    if(!cancelar) {
      alunos.put(aluno.nome, aluno);
      cadastrarEstatisticas(aluno);
    }
  }
  
  public void removerAluno() {
    String mensagem = "", nome = "";
    boolean sair=false;
    if(alunos.size()==0) {
      mensagem="Não há alunos cadastrados";
      sair=true;
    }
    while(!sair) {
      mensagem = "Insira o nome do aluno.";
      nome = input.strInput(mensagem);
      if(nome.equals("")) {
        sair=true;
      }
      if(!alunos.containsKey(nome)) {
        mensagem="Esse aluno não existe.";
      } else {
        alunos.remove(nome);
        mensagem="Aluno removido";
        sair=true;
      }
      JOptionPane.showMessageDialog(null, mensagem);
    }
  }
  
  public void exibirEstatisticas() {
    String mensagem = relatorioEstatisticas();
    JOptionPane.showMessageDialog(null, mensagem);
  }
  
  public String relatorioEstatisticas() {
    String mensagem="";
    if(alunos.size()==0) {
      mensagem="Não há alunos cadastrados";
    } else {
      mensagem="Resumo dos alunos cadastrados. \n" + "-Distribuição por sexo:\n";
      for(Map.Entry<String, Integer> sexo : estatSexos.entrySet()) {
        mensagem+=String.format("Sexo: %1$s, quantidade: %2$s, (%3$.2f%%)\n", sexo.getKey(), sexo.getValue(), (100.0*sexo.getValue())/alunos.size());
      }
      mensagem += "\n-Distribuição por situação: \n";
      for(Map.Entry<String, Integer> situacao : estatSituacoes.entrySet()) {
        mensagem+=String.format("Situação: %1$s, quantidade: %2$s, (%3$.2f%%)\n", situacao.getKey(), situacao.getValue(), (100.0*situacao.getValue())/alunos.size());
      }
    }
    return mensagem;
  }
  
  private void cadastrarEstatisticas(Aluno aluno) {
    String sexo = aluno.textoSexo();
    if(estatSexos.containsKey(sexo)) {
      estatSexos.put(sexo, estatSexos.get(sexo)+1);
    } else {
      estatSexos.put(sexo,1);
    }
    
    String situacao = aluno.situacao();
    if(estatSituacoes.containsKey(situacao)) {
      estatSituacoes.put(situacao, estatSituacoes.get(situacao)+1);
    } else {
      estatSituacoes.put(situacao,1);
    }
  }
}