package desafioUI;

import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

public class SistemaCadastroProdutos {

  public static void main(String[] args) {
    ArrayList<Produto> listaProduto = new ArrayList<>();
    Formulario formulario = new Formulario();
    formulario.carregar(listaProduto);
    formulario.iniciar();

  }

}

class Produto {
  String nome = "";
  double preco = 0, total = 0;
  int quantidade = 0;
}

class Formulario {
  private int alturaFrame = 700;
  private int larguraFrame = 450;
  private int alturaCaixa = 20;
  private int larguraLabel = 100;
  private int larguraCaixas = 200;
  private int margemSuperior = 50;
  private int margemCaixas = 30;
  private int margemEsquerda = 30;
  private Map<String, JComponent> controles = new HashMap<>();
  private ArrayList<Produto> listaProduto = new ArrayList<>();
  private JFrame frame;

  public ArrayList<Produto> getListaProduto() {
    return listaProduto;
  }

  void carregar(ArrayList<Produto> listaProduto) {
    this.listaProduto = listaProduto;
    frame = new JFrame("Exercício");
    montarFormulario();
  }

  private void montarFormulario() {
    frame.setSize(larguraFrame, alturaFrame);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
    // Labels
    int posicao = 0;
    configurarComponente(new JLabel("Produto"), posicao);
    posicao++;
    configurarComponente(new JLabel("Preço"), posicao);
    posicao++;
    configurarComponente(new JLabel("Quantidade"), posicao);
    posicao++;

    // Caixas de texto
    posicao = 0;
    configurarComponente(new JTextField(), posicao);
    posicao++;
    // Testador para o filtro de texto na caixa de preços
    Predicate<String> testadorPreco = new Predicate<String>() {
      String caracPermitido = ".R$,";

      @Override
      public boolean test(String texto) {
        for (char c : texto.toCharArray()) {
          if (!Character.isDigit(c)) {
            if (caracPermitido.indexOf(c) >= 0) {
              if (texto.length() - texto.replace(c, Character.MIN_VALUE).length() > 1) {
                return false;
              }
            } else {
              return false;
            }
          }
        }
        return true;
      }
    };

    // Botão cadastrar

    //
  }

  private void configurarComponente(JLabel label, int posicao) {
    label.setBounds(margemEsquerda, margemSuperior + posicao * (alturaCaixa + margemCaixas),
            larguraLabel, alturaCaixa);
    frame.add(label);
  }

  private <T extends JTextField> void configurarComponente(T txtBox, int posicao) {
    txtBox.setBounds(margemEsquerda + margemCaixas + larguraLabel,
            margemSuperior + posicao * (alturaCaixa + margemCaixas), larguraCaixas, alturaCaixa);
    frame.add(txtBox);
  }

  void iniciar() {
    frame.setVisible(true);
  }

  void encerrar() {
    System.exit(0);
  }

  // Subclasse de DocumentFilter que filtra inputs
  class FiltroTexto extends DocumentFilter {
    private Predicate<String> testador;

    // Construtor com a função de teste
    public FiltroTexto(Predicate<String> testador) {
      this.testador = testador;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (testador.test(sb.toString())) {
        super.insertString(fb, offset, string, attr);
      } else {
        // Toca um bipe caso não seja possível fazer a alteração
        Toolkit.getDefaultToolkit().beep();
      }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (testador.test(sb.toString())) {
        super.replace(fb, offset, length, text, attrs);
      } else {
        // Toca um bipe caso não seja possível fazer a alteração
        Toolkit.getDefaultToolkit().beep();
      }

    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if (testador.test(sb.toString())) {
        super.remove(fb, offset, length);
      } else {
        // Toca um bipe caso não seja possível fazer a alteração
        Toolkit.getDefaultToolkit().beep();
      }

    }
  }

}
