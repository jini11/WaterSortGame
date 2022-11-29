package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DB.DataBase;
import controller.MenuController;
import model.State;
import model.User;
import utils.ImageUtil;
import utils.Timer;

public class GameView extends JFrame {

	private int level;
	private int maxColors;
	private int maxBottles;
	private int[] colorType;
	private boolean clickFrom;
	private JLabel[] bottle;
	private JLabel[] bottleBorder;
	private Thread thread;
	private ArrayList<Integer> bottles[];
	private Stack<Integer> colors = new Stack<>();
	private Stack<Integer> moves = new Stack<>();
	private Stack<Integer> backMovesCounter = new Stack<>();
	private JLabel time = new JLabel();
	private JButton outBtn, undoBtn;
	private Timer timer;
    private ImageUtil util = new ImageUtil();
    
    private ImageIcon fromBottle = util.makeImage("image/bottle.png", 140, 170);
    private ImageIcon toBottle = util.makeImage("image/bottle3.png", 140, 170);
    
    private Color[] waterColor = {Color.red, Color.blue, Color.green, Color.pink, Color.cyan, Color.gray, Color.orange};
	
//    private int second;
//    private int millisecond;
    
    private State state;
    
    public GameView() {
    	state = new State();
    }
    
    public void printLayout(int maxColors, int maxBottles, int level) {  
    	this.level = level;
    	this.maxColors = maxColors;
    	this.maxBottles = maxBottles;
    	Level();
    	setGameLayout();
    }
    
	private void Level() {    	
    	colorType = new int[maxColors];
    	bottle = new JLabel[30];
    	bottleBorder = new JLabel[maxBottles];
		bottles = new ArrayList[maxBottles];
		
    	for(int i = 0; i < maxColors; i++) {
    		colorType[i] = i;
    	}
    	
		for(int i = 0; i < maxBottles; i++) {
			bottles[i] = new ArrayList<Integer>(maxColors);
		}
        
        for(int i = 0; i < maxBottles; i++) {
        	bottleBorder[i] = new JLabel(fromBottle);
        }
    }
	
    private void setGameLayout() {
    	setTitle("WaterSort");
        setLayout(null);
    	setSize(600,500);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
    	outAction();
    	undoAction();
    	timerAction();
    	clickBottleAction();
        
        add(time);
        
        fillColor(colors);

        for (int count = 0; count < 4 * maxColors; count++) {
            fillBottles();
        }

        showAll();
        thread.start();
    }
    
    private void outAction() {
    	outBtn = util.makeUI("image/Back.png", 100);
    	outBtn.setLocation(20, 20);
    	outBtn.setSize(100, 50);
    	outBtn.setVisible(true);
        add(outBtn);

        outBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	thread.interrupt();
            	setVisible(false);
            	new MenuController();
            }
        });
    }
    
    private void undoAction() {
    	JButton undoBtn = util.makeUI("image/Undo.png", 100);
    	undoBtn.setLocation(480, 20);
    	undoBtn.setVisible(true);
    	undoBtn.setSize(100,50);
    	undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(state.getBackLimit() >= 0) {
            		state.setFrom(10);
            		state.setTo(10);
				}
            }
        });
        add(undoBtn);
    }
    
    private void timerAction() {
    	time = new JLabel();
    	time.setLocation(420, 20);
    	time.setSize(100,50);
    	time.setVisible(true);
    	time.setFont(new Font("Gothic", Font.ITALIC, 20));
        timer = new Timer(time);
        thread = new Thread(timer);
        add(time);
    }
    
    private void clickBottleAction() {
    	for(int i = 0; i < maxBottles;i++) {
    		bottleBorder[i].setLocation((600 / (maxBottles + 2)) + (600 / (maxBottles + 2)) * i,140);
    		bottleBorder[i].setSize(40, 80);
    		bottleBorder[i].setVisible(true);
        	add(bottleBorder[i]);
        	bottleBorder[i].addMouseListener(new MyMouseListener());
        }
    }
    
    public void showAll() {
    	for(int i = 0; i< bottle.length; i++) {
    		if(bottle[i] != null) {
    			bottle[i].setVisible(false);
    		}
    	}
    	int z = 0;
    	
        for(int i = 0; i < maxBottles;i++) {
        	for(int j = 0; j < bottles[i].size(); j++) {
        		int colorNumber = (int) bottles[i].get(j);
        		bottle[z] = new JLabel();
        		if (colorNumber >= 0 && colorNumber <= 6) {	    			
        			int xPosition = (600 / (maxBottles + 2)) + (600 / (maxBottles + 2)) * i;
	    			int yPosition = 200 - (j * 20);
	    			bottle[z].setSize(40,20);
	    			bottle[z].setOpaque(true);
	    			bottle[z].setLocation(xPosition, yPosition);
	    			bottle[z].setBackground(waterColor[colorNumber]);
	    			add(bottle[z]);
	    			z++;
        		}
        	}
        }
        setVisible(true);
        paint(getGraphics());
    }
    
    private void fillBottles() {
        int index = generateRandom(0, maxBottles - 2);
        if (bottles[index].size() < 4) {
            bottles[index].add(colors.pop());
        } else {
            fillBottles();
        }
    }

    private void fillColor(Stack<Integer> colors) {
        for (int x = 0; x < 4; x++) {
        	for(int i = 0; i < maxColors;i++) {
        		colors.push(colorType[i]);
        	}
        }
    }
    
    private int generateRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }  
    
