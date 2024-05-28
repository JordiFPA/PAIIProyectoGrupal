package edu.uce.ec.model;

import edu.uce.ec.interfaces.Drawable;
import edu.uce.ec.interfaces.Movable;

import java.awt.Color;
import java.awt.Graphics;

public class Opponents implements Drawable, Movable {

    int[] cord_x = {100, 200, 200, 150, 100};
    int[] cord_y = {100, 100, 150, 125, 150};

    public Opponents(int randomX, int randomY) {

        cord_x[0] = randomX;
        cord_x[1] = randomX + 100;
        cord_x[2] = randomX + 100;
        cord_x[3] = randomX + 50;
        cord_x[4] = randomX;

        cord_y[0] = randomY;
        cord_y[1] = randomY;
        cord_y[2] = randomY + 50;
        cord_y[3] = randomY + 25;
        cord_y[4] = randomY + 50;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(cord_x, cord_y, 5);
    }

    @Override
    public void moveUp(int variable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown(int variable) {

        for (int i = 0; i < cord_y.length; i++) {
            cord_y[i] = cord_y[i] + variable;
        }
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
        // TODO Auto-generated method stub

    }

}