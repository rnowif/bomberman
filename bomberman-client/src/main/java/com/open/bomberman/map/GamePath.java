package com.open.bomberman.map;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class GamePath implements Iterable<Point> {

	private final LinkedList<Point> path = new LinkedList<Point>();

	public void addPosition(Point point) {
		path.addLast(point);
	}

	public void removeLast() {
		path.removeLast();
	}

	public int getLength() {
		return path.size();
	}

	@Override
	public Iterator<Point> iterator() {
		return path.iterator();
	}

}
