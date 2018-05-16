package recursos;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
    
    // Adicionar componentes
    frame.add(lbl);
    frame.add(campo);
    
    // Exibir
    frame.setVisible(true);
  }
}
