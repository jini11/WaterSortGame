package model;

public class State {
	private int from;
	private int to;
	private int backLimit;
	private int moveCnt;
	
	public State() {
		from = 100;
		to = 100;
		backLimit = 2;
		moveCnt = 0;
	}
	
	public void setFrom(int num) {
		from = num;
	}
	
	public void setTo(int num) {
		to = num;
	}
	
	public void minusLimit() {
		backLimit--;
	}
	
	public void plusMoveCnt() {
		moveCnt++;
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
	
	public int getBackLimit() {
		return backLimit;
	}
	
	public int getMoveCnt() {
		return moveCnt;
	}
}
