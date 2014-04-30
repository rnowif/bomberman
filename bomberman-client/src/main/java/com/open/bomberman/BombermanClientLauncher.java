package com.open.bomberman;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.open.bomberman.map.GameCellEnum;
import com.open.bomberman.map.GameMap;
import com.open.bomberman.strategy.PlayerAction;
import com.open.bomberman.strategy.StrategyBuilder;

public class BombermanClientLauncher {

	public static void main(String[] args) throws IOException {
		new BombermanClientLauncher().run();
	}

	public void run() throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 9000);
		final PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		final GameMap gameMap = new GameMap();

		writer.println("zipka");
		writer.flush();

		// TODO :
		// - tâche qui va lire la map, la mettre à jour et activer un sémaphore
		// (barrière)
		// - tâche qui va attendre le sémaphore et qui va déterminer le coup à
		// jouer pour le jouer
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						for (int y = 0; y < GameMap.HEIGHT; ++y) {
							String[] elements = reader.readLine().split(" ");
							for (int x = 0; x < GameMap.WIDTH; ++x) {
								gameMap.set(x, y, GameCellEnum.fromCode(elements[x]));
								System.out.print(elements[x]);
							}
							System.out.println();
						}
						int playerCount = Integer.valueOf(reader.readLine());
						for (int i = 0; i < playerCount; ++i) {
							String[] elements = reader.readLine().split(" ");
							gameMap.setPlayer(i, new Point(Integer.valueOf(elements[0]), Integer.valueOf(elements[1])), elements[2].equals("ALIVE"));

						}

						PlayerAction action = StrategyBuilder.build(gameMap).performStrategy();
						if (action.getDirection() == null) {
							writer.print("NOP");
							System.out.print("NOP");
						} else {
							writer.print(action.getDirection().name());
							System.out.print(action.getDirection().name());
						}

						if (action.isBombToDrop()) {
							writer.print(" BOMB");
							System.out.print(" BOMB");
						}

						writer.println();
						writer.flush();
						System.out.println();
					}

				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}, "reading-thread").start();

	}
}
