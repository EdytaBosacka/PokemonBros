package pokemon.mario.entity.pokemon;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;

public class Victreebel extends Entity {

	private int wait;
	private int pixelsTravelled;
	private boolean moving;
	private boolean insidePipe;
	private static int movingspeed = 3;
	private int frame = 0;
	private int frameDelay = 0;

	public Victreebel(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);

		moving = false;
		insidePipe = true;
	}

	@Override
	public void draw(Graphics graph) {
		switch (frame) {
		case 0:
			graph.drawImage(Main.victreebel[4].getBufferedImage(), x, y, width, height, null);
			break;
		case 1:
			graph.drawImage(Main.victreebel[5].getBufferedImage(), x, y, width, height, null);
			break;
		case 2:
			graph.drawImage(Main.victreebel[6].getBufferedImage(), x, y, width, height, null);
			break;
		case 3:
			graph.drawImage(Main.victreebel[0].getBufferedImage(), x, y, width, height, null);
			break;
		}

	}

	@Override
	public void update() {

		y += speedY;

		if (!moving) {
			wait++;
		}

		if (wait >= 180) {
			if (insidePipe) {
				insidePipe = false;
			} else {
				insidePipe = true;
			}
			moving = true;
			wait = 0;
		}
		
		if(moving) {
			if(insidePipe) {
				setSpeedY(movingspeed);
			}else {
				setSpeedY(-movingspeed);
			}
			pixelsTravelled +=speedY;
			
			if(Math.abs(pixelsTravelled)>=getHeight()) {
				pixelsTravelled = 0;
				moving = false;
				
				setSpeedY(0);
			}
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
