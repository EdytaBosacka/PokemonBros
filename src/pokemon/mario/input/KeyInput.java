package pokemon.mario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pokemon.mario.ID;
import pokemon.mario.Main;
import pokemon.mario.entity.Entity;
import pokemon.mario.tile.Tile;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i=0;i<Main.getHandler().entity.size();i++) {
			Entity en = Main.getHandler().entity.get(i);
			if (en.getId() == ID.player) {
				if(en.goingDownPipe) return;
				switch (key) {
				case KeyEvent.VK_UP:
					for(int j=0; j<Main.getHandler().tile.size();j++) {
						Tile t = Main.getHandler().tile.get(j);
						if(t.getID()==ID.pipe && t.facing==0) {
							if (en.getBoundsTop().intersects(t.getBounds())){
								en.goingDownPipe = true;
							}
						}
						
								}
					if (!en.jumping) {
						en.jumping = true;
						en.gravity = 7.0;
					}

					break;
				case KeyEvent.VK_DOWN:
					for(int j=0; j<Main.getHandler().tile.size();j++) {
						Tile t = Main.getHandler().tile.get(j);
						if(t.getID()==ID.pipe && t.facing==2) {
							if (en.getBoundsBottom().intersects(t.getBounds())){
								en.goingDownPipe = true;
							}
						}
						
								}
					break;
				case KeyEvent.VK_RIGHT:
					en.setSpeedX(10);
					en.facing = 0;
					break;
				case KeyEvent.VK_LEFT:
					en.setSpeedX(-10);
					en.facing = 1;
					break;

				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i=0;i<Main.getHandler().entity.size();i++) {
			Entity en = Main.getHandler().entity.get(i);
			if (en.getId() == ID.player) {
				switch (key) {
				case KeyEvent.VK_UP:
					if (!en.jumping) {
						en.jumping = true;
						en.gravity = 10.0;
					}

					break;

				case KeyEvent.VK_RIGHT:
					en.setSpeedX(0);

					break;
				case KeyEvent.VK_LEFT:
					en.setSpeedX(0);
					break;

				}
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
