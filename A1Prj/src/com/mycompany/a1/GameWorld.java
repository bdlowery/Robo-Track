package com.mycompany.a1;

import java.util.ArrayList;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class GameWorld {
	private int height;
	private int width;
	private Random rand;
	private int minSize;
	private int maxSize;
	private int clock;
	private int lives;

	// store list of game objects
	private ArrayList<GameObject> gameObjects;

	public GameWorld() {
		// set the size of the world to 1024 x 768
		this.width = 1024;
		this.height = 768;
		rand = new Random();
		minSize = 5;
		maxSize = 20;
		this.clock = 0;
		this.lives = 3;
		this.gameObjects = new ArrayList<GameObject>();
	}

	private void spawnEnergyStation() {
		int s = rand.nextInt(this.maxSize - this.minSize) + minSize;
		int x = rand.nextInt(this.width);
		int y = rand.nextInt(this.height);
		EnergyStation energyStation = new EnergyStation(s, x, y);
		gameObjects.add(energyStation);
	}

	private void spawnBase(double x, double y, int sequence) {
		Base base = new Base(x, y, sequence, ColorUtil.YELLOW);
		gameObjects.add(base);
	}

	private void spawnRobot() {
		int color = ColorUtil.BLUE;
		int x = 0;
		int y = 0;
		Robot robot = new Robot(x, y, color);
		gameObjects.add(robot);
	}

	private void spawnDrone() {
		// int size, double x, double y, int color, int heading, int speed, Random rand
		int s = rand.nextInt(this.maxSize - this.minSize) + minSize;
		int x = rand.nextInt(this.width);
		int y = rand.nextInt(this.height);
		int heading = rand.nextInt(359);
		int color = ColorUtil.LTGRAY;
		// speed between 5 and 10
		int speed = rand.nextInt(10 - 5) + 5;
		Drone drone = new Drone(s, x, y, color, heading, speed);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	// initialize all of the gameworld objects
	public void init() {
		// code here to create the
		// initial game objects/setup
		// create robot, drones, bases, and energy stations and add to gameObjects
		// array.

		// spawn 2 - 5 energy stations.
		for (int i = 0; i < rand.nextInt(3) + 2; i++) {
			spawnEnergyStation();
		}

		spawnBase(240, 100, 1);
		spawnBase(340, 200, 2);
		spawnBase(440, 600, 3);
		spawnBase(740, 400, 4);
		spawnBase(140, 300, 5);
		spawnBase(500, 100, 6);

		// spawn 2 - 4 drones
		for (int i = 0; i < rand.nextInt(2) + 2; i++) {
			spawnDrone();
		}

		spawnRobot();

	}

	// additional methods here to
	// manipulate world objects and
	// related game state data

	// increment the clock, and run move() function for all of the movable gameworld
	// objects.
	// change heading of robot and drone.
	public void clockTick() {
		clock++;

		// loop through game objects
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof MovableObject) {
				// call the move function from movable to move all of the moveable game objects.
				((MovableObject) singleObject).move();
			}
			// check if game object is a robot
			if (singleObject instanceof Robot) {
				// if it is, call the Robots consumeEnergy function (because it just moved)

				// check if the robot died
				if (((Robot) singleObject).getEnergyLevel() == 0 || ((Robot) singleObject).getDamageLevel() == 100) {
					System.out.println("Robot has died");
					lives--;
					((Robot) singleObject).respawnRobot();
					init();
				}
			}
		}
	}

	public void accelerate() {
		int accelAmmount = 5;
		// loop through gameobjects array
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).accelerate(accelAmmount);
			}
		}
	}
	// check if the gameobject is a robot
	// call the robots accelerate function passing in the amount to accelerate
	// sysout the new speed of the robot.

	public void brake() {
		// loop through gameobjects array
		int brakeAmmount = 2;
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).brake(brakeAmmount);
			}
		}
	}
	// check if the gameobject is a robot
	// sysout the new speed of the robot

	public void steer(int direction) {
		// loop through the gameobjects array
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).turn(direction);
			}
		}
		// call the steering function from the robot class, passing in the direction.

		// Steer function in robot class will handle what direction to turn the robot.

	}

	public void collide() {
		// make a variable simulating a robots health. int fakeHealth = 20;.
		int fakeDamage = 20;
		// loop through the game objects.
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).damaged(fakeDamage);
			}
		}

		// if the game object is a Robot, call the damage function, passing in the
		// fakeHealth amount.
	}

	public void collideBase(int sequenceNumber) {
		// loop through the game objects.
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				if (sequenceNumber == ((Robot) singleObject).getLastBaseReached() + 1) {
					System.out.println("last base reached was: " + ((Robot) singleObject).getLastBaseReached());
					((Robot) singleObject).setLastBaseReached(sequenceNumber);
					System.out.println("last base reached is now: " + ((Robot) singleObject).getLastBaseReached());
				}
			}
		}
		// check if the game object is a robot.
		// if the sequence number is 1 more than the robots lastBaseReached
		// (Robot.getLastBaseReached())
		// call the function for updating the lastBaseReached (just ++ the lastBase by
		// number by in the robot class)

	}

	public void collideEnergyStation() {
		// loop through all of the game objects.
		ArrayList<EnergyStation> energyStations = new ArrayList<EnergyStation>();
		for (GameObject singleObject : gameObjects) {

			if (singleObject instanceof EnergyStation) {
				energyStations.add((EnergyStation) singleObject);
			}
		}
		// pick a random energy station for the robot to collide with
		EnergyStation randomEnergyStation = energyStations.get(rand.nextInt(energyStations.size()));

		// loop through the game objects
		for (GameObject singleObject : gameObjects) {

			if (singleObject instanceof Robot) {
				System.out.println("Robots energy was: " + ((Robot) singleObject).getEnergyLevel());
				((Robot) singleObject).charge(randomEnergyStation.getCapacity());
				System.out.println("Robots energy charged to: " + ((Robot) singleObject).getEnergyLevel());

				System.out.println("EnergyStations capacity was: " + randomEnergyStation.getCapacity());
				randomEnergyStation.depleteEnergy();
				System.out.println("EnergyStation capacity depleted to: " + randomEnergyStation.getCapacity());
			}
		}

		spawnEnergyStation();

	}

	public void collideDrone() {
		ArrayList<Drone> drones = new ArrayList<Drone>();
		// loop through game objects
		for (GameObject singleObject : gameObjects) {

			if (singleObject instanceof Drone) {
				drones.add((Drone) singleObject);
			}
		}
		// pick a random drone to collide with the robot
		Drone randomDrone = drones.get(rand.nextInt(drones.size()));

		// loop through game objects
		for (GameObject singleObject : gameObjects) {

			if (singleObject instanceof Robot) {
				((Robot) singleObject).damaged(10);
			}
		}
		// if game object is a robot
		// call the Robots applyDamage function, while passing in the drones damage
		// value.
	}

	public void display() {

		String display = "";
		// append all of the required values to that string.
		display += "Lives= " + this.lives + " , ";
		display += "Clock= " + this.clock + "";
		display += "\n";

		for (GameObject singleObject : gameObjects) {

			if (singleObject instanceof Robot) {
				display += "Highest base reached: " + ((Robot) singleObject).getLastBaseReached() + "\n";
				display += "Current energy level: " + ((Robot) singleObject).getEnergyLevel() + "\n";
				display += "Current damage level: " + ((Robot) singleObject).getDamageLevel() + "\n";
			}
		}
		System.out.println(display);

	}

	public void map() {
		String map = "";
		for (GameObject singleObject : gameObjects) {
			map += singleObject.toString();
		}
		System.out.println(map);
	}

	public void exit() {
		System.exit(0);
	}

}
