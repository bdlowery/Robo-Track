package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public abstract class FixedObject extends GameObject {
	private int color;

	public FixedObject(int size, double x, double y, int alpha, int red, int green, int blue) {
		super(size, x, y, alpha, red, green, blue);
		this.color = ColorUtil.argb(alpha, red, green, blue);
	}
	
	// set the x position of a fixed object
	public void setX(double x) {
		
	}
	
	// set the y position of a fixed object
	public void setY(double y) {
		
	}
	
//	public void setColor(int alpha, int red, int green, int blue) {
//		this.color = ColorUtil.argb(alpha, red, green, blue);
//	}
}
