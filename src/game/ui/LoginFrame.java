package game.ui;

import javax.swing.*;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		initFrame();

		this.setVisible(true);
	}

	private void initFrame() {
		this.setTitle("Puzzle Game LoginFrame");
		this.setSize(800, 600);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
