// Harris Christiansen
import java.util.*;
import javax.swing.*;
public class Mine {
	// Board Setup
	public int[][] mine_grid_setup(int getTotalMines) {
		// Random
		Random r=new Random();
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
	public int game_play(int[][] hid_grid,int userX,int userY) {
		// Make show_grid for user inputed value = hid_grid for user inputed value.
		if(hid_grid[userX][userY]!=10) {
			int buttonNew=hid_grid[userX][userY];
			return buttonNew;
		}
		// If value is a bomb end game.
		else {
			return 10;
		}
	}
}
