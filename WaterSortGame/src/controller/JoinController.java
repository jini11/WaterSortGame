package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import DB.DataBase;
import view.JoinView;

public class JoinController {

	private JoinView joinView = new JoinView();
	private WaterSortController ws = new WaterSortController();
	private boolean isDuplicate = true;
	private boolean checkDuplicate = false;
	
	public JoinController() {
		joinView.printLayout();
		joinView.getDuplicate().addActionListener(new DuplicateAction());
		joinView.getCreate().addActionListener(new CreateAction());
		joinView.getBack().addActionListener(new BackAction());
	}
	
	class DuplicateAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DataBase db = new DataBase();
			String id = joinView.getId();
            
            if (validateId(id)) {
            
	            try {
	              ResultSet result = db.selectResult();
	              
	              while(result.next()) {
	                   String user_ID = result.getString("username");
	                   if(id.equals(user_ID)) {
	                	   checkDuplicate = true; //중복된 곳 있을 경우
	                   }
	              }
	              
	              if(checkDuplicate) { // 아이디 중복되었을 경우
	             	 JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	             	checkDuplicate = false;
	             } else {
	              	JOptionPane.showMessageDialog(null, "중복된 아이디가 없습니다");
	              	joinView.setDuplicate();
	              	isDuplicate = false;
	              }
	            } catch (Exception e1) {}
            }
		}
		
		private boolean validateId(String id) {
        	if (idIsEmpty(id)) {
        		JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}
        	if (validateIdType(id)) {
        		JOptionPane.showMessageDialog(null, "아이디는 영어, 숫자만 입력가능합니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}
        	return true;
        }
        
        private boolean idIsEmpty(String id) {
        	return id.replaceAll(" ", "").equals("");
        }
        
        private boolean validateIdType(String id) {
        	//String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-핳]*$"; // 공백 혹은 특수문자가 입력된 경우(자바 정규식 참고)
        	String pattern = "^[0-9|a-z|A-Z]*$";
        	return !Pattern.matches(pattern, id);
        }
		
	}
	
	class CreateAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<String> info = joinView.joinInfo();
			String user_ID = info.get(0);
			String user_PASSWORD = info.get(1);
			String user_PASSRE = info.get(2);
			System.out.println(user_PASSWORD + " " + user_PASSRE);
			if (!user_PASSWORD.equals(user_PASSRE)) { // 비밀번호가 동일하지 않을 경우									
				JOptionPane.showMessageDialog(null, "비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);	
			} else if(user_ID.equals("") || user_PASSWORD.equals("")) { // 정보 입력 제대로 안했을 경우
				JOptionPane.showMessageDialog(null, "정보 입력을 제대로 해주세요!", "입력오류", 1);
			} else if(isDuplicate) { // id 중복확인을 안했을 경우
				JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요!", "중복확인", 1);
			} else {																				
				DataBase dataBase = new DataBase();
				
				if (dataBase.insertUser(user_ID, user_PASSWORD)) {
					JOptionPane.showMessageDialog(null, "회원 가입 완료!", "회원가입", 1);
				    ws.start();
				    joinView.setView();
				}
			}
		}
		
	}
	
	class BackAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ws.start();
			joinView.setView();
		}
		
	}
}
