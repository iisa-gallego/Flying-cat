package Model;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy implements Runnable, Drawable {

	private PApplet app;

	private PImage enemyLeft;
	private PImage enemyRight;

	private int posXEnemy;
	private int posYEnemy;
	private int speedEnemy;
	private int dir;
	private int moveX;
	private int changeX;
	private boolean moveXEnemy;


	public Enemy (PApplet app , int posXEnemy, int posYEnemy, int dir) {

		this.app=app;
		this.posXEnemy=posXEnemy;
		this.posYEnemy=posYEnemy;
		this.dir=dir;
		moveXEnemy = false;

		enemyLeft=app.loadImage("../Resources/enemyleft.png");
		enemyRight=app.loadImage("../Resources/enemyright.png");

		int speedEnemy = 2;

	} //CONSTRUCTOR

	public void moveEnemy () {
		if (moveXEnemy == true) {
			posXEnemy = posXEnemy - 8;
			moveX = moveX - 8;
		}

		posXEnemy+= dir;

		if (posYEnemy==183) {

			if (posXEnemy >= 1103 + moveX || posXEnemy<= 790 + moveX) {
				dir *= -1;
			}
		}

		if (posYEnemy==450) {

			if (posXEnemy>= 1518 + moveX || posXEnemy<= 987  + moveX) {
				dir*=-1;
			}
		}

		if (posYEnemy==184) {

			if (posXEnemy>= 1716  + moveX || posXEnemy<= 1359  + moveX) {
				dir*=-1;
			}
		}

		if (posYEnemy==148) {

			if (posXEnemy>= 2260  + moveX || posXEnemy<= 1903  + moveX) {
				dir*=-1;
			}
		}

		if (posYEnemy==451) {

			if (posXEnemy>= 2757  + moveX || posXEnemy<= 2146  + moveX) {
				dir*=-1;
			}
		}


	}

	public int getPosXEnemy() {
		return posXEnemy;
	}

	public void setPosXEnemy(int posXEnemy) {
		this.posXEnemy = posXEnemy;
	}

	public int getPosYEnemy() {
		return posYEnemy;
	}

	public void setPosYEnemy(int posYEnemy) {
		this.posYEnemy = posYEnemy;
	}

	public int getSpeedEnemy() {
		return speedEnemy;
	}

	public void setSpeedEnemy(int speedEnemy) {
		this.speedEnemy = speedEnemy;
	}


	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}


	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}


	public boolean isMoveXEnemy() {
		return moveXEnemy;
	}

	public void setMoveXEnemy(boolean moveXEnemy) {
		this.moveXEnemy = moveXEnemy;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		moveEnemy();
	}

	@Override
	public void drawChar() {
		if (dir==1) {

			app.image(enemyLeft, posXEnemy, posYEnemy);
		}

		if (dir==-1) {
			app.image(enemyRight, posXEnemy, posYEnemy);
		}
	}

}