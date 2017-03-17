package com.schraitle.flatblox;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import com.schraitle.flatblox.playground.CoordinateSystem;
import com.schraitle.flatblox.playground.CoordinateSystem.InsertStatus;
import com.schraitle.flatblox.playground.FlatSystem;
import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;
import com.schraitle.flatblox.shapes.impl.Circle;
import com.schraitle.flatblox.shapes.impl.Rectangle;
import com.schraitle.flatblox.shapes.impl.Square;

public class ShapeTestingBase {

	private static final int SHAPE_WIDTH = 5;
	private static final int SHAPE_HEIGHT = 7;
	protected static final int SHAPE_AREA = SHAPE_WIDTH * SHAPE_HEIGHT;
	private static final int DEFAULT_HEIGHT = 25;
	private static final int DEFAULT_WIDTH = 20;
	protected static final int DEFAULT_FREE_SPACE = DEFAULT_WIDTH * DEFAULT_HEIGHT;
	protected Coordinate firstPosition = new Coordinate(5, 5);
	protected Coordinate secondPosition = new Coordinate(10, 10);
	protected CoordinateSystem system;
	protected Shape rectangle;
	protected Shape square;
	protected Shape circle;
	protected Coordinate slightlyOOBPosition = new Coordinate(1, SHAPE_HEIGHT);
	protected Coordinate oobPosition = new Coordinate(DEFAULT_WIDTH + 3, DEFAULT_HEIGHT);

	public ShapeTestingBase() {
		super();
	}

	@Before
	public void setup() {
		system = new FlatSystem(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		rectangle = new Rectangle(SHAPE_WIDTH, SHAPE_HEIGHT);
		rectangle.setPosition(firstPosition);
		square = new Square(SHAPE_WIDTH);
		square.setPosition(firstPosition);
		
		circle = new Circle(SHAPE_WIDTH);
		circle.setPosition(firstPosition);
	}

	protected void assertShapeResizeSuccess(Shape shape, int... properties) {
		assertEquals("resize should be successful", InsertStatus.SUCCESS, system.resize(shape, properties));
	}

	protected void assertShapeResizeOOB(Shape shape, int... properties) {
		assertEquals("resize should be out of bounds", InsertStatus.OUT_OF_BOUNDS, system.resize(shape, properties));
	}

	protected void assertShapeResizeOverlap(Shape shape, int... properties) {
		assertEquals("resize should be overlapping", InsertStatus.OVERLAP, system.resize(shape, properties));
	}

	protected void assertSuccessfulMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should have succeeded", InsertStatus.SUCCESS, moveStatus);
	}

	protected void assertOOBMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should be out of bounds", InsertStatus.OUT_OF_BOUNDS, moveStatus);
	}

	protected void assertOverlapMove(Shape shape, Coordinate position) {
		InsertStatus moveStatus = system.move(shape, position);
		assertEquals("the move should be overlapping", InsertStatus.OVERLAP, moveStatus);
	}

	protected void successfullyAddShape(Shape shape) {
		assertEquals("add should be successful", InsertStatus.SUCCESS, system.add(shape));
	}

	protected void addOutOfBoundsShape(Shape shape) {
		assertEquals("shape should be out of bounds", InsertStatus.OUT_OF_BOUNDS, system.add(shape));
	}

	protected void addOverlappingShape(Shape shape) {
		assertEquals("shape should be overlapping", InsertStatus.OVERLAP, system.add(shape));
	}

}