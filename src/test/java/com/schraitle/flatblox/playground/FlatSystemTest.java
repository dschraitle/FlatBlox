package com.schraitle.flatblox.playground;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.schraitle.flatblox.playground.CoordinateSystem.InsertStatus;
import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;

@RunWith(MockitoJUnitRunner.class)
public class FlatSystemTest {
	
	private static final int DEFAULT_HEIGHT = 25;
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_FREE_SPACE = DEFAULT_WIDTH * DEFAULT_HEIGHT;
	
	Coordinate firstPosition = new Coordinate(5, 5);
	FlatSystem system;
	
	@Mock Shape mockShape;
	@Mock Shape secondMockShape;
	
	@Before
	public void setup() {
		system = new FlatSystem(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	@Test
	public void flatSystemDoesntAddNullShape() {
		assertEquals("add should return NULL_SHAPE", InsertStatus.NULL_SHAPE, system.add(null, firstPosition));
	}
	
	@Test
	public void flatSystemAddsAShape() {
		assertEquals("add should be successful", InsertStatus.SUCCESS, system.add(mockShape, firstPosition));
	}
	
	@Test
	public void overlappingShapeFails() {
		Shape firstShape = mockShape;
		Shape secondShape = secondMockShape;
		//this mocking will not work out so well if the addition code is changed so that the shape being inserted
		// isn't the one doing the .overlapsWith
		Mockito.when(secondShape.overlapsWith(firstShape)).thenReturn(true);
		system.add(firstShape, firstPosition);
		assertEquals("shape should be overlapping", InsertStatus.OVERLAP, system.add(secondShape, firstPosition));
	}
	
	@Test
	public void initialFreeSpaceCorrect() {
		assertEquals("free space should be correct", DEFAULT_FREE_SPACE, system.getFreeSpace());
	}
	
	@Test
	public void freeSpaceCorrectAfterAddingAShape() {
		long area = 15l;
		Mockito.when(mockShape.getArea()).thenReturn(area);
		system.add(mockShape, firstPosition);
		assertEquals(DEFAULT_FREE_SPACE - area, system.getFreeSpace());
	}

}
