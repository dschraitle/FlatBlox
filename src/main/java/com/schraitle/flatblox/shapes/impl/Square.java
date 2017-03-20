package com.schraitle.flatblox.shapes.impl;

public class Square extends Rectangle {

	public Square(int width) {
		super(width, width);
	}

	@Override
	public void changeSize(int... newSize) {
		super.changeSize(newSize[0]);
	}
}
