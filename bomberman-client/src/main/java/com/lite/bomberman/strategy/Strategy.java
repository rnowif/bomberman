package com.lite.bomberman.strategy;

import com.lite.bomberman.map.GameMapAnalyzer;

public abstract class Strategy {

	private Strategy nextStrategy;
	private final GameMapAnalyzer analyzer;

	public Strategy(GameMapAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void setNextStrategy(Strategy nextStrategy) {
		this.nextStrategy = nextStrategy;
	}

	protected GameMapAnalyzer getAnalyzer() {
		return analyzer;
	}

	protected PlayerAction performNextStrategy() {
		if (nextStrategy == null) {
			return new PlayerAction();
		}
		return nextStrategy.performStrategy();
	}

	public abstract PlayerAction performStrategy();
}
