package com.schraitle.flatblox.playground;

import java.util.List;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;

/**
 * A System which can accept shapes and place them inside a virtual space
 *
 */
public interface CoordinateSystem {
	
	public enum ShapeStatus {
		/** adding shape was successful */
		SUCCESS,
		/** the shape would have gone out of the boundaries of the system */
		OUT_OF_BOUNDS,
		/** the shape would have overlapped with another shape */
		OVERLAP,
		/** the shape is null */
		NULL_SHAPE,
		/** there are no coordinates in the shape */
		NO_COORDINATES, 
		/** the shape does not already exist in system */
		SHAPE_NOT_EXIST
	}

	/**
	 * adds a shape to the space at the given coordinate
	 * @param shape the shape to be added
	 * @return {@link ShapeStatus#SUCCESS} if successful, otherwise the appropriate status will be returned
	 */
	public ShapeStatus add(Shape shape);
	
	public ShapeStatus move(Shape shape, Coordinate newPosition);
	
	public ShapeStatus resize(Shape shape, int... args);
	
	/**
	 * @return list of all shapes represented in system
	 */
	public List<Shape> getShapes();
	
	/**
	 * @return the amount of free space left in the system
	 */
	public double getFreeSpace();
}
