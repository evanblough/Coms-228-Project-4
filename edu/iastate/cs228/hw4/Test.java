package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;

public class Test {

	public static void main(String[] args) {

		for (int i = 0; i < 5; ++i) {
			Random rand = new Random();
			Point[] p = new Point[9];
			p[0] = new Point(0, 0);
			p[1] = new Point(10, 10);
			p[2] = new Point(-10, -10);
			p[3] = new Point(rand.nextInt(30), rand.nextInt(30));
			p[4] = new Point(rand.nextInt(30), rand.nextInt(30));
			p[5] = new Point(rand.nextInt(30), rand.nextInt(30));
			p[6] = new Point(rand.nextInt(30), rand.nextInt(30));
			p[7] = new Point(rand.nextInt(30), rand.nextInt(30));
			p[8] = new Point(rand.nextInt(30), rand.nextInt(30));
			
			GrahamScan test = null;
			JarvisMarch test1 = null;
			try {
				test = new GrahamScan(p);
				test1 = new JarvisMarch(p);
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			test.constructHull();
			test.draw();
			test1.constructHull();
			test1.draw();

		}

	}
}
