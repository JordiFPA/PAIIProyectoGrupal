package default_package;

import java.awt.Color;
import java.awt.Graphics;

import default_interfaces.IDrawable;

public class Line implements IDrawable {

	private int posYLinea;

	@Override
	public void draw(Graphics g) {
		int alturaFrame = 600;
		posYLinea = (int) (alturaFrame * 0.66);
		g.setColor(Color.red);
		g.drawLine(0, posYLinea, 800, posYLinea);

	}

	public int getPosYLinea() {
		return posYLinea;
	}

	public void setPosYLinea(int posYLinea) {
		this.posYLinea = posYLinea;
	}

}
