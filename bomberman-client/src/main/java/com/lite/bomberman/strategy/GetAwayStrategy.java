package com.lite.bomberman.strategy;

import java.awt.Point;
import java.util.Iterator;

import com.lite.bomberman.map.GameCellEnum;
import com.lite.bomberman.map.GameMapAnalyzer;
import com.lite.bomberman.map.GamePath;

public class GetAwayStrategy extends Strategy {

	public GetAwayStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		// Si on a pas de bombe à disposition, on ne fait rien
		if (!getAnalyzer().hasBombLeft()) {
			return performNextStrategy();
		}

		PlayerAction action = new PlayerAction();

		Point myPosition = getAnalyzer().getPlayerPosition(0);

		// On cherche à se diriger vers une case de type "mur" pour le détruire.
		GamePath path = getAnalyzer().getClosestCell(myPosition, GameCellEnum.WALL);

		if (path == null) {
			return performNextStrategy();
		}

		// Si on est collé au mur, on pose une bombe et on s'enfuit.
		if (path.getLength() == 1) {

			getAnalyzer().setMyBomb(myPosition);
			action.setBombToDrop(true);
			path = getAnalyzer().getSafePath(myPosition);

			// S'il n'y a pas de chemin sûr, on ne va pas se suicider donc on ne fait rien.
			if (path == null) {
				getAnalyzer().deleteMyBomb();
				action.setBombToDrop(false);
			}
		}

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
