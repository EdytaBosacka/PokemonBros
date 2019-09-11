package pokemon.mario.graphics.gui;

import java.awt.Color;
import java.awt.Graphics;

import pokemon.mario.Main;

public class Launcher {
	
	public Button[] buttons;
	
	public Launcher() {
		
		buttons = new Button[2];
		
		buttons[0] = new Button(520,200,200,100,"START");
		buttons[1] = new Button(520,320,200,100,"WYJŒCIE");
		
	}
	
	public void draw (Graphics graphic) {
		
		graphic.setColor(new Color(135, 206, 235));
		//graphic.fillRect(0,0,Main.getFrameWidth(),Main.getFrameHeight());
		graphic.drawImage(Main.backgroundmenu,0,0,Main.getFrameWidth()+20,Main.getFrameHeight()+13,null);
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].draw(graphic);
		}
	}

}
