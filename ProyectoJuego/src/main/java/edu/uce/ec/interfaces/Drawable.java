package edu.uce.ec.interfaces;

import java.awt.Graphics;

public interface Drawable {

    public int[] cord_x = new int[3];
    public int[] cord_y = new int[3];

    public void draw(Graphics graphics);

    public void draw(Graphics graphics, Drawable drawable);

}
