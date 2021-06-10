package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import exception.Lose;
import processing.core.PApplet;

public class Logic {

	private PApplet app;
	private boolean move;
	private ByName byname;
	private ByTime bytime;
	private ByDate bydate;

	private int min,seg;
	private boolean time;
	private boolean loseTouch;

	private String temporalName;
	private int posXEnemy;
	private LinkedList<Player> player;
	private ArrayList<Enemy> enemy;

	private ArrayList <Star> star;

	private Revy revy;

	private int score;


	private static Logic oneInstance;

	private Logic(PApplet app) {

		this.app=app;
		revy = new Revy(50, 457, app);
		byname= new ByName();
		bytime= new ByTime();
		bydate= new ByDate();
		temporalName="";
		score=0;
		loseTouch=false;

		min=0;
		seg=0;
		time=false;

		player = new LinkedList<Player>();
		enemy = new ArrayList<Enemy>();
		star = new ArrayList <Star> ();

		try {
			createEnemy();
			createStar ();
		} catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
		}



	} //CONSTRUCTOR



	public static Logic getInstance(PApplet app) {
		if(oneInstance == null) {
			oneInstance = new Logic(app);
		}
		return oneInstance;
	}

	public void registerPlayer(String name) {
		temporalName=name;
	}

	public void drawGame() {
		revy.drawChar();
		if (move == true) {
			Thread revyMove = new Thread(revy);
			revyMove.start();
		}

		for (int i = 0; i < star.size(); i++) {
			star.get(i).setMoveXStar(enemy.get(0).isMoveXEnemy());
			Thread starMove = new Thread(star.get(i));
			starMove.start();
		}

		time=true;
		paintTime();
		newPoint();
		collision();
	}

	public void newPoint() {
		for (int i = 0; i < star.size(); i++) {
			if (app.dist(revy.getPosXCollision()+66, revy.getPosY()+70, star.get(i).getposXStar(), star.get(i).getposYStar())<=100) {
				star.remove(i);
				score = score + 100;
			}
		}
		app.fill(255);
		app.textSize(40);
		app.text("Score: "+ score, 700, 750);
	}

	public void fallRevy(boolean c) {
		revy.setFall(c);
	}

	public void drawEnemy() {

		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).drawChar();
			Thread newEnemy = new Thread(enemy.get(i));
			newEnemy.start();
			//System.out.println(enemy.get(0).getMoveX());
		}

		moveEnemy();

	}

	public void moveEnemy() {

		for (int i = 0; i < enemy.size(); i++) {

			enemy.get(i).moveEnemy();
		}
	}


	public void createEnemy () {

		enemy.add(new Enemy(app, 946, 183, 1));
		enemy.add(new Enemy(app, 1206, 450, -1));
		enemy.add(new Enemy(app, 1463, 184, -1));
		enemy.add(new Enemy(app, 2037, 148, 1));
		enemy.add(new Enemy(app, 2461, 451, -1));

		System.out.println(enemy.size());
	}

	public void createStar () {

		star.add(new Star (app, 414,490));

		star.add(new Star (app, 683,382));

		star.add(new Star (app, 975,238));

		star.add(new Star (app, 1515,238));

		star.add(new Star (app, 2068,211));

		star.add(new Star (app, 2094,493));

		star.add(new Star (app, 2343,490));

		star.add(new Star (app, 2833,326));

		star.add(new Star (app, 3083,326));

	}

	public void drawStars () {

		for (Star star : star) {
			star.draw();
		}

	}

	public void paintTime() {

		if (time==true) {


			if (app.frameCount % 60 == 0) {
				seg += 1;
			}
			if (seg == 60) {
				seg = 0;
				min += 1;
			}
		}
		app.fill(255);
		app.textSize(40);
		app.text("Time: "+ min + ":" + seg, 400, 750);
	}

	public void loseGame(){

		time=false;
		String m= Integer.toString(min);
		String s= Integer.toString(seg);
		String time = m+":"+s;
		Date date = new Date();
		for (int i = 0; i < 1; i++) {

			Player newPlayer = new Player(temporalName, date, time, score, app);
			player.add(newPlayer);

			System.out.println("player" + player.size());
		}
	}

	public void reset() {

		revy = new Revy(50, 457, app);
		enemy.clear();
		star.clear();
		temporalName="";
		score=0;
		loseTouch=false;

		min=0;
		seg=0;

		createEnemy();
		createStar ();
	}


	public void collision() {
		for (int i = 0; i < enemy.size(); i++) {
			if (app.dist(revy.getPosXCollision()+ 80, revy.getPosY()+ 10, enemy.get(i).getPosXEnemy()+ 80 , enemy.get(i).getPosYEnemy()+ 20) <= 20) {
				loseTouch = true;
				loseGame();
				revy.setPosXCollision(0);
			}
			if (app.dist(revy.getPosXCollision()+ 66, revy.getPosY()+ 140, enemy.get(i).getPosXEnemy()+ 50 , enemy.get(i).getPosYEnemy()) <= 40) {
				enemy.remove(i);
			}

		}		
	}


	public void drawData() {

		for (int i = 0; i < player.size(); i++) {
			player.get(i).drawData(180, 370+(50*i));
		}
	}

	public void getKey(int c) {
		revy.setKey(c);

		if (c == 37 || c== 38 || c == 39) {
			move=true;
		}
	}

	public void notMove (int c) {
		int not = c;
		if (not == 37 || not== 38 || not == 39) {
			move=false;
		}
	}


	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;

	}

	public int getPosX() {
		return revy.getPosX();
	}




	public LinkedList<Player> getPlayer() {
		return player;
	}

	public void setPlayer(LinkedList<Player> player) {
		this.player = player;
	}


	public int getPosY() {
		return revy.getPosY();
	}



	public boolean isLoseTouch() {
		return loseTouch;
	}



	public void setLoseTouch(boolean loseTouch) {
		this.loseTouch = loseTouch;
	}


	public void organizeByName()
	{
		Collections.sort(player, byname);
	}

	public void organizeByScore()
	{
		Collections.sort(player);
	}

	public void organizeByDate()
	{
		Collections.sort(player, bydate);
	}

	public void organizeByTime()
	{
		Collections.sort(player, bytime);	
	}
	public void setPosXRevy(int posXCollision) {
		revy.setPosXCollision(posXCollision);

	}

	public int getXCollision() {
		return revy.getPosXCollision();
	}

	public void setPosX(int c) {
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).setMoveX(c);
		}
	}

	public int getPosXEnemy(){
		for (int i = 0; i < enemy.size(); i++) {
			posXEnemy= enemy.get(i).getMoveX();	
		}
		return posXEnemy;
	}

	public void setIsMoving(boolean c){
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).setMoveXEnemy(c);
		}
	}




}