package com.open.bomberman.strategy;

public class PlayerAction {

	private DirectionEnum direction;
	private boolean bombToDrop = false;

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public boolean isBombToDrop() {
		return bombToDrop;
	}

	public void setBombToDrop(boolean bombToDrop) {
		this.bombToDrop = bombToDrop;
	}
}
