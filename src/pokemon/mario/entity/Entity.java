package pokemon.mario.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import pokemon.mario.Handler;
import pokemon.mario.ID;

public abstract class Entity {
	protected int x,y;
	protected int width, height;
	
	
	public boolean jumping =false;
	public boolean falling = true;
	public boolean goingDownPipe = false;
	
	public int facing=0; //0-right, 1-left
	
	
	public double gravity=0.0;
	
	public double speedX, speedY;
	
	public ID id;
	public Handler handler;
	
	public Entity(int x,int y, int width, int height, ID id, Handler handler) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.id=id;
		this.handler=handler;
		
	}
	
	public abstract void draw (Graphics graph);
	public void die() {
		handler.removeEntity(this);
	}
	
	public abstract void update();
	
	public ID getId() {
		return id;
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


	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),width,height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle(getX()+10, getY(), width-20, 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(getX()+10, getY()+height-5, width-20, 5);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle(getX(), getY()+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle(getX()+width-5, getY()+10, 5, height-20);
	}

}
