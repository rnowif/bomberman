package com.lite.bomberman.map;

import java.util.HashMap;
import java.util.Map;

public class GameTree {

	private final Map<Integer, GameNode> nodesByDepth;

	public GameTree() {
		this.nodesByDepth = new HashMap<Integer, GameNode>();
	}

}
