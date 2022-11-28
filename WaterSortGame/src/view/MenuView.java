package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.ImageUtil;

public class MenuView extends JFrame {

	private final JPanel jPanel = new JPanel();
	private final JLabel menu = new JLabel("MENU");
	private JButton startBtn;
	private JButton rankBtn;
	private JButton backBtn;
	private ImageUtil util = new ImageUtil();
	
	public void printLayout() {
		setTitle("Menu");
        setLayout(null);
        
        jPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        startBtn = util.makeUI("image/Start.png", 100);
        rankBtn = util.makeUI("image/Rank.png", 100);
        backBtn = util.makeUI("image/Back.png", 100);
        
        jPanel.add(startBtn);
        jPanel.add(rankBtn);
        jPanel.add(backBtn);
        jPanel.setBounds(120, 180, 250, 200);
        
        menu.setHorizontalAlignment(JLabel.CENTER);
        menu.setFont(new Font("Gothic", Font.BOLD, 50));
        menu.setBounds(0, 50, 500, 100);
        
        add(menu);
        add(jPanel);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public JButton getStart() {
		return startBtn;
	}
	
	public JButton getRank() {
		return rankBtn;
	}
	
	public JButton getBack() {
		return backBtn;
	}
	
	public void setView() {
		setVisible(false);
	}
}
