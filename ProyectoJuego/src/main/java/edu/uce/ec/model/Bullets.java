package edu.uce.ec.model;

import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.Color;
import java.awt.Graphics;


public class Bullets implements Movable, Drawable {

    @Override
    public void draw(Graphics graphics) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveUp(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics graphics, Drawable drawable) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(((Hero) drawable).cord_x[0] + 10, ((Hero) drawable).cord_y[0] - 10, 10, 10);

    }

}
