package com.open.bomberman.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MapCrawler {

	private final GameMap map;
	private final CrawlStopper stopper;

	// TODO : Transformer la map en arbre qui a pour racine la position initiale et qui ne contient que les cases accessibles
	// Algo : pour la profondeur allant de 0 à n
	// - Pour chaque noeud de cette profondeur
	// - Pour chaque case adjacente qui n'a pas encore été traitée
	// - Ajouter noeud avec cette case en tant que fils

	// TODO : Stocker les noeuds pas depth pour ne pas appeler getNodes à chque fois + mettre position dans constructeur et construire arbre dans constructeur

	public MapCrawler(GameMap map, CrawlStopper stopper) {
		this.map = map;
		this.stopper = stopper;
	}

	public GamePath crawlMap(Point position, int maxSteps) {

		GameNode root = new GameNode(position, map.get(position));

		List<Point> processedPoints = new ArrayList<Point>();

		for (int depth = 0; depth < maxSteps; depth++) {
			for (GameNode node : root.getNodes(depth)) {
				for (Point p : getAdjacentPositions(node.getPosition())) {
					if (!processedPoints.contains(p)) {
						node.addChild(p, map.get(p));
						processedPoints.add(p);
					}
				}
			}
		}

		GameNode node;

		if (stopper.isFinished(root.getPosition())) {
			node = root;
		} else {
			node = crawlMap(root);
		}

		if (node != null) {
			GamePath path = new GamePath();
			// TODO : créer path à partir du node en remontant les parents
			return path;
		}
		return null;
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

	private GameNode crawlMap(GameNode root) {

		GameNode node = null;

		// On scanne d'abord les enfants directs
		for (GameNode child : root.getChildren()) {
			if (stopper.isFinished(child.getPosition())) {
				node = child;
			}
		}

		// TODO : Modifier ça pour scanner étage par étage (en utilisant getNodes ?)
		if (node == null) {
			for (GameNode child : root.getChildren()) {
				node = crawlMap(child);
				if (node != null) {
					return node;
				}
			}
		}

		return node;
	}
}
