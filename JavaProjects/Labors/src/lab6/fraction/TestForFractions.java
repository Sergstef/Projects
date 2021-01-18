package lab6.fraction;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TestForFractions {

	@Test
	public void multiplication() {
		Fraction fraction6 = new Fraction(3,9);
		Fraction fraction1 = new Fraction(10,20);
		fraction1.multiplication(fraction6);
		int num = fraction1.getNum();
		int denom = fraction1.getDenom();	
		assertEquals(30,num);
		assertEquals(180,denom);
	}
	@Test
	public void division() {
		Fraction fraction6 = new Fraction(3,9);
		Fraction fraction1 = new Fraction(10,20);
		fraction1.division(fraction6);
		int num = fraction1.getNum();
		int denom = fraction1.getDenom();	
		assertEquals(90,num);
		assertEquals(60,denom);
	}
	@Test
	public void plus() {
		Fraction fraction6 = new Fraction(3,9);
		Fraction fraction1 = new Fraction(10,20);
		fraction1.plus(fraction6);
		int num = fraction1.getNum();
		int denom = fraction1.getDenom();	
		assertEquals(150,num);
		assertEquals(180,denom);
	}
	@Test
	public void minus() {
		Fraction fraction6 = new Fraction(3,9);
		Fraction fraction1 = new Fraction(10,20);
		fraction1.minus(fraction6);
		int num = fraction1.getNum();
		int denom = fraction1.getDenom();	
		assertEquals(30,num);
		assertEquals(180,denom);
	}
}
