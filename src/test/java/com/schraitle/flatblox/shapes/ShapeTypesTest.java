package com.schraitle.flatblox.shapes;

import org.junit.Test;

import com.schraitle.flatblox.ShapeTestingBase;
import com.schraitle.flatblox.shapes.impl.Triangle;

public class ShapeTypesTest extends ShapeTestingBase {

	@Test
	public void squaresCanBeAdded() {
		successfullyAddShape(square);
	}

	@Test
	public void squaresCanGoOOB() {
		square.setPosition(new Coordinate(2, 2));
		addOutOfBoundsShape(square);
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
	
	@Test
	public void canIEvenAddATriangle() {
		Shape triangle = new Triangle(new Coordinate(-2, 4), new Coordinate(3, -2), new Coordinate(-2, -2));
		triangle.setPosition(new Coordinate(5, 5));
		successfullyAddShape(triangle);
	}
}
