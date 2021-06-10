package Controller;

import Model.Logic;
import processing.core.PApplet;

public class ControllerRegister {

	private PApplet app;
	private Logic logic;
	
	public ControllerRegister(PApplet app) {
		
		this.app=app;
		logic=Logic.getInstance(app);
		
	}
	
	public void registerPlayer(String name) {
		// TODO Auto-generated method stub
		logic.registerPlayer(name);
	}
}
