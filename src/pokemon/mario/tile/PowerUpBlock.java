package pokemon.mario.tile;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.powerup.Mushroom;
import pokemon.mario.graphics.Sprite;

public class PowerUpBlock extends Tile {
	
	private Sprite kanto;
	private boolean poppedUp = false;
	
	private int spriteY = getY();

	public PowerUpBlock(int x, int y, int width, int height, boolean solid, ID id, Handler handler, Sprite kanto) {
		super(x, y, width, height, solid, id, handler);
		this.kanto = kanto;
	}

	@Override
	public void draw(Graphics graph) {
		if(!poppedUp)   graph.drawImage(kanto.getBufferedImage(),x,spriteY,width,height,null);
		if(!activated) graph.drawImage(Main.powerUp.getBufferedImage(),x,y,width,height,null);
		else graph.drawImage(Main.usedPowerUp.getBufferedImage(),x,y,width,height,null);
	}

	@Override
	public void update() {
		if(activated&&!poppedUp) {
			spriteY--;
			if(spriteY<=y-height)
			{
				handler.addEntity(new Mushroom(x,spriteY,width,height,ID.mushroom,handler));
				poppedUp = true;
			}
		}
		
	}
	
	

}
