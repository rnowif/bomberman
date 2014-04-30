package com.lite.bomberman.map;

public enum GameCellEnum {

	EMPTY(true, "EM"), BLOCK(false, "BL"), WALL(false, "WA"), BOMB(true, "BO"), FIRE(false, "FI"), BONUS_BOMB(true, "BB"), BONUS_FIRE(true, "BF"), BONUS_ROLLER(true, "BR");

	private boolean walkable;
	private String code;

	private GameCellEnum(boolean walkable, String code) {
		this.walkable = walkable;
		this.code = code;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public String getCode() {
		return code;
	}

	public static GameCellEnum fromCode(String code) {
		for (GameCellEnum cell : values()) {
			if (cell.getCode().equals(code)) {
				return cell;
			}
		}
		throw new IllegalArgumentException("Unknown cell " + code);
	}

}
