package GameProcess;



import java.util.Scanner;
import Map.Map;
import Players.Player;

public class Game {
	private Map map; 
	private Player player1;
	private Player player2;
	
	public Game(Player currentPlayer1, Player currentPlayer2, Map currentMap) {
		this.player1 = currentPlayer1;
		this.player2 = currentPlayer2;
		this.map = currentMap;
	}
	public void printGame() {
		System.out.println(this.player1.getName());
		System.out.println(this.player2.getName());
		System.out.println(this.map.getMap()[1][0]);
		
	}
	public void process() {
		System.out.println("Игра: Крестики и Нолики ");
		System.out.println("Правила игры: играют два игрока на карте размером 3 на 3. Игрок под номером 1 играет знаком +, ");
		System.out.println("второй игрок играет знаком 0. Каждый ходит по очереди, не нарушая порядка. Игра считается законченной, если какой-то ");
		System.out.println("из двух знаков находится на карте 3 в ряд или по диагоналям. Приятной игры! ");
		this.map.clearMap();
		boolean foundIt = true;
		while(foundIt) {
			System.out.println("Введите символ(один игрок имеет +, другой 0; если хотите закончить игру введите английскую букву x): ");
			Scanner n = new Scanner(System.in);
			String symbol = n.nextLine();
			if(symbol.equals("x")) {
				System.out.println("Игра закончена");
				foundIt = false;
				break;
			}
			System.out.println("Введите строку и столбец, в которую хотите вставить символ(первый символ - строка, второй символ - столбец: ");
			Scanner a = new Scanner(System.in);
			int str = a.nextInt();
		    Scanner b = new Scanner(System.in);
		    int stolb = b.nextInt();
			this.map.addElement(str, stolb, symbol);
			this.map.toString();
			this.map.gameOver();
		}
	}
	
	


}
