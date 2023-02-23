package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.components.ToastBar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Random;

public class Game extends Form {
	private GameWorld gw;

	private int clock;

	public Game() {
		this.clock = 0;
		gw = new GameWorld();
		gw.init();
		play();
	}

	public void updateClock() {
		this.clock++;
	}

	private void play() {
		// code here to accept and
		// execute user commands that
		// operate on the game world
		// (refer to “Appendix - CN1
		// Notes” for accepting
		// keyboard commands via a text
		// field located on the form)

		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();

		myTextField.addActionListener(new ActionListener() {
			boolean quit = false;

			public void actionPerformed(ActionEvent evt) {

				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.length() > 0) {
					// add code to handle rest of the commands
					switch (sCommand.charAt(0)) {
					// increase speed of robot by small amount
					case 'a':
						System.out.println("\n*Accelerate Robot*");
						gw.accelerate();
						break;
					// reduce speed of robot by small amount
					case 'b':
						System.out.println("\n*Slow Robot*");
						gw.brake();
						break;
					case 'l':
						System.out.println("\n*Robot steers left*");
						gw.steer(-5);
						break;
					case 'r':
						System.out.println("\n*Robot steers right*");
						gw.steer(5);
						break;
					case 'c':
						System.out.println("\n*Robot collided with another robot*");
						gw.collide("Robot");
						break;
					case '1':
						System.out.println("\n*Robot collided with base 1*");
						gw.collideBase(1);
						break;
					case '2':
						System.out.println("\n*Robot collided with base 2*");
						gw.collideBase(2);
						break;
					case '3':
						System.out.println("\n*Robot collided with base 3*");
						gw.collideBase(3);
						break;
					case '4':
						System.out.println("\n*Robot collided with base 4*");
						gw.collideBase(4);
						break;
					case '5':
						System.out.println("\n*Robot collided with base 5*");
						gw.collideBase(5);
						break;
					case '6':
						System.out.println("\n*Robot collided with base 6*");
						gw.collideBase(6);
						break;
					case '7':
						System.out.println("\n*Robot collided with base 7*");
						gw.collideBase(7);
						break;
					case '8':
						System.out.println("\n*Robot collided with base 8*");
						gw.collideBase(8);
						break;
					case '9':
						System.out.println("\n*Robot collided with base 9*");
						gw.collideBase(9);
						break;
					case 'e':
						System.out.println("\n*Robot collided with Energy Station*");
						gw.collideEnergyStation();
						break;
					case 'g':
						System.out.println("\n*Robot Collided with Drone*");
						gw.collide("drone");
						break;
					case 't':
						System.out.println("\n*Clock as ticked*");
						gw.clockTick();
						break;
					case 'd':
						gw.display();
						break;

					case 'm':
						gw.map();
						break;
					case 'x':
						quit = true;
						break;
					case 'y':
						if (quit) {
							gw.exit();
						}
						break;
					case 'n':
						if (quit) {
							System.out.println("Game resumed");
							quit = false;
						}
						break;
					default:
						System.out.println("not a valid command");
						myTextField.clear();
						break;

					}
				}
			}
		});
	}
}
