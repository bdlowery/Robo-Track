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
						ToastBar.showInfoMessage("Accelerate Robot +5");
						System.out.println("Accelerate Robot +5");
						gw.accelerate();
						break;
					// reduce speed of robot by small amount	
					case 'b':
						ToastBar.showInfoMessage("Slow Robot -5");
						System.out.println("Slow Robot -5");
						gw.brake();
						break;
					case 'l':
						ToastBar.showInfoMessage("Robot steers left -5");
						System.out.println("Robot steers left -5");
						gw.steer(-5);
						break;
					case 'r':
						ToastBar.showInfoMessage("Robot steers right 5");
						System.out.println("Robot steers right 5");
						gw.steer(5);
						break;
					case 'c':
						ToastBar.showInfoMessage("Robot collided with another robot");
						System.out.println("Robot collided with another robot");
						gw.collide();
						break;
					case '1':
						ToastBar.showInfoMessage("Robot collided with base 1");
						System.out.println("Robot collided with base 1");
						gw.collideBase(1);
						break;
					case '2':
						ToastBar.showInfoMessage("Robot collided with base 2");
						System.out.println("Robot collided with base 2");
						gw.collideBase(2);
						break;
					case '3':
						ToastBar.showInfoMessage("Robot collided with base 3");
						System.out.println("Robot collided with base 3");
						gw.collideBase(3);
						break;
					case '4':
						ToastBar.showInfoMessage("Robot collided with base 4");
						System.out.println("Robot collided with base 4");
						gw.collideBase(4);
						break;
					case '5':
						ToastBar.showInfoMessage("Robot collided with base 5");
						System.out.println("Robot collided with base 5");
						gw.collideBase(5);
					case '6':
						ToastBar.showInfoMessage("Robot collided with base 6");
						System.out.println("Robot collided with base 6");
						gw.collideBase(6);
					case '7':
						ToastBar.showInfoMessage("Robot collided with base 7");
						System.out.println("Robot collided with base 7");
						gw.collideBase(7);
					case '8':
						ToastBar.showInfoMessage("Robot collided with base 8");
						System.out.println("Robot collided with base 8");
						gw.collideBase(8);
					case '9':
						ToastBar.showInfoMessage("Robot collided with base 9");
						System.out.println("Robot collided with base 9");
						gw.collideBase(9);
						
					case 'e':
						ToastBar.showInfoMessage("Robot collided with Energy Station");
						System.out.println("Robot collided with Energy Station");
						gw.collideEnergyStation();
						
					case 'g':
						ToastBar.showInfoMessage("Robot collided with Drone");
						System.out.println("Robot Collided with Drone");
						gw.collideDrone();
						break;			
						
					case 't':
						ToastBar.showInfoMessage("ticked");
						System.out.println("ticked");
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
						if(quit) {
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
