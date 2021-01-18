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
		System.out.println("����: �������� � ������ ");
		System.out.println("������� ����: ������ ��� ������ �� ����� �������� 3 �� 3. ����� ��� ������� 1 ������ ������ +, ");
		System.out.println("������ ����� ������ ������ 0. ������ ����� �� �������, �� ������� �������. ���� ��������� �����������, ���� �����-�� ");
		System.out.println("�� ���� ������ ��������� �� ����� 3 � ��� ��� �� ����������. �������� ����! ");
		this.map.clearMap();
		boolean foundIt = true;
		while(foundIt) {
			System.out.println("������� ������(���� ����� ����� +, ������ 0; ���� ������ ��������� ���� ������� ���������� ����� x): ");
			Scanner n = new Scanner(System.in);
			String symbol = n.nextLine();
			if(symbol.equals("x")) {
				System.out.println("���� ���������");
				foundIt = false;
				break;
			}
			System.out.println("������� ������ � �������, � ������� ������ �������� ������(������ ������ - ������, ������ ������ - �������: ");
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
