package com.schraitle.flatblox.playground;

import java.util.ArrayList;
import java.util.List;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Dimension;
import com.schraitle.flatblox.shapes.Shape;

public class FlatSystem implements CoordinateSystem {
	private int width;
	private int height;
	private double freeSpace;
	private List<Shape> shapes;

	public FlatSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.freeSpace = width * height;
		shapes = new ArrayList<Shape>();
	}

	public ShapeStatus add(Shape shape) {
		if (shape == null) {
			return ShapeStatus.NULL_SHAPE;
		}
		if (shape.getPosition() == null) {
			return ShapeStatus.NO_COORDINATES;
		}

		if (shapeOverlapsWithOtherShapes(shape)) {
			return ShapeStatus.OVERLAP;
		}

		if (shapeOutOfBounds(shape)) {
			return ShapeStatus.OUT_OF_BOUNDS;
		}

		addShapeToSystem(shape);
		return ShapeStatus.SUCCESS;
	}

	/**
	 * determines whether the given shape will fall outside the bounds of the
	 * system
	 * 
	 * @param shape
	 *            the shape under test
	 * @return true if the shape will be out of bounds, false otherwise
	 */
	private boolean shapeOutOfBounds(Shape shape) {
		Dimension size = shape.getSize();
		Coordinate position = shape.getPosition();
		boolean isOut = false;

		// crossing the left
		isOut = isOut || position.getX() - (size.getWidth() / 2.0) < 0;
		// crossing the right
		isOut = isOut || position.getX() + (size.getWidth() / 2.0) > this.width;
		// crossing the top
		isOut = isOut || position.getY() + (size.getHeight() / 2.0) > this.height;
		// crossing the bottom
		isOut = isOut || position.getY() - (size.getHeight() / 2.0) < 0;

		return isOut;
	}

	/**
	 * determines if the given shape will overlap with any existing shapes. If
	 * the shape already exists in the system, it will not be compared to
	 * itself.
	 * 
	 * @param shape
	 *            the shape under test
	 * @return true if the shape will overlap with any other shapes, false
	 *         otherwise
	 */
	private boolean shapeOverlapsWithOtherShapes(Shape shape) {
		for (Shape otherShape : shapes) {
			if (shape == otherShape) {
				continue;
			}
			if (shape.overlapsWith(otherShape)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * adds shape and keeps track of the area used by it
	 * 
	 * @param shape
	 *            the shape to add
	 */
	private void addShapeToSystem(Shape shape) {
		shapes.add(shape);
		freeSpace = freeSpace - shape.getArea();
	}

	/**
	 * removes shape and updates the total free area of system
	 * 
	 * @param shape
	 *            the shape being removed
	 * @return false if the shape did not exist in the system, true if removal
	 *         was successful
	 */
	private boolean removeShapeFromSystem(Shape shape) {
		if (!shapes.remove(shape)) {
			return false;
		}
		freeSpace = freeSpace + shape.getArea();
		return true;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public double getFreeSpace() {
		return freeSpace;
	}

	@Override
	public ShapeStatus move(Shape shape, Coordinate newPosition) {
		Coordinate oldPos = shape.getPosition();
		if (!removeShapeFromSystem(shape)) {
			return ShapeStatus.SHAPE_NOT_EXIST;
		}
		shape.setPosition(newPosition);

		ShapeStatus status = add(shape);

		if (!ShapeStatus.SUCCESS.equals(status)) {
			shape.setPosition(oldPos);
			add(shape);
		}
		return status;
	}

	@Override
	public ShapeStatus resize(Shape shape, int... properties) {
		int[] oldProps = shape.getSizeProperties();
		if (!removeShapeFromSystem(shape)) {
			return ShapeStatus.SHAPE_NOT_EXIST;
		}
		shape.changeSize(properties);

		ShapeStatus status = add(shape);

		if (!ShapeStatus.SUCCESS.equals(status)) {
			shape.changeSize(oldProps);
			add(shape);
		}
		return status;
	}

}
