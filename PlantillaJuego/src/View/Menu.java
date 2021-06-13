package View;

import processing.core.PApplet;
import processing.core.PImage;

public class Menu {

	private PImage menu;
	private PApplet app;
	private int screen;
	
	public Menu(PApplet app) {
		
		this.app=app;
		screen=1;
		menu=app.loadImage("../Resources/Inicio.png");
	}
	
	public void drawScreen() {
		
		app.image(menu, 0, 0);
	}
	
	public int button() {
		
		int screen=1;
		
		if(app.mouseX>510 && app.mouseX<688 && app.mouseY>492 && app.mouseY<515) {
			screen=2;
		}
		if(app.mouseX>510 && app.mouseX<688 && app.mouseY>563 && app.mouseY<571){
			screen=4;
		}
		if(app.mouseX>578 && app.mouseX<623 && app.mouseY>584 && app.mouseY<614){
			app.exit();
		}
		
		return screen;
	}
	
}
