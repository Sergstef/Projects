import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AuctionMain {

	public static void main(String[] args) {
		Lot lot1 = new Lot(1000, "ring");
		Participant p1 = new Participant("sergey", 1500);
		Participant p2 = new Participant("alexei", 1600);
		Participant p3 = new Participant("andrei", 1400);
		Participant p4 = new Participant("maria", 1500);
		Participant p5 = new Participant("vasiliy", 1600);
		ArrayList<Lot> lots = new ArrayList<Lot>();
		lots.add(lot1);
		for (Lot lot: lots) {
			p1.setLot(lot1);
			p2.setLot(lot1);
			p3.setLot(lot1);
			p4.setLot(lot1);
			p5.setLot(lot1);
			p1.start();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p2.start();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p3.start();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p4.start();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p5.start();
		}
		
	}

}
