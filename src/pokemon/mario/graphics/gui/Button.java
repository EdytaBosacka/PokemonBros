package pokemon.mario.graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import pokemon.mario.Main;

public class Button {
	
	private int x,y;
	private int width, height;
	private String label;
	
	public Button(int x, int y, int width, int height, String label) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}
	
	public void draw(Graphics graph) {
		graph.setColor(new Color(0,206,209));
		graph.fillRoundRect(getX(), getY(), getWidth(), getHeight(),40,40);
		graph.setColor(Color.WHITE);
		graph.setFont(new Font("Century Gothic",Font.PLAIN,30));
		
		FontMetrics fm = graph.getFontMetrics();
		int stringX = (getWidth() - fm.stringWidth(getLabel()))/2;
		int stringY = (fm.getAscent()+ (getHeight()-(fm.getAscent()+fm.getDescent()))/2);
		graph.drawString(getLabel(),getX()+stringX, getY()+ stringY);
		
		
	}
	
	public void triggerEvent() {
		if(getLabel().toLowerCase().contains("start")) {
			Main.setPlaying(true);
		}else if(getLabel().toLowerCase().contains("wyjœcie")) {
			System.exit(0);
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
