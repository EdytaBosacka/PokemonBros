package pokemon.mario.entity.powerup;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class PowerUp extends Mushroom {

	public PowerUp(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.mushroom.getBufferedImage(), x, y, width, height, null);

	}

}
