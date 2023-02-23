package com.mycompany.a1;

import java.util.Random;

public class Drone extends MovableObject implements ISteerable {

	private Random rand = new Random();

	public Drone(int size, double x, double y, int alpha, int red, int green, int blue, int heading, int speed) {
		super(size, x, y, alpha, red, green, blue, heading, speed);
		

	}

	// add or subtract small random values to heading who
	public void turn(int angle) {
		System.out.println("this object cannot turn");
	}
	

	public void move() {
		// range from -5 to positive 5;
		this.setHeading(this.getHeading() + rand.nextInt(11) - 5);
		
		double x = this.getLocationX();
		double y = this.getLocationY();
		

		// convert the heading to radians
		double rHeading = Math.toRadians(this.getHeading());

		// find the change in x and change in y
		double deltaX = this.getSpeed() * Math.cos(rHeading);
		double deltaY = this.getSpeed() * Math.sin(rHeading);
		

		
		if(deltaX + x > 1024 || deltaX + x < 0) {
			this.setHeading(180);
		} else {
			this.setLocationX(deltaX + x);
		}
		
		if(deltaY + y > 768 || deltaY + y < 0) {
			this.setHeading(180);
		} else {
			this.setLocationY(deltaY + y);
		}
	}
	
	public String toString() {
		String parentDesc = "\nDrone: " + super.toString() + "\n";
		return parentDesc;
	}
}
