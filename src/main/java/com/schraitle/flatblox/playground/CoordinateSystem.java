package com.schraitle.flatblox.playground;

import java.util.List;

import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;

/**
 * A System which can accept shapes and place them inside a virtual space
 *
 */
public interface CoordinateSystem {
	
	public enum InsertStatus {
		/** adding shape was successful */
		SUCCESS,
		/** the shape would have gone out of the boundaries of the system */
		OUT_OF_BOUNDS,
		/** the shape would have overlapped with another shape */
		OVERLAP,
		/** the shape is null */
		NULL_SHAPE
	}

	/**
	 * adds a shape to the space at the given coordinate
	 * @param shape the shape to be added
	 * @param position the position to add the shape
	 * @return {@link InsertStatus#SUCCESS} if successful, otherwise the appropriate status will be returned
	 */
	public InsertStatus add(Shape shape, Coordinate position);
	
	public InsertStatus move(Shape shape, Coordinate newPosition);
	
	/**
	 * @return list of all shapes represented in system
	 */
	public List<Shape> getShapes();
	
	/**
	 * @return the amount of free space left in the system
	 */
	public long getFreeSpace();
}
