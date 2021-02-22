package com.efimchick.tasks.figures;

import java.io.IOException;
import static java.lang.Math.abs;

//TODO
class Triangle extends Figure{
	
	private Point a;
	private Point b;
	private Point c;
	
	

	public Triangle(Point a, Point b, Point c) {
		super();
		if(a == null || b == null || c == null) {
			throw new RuntimeException();
		} else if(getTwiceSquare(a, b, c) == 0) {
			throw new RuntimeException();
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}
	

	@Override
	public double area() {
		return abs(0.5 * (a.getX() * (b.getY() - c.getY()) + b.getX()
						* (c.getY() - a.getY()) + c.getX()
						* (a.getY() - b.getY())));
	}
	
	

	@Override
	public String toString() {
		return "Triangle[(" + a.getX()+ "," + a.getY() + ")("
				+ b.getX()+ "," + b.getY() + ")("
				+ c.getX()+ "," + c.getY() + ")]";
	}


	@Override
	public Point centroid() {
		double x = (a.getX() + b.getX() + c.getX()) / 3;
		double y = (a.getY() + b.getY() + c.getY()) / 3;
		return new Point(x, y);
	}

	@Override
	public boolean isTheSame(Figure figure) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static double getTwiceSquare(Point a, Point b, Point c) {
		return a.getX() * (b.getY() - c.getY()) + b.getX()
						* (c.getY() - a.getY()) + c.getX()
						* (a.getY() - b.getY());
	}

}
