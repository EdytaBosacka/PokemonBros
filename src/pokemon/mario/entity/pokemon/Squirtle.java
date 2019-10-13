package pokemon.mario.entity.pokemon;

import java.awt.Graphics;
import java.util.Random;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.states.SquirtleState;
import pokemon.mario.tile.Tile;

public class Squirtle extends Entity {

	private int frame = 0;
	private int frameDelay = 0;
	private Random random = new Random();
	private int shellCount;

	private SquirtleState squirtleState;

	public Squirtle(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);

		int direction = random.nextInt(2);

		switch (direction) {
		case 0:
			setSpeedX(-2.0);
			break;
		case 1:
			setSpeedX(2.0);
			break;

		}
		squirtleState = SquirtleState.WALKING;
	}

	@Override
	public void draw(Graphics graph) {
		if (squirtleState == SquirtleState.WALKING) {

			switch (frame) {
			case 0:
				graph.drawImage(Main.squirtle[0].getBufferedImage(), x, y, width, height, null);
				break;
			case 1:
				graph.drawImage(Main.squirtle[4].getBufferedImage(), x, y, width, height, null);
				break;
			case 2:
				graph.drawImage(Main.squirtle[6].getBufferedImage(), x, y, width, height, null);
				break;
			case 3:
				graph.drawImage(Main.squirtle[7].getBufferedImage(), x, y, width, height, null);
				break;
			}
		} else {
			graph.drawImage(Main.squirtleshell.getBufferedImage(), x, y, width, height, null);
		}

	}

	@Override
	public void update() {
		x += speedX;
		y += speedY;

		if (squirtleState == SquirtleState.SHELL) {
			shellCount++;
			if (shellCount >= 300) {

				shellCount = 0;
				squirtleState = SquirtleState.WALKING;
			}
			if (squirtleState == SquirtleState.WALKING || squirtleState == SquirtleState.SPINNING) {
				shellCount = 0;

				if (speedX == 0) {
					int direction = random.nextInt(2);

					switch (direction) {
					case 0:
						setSpeedX(-2.0);
						break;
					case 1:
						setSpeedX(2.0);
						break;
					}
				}
			}

		}

		for (Tile t : handler.tile) {
			if (!t.solid)
				continue;
			if (t.getID() == ID.wall || t.getID() == ID.powerUp) {

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
					if (squirtleState == SquirtleState.WALKING) {
						setSpeedX(2.0);
					} else {
						setSpeedX(8.0);
					}

				}

				if (getBoundsRight().intersects(t.getBounds())) {
					if (squirtleState == SquirtleState.WALKING) {
						setSpeedX(-2.0);
					} else {
						setSpeedX(-8.0);
					}

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

	public SquirtleState getSquirtleState() {
		return squirtleState;
	}

	public void setSquirtleState(SquirtleState squirtleState) {
		this.squirtleState = squirtleState;
	}

}
