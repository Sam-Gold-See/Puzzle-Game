package game.ui;

import game.user.User;
import game.checkcode.CheckCode;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements MouseListener {

	static ArrayList<User> users = new ArrayList<>();

	static {
		users.add(new User("admin", "123456"));
		users.add(new User("root", "123456"));
	}

	String loginPath = "src/image/login/";

	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField code = new JTextField();

	JLabel rightCode = new JLabel();

	JButton login = new JButton();
	JButton register = new JButton();
	JButton seePassword = new JButton();

	public LoginFrame() {
		initFrame();

		initView();

		this.setVisible(true);
	}

	private void initView() {
		JLabel usernameText = new JLabel(new ImageIcon(loginPath + "用户名.png"));
		usernameText.setBounds(116, 135, 47, 17);
		this.getContentPane().add(usernameText);

		username.setBounds(195, 134, 200, 30);
		this.getContentPane().add(username);

		JLabel passwordText = new JLabel(new ImageIcon(loginPath + "密码.png"));
		passwordText.setBounds(130, 195, 32, 16);
		this.getContentPane().add(passwordText);

		password.setBounds(195, 195, 200, 30);
		password.setEchoChar('*');
		this.getContentPane().add(password);

		JLabel codeText = new JLabel(new ImageIcon(loginPath + "验证码.png"));
		codeText.setBounds(133, 256, 50, 30);
		this.getContentPane().add(codeText);

		code.setBounds(195, 256, 100, 30);
		this.getContentPane().add(code);

		String codeStr = CheckCode.getCode();
		rightCode.setText(codeStr);
		rightCode.addMouseListener(this);
		rightCode.setBounds(300, 256, 50, 30);
		this.getContentPane().add(rightCode);

		login.setBounds(125, 310, 128, 47);
		login.setIcon(new ImageIcon(loginPath + "登录按钮.png"));
		login.setBorderPainted(false);
		login.setContentAreaFilled(false);
		login.addMouseListener(this);
		this.getContentPane().add(login);

		register.setBounds(256, 310, 128, 47);
		register.setIcon(new ImageIcon(loginPath + "注册按钮.png"));
		register.setBorderPainted(false);
		register.setContentAreaFilled(false);
		register.addMouseListener(this);
		this.getContentPane().add(register);

		seePassword.setBounds(400,195,18,29);
		seePassword.setIcon(new ImageIcon(loginPath+"显示密码.png"));
		seePassword.setBorderPainted(false);
		seePassword.setContentAreaFilled(false);
		seePassword.addMouseListener(this);
		this.getContentPane().add(seePassword);

		JLabel background = new JLabel(new ImageIcon(loginPath + "background.png"));
		background.setBounds(0, 0, 470, 390);
		this.getContentPane().add(background);
	}

	private void initFrame() {
		this.setTitle("Puzzle Game LoginFrame v1.0");
		this.setSize(488, 430);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == login) {
			String usernameInput = this.username.getText();
			String passwordInput = new String(this.password.getPassword());
			String codeInput = this.code.getText();

			User userInput = new User(usernameInput, passwordInput);

			if (codeInput.isEmpty()) {
				showDialog("验证码不能为空");
			} else if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
				showDialog("用户名或密码为空");
			}else if(!codeInput.equalsIgnoreCase(rightCode.getText())) {
				showDialog("验证码输入错误");
			}else if(contains(userInput)) {
				this.setVisible(false);
				new GameFrame();
			}else{
				showDialog("用户名或密码错误");
			}
		} else if (e.getSource() == register) {
			this.setVisible(false);
			new RegisterFrame();
		} else if (e.getSource() == rightCode) {
			String code = CheckCode.getCode();
			rightCode.setText(code);
		}
	}

	public void showDialog(String content) {
		JDialog jDialog = new JDialog();
		jDialog.setSize(200, 150);
		jDialog.setAlwaysOnTop(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModal(true);

		JLabel warning = new JLabel(content);
		warning.setBounds(0, 0, 200, 150);
		jDialog.getContentPane().add(warning);

		jDialog.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == login) {
			login.setIcon(new ImageIcon(loginPath + "登录按下.png"));
		}else if(e.getSource() == register) {
			register.setIcon(new ImageIcon(loginPath + "注册按下.png"));
		}else if(e.getSource() == seePassword) {
			String passwordInput = new String(this.password.getPassword());
			password.setText(passwordInput);
			password.setEchoChar((char)0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == login) {
			login.setIcon(new ImageIcon(loginPath + "登录按钮.png"));
		}else if (e.getSource() == register) {
			register.setIcon(new ImageIcon(loginPath + "注册按钮.png"));
		}else if (e.getSource() == seePassword) {
			password.setEchoChar('*');
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public boolean contains(User userInput) {
		for (User rightUser : users) {
			if (userInput.getUserName().equals(rightUser.getUserName()) && userInput.getPassword().equals(rightUser.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
