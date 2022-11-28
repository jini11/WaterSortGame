package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JComboBox;

import DB.DataBase;
import view.RankView;

public class RankController {

	private RankView rankView = new RankView();
	private DataBase dataBase;
	private ResultSet result;
	
	private int isCheck = -1;
	
	public RankController() {
		rankView.printLayout();
		rankView.getBack().addActionListener(new BackAction());
		rankView.getCountRank().addActionListener(new RankAction());
		rankView.getTimeRank().addActionListener(new RankAction());
		rankView.getLevelBox().addActionListener(new LevelAction());
	}
	
	class BackAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MenuController();
			rankView.setView();		
		}
		
	}
	
	class RankAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rankView.getCountRank()) {
				rankView.setRank(false);
				isCheck = 1;
			} else {
				rankView.setRank(true);
				isCheck = 0;
			}
			
		}
		
	}
	
	class LevelAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			int level = cb.getSelectedIndex() + 1;
			dataBase = new DataBase();
			
    		if(isCheck == 1) {
    			result = dataBase.scoreInquiryByMove(level);
				rankView.printResult("이동횟수", result);
    		} else if(isCheck == 0) {
				result = dataBase.scoreInquiryByTime(level);
				rankView.printResult("시간", result);
    		}
			
		}
		
	}
}
