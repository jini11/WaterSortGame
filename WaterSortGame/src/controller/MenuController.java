package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MenuView;

public class MenuController {

	private MenuView menuView = new MenuView();
	
	public MenuController() {
		menuView.printLayout();
		menuView.getStart().addActionListener(new MenuAction());
		menuView.getRank().addActionListener(new MenuAction());
		menuView.getBack().addActionListener(new MenuAction());
	}
	
	class MenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuView.getStart()) {
				new LevelController();
				menuView.setView();
			} else if (e.getSource() == menuView.getRank()) {
				new RankController();
				menuView.setView();
			} else if (e.getSource() == menuView.getBack()) {
				WaterSortController ws = new WaterSortController();
				ws.start();
				menuView.setView();
			}
			
		}
		
	}
}
