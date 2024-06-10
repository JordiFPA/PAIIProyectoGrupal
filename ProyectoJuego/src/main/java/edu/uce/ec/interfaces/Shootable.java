package edu.uce.ec.interfaces;

import edu.uce.ec.model.Bullets;

import java.util.ArrayList;

public interface Shootable {

	public void shoot(ArrayList<Bullets> e);
	boolean checkCollision(int x, int y);
}
