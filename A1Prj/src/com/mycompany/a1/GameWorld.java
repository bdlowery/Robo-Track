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
		//set the size of the world to 1024 x 768
		this.width = 1024;
		this.height = 768;
		rand = new Random();
		minSize = 10;
		maxSize = 50;
		this.clock = 0;
		this.lives = 3;
		this.gameObjects = new ArrayList<GameObject>();
	}
	
	private void spawnEnergyStation() {
		int s = rand.nextInt(this.maxSize-this.minSize) + minSize;
		int x = rand.nextInt(this.width);
		int y = rand.nextInt(this.height);
		EnergyStation energyStation = new EnergyStation(s, x, y);
		gameObjects.add(energyStation);
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
		int s = rand.nextInt(this.maxSize-this.minSize) + minSize;
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
		//code here to create the 
		//initial game objects/setup
		//create robot, drones, bases, and energy stations and add to gameObjects array.
		
		// spawn 2 - 5 energy stations.
		for (int i = 0; i < rand.nextInt(3) + 2; i++) {
			spawnEnergyStation();
		}
		
		//span 2 - 4 drones
		for (int i = 0; i < rand.nextInt(2) + 2; i++) {
			spawnDrone();
		}
		
		spawnRobot();
		

	}
	
	 // additional methods here to 
	 // manipulate world objects and 
	 // related game state data 
	
	// increment the clock, and run move() function for all of the movable gameworld objects.
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
//				 ((Robot) singleObject).consumeEnergy();
				
				// check if the robots energyLevel is 0.
				if(((Robot) singleObject).getEnergyLevel() == 0) {
					System.out.println("Robot energy has reached 0");
					// decrement life variable
					lives--;
					// respawn the robot because it died
					((Robot) singleObject).respawnRobot();
					// init the gameworld again
					init();
				}
			}
		}



		

		// if it is, indicate that a life was lost, and decrement the lives variable (instantiated to 3 in gameworld class)
		// Call the robots respawn function, then init the gameworld again.
		
		// check if the robots damage level is 100.
		// if it is, indicate that the robot has lost all of its life
		// decrement lives variable
		// call the robots respawn function, then init the gameworld.
		
		// check if the gameobject is a drone
		// if it is, change the drones heading to a random value (drone should have a randomHeading function)
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
		// call the robots accelerate function, but pass in a negative value to decrease the robots speed (-5)
		// sysout the new speed of the robot
	
	public void steer(int direction) {
		// loop through the gameobjects array
		for (GameObject singleObject : gameObjects) {
			if (singleObject instanceof Robot) {
				((Robot) singleObject).turn(direction);
			}
		}
		// call the steering function from the robot class, passing in the direction.
		
		//Steer function in robot class will handle what direction to turn the robot.
		
	}
	
	public void collide() {
		// make a variable simulating a robots health. int fakeHealth = 20;.
		// loop through the game objects.
		// if the game object is a Robot, call the damage function, passing in the fakeHealth amount.
	}
	
	public void collideBase(int sequenceNumber) {
		// loop through the game objects.
		// check if the game object is a robot.
		// if the sequence number is 1 more than the robots lastBaseReached (Robot.getLastBaseReached())
		// call the function for updating the lastBaseReached (just ++ the lastBase by number by in the robot class)
		
	}
	
	public void collideEnergyStation() {
		// loop through all of the game objects.
		// pick a random energy station for the robot to collide with
		
		// loop through the game objects
		// check for a robot
		// call the charging function from Robot, passing in the capacity of the randomly picked energyStation as the value to charge by
		// remove the energystation from the gameworld
		// create a new energystation (with a random size and location, but should be defined already to do that)
	}
	
	public void collideDrone() {
		// loop through game objects
		// pick a random drone to collide with the robot
		
		// loop through game objects
		// if game object is a robot
		// call the Robots applyDamage function, while passing in the drones damage value.
	}
	
	public void display() {
		// create an empty string
		// append all of the required values to that string. (lives and clock)
		// loop through the gameobjects
		// if the game object is a robot, append the last base reached, current energy level, and current damage level.
		
	}
	
	public void map() {
		// create an empty string
		// loop through all of the game objects
		// append to the string all of the gameobjects .toString functions.
		// display all of the gameobjects stats with sysout.
	}
	
	public void exit() {
		System.exit(0);
	}

	
}
