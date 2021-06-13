package View;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import View.Gameplay;
import View.Menu;
import View.Register;
import View.Score;

public class Main extends PApplet{


	
	private Menu menu;
	private Register register;
	private Gameplay gameplay;
	private Score score;
	
	private int screen;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PApplet.main("View.Main");
	}
	
	public void settings() {
		
		size(1200,680);
	}
	
	public void setup() {
		
		screen=1;
		
		menu= new Menu(this);
		register= new Register(this);
		gameplay= new Gameplay(this);
		score= new Score(this);
	}
	
	public void draw() {
		
		background(0);
		
		
		switch (screen) {
		case 1:
			
			menu.drawScreen();
			register.getCp5().hide();
			break;
			
		case 2:
			
			register.drawScreen();
			register.getCp5().show();
			break;
			
		case 3:
			
			gameplay.drawScreen();
			register.getCp5().hide();
			screen = gameplay.getScreen();
			break;
			
		case 4:
			
			score.drawScreen();
			register.getCp5().hide();
			break;
			
		case 5:
			
			register.getCp5().hide();
			break;
		default:
			break;
		}
		
	}
	
	
	public void mousePressed() {
		
		System.out.println("X= "+ mouseX);
		System.out.println("Y= "+ mouseY);
		
		switch (screen) {
		case 1:
			
			menu.button();
			screen=menu.button();
			break;
			
		case 2:
			
			register.button();
			screen=register.button();
			break;
			
		case 3:
			
			gameplay.button();
			screen=gameplay.button();
			break;
			
		case 4:
			
			score.button();
			screen=score.button();
			break;


		default:
			break;
		}
	}
	public void keyPressed() {
		int c = keyCode;
		gameplay.getKey(c);
	}
	public void keyReleased() {
		int c = keyCode;
		gameplay.notMove(c);
	}

}
