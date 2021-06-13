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
		
		if(app.mouseX>435 && app.mouseX<752 && app.mouseY>650 && app.mouseY<722) {
			screen=1;
			
		}
		
		else if(app.mouseX>113 && app.mouseX<113+216 && app.mouseY>252 && app.mouseY<252+75) {
			scoreControl.sortNames();
			
		} else if(app.mouseX>361 && app.mouseX<361+216 && app.mouseY>252 && app.mouseY<252+75) {
			scoreControl.sortDate(); 
			
		} else if(app.mouseX>609 && app.mouseX<609+216 && app.mouseY>252 && app.mouseY<252+75) {
			scoreControl.sortTime();
			
		} else if(app.mouseX>857 && app.mouseX<857+216 && app.mouseY>252 && app.mouseY<252+75) {
			scoreControl.sortScore();
		}
		
		return screen;
	}
	
}
