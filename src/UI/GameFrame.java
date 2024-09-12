package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener {

	int[][] data = new int[4][4];
	int[][] win = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
	String path = "src\\image\\girl\\girl1\\";

	int x = 0;
	int y = 0;

	public GameFrame() {
		initFrame();

		initJMenuBar();

		initData();

		initImage();

		this.setVisible(true);
	}

	private void initData() {
		int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			int index = random.nextInt(16);
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}

		for (int i = 0; i < 16; i++) {
			if (array[i] == 0) {
				x = i / 4;
				y = i % 4;
			} else
				data[i / 4][i % 4] = array[i];
		}
	}

	public boolean victory(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(data[i][j] != win[i][j])
					return false;
			}
		}
		return true;
	}

	private void initImage() {
		this.getContentPane().removeAll();

		if(victory())
		{
			JLabel winLabel = new JLabel(new ImageIcon("src\\image\\win.png"));
			winLabel.setBounds(203,283,197,73);
			this.getContentPane().add(winLabel);
		}

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
				jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
				jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				this.getContentPane().add(jLabel);
			}

		JLabel background = new JLabel(new ImageIcon("src\\image\\background.png"));
		background.setBounds(40, 40, 508, 560);
		this.getContentPane().add(background);

		this.getContentPane().repaint();
	}

	private void initJMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenuFunction = new JMenu("function");
		JMenu jMenuAboutUs = new JMenu("about us");

		JMenuItem jMenuReplay = new JMenuItem("replay");
		JMenuItem jMenuReLogin = new JMenuItem("re-login");
		JMenuItem jMenuExit = new JMenuItem("exit");
		JMenuItem jMenuAccount = new JMenuItem("account");

		jMenuFunction.add(jMenuReplay);
		jMenuFunction.add(jMenuReLogin);
		jMenuFunction.add(jMenuExit);
		jMenuAboutUs.add(jMenuAccount);

		jMenuBar.add(jMenuFunction);
		jMenuBar.add(jMenuAboutUs);

		this.setJMenuBar(jMenuBar);
	}

	private void initFrame() {
		this.setTitle("Puzzle Game GameFrame v1.0");
		this.setSize(603, 680);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_A) {
			this.getContentPane().removeAll();
			JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
			all.setBounds(83, 134, 420, 420);
			this.getContentPane().add(all);

			JLabel background = new JLabel(new ImageIcon("src\\image\\background.png"));
			background.setBounds(40, 40, 508, 560);
			this.getContentPane().add(background);

			this.getContentPane().repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(victory())
			return;

		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_UP -> {
				if (x == 3)
					return;
				data[x][y] = data[x + 1][y];
				data[++x][y] = 0;
				initImage();
			}
			case KeyEvent.VK_DOWN -> {
				if (x == 0)
					return;
				data[x][y] = data[x - 1][y];
				data[--x][y] = 0;
				initImage();
			}
			case KeyEvent.VK_LEFT -> {
				if (y == 3)
					return;
				data[x][y] = data[x][y + 1];
				data[x][++y] = 0;
				initImage();
			}
			case KeyEvent.VK_RIGHT -> {
				if (y == 0)
					return;
				data[x][y] = data[x][y - 1];
				data[x][--y] = 0;
				initImage();
			}
			case KeyEvent.VK_A -> initImage();
			case KeyEvent.VK_W -> {
				data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
				initImage();
			}
		}
	}
}
