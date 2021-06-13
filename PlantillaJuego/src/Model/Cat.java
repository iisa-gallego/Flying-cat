package Model;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class Cat extends Thread implements Drawable{

	private PApplet app;
	private PImage revy;

	private int floor;
	private int height;
	private int width;
	private int posX;
	private int posXCollision;
	private PVector position;
	private PVector vel;
	private PVector cc;
	private boolean jump;
	private int key;
	private int speed;
	private int posY;
	private boolean move;
	private boolean fall;

	public Cat(int x, int y,PApplet app) {

		posX = x;
		revy= app.loadImage("../Resources/Gatito.png");
		position= new PVector(x,y);
		vel= new PVector(0,0);
		cc= new PVector(0,0);
		floor= 500;
		height=40;
		jump=false;
		this.app = app;
		speed=2;
		move= false;
		fall = false;
	}

	@Override
	public void run() {
		move();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void move() {

		notMove();

		if (move == true) {

			if(key==37) {
				vel.x=-5;
				cc.x=0.5f;
			}

			if(key==39) {
				vel.x=8;
				cc.x=-0.7f;
			}

			if(key==38 && jump==false) {
				jump=true;
				vel.y=-40;
			}
		}
	}

	public void notMove() {

		if (key==37 ||key==39 || key==38 ) {
			move=true;
		}else {
			move = false; 
		}
	}

	public int getPosXCollision() {
		return posXCollision;
	}

	public void setPosXCollision(int posXCollision) {
		this.position.x = posXCollision;
	}


	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public PVector getVel() {
		return vel;
	}

	public void setVel(PVector vel) {
		this.vel = vel;
	}

	public PVector getCc() {
		return cc;
	}

	public void setCc(PVector cc) {
		this.cc = cc;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public PImage getRevy() {
		return revy;
	}

	public void setRevy(PImage revy) {
		this.revy = revy;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public PVector getPosition() {
		return position;
	}

	public void setPosition(PVector position) {
		this.position = position;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int c) {
		this.key = c;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	@Override
	public void drawChar() {
		cc.y= 3.5f;
		if(position.x<=(-1+position.x)|| position.x>=1190) {
			cc.x *=-1;
			vel.x*=-1;
		}

		app.image(revy, position.x, position.y);
		position.add(vel);
		vel.add(cc);


		if(vel.x>-1 && vel.x<1) {
			cc.x=0;
			vel.x=0;
		}
		
		if (fall == false) {
			if(position.y>=floor-height-5) {
				jump=false;
				position.y=floor-height;
				vel.y=0;
				cc.y=0;
			}
		} else if (fall == true ){
			vel.y = 40;
		}
		
		posY = (int) position.y;
		posXCollision = (int) position.x;
	}




}













