package com.schraitle.flatblox.playground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;

public class FlatSystem implements CoordinateSystem {
	private int width;
	private int height;
	private long freeSpace;
	private List<Shape> shapes;
	
	public FlatSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.freeSpace = width * height;
		shapes = new ArrayList<Shape>();
	}

	public InsertStatus add(Shape shape, Coordinate position) {
		if(shape == null) {
			return InsertStatus.NULL_SHAPE;
		}
		
		for (Shape otherShape : shapes) {
			if(shape.overlapsWith(otherShape)) {
				return InsertStatus.OVERLAP;
			}
		}
		shapes.add(shape);
		freeSpace = freeSpace - shape.getArea();
		return InsertStatus.SUCCESS;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public long getFreeSpace() {
		return freeSpace;
	}

	@Override
	public InsertStatus move(Shape shape, Coordinate newPosition) {
		// TODO Auto-generated method stub
		return null;
	}

}
