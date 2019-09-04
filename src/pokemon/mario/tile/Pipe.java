package pokemon.mario.tile;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class Pipe extends Tile{

	public Pipe(int x, int y, int width, int height, boolean solid, ID id, Handler handler,int facing) {
		super(x, y, width, height, solid, id, handler);
		this.facing = facing;
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.pipe.getBufferedImage(), x, y, width, height, null);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
