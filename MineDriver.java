// Harris Christiansen
import javax.swing.*;
public class MineDriver {
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Minesweeper");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new MineGUI());
		window.pack();
		window.setVisible(true);
		window.setSize(300,300);
		window.setResizable(false);
	}
}