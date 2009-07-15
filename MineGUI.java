// Harris Christiansen
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class MineGUI extends JPanel {
	// GUI objects
	public JTextField banner = new JTextField(null, 50);
	public JButton[][] buttons=new JButton[8][8];
	public JButton reset=new JButton("Reset");
	public JLabel scoreDisp=new JLabel("Score: 0");
	public int[][] hid_grid=new int[8][8];
	public int score=0;
	public int getTotalMines=0;
	public int setMinesClicked=0;
	
	MineGUI() {
		Mine mine=new Mine();
		score=0;
		// # mines to place that is reasonable
		getTotalMines=0;
		while(getTotalMines<3 || getTotalMines>30) {
			String getTotalMinesS=JOptionPane.showInputDialog("How many mines do you want to place? (min: 3, max: 30)", "10");
			getTotalMines=Integer.parseInt(getTotalMinesS);
		}
		hid_grid=mine.mine_grid_setup(getTotalMines);
		banner.setEditable(false);
		banner.setText("Welcome to Minesweeper!");
		for(int x=0;x<buttons.length;x++) {
			for(int y=0;y<buttons[x].length;y++) {
				buttons[x][y]=new JButton("?");
				buttons[x][y].setBorder(BorderFactory.createRaisedBevelBorder());
				buttons[x][y].setBackground(Color.lightGray);
			}
		}
		setLayout(new BorderLayout());
		setBackground(Color.gray);
		JPanel top=new JPanel();
		top.setBackground(Color.gray);
		top.setLayout(new GridLayout(1,1));
		top.add(banner);
		JPanel center=new JPanel();
		center.setBackground(Color.gray);
		center.setLayout(new GridLayout(8,8));
		for(int x=0;x<buttons.length;x++) {
			for(int y=0;y<buttons[x].length;y++) {
				center.add(buttons[x][y]);
				buttons[x][y].addActionListener(new ButtonListener());
			}
		}
		JPanel bottom=new JPanel();
		bottom.setBackground(Color.gray);
		bottom.setLayout(new GridLayout(1,2));
		bottom.add(scoreDisp);
		bottom.add(reset);
		reset.addActionListener(new ButtonListener());
		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Mine mine=new Mine();
			if(e.getSource() == reset) {
				getTotalMines=0;
				while(getTotalMines<3 || getTotalMines>30) {
					String getTotalMinesS=JOptionPane.showInputDialog("How many mines do you want to place? (min: 3, max: 30)", "10");
					getTotalMines=Integer.parseInt(getTotalMinesS);
				}
				hid_grid=mine.mine_grid_setup(getTotalMines);
				for(int x2=0;x2<buttons.length;x2++) {
					for(int y2=0;y2<buttons[x2].length;y2++) {
						buttons[x2][y2].setBackground(Color.lightGray);
						buttons[x2][y2].setEnabled(true);
						buttons[x2][y2].setText("?");
						buttons[x2][y2].setBorder(BorderFactory.createRaisedBevelBorder());
					}
				}
				score=0;
				setMinesClicked=0;
				banner.setText("Game reset.");
			}
			for(int x=0;x<buttons.length;x++) {
				for(int y=0;y<buttons[x].length;y++) {
					if(e.getSource() == buttons[x][y]) {
						int buttonsNew=mine.game_play(hid_grid,x,y);
						buttons[x][y].setEnabled(false);
						buttons[x][y].setBorder(BorderFactory.createLoweredBevelBorder());
						if(buttonsNew==0) {
							buttons[x][y].setBackground(Color.green);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=5;
							banner.setText("Looking good so far.");
						}
						if(buttonsNew==1) {
							buttons[x][y].setBackground(Color.green);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=10;
							banner.setText("No problem , it's only one mine.");
						}
						if(buttonsNew==2) {
							buttons[x][y].setBackground(Color.yellow);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=25;
							banner.setText("Not to hard, only 2 mines.");
						}
						if(buttonsNew==3) {
							buttons[x][y].setBackground(Color.yellow);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=50;
							banner.setText("They are growing, 3 mines.");
						}
						if(buttonsNew==4) {
							buttons[x][y].setBackground(Color.orange);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=100;
							banner.setText("Four mines around this square.");
						}
						if(buttonsNew==5) {
							buttons[x][y].setBackground(Color.orange);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=200;
							banner.setText("You arn't dead yet?");
						}
						if(buttonsNew==6) {
							buttons[x][y].setBackground(Color.red);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=300;
							banner.setText("Hard enough?");
						}
						if(buttonsNew==7) {
							buttons[x][y].setBackground(Color.red);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=400;
							banner.setText("Where to go, mines almost all around");
						}
						if(buttonsNew==8) {
							buttons[x][y].setBackground(Color.red);
							buttons[x][y].setText(Integer.toString(buttonsNew));
							score+=500;
							banner.setText("Wow, nice choice, how did you not click a mine. +500 points");
						}
						if(buttonsNew==10) {
							buttons[x][y].setBackground(Color.red);
							buttons[x][y].setText("X");
							banner.setText("You Lose. Score: "+score);
							int playAgain=JOptionPane.showConfirmDialog(null,"Bomb hit, game over. Score: "+score+". Would you like to play again?");
							if(playAgain==0) {
								// # mines to place that is reasonable
								getTotalMines=0;
								while(getTotalMines<3 || getTotalMines>30) {
									String getTotalMinesS=JOptionPane.showInputDialog("How many mines do you want to place? (min: 3, max: 30)", "10");
									getTotalMines=Integer.parseInt(getTotalMinesS);
								}
								hid_grid=mine.mine_grid_setup(getTotalMines);
								for(int x2=0;x2<buttons.length;x2++) {
									for(int y2=0;y2<buttons[x2].length;y2++) {
										buttons[x2][y2].setBackground(Color.lightGray);
										buttons[x2][y2].setEnabled(true);
										buttons[x2][y2].setText("?");
										buttons[x2][y2].setBorder(BorderFactory.createRaisedBevelBorder());
									}
								}
								score=0;
								setMinesClicked=0;
								banner.setText("Welcome back! Previous Score: "+score);
							}
							else {
								System.exit(0);
							}
						}
						scoreDisp.setText("Score: "+Integer.toString(score));
						setMinesClicked++;
						if(setMinesClicked==(64-getTotalMines)) {
							banner.setText("You Win! Score: "+score);
							int playAgain=JOptionPane.showConfirmDialog(null,"You win! Score: "+score+". Would you like to play again?");
							if(playAgain==0) {
								// # mines to place that is reasonable
								getTotalMines=0;
								while(getTotalMines<3 || getTotalMines>30) {
									String getTotalMinesS=JOptionPane.showInputDialog("How many mines do you want to place? (min: 3, max: 30)", "10");
									getTotalMines=Integer.parseInt(getTotalMinesS);
								}
								hid_grid=mine.mine_grid_setup(getTotalMines);
								for(int x2=0;x2<buttons.length;x2++) {
									for(int y2=0;y2<buttons[x2].length;y2++) {
										buttons[x2][y2].setBackground(Color.lightGray);
										buttons[x2][y2].setEnabled(true);
										buttons[x2][y2].setText("?");
										buttons[x2][y2].setBorder(BorderFactory.createRaisedBevelBorder());
									}
								}
								score=0;
								setMinesClicked=0;
								banner.setText("Welcome back! Previous Score: "+score);
							}
							else {
								System.exit(0);
							}
						}
					}
				}
			}
		}
	}
}