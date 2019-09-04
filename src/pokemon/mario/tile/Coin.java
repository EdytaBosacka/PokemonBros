package pokemon.mario.tile;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class Coin extends Tile {

	public Coin(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.coin.getBufferedImage(),x,y,width,height,null);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
