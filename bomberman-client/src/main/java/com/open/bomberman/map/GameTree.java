package com.open.bomberman.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.open.bomberman.utils.MultiValueMap;

public class GameTree {

	private final GameMap map;
	private final GameNode root;

	private GameTree(GameMap map, int maxDepth) {
		this.map = map;
		MultiValueMap<Integer, GameNode> nodesByDepth = new MultiValueMap<Integer, GameNode>();

		Point position = map.getPlayerPosition(0);

		root = new GameNode(position, map.get(position), map.hasEnemy(position));
		nodesByDepth.add(0, root);

		List<Point> processedPoints = new ArrayList<Point>();

		for (int depth = 0; depth < maxDepth; depth++) {
			for (GameNode node : nodesByDepth.get(depth)) {
				for (Point p : getAdjacentPositions(node.getPosition())) {
					if (!processedPoints.contains(p)) {
						GameNode child = new GameNode(p, map.get(p),
								map.hasEnemy(p));
						node.addChild(child);
						processedPoints.add(p);
						nodesByDepth.add(child.getDepth(), child);
					}
				}
			}
		}
	}

	private List<Point> getAdjacentPositions(Point position) {

		List<Point> toReturn = new ArrayList<Point>();
		List<Point> availablePoints = new ArrayList<Point>();
		availablePoints.add(new Point(position.x - 1, position.y));
		availablePoints.add(new Point(position.x + 1, position.y));
		availablePoints.add(new Point(position.x, position.y - 1));
		availablePoints.add(new Point(position.x, position.y + 1));

		for (Point p : availablePoints) {
			if (!map.isOutOfBounds(p) && map.get(p).isWalkable()) {
				toReturn.add(p);
			}
		}

		return toReturn;
	}

	public GamePath crawlMap(CrawlStopper stopper) {
		List<GameNode> nodesToProcess = new ArrayList<GameNode>();
		nodesToProcess.add(root);

		GameNode finalNode = null;

		// Parcours de l'arbre en largeur
		Iterator<GameNode> it = nodesToProcess.iterator();

		while (it.hasNext()) {
			GameNode node = it.next();
			if (stopper.isFinished(node)) {
				finalNode = node;
				break;
			}

			nodesToProcess.addAll(node.getChildren());
			it.remove();
		}

		if (finalNode == null) {
			return null;
		}

		GamePath path = new GamePath();
		while (finalNode != null) {
			path.addPositionFirst(finalNode.getPosition());
			finalNode = finalNode.getParent();
		}
		return path;
	}

	/**
	 * Construit un arbre centr� sur la position du joueur principal
	 * 
	 * @param map
	 * @return
	 */
	public static GameTree buildFromMap(GameMap map, int maxDepth) {
		return new GameTree(map, maxDepth);
	}
}
