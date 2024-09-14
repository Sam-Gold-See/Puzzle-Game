package game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {

	int[][] data = new int[4][4];
	int[][] win = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
	int step = 0;
	Random random = new Random();
	int initRandom = random.nextInt(3) + 1;
	String[] pathName = new String[]{"animal", "girl", "sport"};
	int[] pathLen = new int[]{8, 11, 10};
	String path = "image\\" + pathName[initRandom] + "\\" + pathName[initRandom] + random.nextInt(pathLen[initRandom]) + "\\";

	JMenuItem jMenuReplay = new JMenuItem("replay");
	JMenuItem jMenuReLogin = new JMenuItem("re-login");
	JMenuItem jMenuExit = new JMenuItem("exit");
	JMenuItem jMenuAccount = new JMenuItem("account");
	JMenuItem jMenuChangeAnimal = new JMenuItem("animal");
	JMenuItem jMenuChangeGirl = new JMenuItem("girl");
	JMenuItem jMenuChangeSport = new JMenuItem("sport");

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
			}
			data[i / 4][i % 4] = array[i];
		}
	}

	private void initImage() {
		this.getContentPane().removeAll();

		if (victory()) {
			JLabel winLabel = new JLabel(new ImageIcon("image\\win.png"));
			winLabel.setBounds(203, 283, 197, 73);
			this.getContentPane().add(winLabel);
		}

		JLabel stepCount = new JLabel("已执行步数：" + step);
		stepCount.setBounds(50, 30, 100, 20);
		this.getContentPane().add(stepCount);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
				jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
				jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				this.getContentPane().add(jLabel);
			}

		JLabel background = new JLabel(new ImageIcon("image\\background.png"));
		background.setBounds(40, 40, 508, 560);
		this.getContentPane().add(background);

		this.getContentPane().repaint();
	}

	public boolean victory() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (data[i][j] != win[i][j])
					return false;
			}
		}
		return true;
	}

	private void initJMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenuFunction = new JMenu("function");
		JMenu jMenuAboutUs = new JMenu("about us");
		JMenu jMenuChange = new JMenu("change");

		jMenuFunction.add(jMenuChange);
		jMenuFunction.add(jMenuReplay);
		jMenuFunction.add(jMenuReLogin);
		jMenuFunction.add(jMenuExit);
		jMenuAboutUs.add(jMenuAccount);

		jMenuChange.add(jMenuChangeAnimal);
		jMenuChange.add(jMenuChangeGirl);
		jMenuChange.add(jMenuChangeSport);

		jMenuBar.add(jMenuFunction);
		jMenuBar.add(jMenuAboutUs);

		jMenuReplay.addActionListener(this);
		jMenuReLogin.addActionListener(this);
		jMenuExit.addActionListener(this);
		jMenuAccount.addActionListener(this);
		jMenuChangeAnimal.addActionListener(this);
		jMenuChangeGirl.addActionListener(this);
		jMenuChangeSport.addActionListener(this);

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

			JLabel background = new JLabel(new ImageIcon("image\\background.png"));
			background.setBounds(40, 40, 508, 560);
			this.getContentPane().add(background);

			this.getContentPane().repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (victory())
			return;

		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_UP -> {
				if (x == 3)
					return;
				data[x][y] = data[x + 1][y];
				data[++x][y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_DOWN -> {
				if (x == 0)
					return;
				data[x][y] = data[x - 1][y];
				data[--x][y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_LEFT -> {
				if (y == 3)
					return;
				data[x][y] = data[x][y + 1];
				data[x][++y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_RIGHT -> {
				if (y == 0)
					return;
				data[x][y] = data[x][y - 1];
				data[x][--y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_A -> initImage();
			case KeyEvent.VK_W -> {
				data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
				initImage();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == jMenuReplay) {
			replay();
		} else if (object == jMenuReLogin) {
			this.setVisible(false);
			new LoginFrame();
		} else if (object == jMenuExit) {
			System.exit(0);
		} else if (object == jMenuAccount) {
			account();
		} else if (object == jMenuChangeAnimal) {
			path = "src\\image\\Animal\\Animal" + (random.nextInt(8) + 1) + "\\";
			replay();
		} else if (object == jMenuChangeGirl) {
			path = "src\\image\\Girl\\Girl" + (random.nextInt(11) + 1) + "\\";
			replay();
		} else if (object == jMenuChangeSport) {
			path = "src\\image\\Sport\\Sport" + (random.nextInt(10) + 1) + "\\";
			replay();
		}
	}

	private void replay() {
		initData();
		step = 0;
		initImage();
	}

	private static void account() {
		JDialog jDialog = new JDialog();
		JLabel jLabel = new JLabel(new ImageIcon("src\\image\\about.jpg"));
		jLabel.setBounds(0, 0, 640, 640);
		jDialog.getContentPane().add(jLabel);
		jDialog.setSize(640, 640);
		jDialog.setAlwaysOnTop(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModal(true);
		jDialog.setVisible(true);
	}
}
