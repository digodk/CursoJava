package desafioUI;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Predicate;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class SistemaCadastroProdutos extends JPanel implements ActionListener, FocusListener {
  /**
   * 
   */
  private static final long serialVersionUID = 4007220763252390441L;
  private int alturaFrame = 700;
  private int larguraFrame = 450;
  private int alturaCaixa = 20;
  private int larguraLabel = 100;
  private int larguraCaixas = 200;
  private int margemSuperior = 50;
  private int margemCaixas = 30;
  private int margemEsquerda = 30;
  private ArrayList<JComponent> controles = new ArrayList<>();
  private ArrayList<Produto> listaProduto = new ArrayList<>();
  private JFrame frame;

  class Produto {
    String nome = "";
    double preco = 0, total = 0;
    int quantidade = 0;
  }

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

    // Caixas de texto
    // Nome do Produto
    posicao = 0;
    JTextField campoNome = new JTextField("");
    configurarComponente(campoNome, posicao);
    campoNome.setName("Nome Produto");
    controles.add(campoNome);
    posicao++;

    // Campo Preço

    // Abandonado, opção que usa JFormattedTextField
    /*
     * NumberFormat formatoMoeda =
     * NumberFormat.getCurrencyInstance(Locale.getDefault());
     * formatoMoeda.setMaximumFractionDigits(2);
     * formatoMoeda.setMinimumFractionDigits(2); NumberFormatter formatador =
     * new NumberFormatter(formatoMoeda); formatador.setMinimum(0.00);
     * formatador.setMaximum(10000000.0); formatador.setAllowsInvalid(false);
     * formatador.setOverwriteMode(true); JFormattedTextField campoPreco = new
     * JFormattedTextField(formatador); campoPreco.setValue(0.00);
     */

    /*
     * Regex para o filtro de texto na caixa de preços Retorna true quando o
     * input é diferente de R$ #.###,## Permite que uma sequência de números
     * entre dois pontos não tenha obrigatoriamente 3 números
     */
    String regex =
            "^(R\\$\\s*)?(((\\d{0,3}\\.)?(\\d{3}\\.)*(\\d{0,2}\\.)?(\\d{3}\\.)*(\\d{3},\\d{2})\\s*$)|"
                    + "(\\d{1,3},\\d{2}\\s*$)|" + "\\d*$)";
    JTextField campoPreco = new JTextField("R$ ");
    // Quando o campo perde foco, o texto é formatado para corrigir possíveis
    // formatos inválidos
    campoPreco.addFocusListener(this);
    configurarComponente(campoPreco, posicao, regex);
    campoNome.setName("Preço Produto");
    controles.add(campoPreco);
    posicao++;

    // Campo Quantidade
    regex = "^\\d*$";
    JTextField campoQtd = new JTextField("0");
    configurarComponente(campoQtd, posicao, regex);
    campoNome.setName("Qtd Produto");
    controles.add(campoQtd);

    // Botão cadastrar

    //
  }

  private void configurarComponente(JLabel label, int posicao) {
    label.setBounds(margemEsquerda, margemSuperior + posicao * (alturaCaixa + margemCaixas),
            larguraLabel, alturaCaixa);
    frame.add(label);
  }

  private <T extends JTextField> void configurarComponente(T txtBox, int posicao, String regex) {
    Predicate<String> testador = s -> s.matches(regex);
    PlainDocument doc = (PlainDocument) txtBox.getDocument();
    doc.setDocumentFilter(new FiltroTexto(testador));
    configurarComponente(txtBox, posicao);
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
  // Recebe uma função de teste no construtor para os casos onde não há erro no
  // input
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

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }

  public static void main(String[] args) {
    ArrayList<Produto> listaProduto = new ArrayList<>();
    SistemaCadastroProdutos formulario = new SistemaCadastroProdutos();
    formulario.carregar(listaProduto);
    formulario.iniciar();

  }
  

  @Override
  public void focusGained(FocusEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void focusLost(FocusEvent e) {
    if (e.getComponent().getName() == "Preço Produto"); {
      JTextField campo = (JTextField) e.getComponent();
      NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.getDefault());
      formatoMoeda.setMaximumFractionDigits(2);
      formatoMoeda.setMinimumFractionDigits(2);
      String stringNumero = campo.getText().replaceAll("[R\\$\\.]", "").replace(',', '.');
      campo.setText(formatoMoeda.format(Double.parseDouble(stringNumero)));
    }
    
  }

}
