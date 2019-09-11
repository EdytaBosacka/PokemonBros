package pokemon.mario.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pokemon.mario.Main;
import pokemon.mario.graphics.gui.Button;

public class MouseInput implements MouseListener, MouseMotionListener {

	public int x, y;
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		
		for (int i=0;i < Main.getLauncher().buttons.length; i++) {
			Button button = Main.getLauncher().buttons[i];
			
			if(x >= button.getX()&&y>= button.getY()&&x<= button.getX()+button.getWidth()&& y<= button.getY()+button.getHeight()){
				button.triggerEvent();
				
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
