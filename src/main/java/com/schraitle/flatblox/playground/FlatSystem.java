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

	public InsertStatus add(Shape shape) {
		if (shape == null) {
			return InsertStatus.NULL_SHAPE;
		}
		if (shape.getPosition() == null) {
			return InsertStatus.NO_COORDINATES;
		}

		if (shapeOverlapsWithOtherShapes(shape)) {
			return InsertStatus.OVERLAP;
		}

		if (shapeOutOfBounds(shape)) {
			return InsertStatus.OUT_OF_BOUNDS;
		}

		addShapeToSystem(shape);
		return InsertStatus.SUCCESS;
	}

	private boolean shapeOutOfBounds(Shape shape) {
		Dimension size = shape.getSize();
		Coordinate position = shape.getPosition();
		boolean isOut = false;
		
		//crossing the left
		isOut = isOut || position.getX() - (size.getWidth() / 2.0) < 0;
		//crossing the right
		isOut = isOut || position.getX() + (size.getWidth() / 2.0) > this.width;
		//crossing the top
		isOut = isOut || position.getY() + (size.getHeight() / 2.0) > this.height;
		//crossing the bottom
		isOut = isOut || position.getY() - (size.getHeight() / 2.0) < 0;
		
		return isOut;
	}

	private boolean shapeOverlapsWithOtherShapes(Shape shape) {
		for (Shape otherShape : shapes) {
			if(shape == otherShape) {
				continue;
			}
			if (shape.overlapsWith(otherShape)) {
				return true;
			}
		}
		return false;
	}

	private void addShapeToSystem(Shape shape) {
		shapes.add(shape);
		freeSpace = freeSpace - shape.getArea();
	}

	private void removeShapeFromSystem(Shape shape) {
		shapes.remove(shape);
		freeSpace = freeSpace + shape.getArea();
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public double getFreeSpace() {
		return freeSpace;
	}

	@Override
	public InsertStatus move(Shape shape, Coordinate newPosition) {
		Coordinate oldPos = shape.getPosition();
		removeShapeFromSystem(shape);
		shape.setPosition(newPosition);
		
		InsertStatus status = add(shape);
		
		if(!InsertStatus.SUCCESS.equals(status)) {
			shape.setPosition(oldPos);
			add(shape);
		}
		return status;
	}

	@Override
	public InsertStatus resize(Shape shape, int... properties) {
		int[] oldProps = shape.getSizeProperties();
		removeShapeFromSystem(shape);
		shape.changeSize(properties);
		
		InsertStatus status = add(shape);
		
		if(!InsertStatus.SUCCESS.equals(status)) {
			shape.changeSize(oldProps);
			add(shape);
		}
		return status;
	}

}
