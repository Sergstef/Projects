package lab6.fraction;

import java.util.ArrayList;

public class NewMain {

	public static void main(String[] args) {
		ArrayList<Fraction> fractions = new ArrayList<Fraction>();
		Fraction fraction1 = new Fraction(10,20);
		Fraction fraction2 = new Fraction(10,30);
		Fraction fraction3 = new Fraction(10,40);
		Fraction fraction4 = new Fraction(10,50);
		Fraction fraction5 = new Fraction(17,34);
		Fraction fraction6 = new Fraction(3,9);
		Fraction fraction7 = new Fraction(2,16);
		fraction1.minus(fraction6);
		fraction1.print();
		
		fractions.add(fraction1);
		fractions.add(fraction2);
		fractions.add(fraction3);
		fractions.add(fraction4);
		fractions.add(fraction5);
		fractions.add(fraction6);
		fractions.add(fraction7);
		System.out.print("Дроби в списке: ");
		for(Fraction a: fractions) {
			a.reduction();
			System.out.print(a.getNum() + "/" + a.getDenom() + "," );
		}
		System.out.println();
		Fraction myOwnFraction = new Fraction(10,50);
		for(Fraction a: fractions) {
			if(a.equals(myOwnFraction)) {
				System.out.println(myOwnFraction.getNum() + "/" + myOwnFraction.getDenom() + " - такая дробь есть в списке");
			}
		}

	}

}
