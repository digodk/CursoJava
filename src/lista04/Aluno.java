package lista04;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import recursos.AuxInput;

public class Aluno {
  String nome="";
  private String situacaoAluno="";
  double media=-1;
  int sexo=-1;
  AuxInput input = new AuxInput();
  double[] notas = new double[3];
  String[] sexos= {"Masculino", "Feminino", "Outro"};
  Map<Integer, String> situacoes = new LinkedHashMap<Integer, String>();
  
  public Aluno() {
    cadastrarSituacoes();
  }
  
  public boolean cadastrarNome() {
    String mensagem="Digite o nome completo do aluno";
    nome = input.strInput(mensagem);
    if(nome.equals("")) {
      return false;
    }
    return true;
  }
  
  public boolean cadastrarSexo() {
    String mensagem="Selecione o sexo";
    sexo = input.optionToInt(mensagem, sexos);
    if(sexo==-1) {
      return false;
    }
    return true;
  }
  
  public boolean cadastrarNotas() {
    for(int ix=0;ix<notas.length;ix++) {
      String mensagem = "Insira a nota " + (ix+1);
      notas[ix] = input.intToInt(mensagem, true, 1, -1);
      if(notas[ix]==-1) {
        return false;
      }
    }
    media = Arrays.stream(notas).sum()/notas.length;
    determinarSituacao();
    return true;
  }
  
  public String situacao() {
    if(situacaoAluno.equals("")) {
      determinarSituacao();
    }
    
    return situacaoAluno;
  }
  
  private void determinarSituacao() {
    if(media==-1) {
      situacaoAluno = "Indeterminada";
    } else {
      for(Map.Entry<Integer, String> sit : situacoes.entrySet()) {
        if(media>=sit.getKey()){
          situacaoAluno = sit.getValue();
          break;
        }
      }
    }
    
  }
  
  public boolean cadastroCompleto() {
    return nome!=""&&(sexo>-1&&media>-1);
  }
  
  public String textoSexo() {
    if(sexo>-1) {
      return sexos[sexo];
    } else {
      return "";
    }
  }
  
  void cadastrarSituacoes() {
    situacoes.put(7,"Aprovado");
    situacoes.put(4, "Recupera��o");
    situacoes.put(0, "Reprovado");
  }
}
