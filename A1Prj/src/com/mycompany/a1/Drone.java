package com.mycompany.a1;

import java.util.Random;

public class Drone extends MovableObject implements ISteerable {

	private Random rand;

	public Drone(int size, double x, double y, int color, int heading, int speed) {
		super(size, x, y, color, heading, speed);
//		this.rand = rand;

	}

	// add or subtract small random values to heading who
	public void turn(int angle) {
		System.out.println("this object cannot turn");
	}

	public void move() {
		// range from -5 to positive 5;
		this.heading += rand.nextInt(11) - 5;

		// todo bounce off wall (center goes off screen) change heading to negative
		super.move();
	}
}
