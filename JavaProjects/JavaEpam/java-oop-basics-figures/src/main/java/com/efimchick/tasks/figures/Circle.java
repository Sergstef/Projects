package com.efimchick.tasks.figures;

import static java.lang.Math.pow;
import static java.lang.Math.PI;

class Circle extends Figure{
	
	private Point center;
	private double radius;

	public Circle(Point center, double radius) {
		super();
		if(radius == 0 || radius < 0 || center == null) {
			throw new RuntimeException();
		}
		this.center = center;
		this.radius = radius;
	}

	@Override
	public double area() {
		
		return pow(radius * 2, 2) / 4 * PI;
	}

	@Override
	public String toString() {
		return "Circle[(" + center.getX()+ "," + center.getY() + ")"
				+ radius + "]";
	}

	@Override
	public Point centroid() {
		return center;
	}

	@Override
	public boolean isTheSame(Figure figure) {
		// TODO Auto-generated method stub
		return false;
	}

}
