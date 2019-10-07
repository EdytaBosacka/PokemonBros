package pokemon.mario.tile;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;

public class Flag extends Tile {

	public Flag(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawImage(Main.flag[0].getBufferedImage(), getX(),getY(),width,64,null);
		graph.drawImage(Main.flag[1].getBufferedImage(), getX(),getY()+64,width,64,null);
		graph.drawImage(Main.flag[1].getBufferedImage(), getX(),getY()+128,width,64,null);
		graph.drawImage(Main.flag[1].getBufferedImage(), getX(),getY()+192,width,64,null);
		graph.drawImage(Main.flag[2].getBufferedImage(), getX(),getY()+256,width,64,null);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	

}
