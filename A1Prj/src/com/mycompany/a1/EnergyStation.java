package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public class EnergyStation extends FixedObject {
	private int capacity;
	private int energyScale = 3;
	
	// capacity is proportional to its size,
	
	//random location
	//random size
	
	public EnergyStation(int size, int x, int y) {
		super(size, x, y, ColorUtil.BLACK);
		this.capacity = super.getSize() * energyScale;
	}
	
	public int getCapacity() {
		return this.capacity;
	}

	
	private static double randomXLocation() {
		return 0;
	}
	
	private static double randomYLocation() {
		return 0;
	}
}
