package com.open.bomberman.map;

import java.awt.Point;

import com.open.bomberman.strategy.DirectionEnum;

public class GameMapAnalyzer {

	private final GameMap map;
	private final GameTree tree;

	public GameMapAnalyzer(GameMap map) {
		this.map = map;
		this.tree = GameTree.buildFromMap(map, 6);
	}

	public boolean isThreatenedByBomb(Point position) {
		// On est menac� s'il existe une bombe sur un de nos axes
		// Vers la droite
		for (int x = position.x; x < GameMap.WIDTH; x++) {
			GameCellEnum cell = map.get(x, position.y);
			if (cell == null || !cell.isWalkable()) {
				break;
			}
			if (GameCellEnum.BOMB.equals(cell)
					|| GameCellEnum.FIRE.equals(cell)) {
				return true;
			}
		}

		// Vers la gauche
		for (int x = position.x; x > 0; x--) {
			GameCellEnum cell = map.get(x, position.y);
			if (cell == null || !cell.isWalkable()) {
				break;
			}
			if (GameCellEnum.BOMB.equals(cell)
					|| GameCellEnum.FIRE.equals(cell)) {
				return true;
			}
		}

		// Vers le haut
		for (int y = position.y; y > 0; y--) {
			GameCellEnum cell = map.get(position.x, y);
			if (cell == null || !cell.isWalkable()) {
				break;
			}
			if (GameCellEnum.BOMB.equals(cell)
					|| GameCellEnum.FIRE.equals(cell)) {
				return true;
			}
		}

		// Vers le bas
		for (int y = position.y; y < GameMap.HEIGHT; y++) {
			GameCellEnum cell = map.get(position.x, y);
			if (cell == null || !cell.isWalkable()) {
				break;
			}
			if (GameCellEnum.BOMB.equals(cell)
					|| GameCellEnum.FIRE.equals(cell)) {
				return true;
			}
		}

		return false;
	}

	public boolean hasEnemyAtRange() {
		GamePath path = tree.crawlMap(new CrawlStopper() {
			@Override
			public boolean isFinished(GameNode node) {
				return node.hasEnemy();
			}
		});

		return path != null;
	}

	public boolean hasBonusAtRange() {
		GamePath path = tree.crawlMap(new CrawlStopper() {
			@Override
			public boolean isFinished(GameNode node) {
				return GameCellEnum.BONUS_BOMB.equals(node.getCell())
						|| GameCellEnum.BONUS_FIRE.equals(node.getCell())
						|| GameCellEnum.BONUS_ROLLER.equals(node.getCell());
			}
		});

		return path != null;
	}

	public Point getPlayerPosition(int playerIndex) {
		return map.getPlayerPosition(playerIndex);
	}

	public GamePath getClosestCell(Point position, final GameCellEnum cellType) {

		GamePath path = tree.crawlMap(new CrawlStopper() {

			@Override
			public boolean isFinished(GameNode node) {

				Point position = node.getPosition();

				return cellType.equals(map.get(position.x + 1, position.y))
						|| cellType.equals(map.get(position.x, position.y + 1))
						|| cellType.equals(map.get(position.x - 1, position.y))
						|| cellType.equals(map.get(position.x, position.y - 1));
			}
		});

		return path;
	}

	public GamePath getSafePath(Point position) {

		GamePath path = tree.crawlMap(new CrawlStopper() {

			@Override
			public boolean isFinished(GameNode node) {

				return !isThreatenedByBomb(node.getPosition());
			}
		});

		return path;
	}

	private int getManhattanDistance(Point position1, Point position2) {
		return Math.abs(position1.x - position2.x)
				+ Math.abs(position1.y - position2.y);
	}

	public DirectionEnum getDirection(Point source, Point destination) {
		if (source.x < destination.x) {
			return DirectionEnum.RIGHT;
		}

		if (source.x > destination.x) {
			return DirectionEnum.LEFT;
		}

		if (source.y < destination.y) {
			return DirectionEnum.DOWN;
		}

		if (source.y > destination.y) {
			return DirectionEnum.UP;
		}

		return null;
	}

	public boolean hasBombLeft() {
		return map.hasBombLeft();
	}

	public void setMyBomb(Point position) {
		map.setMyBomb(position);
	}

	public void deleteMyBomb() {
		map.deleteMyBomb();
	}

}
