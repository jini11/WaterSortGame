package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DB.DataBase;
import model.User;
import view.GameView;

public class Timer extends JFrame implements Runnable {
	//private Clear clear;
	private JLabel time;
	//private Thread thread;
	private int millisecond;
	private int second;
	private GameView gameView;
	
	public Timer(JLabel time) {
		//clear = new Clear();
		this.time = time;
		//this.thread = thread;
		this.second = 0;
		this.millisecond = 0;
		gameView = new GameView();
	}
	
	public void run() {
	
		while(true) {
			try {
				++millisecond;
				if(millisecond == 10) {
					millisecond = 0;
					++second;
				}
				
        		time.setText(Integer.toString(second) +  " : " + Integer.toString(millisecond));
				
				gameView.operation();
				//if(isSolved()) {
//					DataBase dataBase = new DataBase();
//					
//					//clear.run(level);
//					System.out.println("시간 = " + second + ":" + millisecond);
//					
//					//LogIn login = new LogIn();
//					User user = new User();
//					int id = user.getUserId();
//					int userlevel = dataBase.checklevel(id, level);
//					int userTime = dataBase.getTime(id, level);
//					
//					ResultSet result = dataBase.getResult(id);
//					String timelabel = time.getText().toString();
//					System.out.println(timelabel);
//					int time = Integer.parseInt(timelabel.split(" : ")[0]);
//					
//					if (!result.next()) {
//						String insertSql = "insert into game (user_id, level, move, time) values (" + id + "," + level + "," + moveCnt + "," + time + ");";
//						if(dataBase.updateResult(insertSql)) {
//							JOptionPane.showMessageDialog(null, "순위 등록 완료");
//						}
//					} else {
//						if(userlevel < level) {
//							String insertSql = "insert into game (use"
//    								+ "r_id, level, move, time) values (" + id + "," + level + "," + moveCnt + "," + time + ");";
//    						if(dataBase.updateResult(insertSql)) {
//    							JOptionPane.showMessageDialog(null, "레벨 순위 등록 완료");
//    						}
//						} else if(userTime > time) {
//							String updateSql = "update game set move=" + moveCnt + ", time=" + time + " where user_id=" + id + " and level=" + level + ";";
//    						if (dataBase.updateResult(updateSql)) {
//    							JOptionPane.showMessageDialog(null, "순위 갱신 완료");
//    						}
//						}
//					}
//					
//					new ResultView(level, moveCnt, second, millisecond);
//					thread.interrupt();
//		        	break;
//
//		        }
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
				//e.printStackTrace();
			} 
		}
		
	}
	
	public int getSecond() {
		return second;
	}
	
	public int getMillisecond() {
		return millisecond;
	}
}
