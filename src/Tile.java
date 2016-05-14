// BUTTON USED FOR THE GAME

import javax.swing.JButton;
import java.awt.Color;

public class Tile extends JButton{
	private int value;

	public Tile () {
		value = 0;
	}

	public Tile (int val) {
		value = val;
	}
	
	public void toggleTile (int player, String symPlayer1, String symPlayer2) {
		if (player == 1) {
			this.setBackground(Color.BLUE);
			this.setText("" + symPlayer1);
			this.setValue(player);
		} else {
			this.setBackground(Color.RED);
			this.setText("" + symPlayer2);
			this.setValue(player);
		}
	}
	
	public int getValue () {
		return value;
	}
	
	public void setValue (int val) {
		value = val;
	}
}
