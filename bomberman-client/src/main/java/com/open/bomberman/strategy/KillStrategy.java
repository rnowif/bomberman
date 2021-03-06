package com.open.bomberman.strategy;

import com.open.bomberman.map.GameMapAnalyzer;

public class KillStrategy extends Strategy {

	public KillStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		// Si on a aucun ennemi � port�e, pas besoin de le tuer
		if (!getAnalyzer().hasEnemyAtRange(0)) {
			return performNextStrategy();
		}

		return null;
	}

}
