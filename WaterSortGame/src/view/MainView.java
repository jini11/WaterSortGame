package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.ImageUtil;


public class MainView extends JFrame {

	private JLabel gameTitle;
	private JPanel jPanel = new JPanel();
	private JButton loginBtn;
	private JButton joinBtn;
	private JButton exitBtn;
	private ImageUtil util = new ImageUtil();
	
	public void printLayout() {
		setTitle("Main");
        setLayout(null);
        
        gameTitle = util.makeLabel("image/Logo.png", 400, 400);
        gameTitle.setBounds(40, 0, 400, 400);
        
        loginBtn = util.makeUI("image/LogIn.png", 100);
        joinBtn = util.makeUI("image/Sign Up.png", 100);
        exitBtn = util.makeUI("image/Exit.png", 100);
        
        loginBtn.setBounds(80, 350, 100, 50);
        joinBtn.setBounds(190, 350, 100, 50);
        exitBtn.setBounds(300, 350, 100, 50);
        
        add(gameTitle);
        add(loginBtn);
        add(joinBtn);
        add(exitBtn);
         
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public JButton getLogin() {
		return loginBtn;
	}
	
	public JButton getJoin() {
		return joinBtn;
	}
	
	public JButton getExit() {
		return exitBtn;
	}
	
	public void setView() {
		this.setVisible(false);
	}
}
