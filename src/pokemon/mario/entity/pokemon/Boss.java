package pokemon.mario.entity.pokemon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.states.BossState;

public class Boss extends Entity {
	
	public Boss(int x, int y, int width, int height, ID id, Handler handler, int health) {
		super(x, y, width, height, id, handler);

		this.health = health;
		bossState = BossState.IDLE;
	}

	private boolean attackable = false;
	private boolean addJumpTime = false;
	
	private int health;
	private int phaseTime;
	private int jumpTime = 0;
	
	private BossState bossState;

	@Override
	public void draw(Graphics graph) {
		BufferedImage image;
		if (bossState == BossState.IDLE || bossState == BossState.SPINNING) {
			image = Main.boss[0].getBufferedImage();
		}
		else if(bossState == BossState.RECOVERING) {
			image = Main.boss[1].getBufferedImage();
		}
		else {
			image = Main.boss[2].getBufferedImage();
		}
		graph.drawImage(image, x, y, width, height, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
