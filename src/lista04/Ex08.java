package lista04;
/*
 * Desenvolver um algoritmo para uma escola. Nesse algoritmo deverá ser possível:
 * a. Cadastrar alunos
 * b. Excluir alunos
 * c. Estatísticas
 * d. Sair do sistema
 * Cadastrar alunos: Deverá ser pedido pelo nome, sexo e mais três notas,
 * após cadastrados os dados deverá ser realizada a média e dada a situação do aluno
 * (aprovado, em exame ou reprovado).
 * A média e a situação deverão ser cadastradas em uma posição do vetor.
 * Não poderá haver nomes repetidos, sendo assim valide o nome.
 * Excluir alunos: Deverá ser pedido o nome do aluno, e em seguida excluir o aluno.
 * Caso não haja aluno com determinado nome cadastrado deverá ser exibido que o nome procurado
 * não existe.
 * Estatísticas: Informar a quantidade e o percentual de homens e mulheres.
 * Informar o percentual e a quantidade de alunos nas situações (aprovado, em exame e reprovado).
 * Sair do sistema: Sair do laço.
 */
public class Ex08 {

  public static void main(String[] args) {
    Escola escola = new Escola();
    
    escola.funcionar();
  }

}
