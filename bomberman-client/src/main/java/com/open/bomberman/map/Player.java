package com.open.bomberman.map;

import java.awt.Point;

public class Player {

	private Point position;
	private boolean alive;

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}
}
