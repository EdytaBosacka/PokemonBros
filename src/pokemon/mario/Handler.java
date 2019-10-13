package pokemon.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import pokemon.mario.entity.Entity;
import pokemon.mario.entity.Player;
import pokemon.mario.entity.pokemon.Boss;
import pokemon.mario.entity.pokemon.Charmander;
import pokemon.mario.entity.pokemon.Squirtle;
import pokemon.mario.entity.powerup.Mushroom;
import pokemon.mario.tile.Coin;
import pokemon.mario.tile.Flag;
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
			if(Main.getVisibleArea()!=null&&en.getBounds().intersects(Main.getVisibleArea())) {
				en.draw(g);
				
			}
		}
		for(Tile ti: tile) {
			if(Main.getVisibleArea()!=null&&ti.getBounds().intersects(Main.getVisibleArea())) {
				ti.draw(g);
			}
		}
	}
	
	public void update() {
		for (int i=0;i<entity.size();i++) {
			Entity en = entity.get(i);
			en.update();
		}
		for(Tile ti: tile) {
			if(Main.getVisibleArea()!=null&&ti.getBounds().intersects(Main.getVisibleArea())) {
			ti.update();
			}
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
				else if(red==128&&green==64&&blue==0) addTile(new Ground(x*64,y*64,64,64,true,ID.wall,this));
				else if(red==255&&green==255&&blue==0) addTile(new PowerUpBlock(x*64,y*64,64,64,true, ID.powerUp,this, Main.mushroom,PowerUpBlock.POWERUP));
				else if(red==0&&green==162&&blue==232) addTile(new PowerUpBlock(x*64,y*64,64,64,true, ID.powerUp,this, Main.upstar,PowerUpBlock.STAR));
				else if(red==0&&green==0&&blue==255) addEntity(new Player(x*64,y*64,64,64, ID.player,this));
				else if(red==181&&green==230&&blue==29) addEntity(new Boss(x*64,y*64,128,128, ID.boss,this,3));
//				if(red==255&&green==0&&blue==0) addEntity(new Mushroom(x*64,y*64,64,64, ID.mushroom,this));
				else if(red==0&&green==128&&blue==0) addEntity(new Charmander(x*64,y*64,64,64, ID.charmander,this));
				else if(red==0&&green==255&&blue==0) addEntity(new Squirtle(x*64,y*64,64,64, ID.squirtle,this));
				else if((red>251 && red<256)&& green==0&&blue==0) addTile(new Pipe(x*64,y*64,64,64,true,ID.pipe,this,255-red,false));
				else if((red>251 && red<256)&& green==176&&blue==50) addTile(new Pipe(x*64,y*64,64,64,true,ID.pipe,this,255-red,true));
				else if(red==247&&green==53&&blue==199) addTile(new Coin(x*64+12,y*64+12,40,40,true, ID.coin,this));
				else if(red==128&&green==0&&blue==64) addTile(new Flag(x*64,y*64,64,320,true,ID.flag,this));
			}
	}
	public void clearLevel() {
		entity.clear();
		tile.clear();
		
	}
}
