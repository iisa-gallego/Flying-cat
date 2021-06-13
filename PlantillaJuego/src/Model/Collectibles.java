package Model;

import processing.core.PApplet;
import processing.core.PImage;

public class Collectibles implements Runnable{

	private PApplet app;

	private PImage yarn;
	private PImage coin;
	private PImage lollipop;

	private int posXCollect;
	private int posYCollect;
	private boolean moveXCollect;
	//private boolean moveYCollect;

	public Collectibles(PApplet app , int posXCollect, int posYCollect) {

		this.app=app;

		this.posXCollect = posXCollect;
		this.posYCollect = posYCollect;
		
		moveXCollect= false;

		yarn=app.loadImage("Estambre.png");
		coin=app.loadImage("moneda.png");
		lollipop=app.loadImage("bombon.png");
	} //CONSTRUCTOR

	public void draw () {

		app.image(yarn, posXCollect, posYCollect);	

	}
	
	public void moveYarn() {
		if (moveXCollect == true) {
			posXCollect = posXCollect - 15;
		}	
	}
	
	

	public int getposXCollect () {
		return posXCollect;
	}
	public void setPosXCollect () {
		this.posXCollect = posXCollect;
	}
	public int getposYCollect () {
		return posYCollect;
	}
	
	public void setPosYCollect () {
		this.posYCollect = posYCollect;
	}

	public boolean isMoveXCollect() {
		return moveXCollect;
	}

	public void setMoveXYarn(boolean moveXYarn) {
		this.moveXCollect = moveXYarn;
	}

	@Override
	public void run() {
		moveYarn();
	}
	
	

} 
