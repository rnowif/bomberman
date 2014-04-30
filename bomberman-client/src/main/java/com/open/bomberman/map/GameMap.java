package com.open.bomberman.map;

import java.awt.Point;

public class GameMap {

	public static final int WIDTH = 13;
	public static final int HEIGHT = 11;
	private static final int CELL_WIDTH_PX = 16;
	private static final int CELL_HEIGHT_PX = 16;
	private static final int MAX_PLAYERS = 4;

	// Contient les dimensions de la map, son contenu, les diff�rents joueurs et
	// les bonus
	private final GameCellEnum[][] map = new GameCellEnum[WIDTH][HEIGHT];
	private final Player[] players = new Player[MAX_PLAYERS];
	private int bombsLeft = 1;
	private Point myBombPosition;

	public void set(int x, int y, GameCellEnum cell) {
		// On supprime ma bombe si besoin
		if (myBombPosition != null && myBombPosition.x == x
				&& myBombPosition.y == y && GameCellEnum.FIRE.equals(cell)) {
			deleteMyBomb();
		}
		map[x][y] = cell;
	}

	public void setPlayer(int index, Point positionInPx, boolean isAlive) {
		Player player = new Player();
		player.setPosition(convertPositionFromPx(positionInPx));
		player.setAlive(isAlive);

		players[index] = player;
	}

	private Point convertPositionFromPx(Point positionInPx) {
		return new Point((int) (positionInPx.getX() / CELL_WIDTH_PX),
				(int) (positionInPx.getY() / CELL_HEIGHT_PX));
	}

	public Point getPlayerPosition(int playerIndex) {
		if (playerIndex >= players.length || playerIndex < 0) {
			return null;
		}
		return players[playerIndex].getPosition();
	}

	public GameCellEnum get(int x, int y) {
		return get(new Point(x, y));
	}

	public boolean isOutOfBounds(Point position) {
		return position.x < 0 || position.y < 0
				|| position.x > GameMap.WIDTH - 1
				|| position.y > GameMap.HEIGHT - 1;
	}

	public GameCellEnum get(Point position) {
		if (isOutOfBounds(position)) {
			return null;
		}
		return map[position.x][position.y];
	}

	public boolean hasBombLeft() {
		return bombsLeft > 0;
	}

	public void setMyBomb(Point position) {
		set(position.x, position.y, GameCellEnum.BOMB);
		myBombPosition = position;
		bombsLeft--;
	}

	public void deleteMyBomb() {
		myBombPosition = null;
		bombsLeft++;
	}

	public boolean hasEnemy(Point position) {
		for (int i = 1; i < MAX_PLAYERS; i++) {
			if (players[i] != null && players[i].getPosition().equals(position)) {
				return true;
			}
		}
		return false;
	}
}
