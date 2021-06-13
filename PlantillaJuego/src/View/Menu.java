package View;

import processing.core.PApplet;
import processing.core.PImage;

public class Menu {

	private PImage menu;
	private PApplet app;
	//private int screen;
	
	public Menu(PApplet app) {
		
		this.app=app;
		//screen=1;
		menu=app.loadImage("data/menu.png");
	}
	
	public void drawScreen() {
		
		app.image(menu, 0, 0);
	}
	
	public int button() {
		
		int screen=1;
		
		if(app.mouseX>580 && app.mouseX<1036 && app.mouseY>445 && app.mouseY<518) {
			screen=2;
		}
		if(app.mouseX>580 && app.mouseX<1036 && app.mouseY>562 && app.mouseY<632){
			screen=4;
		}
		if(app.mouseX>652 && app.mouseX<966 && app.mouseY>680 && app.mouseY<746){
			app.exit();
		}
		
		return screen;
	}
	
}
