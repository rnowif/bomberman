package com.lite.bomberman.strategy;

import com.lite.bomberman.map.GameMapAnalyzer;

public class KillStrategy extends Strategy {

	public KillStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		// Si on a aucun ennemi à portée, pas besoin de le tuer
		if (!getAnalyzer().hasEnemyAtRange(0)) {
			return performNextStrategy();
		}

		return null;
	}

}
