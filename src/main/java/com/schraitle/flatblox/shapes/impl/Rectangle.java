package com.schraitle.flatblox.shapes.impl;

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
	public double getArea() {
		return height * width;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	@Override
	public double getRadius() {
		// distance formula from center of object, to extreme corner
		return Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height / 2, 2));
	}

	@Override
	public void changeSize(int... newSize) {
		if (newSize.length < 2) {
			return;
		}
		this.width = newSize[0];
		this.height = newSize[1];
	}

	@Override
	public int[] getSizeProperties() {
		return new int[] { width, height };
	}

}
