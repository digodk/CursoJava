package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class InternFrame extends JInternalFrame {

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          InternFrame frame = new InternFrame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public InternFrame() {
    setBounds(100, 100, 450, 300);

  }

}
