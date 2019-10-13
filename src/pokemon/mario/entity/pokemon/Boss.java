package pokemon.mario.entity.pokemon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import pokemon.mario.Handler;
import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.states.BossState;
import pokemon.mario.tile.Tile;

public class Boss extends Entity {

	private boolean attackable = false;
	private boolean addJumpTime = false;

	private Random random;

	private int health;
	private int phaseTime;


	private int jumpTime = 0;

	private BossState bossState;



	public Boss(int x, int y, int width, int height, ID id, Handler handler, int health) {
		super(x, y, width, height, id, handler);

		this.health = health;
		bossState = BossState.IDLE;

		random = new Random();
	}

	@Override
	public void draw(Graphics graph) {
		BufferedImage image;
		if (bossState == BossState.IDLE || bossState == BossState.SPINNING) {
			image = Main.boss[0].getBufferedImage();
		} else if (bossState == BossState.RECOVERING) {
			image = Main.boss[1].getBufferedImage();
		} else {
			image = Main.boss[2].getBufferedImage();
		}
		graph.drawImage(image, x, y, width, height, null);
	}

	@Override
	public void update() {
		x += speedX;
		y += speedY;

		if (health <= 0) {
			die();
		}
		phaseTime++;

		if ((phaseTime >= 180 && bossState == BossState.IDLE)||(phaseTime>=600&&bossState!=BossState.SPINNING)) {
			chooseState();
		}

		if(bossState == BossState.RECOVERING && phaseTime >=180){
			bossState = BossState.SPINNING;
			phaseTime = 0;
		}
		if(bossState ==BossState.SPINNING && phaseTime >= 360) {
			bossState = BossState.IDLE;
			phaseTime = 0;
		}
		
		if (bossState == BossState.IDLE || bossState == BossState.RECOVERING) {
			setSpeedX(0);
			setSpeedY(0);
		}

		if (bossState == BossState.JUMPING || bossState == BossState.RUNNING) {
			setAttackable(true);
		} else {
			setAttackable(false);
		}
		
		if(bossState !=BossState.JUMPING) {
			addJumpTime = false;
			jumpTime = 0;
		}
	
		
		if(addJumpTime) {
			jumpTime++;
			if(jumpTime>=30) {
				addJumpTime = false;
				jumpTime=0;
			}
			if(!jumping&&!falling) {
				jumping = true;
				gravity= 8.0;
			}
		}

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (!t.solid || goingDownPipe)
				continue;
			if (t.getID() == ID.wall || t.getID() == ID.powerUp || t.getID() == ID.pipe) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setSpeedY(0);
					if (jumping) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}

				}

				if (getBoundsBottom().intersects(t.getBounds())) {
					setSpeedY(0);
					if (falling) {
						falling = false;
						addJumpTime = true;
					}
						
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setSpeedX(0);
					if (bossState == BossState.RUNNING) {
						setSpeedX(4.0);
					}
					x = t.getX() + t.width;
				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setSpeedX(0);
					if (bossState == BossState.RUNNING) {
						setSpeedX(-4.0);
					}
					x = t.getX() - width;
				}
			}

		}

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if (e.getId() == ID.player) {
				if (bossState == BossState.JUMPING) {
					if (jumping || falling) {
						if(e.getX()>= getX()-4&&e.getX()<=getX()+4) {
							setSpeedX(0);
						}
						else if (e.getX() < getX()) {
							setSpeedX(-4.0);
						}
						else if (e.getX() > getX()) {
							setSpeedX(4.0);
						}
					}else {
						setSpeedX(0);
					}
				}else if(bossState == BossState.SPINNING) {
					if (e.getX() < getX()) {
						setSpeedX(-4.0);
					}
					else if (e.getX() > getX()) {
						setSpeedX(4.0);
					}
					
				}

			}
		}
		if (jumping) {
			gravity -= 0.17;
			setSpeedY(-gravity);
			if (gravity <= 0.5) {
				jumping = false;
				falling = true;

			}
		}
		if (falling) {
			gravity += 0.17;
			setSpeedY(gravity);
		}

	}

	public void chooseState() {
		int nextPhase = random.nextInt(2);
		if (nextPhase == 0) {
			bossState = BossState.RUNNING;
			int direction = random.nextInt(2);
			if (direction == 0) {
				setSpeedX(-4);
			} else {
				setSpeedX(4);
			}
		}else if (nextPhase==1) {
			bossState = BossState.JUMPING;
			
			jumping = true;
			gravity = 8.0;

	}
		phaseTime = 0;

}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public BossState getBossState() {
		return bossState;
	}

	public void setBossState(BossState bossState) {
		this.bossState = bossState;
	}
	
	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}

	public int getPhaseTime() {
		return phaseTime;
	}

	public void setPhaseTime(int phaseTime) {
		this.phaseTime = phaseTime;
	}
}
