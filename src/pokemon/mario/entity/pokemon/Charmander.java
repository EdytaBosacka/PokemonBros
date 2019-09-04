package pokemon.mario.entity.pokemon;

import java.awt.Graphics;
import java.util.Random;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.tile.Tile;

public class Charmander extends Entity {

	private int frame = 0;
	private int frameDelay = 0;
	private Random random = new Random();

	public Charmander(int x, int y, int width, int height,  ID id, Handler handler) {
		super(x, y, width, height,  id, handler);
		int direction = random.nextInt(2);

		switch (direction) {
		case 0:
			setSpeedX(-2);
			break;
		case 1:
			setSpeedX(2);
			break;

		}
	}

	@Override
	public void draw(Graphics graph) {
		switch (frame) {
		case 0:
			graph.drawImage(Main.charmander[2].getBufferedImage(), x, y, width, height, null);
			break;
		case 1:
			graph.drawImage(Main.charmander[5].getBufferedImage(), x, y, width, height, null);
			break;
		case 2:
			graph.drawImage(Main.charmander[39].getBufferedImage(), x, y, width, height, null);
			break;
		case 3:
			graph.drawImage(Main.charmander[38].getBufferedImage(), x, y, width, height, null);
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
						gravity = 0.8;
						falling = true;
					}
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setSpeedX(2);

				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setSpeedX(-2);
				}

			}

		}
		if (falling) {
			gravity += 0.1;
			setSpeedY(gravity);
		}
		frameDelay++;
		if (frameDelay >= 10) {
			frame++;
			if (frame > 3) {
				frame = 0;
			}
			frameDelay = 0;
		}

	}

}
