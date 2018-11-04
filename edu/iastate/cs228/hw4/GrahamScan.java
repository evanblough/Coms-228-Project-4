package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.ArrayList;
/**
 * 
 * @author Evan Blough
 *
 */

public class GrahamScan extends ConvexHull {
	/**
	 * Stack used by Grahma's scan to store the vertices of the convex hull of the
	 * points scanned so far. At the end of the scan, it stores the hull vertices in
	 * the counterclockwise order.
	 */
	private PureStack<Point> vertexStack;

	/**
	 * Call corresponding constructor of the super class. Initialize two variables:
	 * algorithm (from the class ConvexHull) and vertexStack.
	 * 
	 * @throws IllegalArgumentException
	 *             if pts.length == 0
	 */
	public GrahamScan(Point[] pts) throws IllegalArgumentException {
		super(pts);
		super.algorithm = "Graham Scan";
		vertexStack = new ArrayBasedStack<Point>();

	}

	/**
	 * Call corresponding constructor of the super class. Initialize algorithm and
	 * vertexStack.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 *             when the input file contains an odd number of integers
	 */
	public GrahamScan(String inputFileName) throws FileNotFoundException, InputMismatchException {
		super(inputFileName);
		super.algorithm = "Graham Scan";
		vertexStack = new ArrayBasedStack<Point>();

	}

	// -------------
	// Graham's scan
	// -------------

	/**
	 * This method carries out Graham's scan in several steps below:
	 * 
	 * 1) Call the private method setUpScan() to sort all the points in the array
	 * pointsNoDuplicate[] by polar angle with respect to lowestPoint.
	 * 
	 * 2) Perform Graham's scan. To initialize the scan, push pointsNoDuplicate[0]
	 * and pointsNoDuplicate[1] onto vertexStack.
	 * 
	 * 3) As the scan terminates, vertexStack holds the vertices of the convex hull.
	 * Pop the vertices out of the stack and add them to the array hullVertices[],
	 * starting at index vertexStack.size() - 1, and decreasing the index toward 0.
	 * 
	 * Two degenerate cases below must be handled:
	 * 
	 * 1) The array pointsNoDuplicates[] contains just one point, in which case the
	 * convex hull is the point itself.
	 * 
	 * 2) The array contains only collinear points, in which case the hull is the
	 * line segment connecting the two extreme points.
	 */
	public void constructHull() {
		time = System.nanoTime();

		if(pointsNoDuplicate.length == 1) {
			hullVertices = new Point[1];
			hullVertices[0] = pointsNoDuplicate[0];
			time = System.nanoTime() - time;
			return;
		}
		setUpScan();
		Point p1;
		Point p2;
		Point reference;
		PolarAngleComparator c;
		int crossProduct;
		vertexStack.push(super.pointsNoDuplicate[0]);
		vertexStack.push(super.pointsNoDuplicate[1]);
		vertexStack.push(super.pointsNoDuplicate[2]);
		for (int i = 3; i < pointsNoDuplicate.length; ++i) {

			p1 = vertexStack.pop();
			p2 = new Point(pointsNoDuplicate[i].getX() - p1.getX(), pointsNoDuplicate[i].getY() - p1.getY());
			reference = vertexStack.pop();
			PolarAngleComparator p = new PolarAngleComparator(reference, true);
			crossProduct = (p1.getX() - reference.getX()) * (p2.getY()) - (p2.getX() * (p1.getY() - reference.getY()));

			// Left Turn add all elements
			if (crossProduct >= 0) {
				vertexStack.push(reference);
				vertexStack.push(p1);
				vertexStack.push(pointsNoDuplicate[i]);
			}
			// Right Turn Remove top
			else {
				vertexStack.push(reference);
				i--;
			}

			if (i == pointsNoDuplicate.length - 1) {
				p1 = vertexStack.pop();
				p2 = new Point(pointsNoDuplicate[0].getX() - p1.getX(), pointsNoDuplicate[0].getY() - p1.getY());
				reference = vertexStack.pop();
				crossProduct = (p1.getX() - reference.getX()) * (p2.getY())
						- (p2.getX() * (p1.getY() - reference.getY()));

				if (crossProduct >= 0) {
					vertexStack.push(reference);
					vertexStack.push(p1);
					vertexStack.push(pointsNoDuplicate[0]);
				}
				// Right Turn Remove top
				else {
					vertexStack.push(reference);
					i--;
				}

			}

		}

		// Populate Hull Verticies
		hullVertices = new Point[vertexStack.size()];
		for (int i = vertexStack.size() - 1; i > -1; --i) {
			hullVertices[i] = vertexStack.pop();
		}
		time = System.nanoTime() - time;

		// TODO Implement Degenerate cases

	}

	/**
	 * Set the variable quicksorter from the class ConvexHull to sort by polar angle
	 * with respect to lowestPoint, and call quickSort() from the QuickSortPoints
	 * class on pointsNoDupliate[]. The argument supplied to quickSort() is an
	 * object created by the constructor call PolarAngleComparator(lowestPoint,
	 * true).
	 * 
	 * Ought to be private, but is made public for testing convenience.
	 *
	 */
	public void setUpScan() {
		super.quicksorter = new QuickSortPoints(pointsNoDuplicate);
		super.quicksorter.quickSort(new PolarAngleComparator(super.lowestPoint, true));
		super.quicksorter.getSortedPoints(pointsNoDuplicate);

	}
}
