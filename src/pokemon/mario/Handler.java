package pokemon.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import pokemon.mario.entity.Entity;
import pokemon.mario.entity.Player;
import pokemon.mario.entity.pokemon.Charmander;
import pokemon.mario.entity.powerup.Mushroom;
import pokemon.mario.tile.Coin;
import pokemon.mario.tile.Ground;
import pokemon.mario.tile.Pipe;
import pokemon.mario.tile.PowerUpBlock;
import pokemon.mario.tile.Tile;
import pokemon.mario.tile.Wall;

public class Handler {
	
	
	public Handler() {
	}
	
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public void draw(Graphics g) {
		for (int i = 0;i<entity.size();i++) {
			Entity en = entity.get(i);
			en.draw(g);
		}
		for(Tile ti: tile) {
			ti.draw(g);
		}
	}
	
	public void update() {
		for (int i=0;i<entity.size();i++) {
			Entity en = entity.get(i);
			en.update();
		}
		for(Tile ti: tile) {
			ti.update();
		}
		
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity(Entity en) {
		entity.remove(en);
	}
	
	public void addTile(Tile ti) {
		tile.add(ti);
	}
	
	public void removeTile(Tile ti) {
		tile.remove(ti);
	}
	
	public void createLevel(BufferedImage level) {
		int width = level.getWidth();
		int height= level.getHeight();
		
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
			{
				int pixel=level.getRGB(x, y);
				
				int red = (pixel>>16) & 0xff;
				int green =(pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==0&&green==0&&blue==0) addTile(new Wall(x*64,y*64,64,64,true,ID.wall,this));
				if(red==128&&green==64&&blue==0) addTile(new Ground(x*64,y*64,64,64,true,ID.wall,this));
				//if(red==151&&green==75&&blue==0) addTile(new Ground2(x*64,y*64,64,64,true,ID.wall,this));
				//if(red==230&&green==115&&blue==0) addTile(new Ground3(x*64,y*64,64,64,true,ID.wall,this));
				if(red==255&&green==255&&blue==0) addTile(new PowerUpBlock(x*64,y*64,64,64,true, ID.powerUp,this, Main.mushroom));
				if(red==0&&green==0&&blue==255) addEntity(new Player(x*64,y*64,64,64, ID.player,this));
//				if(red==255&&green==0&&blue==0) addEntity(new Mushroom(x*64,y*64,64,64, ID.mushroom,this));
				if(red==0&&green==128&&blue==0) addEntity(new Charmander(x*64,y*64,64,64, ID.charmander,this));
				if((red>251 && red<256)&& green==0&&blue==0) addTile(new Pipe(x*64,y*64,64,64,true,ID.pipe,this,255-red));
				if(red==247&&green==53&&blue==199) addTile(new Coin(x*64+12,y*64+12,40,40,true, ID.coin,this));
			}
	}
}
