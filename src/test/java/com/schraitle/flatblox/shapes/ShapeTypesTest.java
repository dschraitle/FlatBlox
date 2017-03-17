package com.schraitle.flatblox.shapes;

import org.junit.Test;

import com.schraitle.flatblox.ShapeTestingBase;

public class ShapeTypesTest extends ShapeTestingBase {
	
	@Test
	public void squaresCanBeAdded() {
		successfullyAddShape(square);
	}

	@Test
	public void circlesCanBeAdded() {
		successfullyAddShape(circle);
	}
	
	@Test
	public void circlesCanGoOOB() {
		circle.setPosition(new Coordinate(2, 2));
		addOutOfBoundsShape(circle);
	}
}
