package recursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Formularios {
  JFrame frame = new JFrame();

  // Base aula
  void base() {
    // JFrame
    JFrame frame = new JFrame("Exercício");
    frame.setSize(500, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);

    // JLabel
    JLabel lbl = new JLabel("texto");
    lbl.setBounds(0, 0, 100, 20);

    // JTextFields
    JTextField campo = new JTextField();
    campo.setBounds(120, 0, 100, 20);

    // JButton
    JButton botao = new JButton("Calcular");
    botao.setBounds(120, 150, 130, 20);
    // Listener de ação com
    botao.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Mensagem quando clicar no botão");

      }
    });

    // JComboBox
    JComboBox<String> combo = new JComboBox<>();
    combo.setBounds(120, 150, 130, 20);
    combo.addItem("Opção 1");
    combo.addItem("Opção 2");

    // Adicionar componentes
    frame.add(lbl);
    frame.add(campo);
    frame.add(botao);
    frame.add(combo);
    

    // Exibir
    frame.setVisible(true);
  }
}
