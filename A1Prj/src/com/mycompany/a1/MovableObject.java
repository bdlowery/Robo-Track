package com.mycompany.a1;

public abstract class MovableObject extends GameObject {

	protected int heading;
	private int speed;

	// constructor a movable object.
	public MovableObject(int size, double x, double y, int alpha, int red, int green, int blue, int heading,
			int speed) {
		// set in the game object.
		super(size, x, y, alpha, red, green, blue);

		// set the angle of the object.
		this.heading = heading;

		// set the speed of the object.
		this.speed = speed;

	}

	public int getHeading() {
		return this.heading;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setHeading(int heading) {
		this.heading = heading;

	}

	public int setSpeed(int speed) {
		return this.speed = speed;
	}

	// move the object -
	// by getting it's current x and y value,
	// converting the hedaing degree to radians,
	// finding the change in x and y,
	// then setting the new speed.
	public void move() {
//		// get the current location for x and y
//		double x = this.getLocationX();
//		double y = this.getLocationY();
//		
//
//		// convert the heading to radians
//		double rHeading = Math.toRadians(this.getHeading());
//
//		// find the change in x and change in y
//		double deltaX = this.getSpeed() * Math.cos(rHeading);
//		double deltaY = this.getSpeed() * Math.sin(rHeading);
//		
//
//		// update the current location to the new values
//		this.setLocationX(deltaX + x);
//		this.setLocationY(deltaY + y);

	}

	// override game object toString to add outputs needed for movable objects.
	public String toString() {
		String ParentOutput = super.toString();
		String output;
		output = "heading=" + this.getHeading() + " speed=" + this.getSpeed() + " ";
		return ParentOutput + output;
	}

}
