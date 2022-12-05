package view;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import utils.ImageUtil;

public class RankView extends JFrame {

	private final JLabel title = new JLabel("Rank");
	
	private JButton timeRank;
	private JButton countRank;
	private JButton back;
	
	private final JTextArea rankArea = new JTextArea(30, 15);
	private JComboBox levelBox;
	
	private final List<String> level = new ArrayList<>();
	private final int MAX_LEVEL = 6;
	
	private ImageUtil util = new ImageUtil();
	
	public void printLayout() {
		setTitle("Rank");
        
        setSize(620, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        title.setFont(new Font("Gothic", Font.BOLD, 40));
        title.setBounds(257, 5, 150, 100);
        back = util.makeUI("image/Back.png", 100);
        back.setBounds(255, 550, 100, 50);
        
        for (int i = 1; i <= MAX_LEVEL; i++) {
        	level.add("Level " + i);
        }
        
        levelBox = new JComboBox(level.toArray(new String[level.size()]));
        timeRank = util.makeUI("image/Short.png", 200);
        countRank = util.makeUI("image/Minimumt.png", 200);
        
        rankArea.setEditable(false);
        levelBox.setBounds(150, 150, 310, 40);
        timeRank.setBounds(100, 90, 200, 50);
        countRank.setBounds(310, 90, 200, 50);
        rankArea.setBounds(150, 200, 310, 340);
        
        add(title);
        add(back);
        add(levelBox);
        add(timeRank);
        add(countRank);
        add(rankArea);
	}
	
	public JButton getBack() {
		return back;
	}
	
	public JButton getCountRank() {
		return countRank;
	}
	
	public JButton getTimeRank() {
		return timeRank;
	}
	
	public JComboBox getLevelBox() {
		return levelBox;
	}
	
	public void setView() {
		setVisible(false);
	}
	
	public void setRank(boolean flag) {
		countRank.setEnabled(flag);
		timeRank.setEnabled(!flag);
	}
	
	public void printResult(String standard, ResultSet result) {
		int rankIndex = 1;
		int beforeRank = 1;
		
		rankArea.setText("  순위	아이디\t     " + standard + "\n");
		rankArea.append(" ---------------------------------------------------------------------------- \n");
		
		try {
			while (result.next()) {
				if (result.getInt(2) == beforeRank) {
					rankIndex--;
				}
				rankArea.append("   " + (rankIndex++) + "등\t" + result.getString("username") + "\t     " + getMoveOrTime(standard, result) + "\n");
				beforeRank = result.getInt(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("결과 받아오지 못함");
		}
	}
	
	public String getMoveOrTime(String standard, ResultSet result) throws SQLException {
		if (standard.equals("이동횟수")) {
			return result.getInt(2) + " 번";
		}
		return result.getFloat(2) + " 초";
	}
}
