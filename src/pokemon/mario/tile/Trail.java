package pokemon.mario.tile;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pokemon.mario.Handler;
import pokemon.mario.ID;

public class Trail extends Tile {
	
	private float alpha = 1.0f;
	
	private BufferedImage image;

	public Trail(int x, int y, int width, int height, boolean solid, ID id, Handler handler, BufferedImage image) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
		this.image = image;
	}

	@Override
	public void draw(Graphics graph) {
		Graphics2D graph2 = (Graphics2D) graph;
		
		graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		graph2.drawImage(image,getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		alpha-=0.05;
		
		if(alpha<0.05) {
			die();
		}
		
	}

}
