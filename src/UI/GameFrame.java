package UI;

import javax.swing.*;

public class GameFrame extends JFrame {
	public GameFrame() {
		this.setTitle("Puzzle Game GameFrame v1.0");
		this.setSize(800, 600);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}
}
