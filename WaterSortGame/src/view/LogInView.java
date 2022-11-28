package view;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import utils.ImageUtil;

public class LogInView extends JFrame {

	private JLabel title = new JLabel("로그인");
	private JLabel nickName = new JLabel("닉네임");
	private JLabel password = new JLabel("비밀번호");
	private JTextField inputNickName = new JTextField();
	private JTextField inputPassword = new JTextField();
	private JButton logIn;
	private JButton join;
	private JButton home;

	private ImageUtil util = new ImageUtil();
	private Font font = new Font("Gothic", Font.BOLD, 15);
	
	public void printLayout() {
		setTitle("LogIn");
        setSize(500, 500);
        setLayout(null);
        
        logIn = util.makeUI("image/LogIn.png", 100);
        join = util.makeUI("image/Sign Up.png", 100);
        home = util.makeUI("image/Home.png", 100);
        
		title.setFont(new Font("Gothic", Font.BOLD, 50));
		nickName.setFont(font);
		password.setFont(font);
		
		title.setBounds(180, 100, 300, 50);
		nickName.setBounds(80, 200, 70, 30);
		password.setBounds(80, 240, 70, 30);
		inputNickName.setBounds(200, 200, 200, 30);
		inputPassword.setBounds(200, 240, 200, 30);
		logIn.setBounds(80, 300, 100, 50);
		join.setBounds(190, 300, 100, 50);
		home.setBounds(300, 300, 100, 50);
		
		add(title);
		add(nickName);
		add(password);
		add(inputNickName);
		add(inputPassword);
		add(logIn);
		add(join);
		add(home);
		
		setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void printError(String message) {
		JOptionPane.showMessageDialog(null, message, "로그인 실패", JOptionPane.ERROR_MESSAGE);
	}
	
	public void printSuccess() {
		JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
	}
	
	public List<String> getUserInfo() {
		String userName = inputNickName.getText();
		String password = inputPassword.getText();
		if (validateEmpty(userName, password)) {
			printError("아이디와 비밀번호를 입력해주세요.");
			return List.of("0", "0");
		}
		return List.of(inputNickName.getText(), inputPassword.getText());
	}
	
	private boolean validateEmpty(String username, String password) {
		return username.equals("") || password.equals("");
	}
	
	public JButton getLogIn() {
		return logIn;
	}
	
	public JButton getJoin() {
		return join;
	}
	
	public JButton getHome() {
		return home;
	}
	
	public void setView() {
		this.setVisible(false);
	}
}
