package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.MainView;

public class WaterSortController extends JFrame {

	private final MainView mainView = new MainView();
	
	public void start() {
		mainView.printLayout();
		mainView.getLogin().addActionListener(new MainAction());
		mainView.getJoin().addActionListener(new MainAction());
		mainView.getExit().addActionListener(new MainAction());
	}
	
	class MainAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mainView.getLogin()) {
				new LogInController();
				mainView.setView();
			} else if (e.getSource() == mainView.getJoin()) {
				new JoinController();
				mainView.setView();
			} else if (e.getSource() == mainView.getExit()) {
				System.exit(0);
			}
			
		}
		
	}
}
