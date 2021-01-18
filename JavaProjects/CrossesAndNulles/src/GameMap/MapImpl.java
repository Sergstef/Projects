package GameMap;

import java.util.Arrays;

import Map.Map;

public class MapImpl implements Map {
	private String[][] map;
	private int sizeOfMap;

	public MapImpl(int size) {
		this.sizeOfMap = size;
		this.map = new String[sizeOfMap][sizeOfMap];
	}

	public String toString() {
		for (int i = 0; i < map.length; i++) {
			/*
			 * for(int j=0; j < map[i].length;j++) { System.out.print(map[i][j] + " "); }
			 */
			System.out.println("Map [map=" + Arrays.toString(map[i]) + "]");
			// System.out.println();
		}
		return "Map [map=" + Arrays.toString(map) + "]";
	}

	@Override
	public void addElement(int a, int b, String typeOfElement) {
		if (this.map[a][b].equals("*")) {
			this.map[a][b] = typeOfElement;
		} else {
			System.out.println("Ошибка: выберите другое место");
		}
		
	}

	@Override
	public void clearMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = "*";
			}

		}

	}

	public int getSizeOfMap() {
		return sizeOfMap;
	}

	public void setSizeOfMap(int sizeOfMap) {
		this.sizeOfMap = sizeOfMap;
	}

	@Override
	public String[][] getMap() {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public void gameOver() {
		if (((this.map[0][0].equals("+")) & (this.map[0][1].equals("+")) & (this.map[0][2].equals("+")))
				| ((this.map[1][0].equals("+")) & (this.map[1][1].equals("+")) & (this.map[1][2].equals("+")))
				| ((this.map[2][0].equals("+")) & (this.map[2][1].equals("+")) & (this.map[2][2].equals("+")))
				| ((this.map[0][0].equals("+")) & (this.map[1][0].equals("+")) & (this.map[2][0].equals("+")))
				| ((this.map[0][1].equals("+")) & (this.map[1][1].equals("+")) & (this.map[2][1].equals("+")))
				| ((this.map[0][2].equals("+")) & (this.map[1][2].equals("+")) & (this.map[2][2].equals("+")))
				| ((this.map[0][0].equals("+")) & (this.map[1][1].equals("+")) & (this.map[2][2].equals("+")))
				| ((this.map[0][2].equals("+")) & (this.map[1][1].equals("+")) & (this.map[2][0].equals("+")))) {
			System.out.println("Игра окончена. Победил игрок, у которого +");
		} else {
			if (((this.map[0][0].equals("0")) & (this.map[0][1].equals("0")) & (this.map[0][2].equals("0")))
					| ((this.map[1][0].equals("0")) & (this.map[1][1].equals("0")) & (this.map[1][2].equals("0")))
					| ((this.map[2][0] == "0") & (this.map[2][1].equals("0")) & (this.map[2][2].equals("0")))
					| ((this.map[0][0].equals("0")) & (this.map[1][0].equals("0")) & (this.map[2][0].equals("0")))
					| ((this.map[0][1].equals("0")) & (this.map[1][1].equals("0")) & (this.map[2][1].equals("0")))
					| ((this.map[0][2].equals("0")) & (this.map[1][2].equals("0")) & (this.map[2][2].equals("0")))
					| ((this.map[0][0].equals("0")) & (this.map[1][1].equals("0")) & (this.map[2][2].equals("0")))
					| ((this.map[0][2].equals("0")) & (this.map[1][1].equals("0")) & (this.map[2][0].equals("0")))) {
				System.out.println("Игра окончена. Победил игрок, у которого 0");
			}
			else {
				System.out.println("Другой игрок должен сделать следующий ход");
			}
		}

	}
}
