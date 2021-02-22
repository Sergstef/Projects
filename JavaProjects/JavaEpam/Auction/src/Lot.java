
public class Lot {
	private double startPrice;
	private String name;
	
	public Lot(double startPrice, String name) {
		super();
		this.startPrice = startPrice;
		this.name = name;
	}
	
	public double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public synchronized boolean raise(double raiseNumber) {
		if (raiseNumber != 0 || raiseNumber != -1){
			this.setStartPrice(this.getStartPrice() + raiseNumber);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Lot [startPrice=" + startPrice + ", name=" + name + "]";
	}
	
	
}
