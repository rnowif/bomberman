package com.open.bomberman.strategy;

import com.open.bomberman.map.GameMapAnalyzer;

public class SeekBonusStrategy extends Strategy {

	public SeekBonusStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		// Si on a pas de bonus à portée, pas besoin de les chercher
		if (!getAnalyzer().hasBonusAtRange(0)) {
			return performNextStrategy();
		}

		return null;
	}

}
