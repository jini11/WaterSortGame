package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.User;
import view.LogInView;

public class LogInController {
	
	private LogInView logInView = new LogInView();
	private User user;
	
	public LogInController() {
		logInView.printLayout();
		logInView.getLogIn().addActionListener(new BtnAction());
		logInView.getJoin().addActionListener(new BtnAction());
		logInView.getHome().addActionListener(new BtnAction());
		
	}
	
	class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == logInView.getLogIn()) {
				List<String> userInfo = logInView.getUserInfo();
				if (userInfo.get(0) != "0" && userInfo.get(1) != "0") {
					user = new User();
					user.setUserName(userInfo.get(0));
					user.setUserPassword(userInfo.get(1));
					
					if (user.validateUser()) {
						logInView.printSuccess();
						new MenuController();
						logInView.setView();
					} else {
						String message = "일치하는 계정 정보가 없습니다.";
						logInView.printError(message);
					}
				}
				
			} else if (e.getSource() == logInView.getJoin()) {
				new JoinController();
				logInView.setView();
			} else if (e.getSource() == logInView.getHome()) {
				WaterSortController waterSortController = new WaterSortController();
            	waterSortController.start();
				logInView.setView();
			}
			
		}
					
	}
}
