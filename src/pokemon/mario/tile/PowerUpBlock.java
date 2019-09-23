package pokemon.mario.tile;

import java.awt.Graphics;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.powerup.Mushroom;
import pokemon.mario.entity.powerup.PowerUp;
import pokemon.mario.entity.powerup.Star;
import pokemon.mario.graphics.Sprite;

public class PowerUpBlock extends Tile {
	
	public static int POWERUP = 1;
	public static int STAR = 2;
	
	private Sprite kanto;
	private boolean poppedUp = false;
	
	private int spriteY = getY();
	private int type;

	public PowerUpBlock(int x, int y, int width, int height, boolean solid, ID id, Handler handler, Sprite kanto, int type) {
		super(x, y, width, height, solid, id, handler);
		this.type = type;
		this.kanto = kanto;
	}

	@Override
	public void draw(Graphics graph) {
		if(!poppedUp) {  
			graph.drawImage(kanto.getBufferedImage(),x,spriteY,width,height,null);}
		if(!activated) graph.drawImage(Main.powerUp.getBufferedImage(),x,y,width,height,null);
		else graph.drawImage(Main.usedPowerUp.getBufferedImage(),x,y,width,height,null);
	}

	@Override
	public void update() {
		if(activated&&!poppedUp) {
			spriteY--;
			if(spriteY<=y-height)
			{
				if(type ==POWERUP) {
				handler.addEntity(new PowerUp(x,spriteY,width,height,ID.mushroom,handler));
				}else if(type == STAR) {
				handler.addEntity(new Star(x,spriteY,width,height,ID.mushroom,handler));
				}
				poppedUp = true;
			}
		}
		
	}
	
	

}
