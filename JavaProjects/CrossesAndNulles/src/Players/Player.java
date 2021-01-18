package Players;


public class Player {
	private String symbol;
	private String name;

	public Player(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public Player() {
	}

	
	public String getName() {
		return name;
	}

}
