package View;

import Controller.Controller;
import Exception.Lose;
import Exception.Win;
import processing.core.PApplet;
import processing.core.PImage;

public class Gameplay {

	private PApplet app;
	private PImage game;

	private PImage win, lose;
	private int endCase;

	private Controller controlGame;
	private int posX;
	private int screen;
	private boolean moveScreen;
	private boolean endGame;

	public Gameplay(PApplet app) {
		this.app = app;
		controlGame = new Controller(app);
		game = app.loadImage("../Resources/Fondo.png");
		endGame = false;

		win = app.loadImage("../Resources/PantallaWin.png");
		lose = app.loadImage("../Resources/PantallaLose.png");
		endCase = 0;
		posX = 0;
		moveScreen = false;
		screen = 3;
	}

	public void drawScreen() {

		app.image(game, posX, 0);
		controlGame.drawGame();
		controlGame.drawEnemy();
		controlGame.drawYarn();
		if (moveScreen == true) {
			if (controlGame.getPosX() >= 50 && posX >= -2370) {
				controlGame.isMoving(true);
				posX = posX - 15;
			}
		} else {
			controlGame.isMoving(false);
		}
		// System.out.println(controlGame.getXCol()+66 );
		try {
			cattyFall();
		} catch (Lose e) {
			// TODO Auto-generated catch block
			endCase = 2;
		}

		ending();
		try {
			winCase();
		} catch (Win e) {
			endCase = 1;
		}

		try {
			touchEnemy();
		} catch (Lose e) {
			// TODO Auto-generated catch block
			endCase = 2;
		}

	}

	public void cattyFall() throws Lose {

		// System.out.println(701+posX+1112);

		/*
		 * if (app.dist(controlGame.getXCol()+66, controlGame.getPosY()+ 140,
		 * 701+posX+1112, 600)<= 20) { controlGame.fallCatty(false);//Ibamos a hacer una
		 * "caida" pero mejor no xd
		 * 
		 * } else {
		 * 
		 * controlGame.fallCatty(false); }
		 */

		if (controlGame.getPosY() + 140 == 640) {
			controlGame.loseGame();
		}

		if (controlGame.getPosY() + 140 >= 640) {
			controlGame.fallCatty(true);

			if (controlGame.getPosY() + 140 >= 1000) {
				endGame = true;
				throw new Lose("Perdiste");
			}
		}

		if (controlGame.getPosY() + 140 >= 1160) {
			throw new Lose("Perdiste");
		}
	}

	public void winCase() throws Win {
		if (controlGame.getXCol() + 66 >= 1164) {
			controlGame.loseGame();
			controlGame.setCattyPosX(0);
			throw new Win("ganaste");
		}
	}

	public void touchEnemy() throws Lose {
		if (controlGame.isTouch()) {
			throw new Lose("Perdiste");
		}
	}

	public void ending() {

		switch (endCase) {
		case 1:

			app.image(win, 0, 0);
			app.text(controlGame.getScore(), 800, 490);
			break;

		case 2:

			app.image(lose, 0, 0);
			app.text(controlGame.getScore(), 800, 490);
			break;

		default:
			break;
		}
	}

	public void reset() {

		controlGame.reset();
		endCase = 0;
		posX = 0;
		moveScreen = false;
		// screen = 3;
	}

	public int button() {

		int screen = 3;

		if (app.mouseX > 980 && app.mouseX < 1117 && app.mouseY > 608 && app.mouseY < 643) {
			screen = 1;

			try {
				reset();
			} catch (RuntimeException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			endGame = false;
		}

		return screen;
	}

	public void getKey(int c) {
		if (c == 39) {
			moveScreen = true;
		}
		controlGame.getKey(c);

	}

	public void notMove(int c) {
		if (c == 37 || c == 38 || c == 39) {
			moveScreen = false;
		}
		controlGame.notMove(c);
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

}
