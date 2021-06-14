package Controller;

import Model.Logic;
import processing.core.PApplet;

public class Controller {

	private PApplet app;
	private Logic logic;
	
	public Controller(PApplet app) {
		
		this.app=app;
		logic= Logic.getInstance(app);
	}

	public void loseGame() {
		
		logic.loseGame();
	}
	
	public void drawGame() {
		logic.drawGame();
	}
	
	public void drawEnemy() {
		logic.drawEnemy();
	}
	public void getKey(int c) {
		logic.getKey(c);
	}
	public void notMove(int c) {
		logic.notMove(c);
	}
	public int getPosX () {
		return logic.getPosX();
	}
	public void setPosXEnemy(int c) {
		logic.setPosX(c);
	}
	
	public void isMoving(boolean c) {
		logic.setIsMoving(c);
	}

	public int getPosXEnemy() {
		return logic.getPosXEnemy();
	}

	public void drawYarn () {
		logic.drawYarn();
	}

	public int getPosY() {
		return logic.getPosY();
	}
	
	public int getXCol() {
		return logic.getXCollision();
	}
	
	public void fallCatty(boolean c) {
		logic.fallCatty(c);
	}
	
	public int getScore() {
		return logic.getScore();
	}

	public boolean isTouch() {
		return logic.isLoseTouch();
  }
	public void setCattyPosX(int posXCollision) {
		logic.setPosXCatty(posXCollision);
	}
	
	public void reset() {
		logic.reset();
	}

}
