package com.schraitle.flatblox.playground;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.schraitle.flatblox.ShapeTestingBase;
import com.schraitle.flatblox.playground.CoordinateSystem.InsertStatus;
import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;
import com.schraitle.flatblox.shapes.impl.Rectangle;

public class FlatSystemTest extends ShapeTestingBase {

	private static final double DOUBLE_EPSILON = .001;

	@Test
	public void flatSystemDoesntAddNullShape() {
		assertEquals("add should return NULL_SHAPE", InsertStatus.NULL_SHAPE, system.add(null));
	}

	@Test
	public void flatSystemAddsAShape() {
		successfullyAddShape(rectangle);
	}

	@Test
	public void overlappingShapeFails() {
		Shape secondShape = new Rectangle(5, 5);
		secondShape.setPosition(firstPosition);

		system.add(rectangle);
		addOverlappingShape(secondShape);
	}

	@Test
	public void barelyOverlappingShapeFails() {
		successfullyAddShape(rectangle);
		Shape secondShape = new Rectangle(5, 5);
		secondShape.setPosition(new Coordinate(7, 7));

		addOverlappingShape(secondShape);
	}

	@Test
	public void shapeWithPositionOutOfBoundsFails() {
		rectangle.setPosition(oobPosition);
		addOutOfBoundsShape(rectangle);
	}

	@Test
	public void shapeALittleOutOfBoundsFails() {
		rectangle.setPosition(slightlyOOBPosition);
		addOutOfBoundsShape(rectangle);
	}
	
	@Test
	public void shapeOneUnitOutOfBoundsFails() {
		rectangle.changeSize(10,10);
		rectangle.setPosition(new Coordinate(4, 10));
		addOutOfBoundsShape(rectangle);
	}
	
	@Test
	public void shapeHalfUnitOutOfBoundsFails() {
		rectangle.changeSize(5, 5);
		rectangle.setPosition(new Coordinate(2, 10));
		addOutOfBoundsShape(rectangle);
	}

	@Test
	public void initialFreeSpaceCorrect() {
		assertEquals("free space should be correct", DEFAULT_FREE_SPACE, system.getFreeSpace(), DOUBLE_EPSILON);
	}

	@Test
	public void freeSpaceCorrectAfterAddingAShape() {
		system.add(rectangle);
		assertEquals(DEFAULT_FREE_SPACE - SHAPE_AREA, system.getFreeSpace(), DOUBLE_EPSILON);
	}

	@Test
	public void shapeMovesSuccessFully() {
		successfullyAddShape(rectangle);
		assertSuccessfulMove(rectangle, secondPosition);
		assertEquals("the shape should now be at secord position", secondPosition, rectangle.getPosition());
	}

	@Test
	public void shapeFailsToMoveOOB() {
		successfullyAddShape(rectangle);
		assertOOBMove(rectangle, oobPosition);
		assertOOBMove(rectangle, slightlyOOBPosition);
		assertEquals("shape is still in original position", firstPosition, system.getShapes().get(0).getPosition());
	}

	@Test
	public void shapeCanBeAddedToOldPositionAfterMove() {
		shapeMovesSuccessFully();
		Shape newShape = new Rectangle(5, 5);
		newShape.setPosition(firstPosition);
		successfullyAddShape(newShape);
	}
	
	@Test
	public void shapeFailsToOverlapAfterMove() {
		successfullyAddShape(rectangle);
		Shape secondShape = new Rectangle(3, 3);
		secondShape.setPosition(secondPosition);
		successfullyAddShape(secondShape);
		assertOverlapMove(rectangle, secondPosition);
	}
	
	@Test
	public void shapeCanResize() {
		successfullyAddShape(rectangle);
		assertShapeResizeSuccess(rectangle, 10, 10);
		assertEquals("area of shape should be 100", 100, rectangle.getArea(), DOUBLE_EPSILON);
		assertEquals("free space in system should be less", DEFAULT_FREE_SPACE - 100, system.getFreeSpace(), DOUBLE_EPSILON);
	}

	@Test
	public void shapeFailsToResizeOOB() {
		successfullyAddShape(rectangle);
		double orignalArea = rectangle.getArea();
		assertShapeResizeOOB(rectangle, 11, 6);
		assertEquals("the shape should have the original area", orignalArea, rectangle.getArea(), DOUBLE_EPSILON);
	}
	
	@Test
	public void shapeFailsToResizeOverlap() {
		successfullyAddShape(rectangle);
		Shape secondShape = new Rectangle(2, 2);
		secondShape.setPosition(new Coordinate(firstPosition.getX() + 7, firstPosition.getY()));
		successfullyAddShape(secondShape);
		assertShapeResizeOverlap(rectangle, 10, 10);
	}

}
