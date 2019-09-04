package pokemon.mario.tile;

import java.awt.Color;
import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class Ground extends Tile {

	public Ground(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.ground.getBufferedImage(),x,y,width,height,null);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}