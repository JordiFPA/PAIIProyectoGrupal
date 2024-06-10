package edu.uce.ec.controller;

import edu.uce.ec.Api.Consumer;
import edu.uce.ec.model.Bullets;
import edu.uce.ec.model.Hero;
import edu.uce.ec.model.Opponents;
import edu.uce.ec.model.User;
import edu.uce.ec.view.RankingListWindow;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class Container {
    private final Consumer consumer = new Consumer();
    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT_2 = 800;
    public Hero hero;
    List<Opponents> opponents = new ArrayList<>();
    List<Bullets> bulletsL = new ArrayList<>();
    List<Bullets> bulletsOp = new ArrayList<>();
    Random random = new Random();
    private int level;
    private int maxOpponents;
    private int score;
    private int health;
    private Timer spawnTimer;
    private Timer opponentShootTimer;
    private int enemiesKilled = 0;
    private boolean levelComplete = false;
    private User user = new User();

    public Container() {
    }

    public Container(User user) {
        this.user = user;
        this.hero = new Hero(user);
        this.health = user.getHealth();
        this.score = user.getScore();
        checkLevelUp();
    }

    public Container(int level, User user) {
        this.level = level;
        this.user = user;
        this.hero = new Hero(user);
        this.score = user.getScore();
        this.health = user.getHealth();
        setMaxOpponents();
        startSpawningOpponents();
        startOpponentShooting();
        updateScore(user.getScore());
        checkLevelUp();
    }

    public Container(int level) {
        this(level, new User());
    }

    private void setMaxOpponents() {
        switch (level) {
            case 1:
                maxOpponents = 5;
                break;
            case 2:
                maxOpponents = 10;
                break;
            case 3:
                maxOpponents = 1;
                break;
            default:
                maxOpponents = 5;
                break;
        }
    }

    private void startSpawningOpponents() {
        if (spawnTimer != null && spawnTimer.isRunning()) {
            spawnTimer.stop();
        }

        spawnTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level < 3 && opponents.size() < maxOpponents) {
                    Opponents opponent = new Opponents(random.nextInt(SCREEN_WIDTH), 0);
                    if (level == 2) {
                        opponent.setHealth(3);
                    }
                    opponents.add(opponent);
                } else if (level == 3 && opponents.size() < maxOpponents) {
                    Opponents opponent = new Opponents(random.nextInt(SCREEN_WIDTH), 0, true);
                    opponents.add(opponent);
                }
            }
        });
        spawnTimer.start();
    }

    private void startOpponentShooting() {
        if (opponentShootTimer != null && opponentShootTimer.isRunning()) {
            opponentShootTimer.stop();
        }

        opponentShootTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Opponents opponent : opponents) {
                    if (level == 1) {
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 40, opponent.cord_y[0] + 10));
                    } else if (level == 2) {
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 30, opponent.cord_y[0] + 10));
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 40, opponent.cord_y[0] + 10));
                    } else if (level == 3) {
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 65, opponent.cord_y[0] + 10));
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 75, opponent.cord_y[0] + 10));
                        bulletsOp.add(new Bullets(opponent.cord_x[0] + 85, opponent.cord_y[0] + 10));
                    }
                }
            }
        });
        opponentShootTimer.start();
    }

    public void draw(Graphics graphics) {
        hero.draw(graphics);
        for (Opponents opponent : opponents) {
            opponent.draw(graphics);
        }
        for (Bullets bullet : bulletsL) {
            bullet.draw(graphics);
        }
        for (Bullets bullet : bulletsOp) {
            bullet.draw(graphics);
        }
    }

    public void moveLeft(int variable) {
        hero.moveLeft(variable);
    }

    public void moveRight(int variable) {
        hero.moveRight(variable);
    }

    public void moveDown(int variable) {
        int adjustedVariable = variable;
        if (level == 3) {
            adjustedVariable = 1;
        }
        for (Opponents opponent : opponents) {
            opponent.moveDown(adjustedVariable);
            if (opponentReachedBottom(opponent)) {
                endGame("Game Over! Enemy reached the bottom line.");
                return;
            }
        }
        for (Bullets bullets : bulletsOp) {
            bullets.moveDown(adjustedVariable);
        }
    }

    private boolean opponentReachedBottom(Opponents opponent) {
        return opponent.cord_y[0] >= (SCREEN_HEIGHT_2 * 0.44);
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        new RankingListWindow("Ranking").setVisible(true);
        System.exit(0);
    }

    public void moveUp(int v) {
        for (Bullets bullets : bulletsL) {
            bullets.moveUp(v + 3);
        }
    }

    public void drawShoot(Graphics graphics) {
        bulletsL.add(new Bullets(hero.cord_x[0], hero.cord_y[0] + 10));
    }

    public void checkCollisions() {
        List<Bullets> bulletsToRemove = new ArrayList<>();
        List<Opponents> opponentsToRemove = new ArrayList<>();

        for (Bullets bullet : bulletsL) {
            for (Opponents opponent : opponents) {
                if (opponent.checkCollision(bullet.getX(), bullet.getY())) {
                    bulletsToRemove.add(bullet);
                    int damage = (level == 3) ? calculateDamage(opponent) : 1;

                    if (level == 3) {
                        score += 15;
                        updateScore(score);
                    }

                    opponent.setHealth(opponent.getHealth() - damage);

                    if (opponent.getHealth() <= 0) {
                        opponentsToRemove.add(opponent);
                        enemiesKilled++;
                        if (level != 3) {
                            updateScoreBasedOnLevel();
                        }
                    }
                }
            }
        }

        for (Bullets bullet : bulletsOp) {
            if (hero.checkCollision(bullet.getX(), bullet.getY())) {
                bulletsToRemove.add(bullet);
                int damage = 5;
                hero.reduceHealth(damage);
                user.setHealth(hero.getHealth());
                Consumer consumer = new Consumer();
                consumer.updateUser(user);
                if (hero.getHealth() <= 0) {
                    endGame("Game Over! Your hero is dead.");
                    return;
                }
            }
        }

        bulletsL.removeAll(bulletsToRemove);
        bulletsOp.removeAll(bulletsToRemove);
        opponents.removeAll(opponentsToRemove);

        if (enemiesKilled >= maxOpponents) {
            levelComplete = true;
        }
    }

    private int calculateDamage(Opponents opponent) {
        double healthPercentage = (opponent.getHealth() / (double) opponent.getMaxHealth()) * 100;
        if (healthPercentage == 100) {
            return 15;
        } else if (healthPercentage >= 50 && healthPercentage < 100) {
            return 10;
        } else {
            return 5;
        }
    }

    private void updateScoreBasedOnLevel() {
        switch (level) {
            case 1:
                score += 5;
                break;
            case 2:
                score += 10;
                break;
            case 3:
                score += 15;
                break;
        }
        updateScore(score);
    }

    public void updateScore(int score) {
        this.score = score;
        user.setScore(score);
        Consumer consumer = new Consumer();
        consumer.updateUser(user);
        checkLevelUp();
    }

    private void checkLevelUp() {
        int previousLevel = level;
        if (score >= 0 && score < 25) {
            level = 1;
        } else if (score >= 25 && score < 125) {
            level = 2;
        } else if (score >= 125 && score < 335) {
            level = 3;
        } else {
            JOptionPane.showMessageDialog(null, "¡Felicidades! ¡Has completado el juego!");
            System.exit(0);
        }
        if (previousLevel != level) {
            setMaxOpponents();
            startSpawningOpponents();
            startOpponentShooting();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    public void update() {
        checkCollisions();
        moveUp(1);
        moveDown(1);
    }

    public User getUser() {
        return user;
    }

    public int getHealth() {
        return hero.getHealth();
    }

    public int getLevel() {
        return level;
    }
}