package com.open.bomberman.map;

import java.awt.Point;
import java.util.ArrayList;
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

	private void setParent(GameNode parent) {
		this.parent = parent;
		this.depth = parent.getDepth() + 1;
	}

	public void addChild(GameNode child) {
		this.children.add(child);
		child.setParent(this);
	}
}
