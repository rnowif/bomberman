package com.lite.bomberman.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameNode {

	private final Point position;
	private final GameCellEnum cell;

	private final List<GameNode> children;
	private GameNode parent;
	private int depth = 0;

	public GameNode(Point position, GameCellEnum cell) {
		super();
		this.position = position;
		this.cell = cell;
		this.children = new ArrayList<GameNode>();
	}

	public Point getPosition() {
		return position;
	}

	public GameCellEnum getCell() {
		return cell;
	}

	public List<GameNode> getChildren() {
		return children;
	}

	public GameNode getParent() {
		return parent;
	}

	public int getDepth() {
		return depth;
	}

	public void addChild(Point position, GameCellEnum cell) {
		GameNode child = new GameNode(position, cell);
		this.children.add(child);
		child.setParent(this);
	}

	private void setParent(GameNode parent) {
		this.parent = parent;
		this.depth = parent.getDepth() + 1;
	}

	public List<GameNode> getNodes(int depth) {

		if (depth == this.depth) {
			return Arrays.asList(this);
		}

		List<GameNode> toReturn = new ArrayList<GameNode>();

		for (GameNode child : children) {
			toReturn.addAll(child.getNodes(depth));
		}

		return toReturn;
	}
}
