package com.schraitle.flatblox.playground;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.schraitle.flatblox.playground.CoordinateSystem.InsertStatus;
import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;
import com.schraitle.flatblox.shapes.rectangle.Rectangle;

public class FlatSystemTest {

	private static final int SHAPE_WIDTH = 5;
	private static final int SHAPE_HEIGHT = 7;
	private static final int SHAPE_AREA = SHAPE_WIDTH * SHAPE_HEIGHT;
	private static final int DEFAULT_HEIGHT = 25;
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_FREE_SPACE = DEFAULT_WIDTH * DEFAULT_HEIGHT;

	Coordinate firstPosition = new Coordinate(5, 5);
	Coordinate secondPosition = new Coordinate(10, 10);
	FlatSystem system;

	Shape defaultShape;
	private Coordinate slightlyOOBPosition = new Coordinate(1, SHAPE_HEIGHT);
	private Coordinate oobPosition = new Coordinate(DEFAULT_WIDTH + 3, DEFAULT_HEIGHT);

	@Before
	public void setup() {
		system = new FlatSystem(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		defaultShape = new Rectangle(SHAPE_WIDTH, SHAPE_HEIGHT);
		defaultShape.setPosition(firstPosition);
	}

	@Test
	public void flatSystemDoesntAddNullShape() {
		assertEquals("add should return NULL_SHAPE", InsertStatus.NULL_SHAPE, system.add(null));
	}

	@Test
	public void flatSystemAddsAShape() {
		successfullyAddShape(defaultShape);
	}

	@Test
	public void overlappingShapeFails() {
		Shape secondShape = new Rectangle(5, 5);
		secondShape.setPosition(firstPosition);

		system.add(defaultShape);
		addOverlappingShape(secondShape);
	}

	@Test
	public void barelyOverlappingShapeFails() {
		flatSystemAddsAShape();
		Shape secondShape = new Rectangle(5, 5);
		secondShape.setPosition(new Coordinate(7, 7));

		addOverlappingShape(secondShape);
	}

	@Test
	public void shapeWithPositionOutOfBoundsFails() {
		defaultShape.setPosition(oobPosition);
		addOutOfBoundsShape(defaultShape);
	}

	@Test
	public void shapeALittleOutOfBoundsFails() {
		defaultShape.setPosition(slightlyOOBPosition);
		addOutOfBoundsShape(defaultShape);
	}

	@Test
	public void initialFreeSpaceCorrect() {
		assertEquals("free space should be correct", DEFAULT_FREE_SPACE, system.getFreeSpace());
	}

	@Test
	public void freeSpaceCorrectAfterAddingAShape() {
		system.add(defaultShape);
		assertEquals(DEFAULT_FREE_SPACE - SHAPE_AREA, system.getFreeSpace());
	}

	@Test
	public void shapeMovesSuccessFully() {
		flatSystemAddsAShape();
		assertSuccessfulMove(defaultShape, secondPosition);
		assertEquals("the shape should now be at secord position", secondPosition, defaultShape.getPosition());
	}

	@Test
	public void shapeFailsToMoveOOB() {
		flatSystemAddsAShape();
		assertOOBMove(defaultShape, oobPosition);
		assertOOBMove(defaultShape, slightlyOOBPosition);
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
		flatSystemAddsAShape();
		Shape secondShape = new Rectangle(3, 3);
		secondShape.setPosition(secondPosition);
		successfullyAddShape(secondShape);
		assertOverlapMove(defaultShape, secondPosition);
	}

	private void assertSuccessfulMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should have succeeded", InsertStatus.SUCCESS, moveStatus);
	}

	private void assertOOBMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should be out of bounds", InsertStatus.OUT_OF_BOUNDS, moveStatus);
	}

	private void assertOverlapMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should be overlapping", InsertStatus.OVERLAP, moveStatus);
	}

	private void successfullyAddShape(Shape shape) {
		assertEquals("add should be successful", InsertStatus.SUCCESS, system.add(shape));
	}

	private void addOutOfBoundsShape(Shape shape) {
		assertEquals("shape should be out of bounds", InsertStatus.OUT_OF_BOUNDS, system.add(shape));
	}

	private void addOverlappingShape(Shape shape) {
		assertEquals("shape should be overlapping", InsertStatus.OVERLAP, system.add(shape));
	}

}
