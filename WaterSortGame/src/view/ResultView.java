package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.LevelController;
import controller.MenuController;
import controller.RankController;
import utils.ImageUtil;

public class ResultView extends JFrame {
	
	private JLabel clearLabel;
	private JLabel moveLabel;
	private JLabel timeLabel;
	private JButton closeBtn;
	private JButton exitBtn;
	private JButton rankBtn;
	private ImageUtil util = new ImageUtil();
	
	public ResultView(int level, int moveCnt, int second, int millisecond) {
		setResultLayout(level, moveCnt, second, millisecond);

		clickAction();
	}
	
	private void setResultLayout(int level, int moveCnt, int second, int millisecond) {
		setTitle("결과 화면");
		setSize(400, 400);
		setLayout(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    	setLocationRelativeTo(null);
    
    	clearLabel = new JLabel("Level" + level + " 클리어!");
    	moveLabel = new JLabel("이동 : " + moveCnt + "번");
    	timeLabel = new JLabel("시간 : " + second + "." + millisecond + "초");
    
    	clearLabel.setFont(new Font("Gothic", Font.BOLD, 40));
    	moveLabel.setFont(new Font("Gothic", Font.BOLD, 22));
    	timeLabel.setFont(new Font("Gothic", Font.BOLD, 22));
    	
    	closeBtn = util.makeUI("image/Home.png", 100);
    	exitBtn = util.makeUI("image/Select.png", 100);
    	rankBtn = util.makeUI("image/Rank.png", 100);
    	
    	clearLabel.setBounds(55, 5, 300, 100);
    	moveLabel.setBounds(135, 80, 130, 100);
    	timeLabel.setBounds(135, 140, 200, 100);
    	closeBtn.setBounds(145, 250, 100, 50);
    	exitBtn.setBounds(35, 250, 100, 50);
    	rankBtn.setBounds(255, 250, 100, 50);
    	
    	add(clearLabel);
    	add(moveLabel);
    	add(timeLabel);
    	
    	add(exitBtn);
    	add(rankBtn);
    	add(closeBtn);
    	paint(getGraphics());
	}
	
	private void clickAction() {
		rankBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new RankController();
    			back();
    		}
    	});

    	exitBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			setVisible(false);
    			new LevelController();
    			back();
    		}
    	});
    	
    	
    	closeBtn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			setVisible(false);
    			new MenuController();
    			back();
    		}
    	});
	}
	
	public void back() {
    	setVisible(false);
    }
}
