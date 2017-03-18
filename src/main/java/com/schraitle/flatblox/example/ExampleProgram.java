package com.schraitle.flatblox.example;

import com.schraitle.flatblox.playground.CoordinateSystem;
import com.schraitle.flatblox.playground.FlatSystem;
import com.schraitle.flatblox.shapes.Coordinate;
import com.schraitle.flatblox.shapes.Shape;
import com.schraitle.flatblox.shapes.impl.Circle;
import com.schraitle.flatblox.shapes.impl.Rectangle;
import com.schraitle.flatblox.shapes.impl.Square;

public class ExampleProgram {

	private static final int SYSTEM_HEIGHT = 50;
	private static final int SYSTEM_WIDTH = 50;

	public static void main(String[] args) {
		// Create our 2D coordinate system
		CoordinateSystem flatSystem = new FlatSystem(SYSTEM_WIDTH, SYSTEM_HEIGHT);
		log("free space to start: " + flatSystem.getFreeSpace());

		// Create some shapes for our system
		Shape circle = new Circle(5);
		Shape rectangle = new Rectangle(5, 3);
		Shape square = new Square(4);

		// Set the coordinates to insert the shapes
		circle.setPosition(new Coordinate(7, 7));
		rectangle.setPosition(new Coordinate(10, 10));
		// this will make the edges go OOB
		square.setPosition(new Coordinate(49, 49));

		// Add our shapes to the system
		logSpacer();
		log("adding circle to system: " + flatSystem.add(circle));
		// going to overlap with circle
		log("adding rectangle to system: " + flatSystem.add(rectangle));
		// put it at a different place
		rectangle.setPosition(new Coordinate(35, 20));
		log("adding rectangle to system at different position: " + flatSystem.add(rectangle));
		// this will be OOB
		log("adding square to system: " + flatSystem.add(square));
		// change position of square and retry
		square.setPosition(new Coordinate(48, 48));
		log("adding square to system at different position: " + flatSystem.add(square));

		// Make the rectangle a little bigger
		logSpacer();
		log("beginning free space: " + flatSystem.getFreeSpace());
		log("old rectangle properties: " + rectangle.toString());
		log("changing size of rectangle: " + flatSystem.resize(rectangle, 10, 20));
		log("new rectangle properties: " + rectangle.toString());
		log("new free space: " + flatSystem.getFreeSpace());

		// Move the circle to a new location
		logSpacer();
		log("old circle properties: " + circle.toString());
		log("changing position of circle: " + flatSystem.move(circle, new Coordinate(15, 40)));
		log("new circle properties: " + circle.toString());

		// Find out how much free space is remaining
		logSpacer();
		log("free space remaining: " + flatSystem.getFreeSpace());
	}

	private static void logSpacer() {
		log("---");
	}

	private static void log(String message) {
		System.out.println(message);
	}

}
