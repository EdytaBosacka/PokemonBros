package pokemon.mario.entity;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class Particle extends Entity {

	private int frame = 0;
	private int frameDelay = 0;

	private boolean fading = false;

	public Particle(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graph) {
		if(!fading) {
			graph.drawImage(Main.particle[frame].getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null);
		}else {
			graph.drawImage(Main.particle[8-(frame-7)].getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null);
		}

	}

	@Override
	public void update() {
		frameDelay++;

		if (frameDelay > 2) {
			frame++;
			frameDelay = 0;
		}
		if(frame>=8) {
			fading = true;
			if(frame>=16) 
			{
				die();
			}
		}

	}

}
