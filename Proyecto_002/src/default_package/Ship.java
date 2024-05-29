package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import default_interfaces.IDead;
import default_interfaces.IDrawable;
import default_interfaces.IMove;

public class Ship implements IDrawable, IMove {

	private int x, y;
	private int speed, moveDirection;
	private int width, height;
	private int minX, maxX;
	private int vida;

	public Ship(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.moveDirection = 0;
		this.vida = 100;
	}

	@Override
	public void draw(Graphics g) {
		// Logica para dibujar mi nave.
		int[] xPoints = { x - 40, x + 40, x + 120 };
		int[] yPoints = { y + 80, y, y + 80 };
		int nPoints = 3;
		g.setColor(Color.WHITE);
		g.fillPolygon(xPoints, yPoints, nPoints);

		minX = xPoints[0];
		maxX = xPoints[0];
		for (int i = 1; i < xPoints.length; i++) {
			if (xPoints[i] < minX) {
				minX = xPoints[i];
			}
			if (xPoints[i] > maxX) {
				maxX = xPoints[i];
			}
		}
		width = maxX - minX;

		int minY = y;
		int maxY = y;
		for (int i = 0; i < 3; i++) {
			if (yPoints[i] < minY) {
				minY = yPoints[i];
			}
			if (yPoints[i] > maxY) {
				maxY = yPoints[i];
			}
		}
		minY = yPoints[0];
		maxY = yPoints[0];
		for (int i = 1; i < yPoints.length; i++) {
			if (yPoints[i] < minY) {
				minY = yPoints[i];
			}
			if (yPoints[i] > maxY) {
				maxY = yPoints[i];
			}
		}
		height = maxY - minY;

	}

	@Override
	public void move() {

		x += moveDirection * speed;
	}

	public void setMoveDirection(int direction) {
		this.moveDirection = direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getSpeed() {
		return speed;
	}

	public int getMoveDirection() {
		return moveDirection;
	}

	public boolean intersects(Bullets bullet) {
		Rectangle shipRect = new Rectangle(x, y, width, height);
		Rectangle bulletRect = new Rectangle(bullet.getX() - 2, bullet.getY() - 2, bullet.getWidth() - 2,
				bullet.getHeight() - 2);
		return shipRect.intersects(bulletRect);
	}

}
