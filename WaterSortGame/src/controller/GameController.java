package controller;

import java.util.List;

import model.Level;
import view.GameView;

public class GameController {
	private GameView gameView = new GameView();
	
	public GameController(int level) {
		List<Integer> max = Level.getMax(level);
		gameView.printLayout(max.get(0), max.get(1));
		//gameView.getOut().addActionListener(new OutAction());
		//gameView.getUndo().addActionListener(new UndoAction());
		//gameView.getTimer().addMouseListener(new TimerAction());
//		for (int i = 0; i < max.get(1); i++) {
//			gameView.clickBottleAction();
//			gameView.getBottle(i).addMouseListener(new MyMouseListener());
//		}
	}
	
//	class OutAction implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			//thread.interrupt();
//        	new MenuController();
//        	gameView.setView();
//		}
//		
//	}
	
}
