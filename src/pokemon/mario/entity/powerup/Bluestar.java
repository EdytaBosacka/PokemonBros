package pokemon.mario.entity.powerup;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.tile.Tile;

public class Bluestar extends Mushroom {

	public Bluestar(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.bluestar.getBufferedImage(), x, y, width, height, null);
		
	}
	public void update() {
		x += speedX;
		y += speedY;

		for (Tile t : handler.tile) {
			if (!t.solid)
				continue;
			if (t.getID() == ID.wall||t.getID() == ID.powerUp) {

				if (getBoundsBottom().intersects(t.getBounds())) {
					
					jumping = true;
					gravity = 5.5;
					if (falling)
						falling = false;
				}
//			

				if (getBoundsLeft().intersects(t.getBounds())) {
					setSpeedX(5.0);

				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setSpeedX(-5.0);
				}

			}

		}
		if(jumping){
			gravity -= 0.17;
			setSpeedY((int)-gravity);
			if(gravity <=0.5) {
				jumping = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.1;
			setSpeedY(gravity);
		}

	}

}
