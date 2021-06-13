package Controller;


import Model.Logic;
import processing.core.PApplet;

public class ControllerScore {

	private Logic logic;
	
	public ControllerScore(PApplet app) {
		
		logic=Logic.getInstance(app);
		
	}
	
	public void drawData() {
		
		logic.drawData();
	}
	
	public void sortNames()
	{
		logic.organizebyName();
		//logic.drawData();
	}
	
	public void sortTime()
	{
		logic.organizebyTime();
		//logic.drawData();
	}
	
	public void sortDate()
	{
		logic.organizebyDate();
		//logic.drawData();
	}
	
	public void sortScore()
	{
		logic.organizebyScore();
		//logic.drawData();
	}
}
