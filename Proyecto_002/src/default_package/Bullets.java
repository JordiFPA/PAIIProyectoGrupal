package default_package;

import java.awt.Color;
import java.awt.Graphics;

import default_interfaces.IDead;
import default_interfaces.IDrawable;
import default_interfaces.IMove;
import default_interfaces.IShootable;

public class Bullets implements IDrawable, IMove, IDead {
	private int x, y, speed;
	private int width = 5;
	private int height = 10;
	private boolean alive;
	private int direccion ; 
	

	public Bullets(int x, int y, int speed, int direccion) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.alive = true;
		this.direccion = direccion;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);

	}

	@Override
	public void move() {

		y -= speed * direccion;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void die() {
		this.alive = false;
		
	}

	@Override
	public void checkCollision(IDead other) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	

}
