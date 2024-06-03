package edu.uce.ec.view;

import edu.uce.ec.controller.Container;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {

    private static final long serialVersionUID = 11;
    private JPanel contentPane;
    private Container container;
    private int level = 1;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        container = new Container(level);
        contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        addKeyListener(this);
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.update();
                if (container.isLevelComplete()) {
                    nextLevel();
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        container.draw(g);

        // Dibujar la línea en el 2/3 de la pantalla
        int y = getHeight() * 2 / 3;
        g.setColor(Color.RED);
        g.drawLine(0, y, getWidth(), y);

        // Dibujar el texto del nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Nivel " + level, 350, 75);

        // Dibujar la barra de vida
        drawHealthBar(g);

        // Dibujar el puntaje
        drawScore(g);
    }

    private void drawHealthBar(Graphics g) {
        int maxHealth = 100;  // Salud máxima
        int currentHealth = container.getHeroHealth();
        int healthBarWidth = 100;  // Ancho de la barra de salud

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
                container.drawShoot(getGraphics());
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value:");
        }
        repaint();
    }

    private void nextLevel() {
        if (level < 3) {
            level++;
            container = new Container(level, container.hero, container.getScore());
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You've completed the game!");
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
