package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public class EnergyStation extends FixedObject {
	private int capacity;
	private int energyScale = 2;

	// capacity is proportional to its size,

	// random location
	// random size

	public EnergyStation(int size, int x, int y) {
		super(size, x, y, ColorUtil.BLACK);
		this.capacity = super.getSize() * energyScale;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void depleteEnergy() {
		this.setColor(ColorUtil.GRAY);
		this.capacity = 0;
	}

	public String toString() {
		String parentDesc = "\nEnergyStation: " + super.toString();
		String myDesc = "capacity=" + getCapacity() + "\n";
		return parentDesc + myDesc;
	}

}
