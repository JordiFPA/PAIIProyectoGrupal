package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import default_interfaces.IDead;
import default_interfaces.IDrawable;
import default_interfaces.IMove;
import default_interfaces.IShootable;

public class Enemy implements IDrawable, IMove, IDead, IShootable {
	private int x, y, speed, moveDirection;
	private boolean alive;
	private int minX, maxX;
	private int width, height;

	public Enemy(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.moveDirection = 0;
		this.alive = true;

	}

	@Override
	public void draw(Graphics g) {
		int size = 35;
		int[] xPoints = { x - size, x + size, x + size / 2, x + size / 4, x - size / 4, x - size / 2 };
		int[] yPoints = { y - size / 2, y - size / 2, y + size / 2, y + size / 4, y + size / 4, y + size / 2 };

		g.setColor(Color.GREEN); //
		g.fillPolygon(xPoints, yPoints, 6);
		int minX = xPoints[0];
		int maxX = xPoints[0];
		for (int i = 1; i < xPoints.length; i++) {
			if (xPoints[i] < minX) {
				minX = xPoints[i];
			}
			if (xPoints[i] > maxX) {
				maxX = xPoints[i];
			}
		}
		int width = maxX - minX;

		int minY = yPoints[0];
		int maxY = yPoints[0];
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
		y += speed;
	}

	public void setMoveDirection(int direction) {
		this.moveDirection = direction;
	}

	@Override
	public void die() {
		this.alive = false;

	}

	public boolean isAlive() {
		return alive;
	}

	@Override
	public void checkCollision(IDead other) {
		if (other instanceof Bullets) {
			Bullets bullet = (Bullets) other;
			if (this.intersects(bullet)) {
				this.die();
				bullet.die();
			}
		}
	}
	
	@Override
	public void shoot(ArrayList<Bullets> bulletsList) {
		Bullets newBullet = new Bullets(x, y, 2, -10);
		bulletsList.add(newBullet);

	}

	public boolean intersects(Bullets bullet) {
		int shipX = getX() - 50;
		int shipY = getY() - 50;
		int shipWidth = 75;
		int shipHeight = 75;

		Rectangle shipRect = new Rectangle(shipX, shipY, shipWidth, shipHeight);
		Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

		return shipRect.intersects(bulletRect);

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

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}
	
	

}
