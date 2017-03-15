package com.schraitle.flatblox.playground;

import java.util.ArrayList;
import java.util.List;

import com.schraitle.flatblox.shapes.Shape;

public class FlatSystem implements CoordinateSystem {
	int width;
	int height;
	List<Shape> shapes;
	
	public FlatSystem(int width, int height) {
		this.width = width;
		this.height = height;
		shapes = new ArrayList<Shape>();
	}

	public InsertStatus add(Shape shape) {
		// TODO Auto-generated method stub
		return InsertStatus.OVERLAP;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public double getFreeSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

}
