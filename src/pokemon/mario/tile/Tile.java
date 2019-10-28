package pokemon.mario.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import pokemon.mario.Handler;
import pokemon.mario.ID;

public abstract class Tile {
	public int x,y;
	public int width, height;
	
	public boolean solid;
	public boolean activated = false;
	
	public int speedX, speedY;
	public int facing;
	
	public ID id;
	public Handler handler;
	
	public Tile(int x,int y, int width, int height, boolean solid, ID id, Handler handler) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.solid=solid;
		this.id=id;
		this.handler=handler;
	}
	
	public abstract void draw (Graphics graph);
	public void die() {
		handler.removeTile(this);
	}
	
	public abstract void update();

	public ID getID() {
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

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),width,height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	

}
