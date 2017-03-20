package com.schraitle.flatblox.shapes;

/**
 * contains the overall dimensions of a shape
 * 
 * @author David
 *
 */
public class Dimension {
	int width;
	int height;

	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "width: " + width + " height: " + height;
	}
}
