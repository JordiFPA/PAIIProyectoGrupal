package edu.uce.ec.controller;


import edu.uce.ec.model.Bullets;
import edu.uce.ec.model.Hero;
import edu.uce.ec.model.Opponents;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Container {

    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT = 200;

    Hero hero = new Hero();
    List<Opponents> opponents = new ArrayList();
    Random random = new Random();

    public Container() {
        opponents.add(new Opponents(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
        opponents.add(new Opponents(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
        opponents.add(new Opponents(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
        opponents.add(new Opponents(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
        opponents.add(new Opponents(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);
        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).draw(graphics);
        }

    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);
    }

    public void moveRight(int variable) {
        hero.moveRight(variable);

    }

    public void moveDown(int variable) {
        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).moveDown(variable);

        }
    }

    public void drawShoot(Graphics graphics) {
        new Bullets().draw(graphics, hero);
    }
}

