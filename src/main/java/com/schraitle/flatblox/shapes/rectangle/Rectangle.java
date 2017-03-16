package com.schraitle.flatblox.shapes.rectangle;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Dimension;
import com.schraitle.flatblox.shapes.Shape;

public class Rectangle extends Shape {
	int height;
	int width;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public long getArea() {
		return height * width;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	@Override
	public double getRadius() {
		return Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height / 2, 2));
	}

	@Override
	public void changeSize(int... newSize) {
		if(newSize.length > 2) {
			return;
		}
		this.width = newSize[0];
		this.height = newSize[1];
	}

	@Override
	public int[] getProperties() {
		return new int[]{width, height};
	}

}
