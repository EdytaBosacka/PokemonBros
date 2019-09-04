package pokemon.mario;

import pokemon.mario.entity.Entity;

public class Camera {
	private int x,y;
	
	public void update (Entity player) {
		setX(Main.getFrameWidth()/2-player.getX());
		setY(Main.getFrameHeight()/2-player.getY());
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

}
