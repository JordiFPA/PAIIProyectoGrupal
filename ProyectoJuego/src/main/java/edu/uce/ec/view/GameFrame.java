package edu.uce.ec.view;

import edu.uce.ec.controller.Container;
import edu.uce.ec.model.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {

    @Serial
    private static final long serialVersionUID = 11;
    private final GamePanel gamePanel;
    private Container container;
    private final int level = 1;
    private boolean paused = false;
    private final Timer timer;

    public GameFrame(String title, User user) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        container = new Container(level, user);
        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLACK);
        setContentPane(gamePanel);
        addKeyListener(this);
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    container.update();
                    if (container.isLevelComplete()) {
                        nextLevel();
                    }
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            container.draw(g);

            // Dibujar la línea en el 2/3 de la pantalla
            int y = getHeight() * 2 / 3;
            g.setColor(Color.RED);
            g.drawLine(0, y, getWidth(), y);

            // Dibujar el texto del nivel
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Nivel " + container.getLevel(), 350, 75);

            // Dibujar la barra de vida
            drawHealthBar(g);

            // Dibujar el puntaje
            drawScore(g);

            if (paused) {
                drawPauseMessage(g);
            }
        }

        private void drawHealthBar(Graphics g) {
            int maxHealth = 100;
            int currentHealth = container.getHealth();
            int healthBarWidth = 100;

            // Dibujar el fondo de la barra de salud
            g.setColor(Color.RED);
            g.fillRect(40, 50, healthBarWidth, 25);

            // Dibujar la salud actual
            g.setColor(Color.GREEN);
            int currentHealthWidth = (int) ((currentHealth / (double) maxHealth) * healthBarWidth);
            g.fillRect(40, 50, currentHealthWidth, 25);

            // Dibujar el texto de porcentaje de salud
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(currentHealth + "%", 70, 70);
        }

        private void drawScore(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Puntaje: " + container.getScore(), 600, 100);
        }

        private void drawPauseMessage(Graphics g) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Juego Pausado", getWidth() / 2 - 150, getHeight() / 2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setFocusable(true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                container.moveLeft(25);
                break;
            }
            case KeyEvent.VK_D: {
                container.moveRight(25);
                break;
            }
            case KeyEvent.VK_SPACE: {
                container.drawShoot(gamePanel.getGraphics());
                break;
            }
            case KeyEvent.VK_P: {
                togglePause();
                break;
            }
        }
        gamePanel.repaint();
    }

    private void togglePause() {
        paused = !paused;
        if (paused) {
            timer.stop();
        } else {
            timer.start();
        }
        gamePanel.repaint();
    }

    private void nextLevel() {
        System.out.print(container.getScore());
        if (container.getLevel() <= 4) {
            container = new Container(level, container.getUser());
        } else {
            JOptionPane.showMessageDialog(this, "¡Felicidades! ¡Has completado el juego!");
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
