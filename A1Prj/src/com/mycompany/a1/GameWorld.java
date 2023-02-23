package com.mycompany.a1;

import java.util.ArrayList;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;


// TODO: Check clock tick functionality.


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
		Base base = new Base(x, y, sequence, 100, 192, 192, 192);
		gameObjects.add(base);
	}

	private void spawnRobot() {
		int color = ColorUtil.BLUE;
		int x = 0;
		int y = 0;
		Robot robot = new Robot(x, y, 100, 255, 0, 0);
		gameObjects.add(robot);
	}

	// Spawn a drone with random attributes
	private void spawnDrone() {
		int s = rand.nextInt(this.maxSize - this.minSize) + minSize;
		int x = rand.nextInt(this.width);
		int y = rand.nextInt(this.height);
		int heading = rand.nextInt(359);
		int color = ColorUtil.LTGRAY;
		// speed between 5 and 10
		int speed = rand.nextInt(10 - 5) + 5;

		Drone drone = new Drone(s, x, y, 100, 0, 0, 255, heading, speed);
		gameObjects.add(drone);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	// initialize all of the gameworld objects
	public void init() {

		// create robot, drones, bases, and energy stations and add to gameObjects

		// spawn 2 - 5 energy stations.
		for (int i = 0; i < rand.nextInt(3) + 2; i++) {
			spawnEnergyStation();
		}

		spawnBase(240, 100, 1);
		spawnBase(340, 200, 2);
		spawnBase(440, 600, 3);
		spawnBase(740, 400, 4);
		spawnBase(140, 300, 5);
		spawnBase(100, 100, 7);
		spawnBase(500, 300, 8);
		spawnBase(300, 50, 9);

		// spawn 2 - 4 drones
		for (int i = 0; i < rand.nextInt(2) + 2; i++) {
			spawnDrone();
		}

		spawnRobot();
		
	}



	// increment the clock and run the discrete simulation
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
					checkLives();
				}
			}
		}
	}

	// Check how many lives the robot has left
	public void checkLives() {
		if(lives > 0) {
			System.out.println("you have " + lives + " lives left");
		} else {
			System.out.println("Game over, you failed!");
			System.exit(0);
		}
	}
	
	// accelerate the robot by a set ammount
	public void accelerate() {
		int accelAmmount = 5;
		// loop through gameobjects array
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).accelerate(accelAmmount);
			}
		}
	}


	// slow down the robot by a set amount
	public void brake() {
		int brakeAmmount = 2;
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).brake(brakeAmmount);
			}
		}
	}

	// change the robots steering direction
	public void steer(int direction) {
		// loop through the gameobjects array
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).turn(direction);
			}
		}
	}

	// collide with a drone or a robot.
	public void collide(String enemy) {
		int fakeRobotDamage = 20;
		int droneDamage = fakeRobotDamage / 2;
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				if (enemy.equals("drone")) {
					((Robot) singleObject).damaged(droneDamage);
				} else {
					((Robot) singleObject).damaged(fakeRobotDamage);
				}
			}
		}
	}

	
	// collide with a base, and check if the correct base is being hit.
	public void collideBase(int sequenceNumber) {
		// loop through the game objects.
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				if (sequenceNumber == ((Robot) singleObject).getLastBaseReached() + 1) {

					((Robot) singleObject).setLastBaseReached(sequenceNumber);
					System.out.println("last base reached is now: " + ((Robot) singleObject).getLastBaseReached());
					if (((Robot) singleObject).getLastBaseReached() == 9) {
						System.out.println("Game over, you win! Total time: " + this.clock);
						System.exit(0);
					}
				}
			}
		}
	}

	// collide with an energy station and deplete the energy
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

				System.out.println("\nEnergyStations capacity was: " + randomEnergyStation.getCapacity());
				randomEnergyStation.depleteEnergy();
				System.out.println("EnergyStation capacity depleted to: " + randomEnergyStation.getCapacity());
			}
		}

		spawnEnergyStation();

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
