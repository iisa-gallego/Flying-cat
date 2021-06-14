package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import Exception.Lose;
import processing.core.PApplet;

public class Logic {

	private PApplet app;
	private boolean move;
	private byName byname;
	private byTime bytime;
	private byDate bydate;

	private int min,seg;
	private boolean time;
	private boolean loseTouch;

	private String temporalName;
	private int posXEnemy;
	private LinkedList<player> player;
	private ArrayList<Enemy> enemy;

	private ArrayList <Collectibles> collectibles;

	private Cat cat;

	private int score;


	private static Logic oneInstance;

	private Logic(PApplet app) {

		this.app=app;
		cat = new Cat(50, 457, app);
		byname= new byName();
		bytime= new byTime();
		bydate= new byDate();
		temporalName="";
		score=0;
		loseTouch=false;

		min=0;
		seg=0;
		time=false;

		player = new LinkedList<player>();
		enemy = new ArrayList<Enemy>();
		collectibles = new ArrayList <Collectibles> ();

		try {
			createEnemy();
			createCollectibles ();
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
		cat.drawChar();
		if (move == true) {
			Thread revyMove = new Thread(cat);
			revyMove.start();
		}

		for (int i = 0; i < collectibles.size(); i++) {
			collectibles.get(i).setMoveXYarn(enemy.get(0).isMoveXEnemy());
			Thread starMove = new Thread(collectibles.get(i));
			starMove.start();
		}

		time=true;
		paintTime();
		newPoint();
		collision();
	}

	public void newPoint() {
		for (int i = 0; i < collectibles.size(); i++) {
			if (app.dist(cat.getPosXCollision()+66, cat.getPosY()+70, collectibles.get(i).getposXCollect(), collectibles.get(i).getposYCollect())<=100) {
				collectibles.remove(i);
				score = score + 100;
			}
		}
		app.fill(255);
		app.textSize(40);
		app.text("Score: "+ score, 300, 50);
	}

	public void fallRevy(boolean c) {
		cat.setFall(c);
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

	public void createCollectibles () {

		collectibles.add(new Collectibles (app, 414,490));

		collectibles.add(new Collectibles (app, 683,382));

		collectibles.add(new Collectibles (app, 975,238));

		collectibles.add(new Collectibles (app, 1515,238));

		collectibles.add(new Collectibles (app, 2068,211));

		collectibles.add(new Collectibles (app, 2094,493));

		collectibles.add(new Collectibles (app, 2343,490));

		collectibles.add(new Collectibles (app, 2833,326));

		collectibles.add(new Collectibles (app, 3083,326));

	}

	public void drawYarn () {

		for (Collectibles collectibles : collectibles) {
			collectibles.draw();
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
		app.text("Time: "+ min + ":" + seg, 100,50);
	}

	public void loseGame(){

		time=false;
		String m= Integer.toString(min);
		String s= Integer.toString(seg);
		String time = m+":"+s;
		Date date = new Date();
		for (int i = 0; i < 1; i++) {

			player newPlayer = new player(temporalName, date, time, score, app);
			player.add(newPlayer);

			System.out.println("player" + player.size());
		}
	}

	public void reset() {

		cat = new Cat(50, 457, app);
		enemy.clear();
		collectibles.clear();
		temporalName="";
		score=0;
		loseTouch=false;

		min=0;
		seg=0;

		createEnemy();
		createCollectibles ();
	}


	public void collision() {
		for (int i = 0; i < enemy.size(); i++) {
			if (app.dist(cat.getPosXCollision()+ 80, cat.getPosY()+ 10, enemy.get(i).getPosXEnemy()+ 80 , enemy.get(i).getPosYEnemy()+ 20) <= 20) {
				loseTouch = true;
				loseGame();
				cat.setPosXCollision(0);
			}
			if (app.dist(cat.getPosXCollision()+ 66, cat.getPosY()+ 140, enemy.get(i).getPosXEnemy()+ 50 , enemy.get(i).getPosYEnemy()) <= 40) {
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
		cat.setKey(c);

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
		return cat.getPosX();
	}




	public LinkedList<player> getPlayer() {
		return player;
	}

	public void setPlayer(LinkedList<player> player) {
		this.player = player;
	}


	public int getPosY() {
		return cat.getPosY();
	}



	public boolean isLoseTouch() {
		return loseTouch;
	}



	public void setLoseTouch(boolean loseTouch) {
		this.loseTouch = loseTouch;
	}


	public void organizebyName()
	{
		Collections.sort(player, byname);
	}

	public void organizebyScore()
	{
		Collections.sort(player);
	}

	public void organizebyDate()
	{
		Collections.sort(player, bydate);
	}

	public void organizebyTime()
	{
		Collections.sort(player, bytime);	
	}
	public void setPosXRevy(int posXCollision) {
		cat.setPosXCollision(posXCollision);

	}

	public int getXCollision() {
		return cat.getPosXCollision();
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