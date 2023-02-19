package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;

public class Base extends FixedObject {
	private static int size = 10;
	private int sequenceNumber;
	private int color;

	public Base(double x, double y, int sequenceNumber, int color) {
		super(size, x, y, color);
		this.sequenceNumber = sequenceNumber;
	}

	// color can't change for bases
	@Override
	public void setColor(int color) {
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
