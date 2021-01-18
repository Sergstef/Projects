package lab6.fraction;

public class Fraction {
	private int num;
	private int denom;

	public Fraction() {
		num = 0;
		denom = 1;
	}

	public Fraction(int num, int denom) {
		if(denom != 0) {
			this.num = num;
			this.denom = denom;
		} else {
			System.out.println("Error!");
		}
	}

	public Fraction(int a) {
		this(a, 1);
	}

	public void reduction() {
		int den;
		if (num <= denom) {
			den = num;
			while (den > 1) {
				if ((denom % den == 0) && (num % den == 0)) {
					num = num / den;
					denom = denom / den;
					den--;
				} else
					den--;
			}
		} else {
			den = denom;
			while (den > 1) {
				if ((num % den == 0) && (denom % den == 0)) {
					num = num / den;
					denom = denom / den;
					den--;
				} else
					den--;
			}
		}

	}

	public void add(Fraction[] f) {
		for (int i = 0; i < f.length; i++) {
			num = num * f[i].denom + denom * f[i].num;
			denom *= f[i].denom;
		}
	}

	public void multiplication(Fraction b) {
		this.num = this.num*b.getNum();
		this.denom = this.denom*b.getDenom();
		
	}

	public void division(Fraction b) {
		this.num = this.num*b.getDenom();
		this.denom = this.denom*b.getNum();
	}
	public void plus(Fraction b) {
		this.num = this.num*b.getDenom() + b.getNum()*this.denom;
		this.denom = this.denom*b.getDenom(); 
	}
	public void minus(Fraction b) {
		this.num = this.num*b.getDenom() - b.getNum()*this.denom;
		this.denom = this.denom*b.getDenom(); 
	}

	public void print() {
		if ((denom != 1) && (num != 0))
			System.out.println(num + "/" + denom);
		else
			System.out.println(num);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getDenom() {
		return denom;
	}

	public void setDenom(int denom) {
		this.denom = denom;
	}

	

}
