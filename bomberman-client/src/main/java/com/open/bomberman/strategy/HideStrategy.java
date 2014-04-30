package com.open.bomberman.strategy;

import java.awt.Point;
import java.util.Iterator;

import com.open.bomberman.map.GameMapAnalyzer;
import com.open.bomberman.map.GamePath;

public class HideStrategy extends Strategy {

	public HideStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		PlayerAction action = new PlayerAction();

		Point myPosition = getAnalyzer().getPlayerPosition(0);

		// Si on n'est pas menacé par une bombe, on n'a pas besoin de se cacher
		if (!getAnalyzer().isThreatenedByBomb(myPosition)) {
			return performNextStrategy();
		}

		GamePath path = getAnalyzer().getSafePath(myPosition);
		if (path != null) {
			Iterator<Point> it = path.iterator();
			// On saute le premier point qui est la position actuelle.
			it.next();
			if (it.hasNext()) {
				action.setDirection(getAnalyzer().getDirection(myPosition, it.next()));
			}
		}

		return action;
	}

}
