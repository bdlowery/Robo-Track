package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public class Base extends FixedObject {
	private static int size = 10;
	private int sequenceNumber;
	private int color;

	public Base(double x, double y, int sequenceNumber, int alpha, int red, int green, int blue) {
		super(size, x, y, alpha, red, green, blue);
		this.sequenceNumber = sequenceNumber;

	}

	// color can't change for bases
	@Override
	public void setColor(int alpha, int red, int green, int blue) {
		System.out.println("cant change color of a base ");
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public String toString() {
		String parentDesc = "\nBase: " + super.toString();
		String myDesc = "seqNum=" + getSequenceNumber() + "\n";
		return parentDesc + myDesc;
	}
}
