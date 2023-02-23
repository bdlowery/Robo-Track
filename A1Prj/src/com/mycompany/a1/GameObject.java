package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {

	private final int size;
	private Point2D location;
	private int color;

	// construct the game object with these values.
	public GameObject(int size, double x, double y, int alpha, int red, int green, int blue) {
		this.size = size;
		this.location = new Point2D(x, y);
		this.color = ColorUtil.argb(alpha, red, green, blue);
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
		double roundedX = Math.round(x*10.0)/10.0;
		this.location.setX(roundedX);
	}

	// set the y location using point2d
	public void setLocationY(double y) {
		double roundedY = Math.round(y*10.0)/10.0;
		this.location.setY(roundedY);
	}

	public void setLocation(double y, double x) {
		double roundedX = Math.round(x*10.0)/10.0;
		double roundedY = Math.round(y*10.0)/10.0;
		this.location.setX(roundedX);
		this.location.setY(roundedY);
	}

	// get the color of the game object.
	public int getRed() {
		return ColorUtil.red(color);
	}
	
	public int getGreen() {
		return ColorUtil.green(color);
	}
	
	public int getBlue() {
		return ColorUtil.blue(color);
	}
	
	public int getAlpha() {
		return ColorUtil.alpha(color);
	}

	public int getColor() {
		return this.color;
	}
	
	// set the color of a game object
	public void setColor(int alpha, int red, int green, int blue) {
		this.color = ColorUtil.argb(alpha, red, green, blue);
	}

	public int generateColor(int alpha, int red, int green, int blue) {
		return ColorUtil.argb(alpha, red, green, blue);
	}


	// game object function to show a gameobject in the console
	public String toString() {
		String output = null;
		output = "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ", "
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + 
				" color=" + "[" + this.getAlpha() + "%" + "," + this.getRed() + "," + this.getBlue() + "," + this.getGreen() + "] size="
				+ this.getSize() + " ";
		return output;
	}
}
