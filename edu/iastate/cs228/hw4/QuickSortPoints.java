package edu.iastate.cs228.hw4;
/**
 * 
 * @author Evan Blough
 *
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class sorts an array of Point objects using a provided Comparator.  For the purpose
 * you may adapt your implementation of quicksort from Project 2.  
 */

public class QuickSortPoints
{
	private Point[] points;  	// Array of points to be sorted.
	

	/**
	 * Constructor takes an array of Point objects. 
	 * 
	 * @param pts
	 */
	QuickSortPoints(Point[] pts)
	{
		points = new Point[pts.length];
		int i = 0;
		int k = 0;
		for (Point p : pts) {
			points[i++] = new Point(p.getX(), p.getY());
		}
	
	}
	
	
	/**
	 * Copy the sorted array to pts[]. 
	 * 
	 * @param pts  array to copy onto
	 */
	void getSortedPoints(Point[] pts)
	{
		for(int i = 0; i < points.length; ++i) {
			pts[i] = new Point(points[i].getX(), points[i].getY());
		}
		
	}

	
	/**
	 * Perform quicksort on the array points[] with a supplied comparator. 
	 * 
	 * @param comp
	 */
	public void quickSort(Comparator<Point> comp)
	{
		quickSortRec(0, points.length-1, comp);
		
		
		
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last, Comparator<Point> comp)
	{
		if(first >= last) {
			return;
		}
		int p = partition(first, last, comp);
		quickSortRec(first, p-1, comp);
		quickSortRec(p, last, comp);
	}
	

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last, Comparator<Point> comp)
	{
		Point pivot = new Point(points[last].getX(), points[last].getY());
		int i = first - 1;
		for (int j = first; j < last; ++j) {
			//TODO check value of comparator
			if (comp.compare(points[j], pivot) <= 0) {
				i++;
				Point temp = new Point(points[j].getX(), points[j].getY());
				points[j] = new Point(points[i].getX(), points[i].getY());
				points[i] = temp;
			}
		}

		points[last] = new Point(points[i + 1].getX(), points[i + 1].getY());
		points[i + 1] = pivot;
		return i + 1;
		
	}
}


