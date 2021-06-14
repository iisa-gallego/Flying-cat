package View;

import controlP5.ControlP5;
import controlP5.Textfield;
import Controller.Controller;
import Controller.ControllerRegister;
import Exception.NameLenght;
import Exception.NoName;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Register {

	private PApplet app;
	private ControlP5 cp5;
	private PFont fontText;
	private ControllerRegister controller;
	private PImage register;
	
	private boolean correct;
	
	private String [] inputs;
	private String name;
	
	
	public Register(PApplet app) {
		
		this.app=app;
		//screen=2;
		register=app.loadImage("../Resources/Register.png");
		controller = new ControllerRegister(app);
		fontText=app.createFont("../Resources/Krungthep.ttf", 40);
		correct= false;
		
		cp5= new ControlP5(app);
		inputs= new String[1];
		
		createText();
		
	}
	
	public void createText() {
		
		inputs[0] = "Name";
		
		cp5.addTextfield(inputs[0]).setPosition(462,295).setSize(400,40).setAutoClear(true)
		.setColorBackground(app.color(0,0,0,1)).setColorActive(app.color(0,0,0,1))
		.setColorValueLabel(app.color(0)).setFont(fontText);
		
	}
	
	public void registerPlayer() throws NoName, NameLenght{
		
		name=cp5.get(Textfield.class, "Name").getText();
		
		boolean noName = false;
		
		if (name.equals("")) {
			
			noName = true;
		}
		
		if(noName==true) {
			throw new NoName("There is NO name");
			
		}else if (name.length()>10) {
			throw new NameLenght("Name is too long");
			
		}else {
			controller.registerPlayer(name);
			correct=true;
		}
	}
	
	public void drawScreen() {
		
		app.image(register, 0, 0);
	}
	
	public int button() {
		int screen=2;
		
			if(app.mouseX>544 && app.mouseX<634 && app.mouseY>444 && app.mouseY<519) {
			// esq sup der:634 esq sup izq:544 esq inf izq:444 esq inf der:519
			
			try {
				registerPlayer();
			} catch (NoName e) {
				// TODO Auto-generated catch block
				System.out.println("There is no name in the text field");
			} catch (NameLenght e) {
				// TODO: handle exception
				System.out.println("Name is too long");
			}
			
			if (correct==true) {
				
				screen=3;
				cp5.get(Textfield.class, "Name").setText("");
			}
			
		}
		
		if(app.mouseX>513 && app.mouseX<653 && app.mouseY>595 && app.mouseY<628) {
			screen=1;
		}
		
		return screen;
	}



	public ControlP5 getCp5() {
		return cp5;
	}

	public void setCp5(ControlP5 cp5) {
		this.cp5 = cp5;
	}
	
	
	
	
}
