package com.efimchick.tasks.figures;

import static java.lang.Math.abs;

//TODO
class Quadrilateral extends Figure{
	
	private Point a;
	private Point b;
	private Point c;
	private Point d;

	public Quadrilateral(Point a, Point b, Point c, Point d) {
		super();
		if (!(((getTwiceSquare(a, b, c) * getTwiceSquare(c, d, a)) > 0)
				&& ((getTwiceSquare(b, c, d) * getTwiceSquare(d, a, b)) > 0))) {
			throw new RuntimeException();
		}
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	public double area() {
		return abs((a.getX() * b.getY() - a.getY() * b.getX()) +
				(b.getX() * c.getY() - c.getX() * b.getY()) +
				(c.getX() * d.getY() - d.getX() * c.getY()) +
				(d.getX() * a.getY() - a.getX() * d.getY())) * 0.5;
	}
	
	

	@Override
	public String toString() {
		return "Quadrilateral[(" + a.getX()+ "," + a.getY() + ")("
				+ b.getX()+ "," + b.getY() + ")("
				+ c.getX()+ "," + c.getY() + ")("
				+ d.getX()+ "," + d.getY() + ")]";
	}

	@Override
	public Point centroid() {
		double areaABC = new Triangle(a, b, c).area();
		double areaADC = new Triangle(a, d, c).area();
		Point centroidABC = new Triangle(a, b, c).centroid();
		Point centroidADC = new Triangle(a, d, c).centroid();
		double x = (areaABC * centroidABC.getX() + areaADC * centroidADC.getX())
				/ (areaABC + areaADC);
		double y = (areaABC * centroidABC.getY() + areaADC * centroidADC.getY())
				/ (areaABC + areaADC);
		return new Point(x, y);
	}

	@Override
	public boolean isTheSame(Figure figure) {
		if(figure instanceof Quadrilateral) {
			figure = (Quadrilateral)figure;
		} else return false;
		Point x = figure.centroid();
		Point y = this.centroid();
		if(figure.area() == this.area() && x.getX() == y.getX() && x.getY() == y.getY()) {
			return true;
		}
		return false;
	}
	
	private static double getTwiceSquare(Point a, Point b, Point c) {
		return a.getX() * (b.getY() - c.getY()) + b.getX()
						* (c.getY() - a.getY()) + c.getX()
						* (a.getY() - b.getY());
	}

}
