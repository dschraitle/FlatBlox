package com.schraitle.flatblox.shapes.impl;

import com.schraitle.flatblox.shapes.Dimension;
import com.schraitle.flatblox.shapes.Shape;

public class Circle extends Shape {
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return 2 * Math.PI * Math.pow(radius, 2);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(radius * 2, radius * 2);
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public int[] getSizeProperties() {
		return new int[]{radius};
	}

	@Override
	public void changeSize(int... newSize) {
		if(newSize.length > 1) {
			return;
		}
		this.radius = newSize[0];
	}

}
