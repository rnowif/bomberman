package com.lite.bomberman.strategy;

import com.lite.bomberman.map.GameMap;
import com.lite.bomberman.map.GameMapAnalyzer;

public class StrategyBuilder {

	public static Strategy build(GameMap gameMap) {

		// Les bonus se cumulent (!!) donc il ne faut jamais se trouver dans la
		// m�me direction qu'une bombe (m�me loin)
		// Les bombes ne d�truisent qu'une seule case

		GameMapAnalyzer analyzer = new GameMapAnalyzer(gameMap);

		// On applique le pattern COR pour indiquer quelle strat�gie adopter en priorit�, et celle qu'il faut adopter si la premi�re ne fonctionne pas.
		// La premi�re � adopter est de se cacher, ensuite on tue, on cherche des bonus et enfin on cherche � sortir de sa zone.
		GetAwayStrategy getAwayStrategy = new GetAwayStrategy(analyzer);

		SeekBonusStrategy seekBonusStrategy = new SeekBonusStrategy(analyzer);
		seekBonusStrategy.setNextStrategy(getAwayStrategy);

		KillStrategy killStrategy = new KillStrategy(analyzer);
		killStrategy.setNextStrategy(seekBonusStrategy);

		HideStrategy hideStrategy = new HideStrategy(analyzer);
		hideStrategy.setNextStrategy(killStrategy);

		return hideStrategy;
	}
}
