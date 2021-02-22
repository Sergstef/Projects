import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Participant extends Thread{
	public Lot lot;
	public String name;
	public double wallet;
	
	public Participant(String name, double wallet) {
		super();
		this.name = name;
		this.wallet = wallet;
	}

	@Override
	public void run() {
		System.out.println("Игрок " + name);
		System.out.println(lot);
		System.out.println("Введите сумму");
		double number = new Scanner(System.in).nextDouble();
		if (lot.getStartPrice() + number > this.wallet) {
			System.out.println("Player " + name + " was blocked for 5 minutes.");
			try {
				this.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(lot.raise(number)) {
			System.out.println("Raised success");
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Игрок " + name);
			System.out.println(lot);
			System.out.println("Введите сумму");
			number = new Scanner(System.in).nextDouble();
			if (lot.getStartPrice() + number > this.wallet) {
				System.out.println("Player " + name + " was blocked for 5 minutes.");
				try {
					this.sleep(300000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(number == -1) {
			wallet = wallet - lot.getStartPrice();
		}
		System.out.println("Finished " + name);
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	

}
