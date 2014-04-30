package com.lite.bomberman.strategy;

import com.lite.bomberman.map.GameMapAnalyzer;

public class SeekBonusStrategy extends Strategy {

	public SeekBonusStrategy(GameMapAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public PlayerAction performStrategy() {

		// Si on a pas de bonus � port�e, pas besoin de les chercher
		if (!getAnalyzer().hasBonusAtRange(0)) {
			return performNextStrategy();
		}

		return null;
	}

}
