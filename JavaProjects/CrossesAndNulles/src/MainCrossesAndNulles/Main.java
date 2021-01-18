package MainCrossesAndNulles;

import GameMap.MapImpl;
import GameProcess.Game;
import Map.Map;
import Players.Player;

public class Main {

	public static void main(String[] args) {
		Map mainMap = new MapImpl(3);
		Player first = new Player("Ivan", "+");
		Player second = new Player("Sergey", "0");
		Game play = new Game(first,second,mainMap);
		play.process();

	}

}