//    public JButton getOut() {
//    	return outBtn;
//    }
//    
//    public JButton getUndo() {
//    	return undoBtn;
//    }
//    
//    public JLabel getTimer() {
//    	return time;
//    }
//    
//    public JLabel getBottle(int idx) {
//    	return bottleBorder[idx];
//    }
    
    public void setView() {
    	setVisible(false);
    }
    
    // ----------------------------------------
    
    public boolean isSolved() { 
        for (int x = 0; x < maxBottles; x++) {
            if (!checkPaintType(x) || !checkBottleSize(x)) {
            	return false;
            }
            showAll();
        }
        return true;
    }
    
    private boolean checkPaintType(int x) { 
    	int count = 0;
    	
    	if (bottles[x].size() == 0) {
    		 return true;
    	} 
    	
    	for (int i = 0; i < bottles[x].size(); i++) {
    		for (int j = 1; j < bottles[x].size(); j++) {
    			if (!bottles[x].get(i).equals(bottles[x].get(j))) {
    				count++;
    			}
    		}
    		if (count > 2) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean checkBottleSize(int x) {
    	return bottles[x].size() == 4 || bottles[x].size() == 0;
    }
    
    private int peek(int x) {
    	int temp = ' ';
    	
        if (bottles[x].size() > 0) {
            temp = (int) bottles[x].get(bottles[x].size() - 1);
        }
        return temp;
    }

    public boolean isValidMove(int from, int to) {
    	
        if (bottles[from].size() == 0) { // from bottle이 0인 경우
        	System.out.println("empty");
            return false;
        }  
        if (bottles[to].size() >= maxColors) { // to bottle의 maxsize를 초과하는 경우
        	System.out.println("fill");
            return false;
        } 
        if (from == to) { // to, from 같은 위치를 클릭한 경우
        	System.out.println("same");
            return false;
        }
        if (!bottles[from].get(bottles[from].size()-1).equals(null) && bottles[to].size() == 0) { // to 물병의 크기가 0일때
        	int excount = 0;
            do {
            	if(bottles[to].size() <= 4) {
                	bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    moves.push(from);
                    moves.push(to);
                    excount++;
            	}
            	else {
            		break;
            	}

            } while (peek(from) == peek(to));
            backMovesCounter.push(excount);
            state.plusMoveCnt();
            //System.out.println("moves : " + moveCnt );
            return true;
            
        } 
        if ((!bottles[from].get(bottles[from].size()-1).equals(null) && !bottles[to].get(bottles[to].size()-1).equals(null))
                && bottles[from].get(bottles[from].size()-1).equals(bottles[to].get(bottles[to].size()-1))) {//to, from 물병의 크기가 0이 아니고, form물병의 최상단이
        	//to 물병의 최상단과 같을 때
        	int excount2 = 0;
            do {
            	if(bottles[to].size() < 4) {
            		bottles[to].add(bottles[from].get(bottles[from].size()-1));
                    bottles[from].remove(bottles[from].size()-1);
                    moves.push(from);
                    moves.push(to);
                    excount2++;
            	}
            	else {
            		break;
            	}
                
                
            } while (peek(from) == peek(to));
            
            backMovesCounter.push(excount2);
            System.out.println("backmove = " + backMovesCounter.peek());
            state.plusMoveCnt();
            //System.out.println("moves : " + moveCnt );
            return true;

        } 
        return false;
    }

    public void undo() { // 뒤로 가기 액션
    	int undoTo = 0;
    	int undoFrom = 0;
    	int peekCount = 0;
    	
        if (moves.peek() != null) {
        	if(backMovesCounter.peek() != null) {
        		peekCount = (int)backMovesCounter.peek();
        		if((int) backMovesCounter.peek() == 1) {
        			undoTo = (int) (moves.pop());
                    undoFrom = (int) (moves.pop());
                    bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                    bottles[undoTo].remove(bottles[undoTo].size()-1);
        		} else {
        			while(peekCount > 0) {
        				undoTo = (int) (moves.pop());
        				undoFrom = (int) (moves.pop());
                        bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                        bottles[undoTo].remove(bottles[undoTo].size()-1);
                        peekCount--;
                        
            		}
        		}
        		backMovesCounter.pop();
        	} else {
        		undoTo = (int) (moves.pop());
        		undoFrom = (int) (moves.pop());
                bottles[undoFrom].add(bottles[undoTo].get(bottles[undoTo].size()-1));
                bottles[undoTo].remove(bottles[undoTo].size()-1);
        	}
            showAll();
        }
    }

    public void operation() {    	
    	try {
    		if (state.getFrom() == 10 && state.getTo() == 10) {
    			undo();
    			//System.out.println("backcount =" + backLimit);
    			state.minusLimit();
    			state.setFrom(7);
    			state.setTo(7);
    		} else if (state.getFrom() != 100 && state.getTo() != 100) {

                if (isValidMove(state.getFrom(), state.getTo())) {
                    //System.out.println("move");
                    showAll();
                    state.setFrom(100);
                    state.setTo(100);
                } else {
                	//System.out.println("move ex");
                    showAll();
                    state.setFrom(100);
                    state.setTo(100);
                }
            }
		} catch (Exception e) {
        	System.out.println("catch");
        	return;
		}	
    }
    
	class MyMouseListener implements MouseListener {
	    	
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
			
		@Override
		public void mousePressed(MouseEvent e) {
			JLabel click = (JLabel)e.getSource();
			for(int i = 0; i < maxBottles; i++) {
				if(bottleBorder[i] == click) {
					if(!clickFrom) {
						bottleBorder[i].setIcon(toBottle);
						clickFrom = true;
						state.setFrom(i);
						state.setCurrent(i);
					}
					else {						
						bottleBorder[state.getCurrent()].setIcon(fromBottle);
						clickFrom = false;
						state.setTo(i);
						state.setCurrent(100);
					}
				}
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
    }
	
	class Timer extends JFrame implements Runnable {
    	
    	private JLabel time;
    	private int second;
        private int millisecond;
        
    	public Timer(JLabel time) {
    		this.time = time;
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
    				
    				operation();
    				
    				if(isSolved()) {
    					DataBase dataBase = new DataBase();
    					
    					System.out.println("시간 = " + second + ":" + millisecond);
    					
    					User user = new User();
    					int id = user.getUserId();
    					int userTime = dataBase.getTime(id, level);
    					int userMove = dataBase.getMove(id, level);
    					
    					String timelabel = time.getText().toString();
    					//System.out.println(timelabel);
    					int time = Integer.parseInt(timelabel.split(" : ")[0]);
    					
    					if (userTime == 0 && userMove == 0) {
    						if(dataBase.insertResult(id, level, state.getMoveCnt(), time)) {
    							JOptionPane.showMessageDialog(null, "순위 등록 완료");
    						}
    					} else {
							if(userTime > time || userMove > state.getMoveCnt()) {
								if (dataBase.updateResult(id, level, state.getMoveCnt(), time)) {
	    							JOptionPane.showMessageDialog(null, "순위 갱신 완료");
	    						}
    						}
    					}
    					setView();
    					new ResultView(level, state.getMoveCnt(), second, millisecond);
    					thread.interrupt();
    		        	break;

    		        }
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    		
    	}

    }

}
