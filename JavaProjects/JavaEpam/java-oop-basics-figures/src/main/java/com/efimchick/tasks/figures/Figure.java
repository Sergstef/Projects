package com.efimchick.tasks.figures;

abstract class Figure{

    public abstract double area();

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    public String toString() {
        throw new UnsupportedOperationException();
    }
    
    public static void main(String[] args) {
    	Point a = new Point(0, 0);
        Point b = new Point(1, 10);
        Point c = new Point(11, 11);
        Point d = new Point(19, 2);

        final Quadrilateral abcd = new Quadrilateral(a, b, c, d);
        final Quadrilateral abcd_clone = new Quadrilateral(new Point(0, 0), new Point(1, 10), new Point(11, 11), new Point(19, 2));

        System.out.println(abcd.centroid());
        System.out.println(abcd_clone.centroid());
	}
}
