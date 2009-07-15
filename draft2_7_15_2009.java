// Harris Christiansen
// 7-15-2009
// Minesweeper
import java.util.*;
import javax.swing.*;
public class draft2_7_15_2009 {
	
	// ToDo, fileIO high score board
	public static void main(String[] args) {
		// Repeat until player done
		boolean userPlay=true;
		while(userPlay) {
			// Function app
			draft2_7_15_2009 app=new draft2_7_15_2009();
			// Setup grids
			int hid_grid[][]=app.mine_grid_setup();
			String show_grid[][]=new String[hid_grid.length][hid_grid[0].length];
			// Fill show_grid with ?'s
			for(int x=0;x<show_grid.length;x++) {
				for(int y=0;y<show_grid[x].length;y++) {
					show_grid[x][y]="?";
				}
			}
			// Game Score
			int gameScore=0;
			// Repeat game until bomb hit
			boolean bombRep=true;
			while(bombRep) {
				// Bomb hit
				if(show_grid[0][0].equals("found")) {
					// Play again?
					String popupGridWin="   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |\n";
					for(int x=0;x<hid_grid.length;x++) {
						popupGridWin+=(x+1);
						for(int y=0;y<hid_grid[x].length;y++) {
							if(hid_grid[x][y]!=10) {
								popupGridWin+=" | "+hid_grid[x][y];
							}
							else {
								popupGridWin+=" | X";
							}
						}
						popupGridWin+=" |\n";
					}
					popupGridWin+="Score: "+gameScore+"\nBomb hit, game over. Play again?";
					String playAgain=JOptionPane.showInputDialog("Bomb hit, game over. Play again", "");
					// No play again
					if(playAgain.equals("no")) {
						userPlay=false;
						System.exit(0);
					}
					// Yes play again
					else {
						break;
					}
				}
				// Play Game
				else {
					// Output guide numbers
					String popupGridWin="   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |\n";
					for(int x=0;x<show_grid.length;x++) {
						// Output guide numbers/line
						popupGridWin+=(x+1);
						// Output show_grid
						for(int y=0;y<show_grid[x].length;y++) {
							popupGridWin+=" | "+show_grid[x][y];
						}
						popupGridWin+=" |\n";
					}
					// Get user input for value to click
					popupGridWin+="What value do you want to click?(Row then column)(ex. 3 6 = third down then sixth over)";
					boolean numFal=true;
					int userX=0,userY=0;
					while(numFal) {
						String usrIn=JOptionPane.showInputDialog(popupGridWin, "x y");
						if(usrIn.length()!=3) {
							continue;
						}
						userX=Integer.parseInt(usrIn.substring(0,1));
						userY=Integer.parseInt(usrIn.substring(2,3));
						if(userX<9&&userY<9) {
							numFal=false;
						}
						else {
						}
					}
					app.game_play(hid_grid,show_grid,userX,userY);
					gameScore+=5;
				}
			}
		}
	}
	
	// Board Setup
	public int[][] mine_grid_setup() {
		// Random
		Random r=new Random();
		// # mines to place that is reasonable
		int getTotalMines=0;
		while(getTotalMines<3 || getTotalMines>30) {
			String getTotalMinesS=JOptionPane.showInputDialog("How many mines do you want to place? (min: 3, max: 30)", "10");
			getTotalMines=Integer.parseInt(getTotalMinesS);
		}
		// Create array
		int[][] hid_grid=new int[8][8];
		// Set all values to 0
		for(int x=0;x<hid_grid.length;x++) {
			for(int y=0;y<hid_grid[x].length;y++) {
				hid_grid[x][y]=0;
			}
		}
		//// Randomly place mines
		// mines to place
		for(int c=getTotalMines;c>0;c--) {
			// random placement
			int x=r.nextInt(hid_grid.length);
			int y=r.nextInt(hid_grid[1].length);
			// random place not already taken by mine
			if(hid_grid[x][y]==10) {
				c++;
			}
			// place mine
			else {
				hid_grid[x][y]=10;
			}
		}
		//// Get mines touching square count
		for(int x=0;x<hid_grid.length;x++) {
			for(int y=0;y<hid_grid[x].length;y++) {
				if(hid_grid[x][y]==10) {
					if(x>0&&hid_grid[x-1][y]!=10) {
						hid_grid[x-1][y]++;
					}
					if(x>0&&y>0&&hid_grid[x-1][y-1]!=10) {
							hid_grid[x-1][y-1]++;
						}
					if(x>0&&y<7&&hid_grid[x-1][y+1]!=10) {
						hid_grid[x-1][y+1]++;
					}
					if(y>0&&hid_grid[x][y-1]!=10) {
						hid_grid[x][y-1]++;
					}
					if(x<7&&y>0&&hid_grid[x+1][y-1]!=10) {
						hid_grid[x+1][y-1]++;
					}
					if(x<7&&y<7&&hid_grid[x+1][y+1]!=10) {
						hid_grid[x+1][y+1]++;
					}
					if(x<7&&hid_grid[x+1][y]!=10) {
						hid_grid[x+1][y]++;
					}
					if(y<7&&hid_grid[x][y+1]!=10) {
						hid_grid[x][y+1]++;
					}
				}
			}
		}
		return hid_grid;
	}
	
	// Game Play
	public void game_play(int hid_grid[][],String show_grid[][],int userX,int userY) {
		userX--;
		userY--;
		// Make show_grid for user inputed value = hid_grid for user inputed value.
		if(hid_grid[userX][userY]!=10) {
			show_grid[userX][userY] = Integer.toString(hid_grid[userX][userY]);
		}
		// If value is a bomb set show_grid[0][0] to found.
		else {
			show_grid[0][0]="found";
		}
	}
}