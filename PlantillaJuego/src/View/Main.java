package View;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Main {

	public class Principal extends PApplet {

		public void MAIN(String[] args) {
			PApplet.main("Main");
		}
		
		//Screens
		PImage home;
		PImage sscore; // score screen
		PImage levelOne; 
		PImage levelTwo;
		PImage levelThree;

		public void settings() {
			size(1200, 700);
		}

		public void setup() {
			
		}
	}
}
