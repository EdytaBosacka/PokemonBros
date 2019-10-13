package pokemon.mario.entity;

import java.awt.Graphics;
import java.util.Random;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.pokemon.Boss;
import pokemon.mario.entity.pokemon.Squirtle;
import pokemon.mario.entity.powerup.PowerUp;
import pokemon.mario.entity.powerup.Star;
import pokemon.mario.states.BossState;
import pokemon.mario.states.PlayerState;
import pokemon.mario.states.SquirtleState;
import pokemon.mario.tile.Tile;

public class Player extends Entity {

	private PlayerState state;

	private int pixelsTravelled = 0;

	private int frame = 0;
	private int frameDelay = 0;
	private boolean animated = false;

	private Random random = new Random();

	public Player(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);

		state = PlayerState.SMALL;

	}

	@Override
	public void draw(Graphics graph) {
		if (facing == 0) { // 0-right, 1-left
			graph.drawImage(Main.player[frame % 5 + 15].getBufferedImage(), x, y, width, height, null);
		} else {
			graph.drawImage(Main.player[frame % 5 + 5].getBufferedImage(), x, y, width, height, null);
		} // graph.drawImage(Main.player.getBufferedImage(),x,y,width,height,null);
	}

	@Override
	public void die() {

		super.die();
		Main.setLives(Main.getLives() - 1);
		Main.setDeathScreen(true);

		if (Main.getLives() == 0) {
			Main.setGameover(true);
		}

	}

	@Override
	public void update() {
		x += speedX;
		y += speedY;
		if (x < 0)
			x = 0;

		if (speedX != 0) {
			animated = true;
		} else {
			animated = false;
		}

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (!t.solid || goingDownPipe)
				continue;
			if (t.getID() == ID.flag) {
				if (getBounds().intersects(t.getBounds())) {
					Main.switchLevel();
				}

			} else if (t.getID() == ID.wall || t.getID() == ID.powerUp || t.getID() == ID.pipe) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setSpeedY(0);
					if (jumping && !goingDownPipe) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}
					if (t.getID() == ID.powerUp) {
						t.activated = true;
					}
				}

				if (getBoundsBottom().intersects(t.getBounds())) {
					setSpeedY(0);
					if (falling)
						falling = false;
				} else {
					if (!falling && !jumping) {
						gravity = 0.8;
						falling = true;
					}
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setSpeedX(0);
					x = t.getX() + t.width;
				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setSpeedX(0);
					x = t.getX() - width;
				}
			}
			if (getBounds().intersects(t.getBounds()) && t.getID() == ID.coin) {
				Main.setCoins(Main.getCoins() + 1);
				t.die();
			}

		}

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if (e.getId() == ID.mushroom) {
				if (getBounds().intersects(e.getBounds())) {
					if (e instanceof PowerUp) {
						y -= 0.3 * height;
						height *= 1.3;
						if (state == PlayerState.SMALL)
							state = PlayerState.BIG;
					} else if (e instanceof Star) {
						Main.setLives(Main.getLives() + 1);
					}
					e.die();

				}
			} else if (e.getId() == ID.charmander || e.getId() == ID.boss || e.getId() == ID.plant) {
				if (getBoundsBottom().intersects(e.getBoundsTop())) {
					if (e.getId() != ID.boss)
						e.die();
					else if (((Boss) e).isAttackable()) {
						((Boss) e).setHealth(((Boss) e).getHealth() - 1);
						e.falling = true;
						e.gravity = 3.0;
						((Boss) e).setBossState(BossState.RECOVERING);
						((Boss) e).setAttackable(false);
						((Boss) e).setPhaseTime(0);

						jumping = true;
						falling = false;
						gravity = 3.5;

					}
				} else if (getBounds().intersects(e.getBounds())) {
					if (state == PlayerState.BIG) {
						state = PlayerState.SMALL;
						height /= 1.3;
					} else if (state == PlayerState.SMALL) {
						die();
					}

				}

			} else if (e.getId() == ID.squirtle) {
				Squirtle squirtle = (Squirtle) e;
				if (squirtle.getSquirtleState() == SquirtleState.WALKING) {
					if (getBoundsBottom().intersects(squirtle.getBoundsTop())) {

						squirtle.setSquirtleState(SquirtleState.SHELL);
						squirtle.setSpeedX(0);

						jumping = true;
						falling = false;
						gravity = 3.5;

					} else if (getBounds().intersects(squirtle.getBounds())) {
						die();
					}

				} else if (squirtle.getSquirtleState() == SquirtleState.SHELL) {
					if (getBoundsBottom().intersects(squirtle.getBoundsTop())) {

						squirtle.setSquirtleState(SquirtleState.SPINNING);

						int direction = random.nextInt(2);

						switch (direction) {
						case 0:
							squirtle.setSpeedX(-10);
							break;
						case 1:
							squirtle.setSpeedX(10);
							break;

						}

						jumping = true;
						falling = false;
						gravity = 3.5;
					}
					if (getBoundsLeft().intersects(squirtle.getBoundsRight())) {
						squirtle.setSpeedX(-10);
						squirtle.setSquirtleState(SquirtleState.SPINNING);
					}
					if (getBoundsRight().intersects(squirtle.getBoundsLeft())) {
						squirtle.setSpeedX(10);
						squirtle.setSquirtleState(SquirtleState.SPINNING);
					}
				} else if (squirtle.getSquirtleState() == SquirtleState.SPINNING) {

					if (getBoundsBottom().intersects(squirtle.getBoundsTop())) {

						squirtle.setSquirtleState(SquirtleState.SHELL);

						jumping = true;
						falling = false;
						gravity = 3.5;

					} else if (getBounds().intersects(squirtle.getBounds())) {
						die();
					}

				}
			}

		}
		if (jumping && !goingDownPipe) {
			gravity -= 0.17;
			setSpeedY(-gravity);
			if (gravity <= 0.5) {
				jumping = false;
				falling = true;

			}
		}
		if (falling && !goingDownPipe) {
			gravity += 0.17;
			setSpeedY(gravity);
		}

		if (animated) {
			frameDelay++;
			if (frameDelay >= 5) {
				frame++;
				if (frame > Main.player.length) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}

		if (goingDownPipe) {
			for (int i = 0; i < Main.getHandler().tile.size(); i++) {
				Tile t = Main.getHandler().tile.get(i);
				if (t.getID() == ID.pipe) {
					if (getBounds().intersects(t.getBounds())) {

						switch (t.facing) {
						case 0:
							setSpeedY(-5);
							setSpeedX(0);
							pixelsTravelled += -speedY;
							break;
						case 2:
							setSpeedY(5);
							setSpeedX(0);
							pixelsTravelled += speedY;
							break;

						}
						if (pixelsTravelled >= t.height + height - 5) {
							goingDownPipe = false;
							pixelsTravelled = 0;
						}
					}
				}
			}
		}

	}

}
