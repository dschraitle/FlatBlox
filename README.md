# FlatBlox
managing shapes in a constrained 2D plane

### How To Build

1. Open Eclipse and import a project
1. Choose existing maven project
1. Select the directory for this project and import
1. Run the unit tests in the src/test/java folder by right-clicking -> Run As -> Junit Test
1. Run ExampleProgram.java to see the system in action

### Prompt

Implement the classes to build the following framework. The classes should have unit tests as appropriate.

* A coordinate system in which shapes will be created within and ultimately rendered.
* you do not need to write any rendering code to display the shapes
* All shapes are axis-aligned
* Shapes are not allowed to overlap.
* Shapes can move and change size after being created.  move means re-arrange; we do not need to check the path to the new position.
* Any number of shapes can be created so long as there is room in the coordinate space.
* Adding a shape will fail if it cannot fit.
* a way for the coordinate system to provide a calculation of the free area remaining within it

supported shapes:
square
rectangle
circle

-----------
Tips:
Working solution is better than no solution!  We're more interested in the object design than a super efficient collision detection engine.

-----------
Bonus:
triangle shapes supported

### Answers to questions:
* Can I assume this is a 2D space?
    * Yes
* When a shape fails to be added, should the result be a simple boolean, or should a failure reason be given?
    * This is up to you; whatever you think is best
* I'm not positive what the second part of the 5th line means "move means re-arrange; we do not need to check the path to the new position." does this mean that the shape can be moved to a new position, regardless if there is something in between the old and new points?
    * Yes, regardless of something between the old and new points, the shape can be moved to a new position
* Should I include a program that uses this coordinate system, or are the classes themselves enough?
    * Whatever you think is best
* Which kinds of triangles are wanted? (right, equilateral, isosceles) 
    * Think of all possible triangle types
	
	
