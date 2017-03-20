package com.schraitle.flatblox.shapes.impl;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Dimension;
import com.schraitle.flatblox.shapes.Shape;

/**
 * EXPERIMENTAL - this shape type will most likely not work right now and has
 * not been tested
 * 
 * @author David
 *
 */
public class Triangle extends Shape {
	// corners of the triangle
	Coordinate corner1;
	Coordinate corner2;
	Coordinate corner3;

	// stats to be used for calculating area and whatnot
	int max;
	int min;
	int left;
	int right;

	/**
	 * create a triangle, giving the corners in a clockwise fashion, starting
	 * from the top left of the shape. The coordinates for the corners will be
	 * relative to the center of the triangle
	 * 
	 * @param corner1
	 * @param corner2
	 * @param corner3
	 */
	public Triangle(Coordinate corner1, Coordinate corner2, Coordinate corner3) {
		this.corner1 = corner1;
		updateExtremes(corner1);

		this.corner2 = corner2;
		updateExtremes(corner2);

		this.corner3 = corner3;
		updateExtremes(corner3);
	}

	private void updateExtremes(Coordinate corner) {
		if (max < corner.getY()) {
			max = corner.getY();
		}
		if (min > corner.getY()) {
			min = corner.getY();
		}
		if (left > corner.getX()) {
			left = corner.getX();
		}
		if (right < corner.getY()) {
			right = corner.getY();
		}
	}

	@Override
	public double getArea() {
		return .5 * getBase() * getHeight();
	}

	/**
	 * get the base of the triangle. The base will be defined as the edge
	 * between corner2 and corner3
	 * 
	 * @return length of the base
	 */
	private double getBase() {
		return getDistanceBetween(corner2, corner3);
	}

	/**
	 * get the height of the triangle. height will be the distance from the
	 * plane of corner2 and corner3, to corner 1
	 * 
	 * @return the height of the triangle
	 */
	private double getHeight() {
		return max - min; // this will only be correct when the base of the
							// triangle is aligned with the x axis;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(right - left, max - min);
	}

	@Override
	public double getRadius() {
		// distance formula from center of object, to extreme corner
		return Math.sqrt(Math.pow((right - left) / 2, 2) + Math.pow((max - min) / 2, 2));
	}

	@Override
	public int[] getSizeProperties() {
		return new int[] { corner1.getX(), corner1.getY(), corner2.getX(), corner2.getY(), corner3.getX(),
				corner3.getY() };
	}

	@Override
	public void changeSize(int... newSize) {
		if (newSize.length < 6) {
			return;
		}
		corner1 = new Coordinate(newSize[0], newSize[1]);
		corner2 = new Coordinate(newSize[2], newSize[3]);
		corner3 = new Coordinate(newSize[4], newSize[5]);

		max = min = left = right = 0;
		updateExtremes(corner1);
		updateExtremes(corner2);
		updateExtremes(corner3);
	}

}
