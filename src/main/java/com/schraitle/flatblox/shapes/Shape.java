package com.schraitle.flatblox.shapes;

/**
 * Class representing an object in virtual space
 *
 */
public interface Shape {
	
	/**
	 * @return the current position of the shape
	 */
	public Coordinate getPosition();

	/**
	 * change the size of the shape
	 * @param newDimension the new dimensions of the shape
	 */
	public void resize(Dimension newDimension);
	
	/**
	 * change the position of the shape
	 * @param newPosition the new position of the shape
	 */
	public void setPosition(Coordinate newPosition);
	
	/**
	 * checks to see if this shape will overlap with the given shape
	 * @param otherShape another shape to check for overlap 
	 * @return true if the shapes will overlap, false otherwise
	 */
	public boolean overlapsWith(Shape otherShape);
	
	/**
	 * @return the area taken up by this shape
	 */
	public long getArea();
}
