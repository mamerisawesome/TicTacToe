// MAIN FILE

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

public class MendozaPiosEx10 {
  public static void main (String[] args) {

	// configure frame and panels
	JFrame frame = new JFrame("TicTacToe");
	JPanel headerPanel = new JPanel();
	JPanel bodyPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	String symPlayer1 = JOptionPane.showInputDialog("Input symbol for player 1");
	String symPlayer2 = JOptionPane.showInputDialog("Input symbol for player 2");

	TicTacToe tictactoe = new TicTacToe(headerPanel, bodyPanel, menuPanel, symPlayer1, symPlayer2);

	// positioning elements
	frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
	frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
	frame.getContentPane().add(menuPanel, BorderLayout.SOUTH);

	// show game
	frame.pack();
	frame.setVisible(true);
	
	System.out.println("\nGame generated");
  }
}
