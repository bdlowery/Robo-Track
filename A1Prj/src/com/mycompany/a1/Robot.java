package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Robot extends MovableObject implements ISteerable {
	private static final int maxSpeed = 15;
	private static final int maxDamage = 100;
	private static final int size = 40;
	private static final int initialEnergy = 100;
	private static final int energyConsumptionRate = 1;

	private int steeringDirection;
	private int energyLevel;
	private int damageLevel;
	private int lastBaseReached;

	public Robot(double x, double y, int alpha, int red, int green, int blue) {
		super(size, x, y, alpha, red, green, blue, 0, maxSpeed / 2);
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

	// reset all values back to the initial values when the robot dies.
	public void respawnRobot() {
		this.setEnergyLevel(initialEnergy);
		this.setDamageLevel(0);
		this.setSpeed(maxSpeed / 2);
		this.setLastBaseReached(this.getLastBaseReached());
		this.setColor(100, 0, 0, 255);
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

	// accelerate the robot based off certain parameters.
	public void accelerate(int ammount) {
		int speed = this.getSpeed();
		if (this.getEnergyLevel() == 0) {
			this.setSpeed(0);
			System.out.println("Energy level is 0, speed is now 0.");
		} else {
			int damageLimit = this.getMaxDamage() - this.getDamageLevel();

			// convert to percent
			int newMaxSpeed = (int) (this.getMaxSpeed() * (damageLimit / 100.0f));
			if (speed + ammount >= newMaxSpeed) {
				speed = newMaxSpeed;
				this.setSpeed(speed);
				System.out.println("You've reach maximum speed, your speed is: " + this.getSpeed());
			} else {
				speed += ammount;
				this.setSpeed(speed);
				System.out.println("Increased Speed to: " + this.getSpeed());
			}
		}
	}

	public void brake(int amount) {
		int currSpeed = this.getSpeed();
		if ((currSpeed - amount) < 0) {
			this.setSpeed(0);
			System.out.println("curr speed is 0, can't slow down anymore");
		} else {
			this.setSpeed(currSpeed - amount);
			System.out.println("You slowed down by " + amount + " your new speed is " + this.getSpeed());
		}
	}

	public void turn(int angle) {
		System.out.println("turning by: " + angle + " degrees");
		int steeringDirection = this.getSteeringDirection();
		if (steeringDirection + angle > 40) {
			this.setSteeringDirection(40);
			System.out.println("You've turned the maximum amount direction is: " + this.getSteeringDirection() + " degrees");
		} else if (steeringDirection + angle < -40) {
			this.setSteeringDirection(-40);
			System.out.println("You've turned the maximum amount direction is: " + this.getSteeringDirection() + " degrees");
		} else {
			this.setSteeringDirection(steeringDirection + angle);
			System.out.println("You've turned: " + this.getSteeringDirection() + " degrees");
		}

	}

	public void move() {
		// Check if the robot can even move:
		if (this.energyLevel > 0 && this.damageLevel < this.maxDamage && this.getSpeed() > 0) {
			// Check if the robot has turning more than 360 degrees.
			int checker = this.getHeading() + this.getSteeringDirection();
			if (checker > 359) {
				checker -= 360;
			} else {
				this.setHeading(checker);
			}

			this.setEnergyLevel(this.energyLevel - this.energyConsumptionRate);

			System.out.println("Energy level depleted by " + this.energyConsumptionRate + ", energy level is now: "
					+ this.getEnergyLevel());

			// super.move();

			double x = this.getLocationX();
			double y = this.getLocationY();

			// convert the heading to radians
			double rHeading = Math.toRadians(this.getHeading());

			// find the change in x and change in y
			double deltaX = this.getSpeed() * Math.cos(rHeading);
			double deltaY = this.getSpeed() * Math.sin(rHeading);

			// Check if robot has hit the boundries of the game world.
			if (deltaX + x > 1024) {
				this.setLocationX(1024);
			} else if (deltaX + x < 0) {
				this.setLocationX(0);
			} else {
				this.setLocationX(deltaX + x);
			}

			if (deltaY + y > 768) {
				this.setLocationY(768);
			} else if (deltaY + y < 0) {
				this.setLocationY(0);
			} else {
				this.setLocationY(deltaY + y);
			}
			
			System.out.println("Robot has moved to " + this.getLocationX() + "," + this.getLocationY());

		} else if (this.energyLevel <= 0) {
			System.out.println("Can't move, energy level is: " + this.getEnergyLevel());
		}
	}

	public void charge(int amount) {
		this.setEnergyLevel(this.getEnergyLevel() + amount);
	}

	public void damaged(int amount) {
		int currentDamageLevel = this.getDamageLevel();
		int alphaValue = this.getAlpha();

		if (currentDamageLevel + amount > this.getMaxDamage()) {
			this.setDamageLevel(this.getMaxDamage());
			System.out.println("This robot has reached max damage");
		} else {
			alphaValue -= amount;

			this.setDamageLevel(currentDamageLevel + amount);
			this.setColor(alphaValue, this.getRed(), this.getBlue(), this.getGreen());
		}

		System.out.println("damage level is now: " + this.getDamageLevel());
	}

	public String toString() {
		String parentDesc = "\nRobot: " + super.toString();
		String myDesc = "maxSpeed=" + this.getMaxSpeed() + ", steeringDirection=" + this.getSteeringDirection()
				+ ", energyLevel=" + this.getEnergyLevel() + ", damageLevel=" + this.getDamageLevel() + "\n";
		return parentDesc + myDesc;
	}
}
