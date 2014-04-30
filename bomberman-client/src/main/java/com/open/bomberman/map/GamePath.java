package com.open.bomberman.map;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class GamePath implements Iterable<Point> {

	private final LinkedList<Point> path = new LinkedList<Point>();

	public void addPositionLast(Point point) {
		path.addLast(point);
	}

	public void addPositionFirst(Point point) {
		path.addFirst(point);
	}

	public int getLength() {
		return path.size();
	}

	@Override
	public Iterator<Point> iterator() {
		return path.iterator();
	}

}
