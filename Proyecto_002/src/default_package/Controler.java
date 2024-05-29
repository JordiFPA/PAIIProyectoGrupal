package default_package;

import java.awt.Graphics;

import default_interfaces.IDead;
import default_interfaces.IDrawable;
import default_interfaces.IMove;

public class Controler {

	public void draw(IDrawable id, Graphics g) {
		id.draw(g);
	}
	
	public void move(IMove im) {
		im.move();
	}
	
	public void die(IDead id) {
		id.die();
	}
	
	
	
}
