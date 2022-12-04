package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.User;
import view.LevelView;

public class LevelController {
	
	private final int MAX_LEVEL = 5;
	private LevelView levelView = new LevelView();
	private User user = new User();
	
	public LevelController() {
		levelView.setLevel(user.getLevel());
		levelView.printLayout();
		levelView.getBack().addActionListener(new BackAction());
		for (int i = 0; i < MAX_LEVEL; i++) {
			levelView.getLevel(i).addActionListener(new LevelAction());
		}
	}
	
	class LevelAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < MAX_LEVEL; i++) {
				JButton click = (JButton) e.getSource();
				if (levelView.getLevel(i) == click) {
					System.out.println(i+1);
					new GameController(i+1);
					levelView.setView();
				}
			}
		}
		
	}
	
	class BackAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MenuController();
			levelView.setView();
		}
		
	}
}
