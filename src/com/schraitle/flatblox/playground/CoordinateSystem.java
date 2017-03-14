package com.schraitle.flatblox.playground;

import java.util.List;

import com.schraitle.flatblox.shapes.Shape;

/**
 * A System which can accept shapes and place them inside a virtual space
 *
 */
public interface CoordinateSystem {

	/**
	 * adds a shape to the space at the given coordinate
	 * @param shape the shape to be added
	 * @param coordinate the position to add the shape
	 * @return true if successful, false if the shape failed to be added
	 */
	public boolean add(Shape shape);
	
	/**
	 * @return list of all shapes represented in system
	 */
	public List<Shape> getShapes();
	
	/**
	 * @return the amount of free space left in the system
	 */
	public double getFreeSpace();
}
