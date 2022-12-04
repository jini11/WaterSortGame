package model;

import java.util.List;

public enum Level {
	LEVEL1(1, 4, 6),
	LEVEL2(2, 5, 7),
	LEVEL3(3, 6, 8),
	LEVEL4(4, 7, 9),
	LEVEL5(5, 8, 10);
	
	private int level;
	private int maxColor;
	private int maxBottle;
	
	Level(int level, int maxColor, int maxBottle) {
		this.level = level;
		this.maxColor = maxColor;
		this.maxBottle = maxBottle;
	}
	
	public static List<Integer> getMax(int userLevel) {
		for (Level level : Level.values()) {
			System.out.println(level.getLevel());
			if (level.getLevel() == userLevel) {
				return List.of(level.getMaxColor(), level.getMaxBottle());
			}
		}
		return List.of(0, 0);
	}
	
	private int getMaxColor() {
		return maxColor;
	}
	
	private int getMaxBottle() {
		return maxBottle;
	}
	
	public int getLevel() {
		return level;
	}
}
