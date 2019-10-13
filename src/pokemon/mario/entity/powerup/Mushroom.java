package pokemon.mario.entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.tile.Tile;

public abstract class Mushroom extends Entity {

	private Random random = new Random();
	public Mushroom(int x, int y, int width, int height,  ID id, Handler handler) {
		super(x, y, width, height,  id, handler);
		// TODO Auto-generated constructor stub
		int direction = random.nextInt(2);
		
		switch(direction) {
		case 0:
			setSpeedX(-5.0);
			break;
		case 1:
			setSpeedX(5.0);
			break;
		
		}
		
	}

	

	@Override
	public void update() {
		x += speedX;
		y += speedY;

		for (Tile t : handler.tile) {
			if (!t.solid)
				continue;
			if (t.getID() == ID.wall||t.getID() == ID.powerUp) {

				if (getBoundsBottom().intersects(t.getBounds())) {
					setSpeedY(0);
					if (falling)
						falling = false;
				} else {
					if (!falling) {
						gravity = 0.80;
						falling = true;
					}
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setSpeedX(5.0);

				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setSpeedX(-5.0);
				}

			}

		}
		if (falling) {
			gravity += 0.1;
			setSpeedY(gravity);
		}

	}

}
