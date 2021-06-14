package View;

import Controller.ControllerScore;
import processing.core.PApplet;
import processing.core.PImage;

public class Score {

	private PApplet app;
	private PImage score;
	private ControllerScore scoreControl;
	
	public Score(PApplet app) {
		
		this.app=app;
		score = app.loadImage("../Resources/Score.png");
		scoreControl= new ControllerScore(app);
	}
	
	public void drawScreen() {
		
		app.image(score, 0, 0);
		scoreControl.drawData();
		
	}
	
	public int button() {
		
		int screen=4;
		
		if(app.mouseX>957 && app.mouseX<1093 && app.mouseY>615 && app.mouseY<643) {
			screen=1;
			
		}
		
		else if(app.mouseX>165 && app.mouseX<232 && app.mouseY>215 && app.mouseY<228) {
			scoreControl.sortNames();
			
		} else if(app.mouseX>398 && app.mouseX<447 && app.mouseY>215 && app.mouseY<228) {
			scoreControl.sortDate(); 
			
		} else if(app.mouseX>672 && app.mouseX<722 && app.mouseY>215 && app.mouseY<228) {
			scoreControl.sortTime();
			
		} else if(app.mouseX>968 && app.mouseX<1035 && app.mouseY>215 && app.mouseY<228) {
			scoreControl.sortScore();
		}
		
		return screen;
	}
	
}
