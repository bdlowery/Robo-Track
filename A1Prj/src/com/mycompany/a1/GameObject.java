package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {

	private final int size;
	private Point2D location;
	private int color;

	// construct the game object with these values.
	public GameObject(int size, double x, double y, int color) {
		this.size = size;
		this.location = new Point2D(x, y);
		this.color = color;
	}

	// allow game objects to get their size
	public int getSize() {
		return this.size;
	}

	// return the x coordinate
	public double getLocationX() {
		return this.location.getX();
	}

	// return the y coordinate
	public double getLocationY() {
		return this.location.getY();
	}

	public Point2D getLocation() {
		return this.location;
	}

	// set the x location using point2d
	public void setLocationX(double x) {
		this.location.setX(x);
	}

	// set the y location using point2d
	public void setLocationY(double y) {
		this.location.setY(y);
	}

	public void setLocation(double y, double x) {
		this.location.setX(x);
		this.location.setY(y);
	}

	// get the color of the game object.
	public int getColor() {
		return color;
	}

	// set the color of a game object
	public void setColor(int color) {
		this.color = color;
	}

	public int generateColor(int red, int green, int blue) {
		return ColorUtil.rgb(red, green, blue);
	}

	// game object function to show a gameobject in the console
	public String toString() {
		String output = null;
		output = "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ", "
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=" + "[" + ColorUtil.red(this.getColor())
				+ ", " + ColorUtil.green(this.getColor()) + ", " + ColorUtil.blue(this.getColor()) + "] size="
				+ this.getSize() + " ";
		return output;
	}
}
