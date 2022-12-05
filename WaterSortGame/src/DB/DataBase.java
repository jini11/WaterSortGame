package DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends JFrame{
	
	private Connection conn;
	private Statement stmt;
	private final String url = "jdbc:mysql://localhost:3306/watersort?serverTimezone=UTC";
	private final String id = "root";
	private final String pw = "angel21124";
	
	public DataBase() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결완료");
			
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}

    }

	public int checkLogIn(String userName, String password) {
		try {
			String logInSql = "select id, password from user where username='" + userName + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if (password.equals(result.getString("password"))) {
					return result.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 SQL 오류");
		}
		
		return 0;
	}
	
//	public int checklevel(int user_id, int level) {
//		try {
//			String logInSql = "select level from game where user_id='" + user_id + "';";
//			
//			ResultSet result = stmt.executeQuery(logInSql);
//			
//			while (result.next()) {
//				if(result.getInt(1) == level) {
//					return result.getInt(1);
//				}
//				
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("레벨 SQL 오류");
//		}
//		
//		return 0;
//	}
	
	public int getLevel(int user_id) {
		int countLevel = 0;
		try {
			String levelSql = "select level from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(levelSql);
			
			while (result.next()) {
				if(result.getInt(1) >= countLevel) {
					countLevel = result.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("레벨 SQL 오류");
		}
		
		return countLevel;
	}
	
	public float getTime(int user_id, int level) {
		int Level = level;
		float countTime = 0;
		try {
			String logInSql = "select level, time from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(logInSql);
			
			while (result.next()) {
				if(result.getInt(1) == Level) {
					countTime = result.getFloat(2);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("select time SQL 오류");
		}
		
		return countTime;
	}
	
	public int getMove(int user_id, int level) {
		int Level = level;
		int move = 0;
		try {
			String sql = "select level, move from game where user_id='" + user_id + "';";
			
			ResultSet result = stmt.executeQuery(sql);
			
			while (result.next()) {
				if(result.getInt(1) == Level) {
					move = result.getInt(2);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("select move SQL 오류");
		}
		
		return move;
	}
	
	public ResultSet scoreInquiryByMove(int level) {
		ResultSet result = null;
		
		try {
			String rankSql = "select username, move from user, game where user.id = game.user_id and level =" + level + " order by move";
			result = stmt.executeQuery(rankSql);
		} catch (SQLException e) {
			System.out.println("등수 조회 SQL 오류");
		}
		
		return result;
	}
	
	public ResultSet scoreInquiryByTime(int level) {
		ResultSet result = null;
		
		try {
			String rankSql = "select username, time from user, game where user.id = game.user_id and level =" + level + " order by time";
			result = stmt.executeQuery(rankSql);
		} catch (SQLException e) {
			System.out.println("등수 조회 SQL 오류");
		}
		
		return result;
	}
	
	public boolean insertResult(int id, int level, int move, float time) {
		String sql = "insert into game (user_id, level, move, time) values (?, ?, ?, ?);";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, level);
			pstmt.setInt(3, move);
			pstmt.setFloat(4, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert SQL 오류");
			return false;
		}
		return true;
	}
	
	public boolean updateTime(int id, int level, float time) {
		String updateSql = "update game set time=? where user_id=? and level=?;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setFloat(1, time);
			pstmt.setInt(2, id);
			pstmt.setInt(3, level);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update SQL 오류");
			return false;
		}
		
		return true;
	}
	
	public boolean updateMove(int id, int level, int move) {
		String updateSql = "update game set move=? where user_id=? and level=?;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, move);
			pstmt.setInt(2, id);
			pstmt.setInt(3, level);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update SQL 오류");
			return false;
		}
		return true;
	}
	
	public ResultSet selectResult() {
		ResultSet result = null;
		try {
			String sql = "select * from user";
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류");
		}
		
		return result;
	}
	
//	public ResultSet getResult(int id, int level) {
//		String sql = "select * from game where user_id = " + id + " and level=" + level + ";";
//		ResultSet result = null;
//		try {
//			result = stmt.executeQuery(sql);
//		} catch (SQLException e) {
//			System.out.println("get SQL 오류");
//		}
//		
//		return result;
//	}
	
	public boolean insertUser(String userId, String userPassword) {
		String sql = "insert into user(username, password) values(?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원가입 오류");
			return false;
		}
		
		return true;
	}
}

