package com.schraitle.flatblox.shapes;

/**
 * holds the location of a point in the system
 * 
 * @author David
 *
 */
public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "x:" + x + ",y:" + y;
	}
}
