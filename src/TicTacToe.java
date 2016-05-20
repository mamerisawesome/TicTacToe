// GAME FILE

import java.lang.Integer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;

import java.util.LinkedList;
import java.util.Random;

public class TicTacToe {
	TicTacToe game;
	State state;

	Tile[][] tiles;
	Tile[][] output;
	
	JButton resetButton;
	JButton prevButton;
	JButton nextButton;
	
	int pageFlag;
	int currentPlayer;
	int[][] config;
	int tmpFlag;
	
	JLabel headerLabel;
	
	String symPlayer1;
	String symPlayer2;
	int ps;

	public TicTacToe (JPanel headerPanel, JPanel tilesPanel, JPanel menuPanel, String sym1, String sym2, int playerStart) {
		
		/*************************************************************************
				INITIALIZATION
		*/
		
		int num = 1;
		
		tmpFlag = 0;
		
		tiles = new Tile[3][3];
		currentPlayer = 1;
		
		symPlayer1 = sym1;
		symPlayer2 = sym2;
		ps = playerStart;
		
		headerLabel = new JLabel("Pios-Mendoza Dragonball Fusion!", SwingConstants.CENTER);
		resetButton = new JButton ("Reset");
		for (int i = 0; i < 3; i += 1) {
			for (int j = 0; j < 3; j += 1) {
				tiles[i][j] = new Tile();				
			}
		}
		
		/*************************************************************************/
		
		
		
		/*************************************************************************
				LISTENERS
		*/
		
		for (int i = 0; i < 3; i += 1) {
			for (int j = 0; j < 3; j += 1) {
				tiles[i][j].addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						Object source = e.getSource();
						if (source instanceof Tile) {
                            Tile btn = (Tile)source;
							if (btn.getValue() == 0) {
								
								config = updateConfig(tiles);
								if (goalTest(new State(config))) {
									endGame(config, tiles, currentPlayer);
								} else {
									btn.toggleTile(currentPlayer, symPlayer1, symPlayer2);
									
									config = updateConfig(tiles);
									if (goalTest(new State(config))) {
										endGame(config, tiles, currentPlayer);
									} else {
										if (currentPlayer == 1) currentPlayer = 2;
										else currentPlayer = 1;

										startAI(tiles, currentPlayer);
										
										if (currentPlayer == 1) currentPlayer = 2;
										else currentPlayer = 1;
									}
								}
							}
                        }
                    }
				});
			}
		}
		
		resetButton.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				int num = 1;
				for (int i = 0; i < 3; i += 1) {
					for (int j = 0; j < 3; j += 1, num += 1) {
						tiles[i][j].setPreferredSize(new Dimension(125, 125));
						tiles[i][j].setBackground(Color.WHITE);

						tiles[i][j].setValue(0);
						tiles[i][j].setText("");

						if (tiles[i][j].getValue() != 0) {
							tiles[i][j].setEnabled(false);
						} else {
							tiles[i][j].setEnabled(true);
						}
					}
				}
				
				currentPlayer = 1;
				
				tmpFlag = 0;
				
				if (ps != JOptionPane.YES_OPTION && tmpFlag == 0) {
					startAI(tiles, currentPlayer);
					
					if (currentPlayer == 1) currentPlayer = 2;
					else currentPlayer = 1;
					
					tmpFlag += 1;
				}
			}
		});
		
		/*************************************************************************/
		
		
		
		/*************************************************************************
			GUI ESSENTIALS
		*/
		
		for (int i = 0; i < 3; i += 1) {
			for (int j = 0; j < 3; j += 1) {
				tiles[i][j].setPreferredSize(new Dimension(125, 125));
				tiles[i][j].setForeground(Color.BLACK);
				tiles[i][j].setBackground(Color.WHITE);
				tiles[i][j].setEnabled(true);

				tiles[i][j].setText("");
				tiles[i][j].setValue(0);
				
				tilesPanel.add(tiles[i][j]);
			}
		}
		
		tilesPanel.setLayout(new GridLayout(3, 3));
		
		headerLabel.setForeground(Color.YELLOW);
		headerPanel.setBackground(Color.DARK_GRAY);
		headerPanel.add(headerLabel);
		
		resetButton.setBackground(Color.BLACK);
		resetButton.setForeground(Color.YELLOW);
		
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanel.add(resetButton);
		
		/*************************************************************************/
		
		/*************************************************************************
			AI INITIALIZATION
		*/
		
		if (ps != JOptionPane.YES_OPTION && tmpFlag == 0) {
			startAI(tiles, currentPlayer);
			
			if (currentPlayer == 1) currentPlayer = 2;
			else currentPlayer = 1;
			
			tmpFlag += 1;
		}
		
		/*************************************************************************/
	}
	
	public boolean goalTest (State currentState) {
		
		int[][] c = currentState.getConfig();
				
		for (int i = 0; i < 3; i += 1) {
			if (
				(
					(c[i][0] != 0) &&
					(c[i][0] == c[i][1] && c[i][1] == c[i][2] && c[i][0] == c[i][2])
				) ||
				(
					(c[0][i] != 0) &&
					(c[0][i] == c[1][i] && c[1][i] == c[2][i] && c[0][i] == c[2][i])
				) ||
				(
					(c[0][0] != 0) &&
					(c[0][0] == c[1][1] && c[1][1] == c[2][2] && c[0][0] == c[2][2])
				) ||
				(
					(c[0][2] != 0) &&
					(c[0][2] == c[1][1] && c[1][1] == c[2][0] && c[0][2] == c[2][0])
				)
			) {
				return true;
			}
		}
		
		return false;
	}
	
	public void disableBoard (Tile[][] tiles) {
		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				tiles[x][y].setEnabled(false);
			}
		}
	}
	
	public void endGame (int [][] config, Tile[][] tiles, int currentPlayer) {
		JOptionPane.showMessageDialog(new JFrame(), "Player " + currentPlayer + " win!");
		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				tiles[x][y].setEnabled(false);
			}
		}
	}
	
	public boolean fullBoard (Tile[][] tiles) {
		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				if (tiles[x][y].getValue() == 0) return false;
			}
		}
		
		disableBoard(tiles);
		JOptionPane.showMessageDialog(new JFrame(), "Draw!");
		return true;
	}
	
	public int[][] updateConfig (Tile[][] tiles) {
		int[][] config = new int[3][3];
		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = tiles[x][y].getValue();
			}
		}
		
		return config;
	}
	
	public void startAI (Tile[][] tiles, int currentPlayer) {
		int[][] config;
		String almer = "weird";
		while (almer != "is cute") {
			int x = new Random().nextInt(3);
			int y = new Random().nextInt(3);
			
			if (fullBoard(tiles) == true) break;
			
			if (tiles[x][y].getValue() == 0) {
				tiles[x][y].toggleTile(currentPlayer, symPlayer1, symPlayer2);
		
				config = updateConfig(tiles);
				if (goalTest(new State(config))) endGame(config, tiles, currentPlayer);
				
				break;
			}
		}
	}
}
