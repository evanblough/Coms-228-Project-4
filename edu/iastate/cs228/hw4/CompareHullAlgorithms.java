package edu.iastate.cs228.hw4;

/**
 *  
 * @author Evan Blough
 *
 */

/**
 * 
 * This class executes two convex hull algorithms: Graham's scan and Jarvis' march, over randomly
 * generated integers as well integers from a file input. It compares the execution times of 
 * these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random; 


public class CompareHullAlgorithms 
{
	/**
	 * Repeatedly take points either randomly generated or read from files. Perform Graham's scan and 
	 * Jarvis' march over the input set of points, comparing their performances.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) 
	{		
		
		boolean quit = false;
		Point[] pts; 
		GrahamScan g = null; 
		JarvisMarch j = null; 
		int trial = 1;
		
		while(!quit) {
		// Conducts multiple rounds of convex hull construction. Within each round, performs the following: 
		// 
		//    1) If the input are random points, calls generateRandomPoints() to initialize an array 
		//       pts[] of random points. Use pts[] to create two objects of GrahamScan and JarvisMarch, 
		//       respectively.
				System.out.print("Trial " + trial +":");
				Scanner in = new Scanner(System.in);
				
				int selection = in.nextInt();
				System.out.println("\n");
				if(selection == 1) {
				System.out.print("Enter number of random points:");
				pts = generateRandomPoints(in.nextInt(), new Random()) ;
				g = new GrahamScan(pts);
				j = new JarvisMarch(pts);
				System.out.println("\n");
		
				}
				if(selection == 2) {
					
					System.out.println("Points From a file");
					System.out.print("File Name: ");
					try {
						g = new GrahamScan(in.next());
						j = new JarvisMarch(in.next());
						
					} catch (InputMismatchException | FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("\n");
				}
		
		
		
		
		
		//    2) If the input is from a file, construct two objects of the classes GrahamScan and  
		//       JarvisMarch, respectively, using the file. 
				
				
				
				
				
		//
		//    3) Have each object call constructHull() to build the convex hull of the input points.
				
				
				g.constructHull();
				j.constructHull();
				
		//
		//    4) Meanwhile, prints out the table of runtime statistics.
				System.out.println("algorithm\tsize\ttime (ns)");
				System.out.println(g.stats());
				System.out.println(j.stats());
				
				
				
		// 
		// A sample scenario is given in Section 5 of the project description. 
		// 	
		ConvexHull[] algorithms = new ConvexHull[2]; 
		g.draw();
		j.draw();
		
		// Within a hull construction round, have each algorithm call the constructHull() and draw()
		// methods in the ConvexHull class.  You can visually check the result. (Windows 
		// have to be closed manually before rerun.)  Also, print out the statistics table 
		// (see Section 5). 
		}
		
	}
	
	
	/**
	 * This method generates a given number of random points.  The coordinates of these points are 
	 * pseudo-random numbers within the range [-50,50] × [-50,50]. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		 
		
		Point[] p = new Point[numPts];
		for(int i = 0; i < numPts; i++) {
			p[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(100) - 50) ;
		}
		
		
		
		
		return p;
		
		
		
		
		
		
	}
}
