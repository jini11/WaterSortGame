package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.ImageUtil;

public class LevelView extends JFrame {

	private final int MAX_LEVEL = 5;
	
	private final JPanel levelPanel = new JPanel();
	private final JLabel levelTitle = new JLabel("LEVEL");
	private JButton backBtn;
	private JButton[] levelBtn = new JButton[MAX_LEVEL];
	
	private final List<String> level = new ArrayList<>();
	
	private ImageUtil util = new ImageUtil();
	private int userLevel;

	public void printLayout() {
		setTitle("Level");
        setLayout(null);
        
        for (int i = 1; i <= levelBtn.length; i++) {
        	level.add("image/Level" + i + ".png");
        }
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelBtn[i] = util.makeUI(level.get(i), 100);
        	levelBtn[i].setEnabled(false);
        }
        
        int row = level.size() / 3 + 1;
        levelPanel.setLayout(new GridLayout(row, 3, 10, 10));
        levelPanel.setBounds(15, 100, 450, row * 60);
        
        levelTitle.setFont(new Font("Gothic", Font.BOLD, 40));
        levelTitle.setBounds(180, 20, 300, 40);
        
        backBtn = util.makeUI("image/Back.png", 100);
        backBtn.setBounds(15, 400, 100, 50);
        
        for (int i = 0; i < levelBtn.length; i++) {
        	levelPanel.add(levelBtn[i]);
        }       
        
        add(levelTitle);
        add(levelPanel);
        add(backBtn);
        
        for(int i = 0; i < levelBtn.length; i++) {
        	if(userLevel >= i) {
        		levelBtn[i].setEnabled(true);
        	}
        }
        
        setSize(500, 500);
        setLocationRelativeTo(null);
 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void setLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	public JButton getLevel(int idx) {
		return levelBtn[idx];
	}
	
	public JButton getBack() {
		return backBtn;
	}
	
	public void setView() {
		setVisible(false);
	}
}
