package com.schraitle.flatblox.playground;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.schraitle.flatblox.playground.CoordinateSystem.InsertStatus;
import com.schraitle.flatblox.shapes.Shape;

public class FlatSystemTest {
	
	private static final int DEFAULT_HEIGHT = 25;
	private static final int DEFAULT_WIDTH = 20;
	FlatSystem system;
	
	@Before
	public void setup() {
		system = new FlatSystem(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	@Test
	public void flatSystemAddsAShape() {
		Shape shape = null;
		assertEquals("shape should add successfully", InsertStatus.SUCCESS, system.add(shape));
	}

}
