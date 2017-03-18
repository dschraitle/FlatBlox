package com.schraitle.flatblox.shapes;

import java.util.Arrays;

/**
 * Class representing an object in virtual space
 *
 */
public abstract class Shape {
	Coordinate position;

	/**
	 * @return the current position of the shape
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * change the position of the shape
	 * 
	 * @param newPosition
	 *            the new position of the shape
	 */
	public void setPosition(Coordinate newPosition) {
		this.position = newPosition;
	}

	/**
	 * checks to see if this shape will overlap with the given shape. It
	 * achieves this by using a bounding circle around the entire object, which
	 * will lead to false negatives - especially for oblong shapes
	 * 
	 * @param otherShape
	 *            another shape to check for overlap
	 * @return true if the shapes will overlap, false otherwise
	 */
	public boolean overlapsWith(Shape otherShape) {
		double distanceBetween = getDistanceBetween(getPosition(), otherShape.getPosition());
		double thisRadius = getRadius();
		double otherRadius = otherShape.getRadius();

		return distanceBetween < thisRadius + otherRadius;
	}

	/**
	 * @return the area taken up by this shape
	 */
	public abstract double getArea();

	/**
	 * gets the height and width of the shape
	 * 
	 * @return the height and width of the shape
	 */
	public abstract Dimension getSize();

	/**
	 * gets the farthest distance from the center position point of the shape
	 * 
	 * @return farthest distance from the center position of the shape
	 */
	public abstract double getRadius();

	public abstract int[] getSizeProperties();

	public abstract void changeSize(int... newSize);

	protected static double getDistanceBetween(Coordinate pos1, Coordinate pos2) {
		double xs = Math.pow(pos2.getX() - pos1.getX(), 2);
		double ys = Math.pow(pos2.getY() - pos1.getY(), 2);
		return Math.sqrt(xs + ys);
	}
	
	@Override
	public String toString() {
		return "Properties: " + Arrays.toString(getSizeProperties())
			+ " Area: " + getArea()
			+ " Position: " + position.toString()
			;
	}
}
