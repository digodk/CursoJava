package lista04;
/*
 * Desenvolver um algoritmo para uma escola. Nesse algoritmo dever� ser poss�vel:
 * a. Cadastrar alunos
 * b. Excluir alunos
 * c. Estat�sticas
 * d. Sair do sistema
 * Cadastrar alunos: Dever� ser pedido pelo nome, sexo e mais tr�s notas,
 * ap�s cadastrados os dados dever� ser realizada a m�dia e dada a situa��o do aluno
 * (aprovado, em exame ou reprovado).
 * A m�dia e a situa��o dever�o ser cadastradas em uma posi��o do vetor.
 * N�o poder� haver nomes repetidos, sendo assim valide o nome.
 * Excluir alunos: Dever� ser pedido o nome do aluno, e em seguida excluir o aluno.
 * Caso n�o haja aluno com determinado nome cadastrado dever� ser exibido que o nome procurado
 * n�o existe.
 * Estat�sticas: Informar a quantidade e o percentual de homens e mulheres.
 * Informar o percentual e a quantidade de alunos nas situa��es (aprovado, em exame e reprovado).
 * Sair do sistema: Sair do la�o.
 */
public class Ex08 {

  public static void main(String[] args) {
    Escola escola = new Escola();
    
    escola.funcionar();
  }

}
