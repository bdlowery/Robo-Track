package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Robot extends MovableObject implements ISteerable {
	private static final int maxSpeed = 8;
	private static final int maxDamage = 100;
	private static final int size = 40;
	private static final int initialEnergy = 100;
	private static final int energyConsumptionRate = 1;

	private int steeringDirection;
	private int energyLevel;
	private int damageLevel;
	private int lastBaseReached;

	// all robots have the same size
	public Robot(double x, double y, int color) {
		super(size, x, y, color, 0, maxSpeed / 2);
		lastBaseReached = 1;
		damageLevel = 0;
		energyLevel = initialEnergy;
		steeringDirection = 0;
		lastBaseReached = 1;

	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	public int getSteeringDirection() {
		return this.steeringDirection;
	}

	public int setSteeringDirection(int angle) {
		return this.steeringDirection = angle;
	}

	public int getMaxSpeed() {
		return Robot.maxSpeed;

	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public int getDamageLevel() {
		return this.damageLevel;
	}

	public int getLastBaseReached() {
		return this.lastBaseReached;
	}

//	public void turn(int angle) {
//
//	}

	// reset all values back to the initial values when the robot dies.
	public void respawnRobot() {
		this.setEnergyLevel(initialEnergy);
		this.setDamageLevel(damageLevel);
		this.setSpeed(maxSpeed / 2);
		this.setLastBaseReached(1);
		this.setColor(ColorUtil.BLUE);
		this.setLocation(0, 0);
	}

	public int setDamageLevel(int damageLevel) {
		return this.damageLevel = damageLevel;
	}

	public int setEnergyLevel(int energyLevel) {
		return this.energyLevel = energyLevel;
	}

	public int setLastBaseReached(int base) {
		return this.lastBaseReached = base;
	}

//	public int setMaxSpeed(int speed) {
//	return this.maxSpeed = speed;
//}

	public void accelerate(int ammount) {
		int speed = this.getSpeed();
		if (this.getEnergyLevel() == 0) {
			this.setSpeed(0);
			System.out.println("Energy level is 0, speed is now 0.");
		} else {
			// maxDamage - currentDamage convert to percent
			int damageLimit = this.getMaxDamage() - this.getDamageLevel(); // 100 - 7 = 93
			// convert to percent

//			int percentage = (damageLimit / this.getMaxSpeed()) * 100;
			int newMaxSpeed = (int) (this.getMaxSpeed() * (damageLimit / 100.0f));
			System.out.println("maxspeed: " + this.getMaxSpeed());
			System.out.println("newMaxSpeed: " + newMaxSpeed);
			System.out.println("percent: " + damageLimit);
			if (speed + ammount >= newMaxSpeed) {
				speed = newMaxSpeed;
				this.setSpeed(speed);
			} else {
				speed += ammount;
				this.setSpeed(speed);
			}
		}
		System.out.println("Robots speed is " + this.getSpeed());
	}

	public void brake(int amount) {
		int currSpeed = this.getSpeed();
		if ((currSpeed - amount) < 0) {
			this.setSpeed(0);
			System.out.println("curr speed 0, can't slow down anymore");
		} else {
			this.setSpeed(currSpeed - amount);
			System.out.println("braked");
		}
		System.out.println("Robots speed is " + this.getSpeed());
	}

	public void turn(int angle) {
		System.out.println("turning by: " + angle);
		int steeringDirection = this.getSteeringDirection();
		if (steeringDirection == 40 || steeringDirection == -40) {
			this.setSteeringDirection(steeringDirection);
		} else {
			this.setSteeringDirection(steeringDirection + angle);
		}

		System.out.println("Steering direction is now: " + this.getSteeringDirection());
	}

	public void move() {
		if (this.energyLevel > 0 && this.damageLevel < this.maxDamage && this.getSpeed() > 0) {
			super.setHeading(this.getHeading() + this.getSteeringDirection());

			this.setEnergyLevel(this.energyLevel - this.energyConsumptionRate);
		}
		super.move();
	}

	public void charge(int amount) {
		this.setEnergyLevel(this.getEnergyLevel() + amount);
	}

	public String toString() {
		String parentDesc = "\nRobot: " + super.toString();
		String myDesc = "maxSpeed=" + this.getMaxSpeed() + ", steeringDirection=" + this.getSteeringDirection()
				+ ", energyLevel=" + this.getEnergyLevel() + ", damageLevel=" + this.getDamageLevel() + "\n";
		return parentDesc + myDesc;
	}

	public void damaged(int amount) {
		System.out.println("damage level was: " + this.getDamageLevel());
		this.setDamageLevel(amount);
		System.out.println("damage level is now: " + this.getDamageLevel());
		// fade robots color

	}
}
