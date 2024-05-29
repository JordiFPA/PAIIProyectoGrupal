package default_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.text.DecimalFormat;

public class Window extends JFrame implements ActionListener {

	private JPanel panel;
	private Timer timer;
	private Timer timer2;
	private Controler control;
	private Ship ship;
	private Enemy enemy;
	private Bullets bullet;
	private Line line;
	private ArrayList<Enemy> enemys;
	private ArrayList<Bullets> bulletsList;
	private Random random;
	private int moveDirection;
	public int score;
	private ArrayList<Bullets> enemyBulletsList;
	private double vida;
	private boolean alive;

	public Window(String title, int width, int height) {
		enemy = new Enemy(height / 2, 20, 1);
		ship = new Ship(width / 2, height - 60, 10);
		control = new Controler();
		enemys = new ArrayList<Enemy>();
		random = new Random();
		bulletsList = new ArrayList<Bullets>();
		bullet = new Bullets(height, width, 1, 1);
		score = 0;
		line = new Line();
		enemyBulletsList = new ArrayList<>();
		this.vida = 100;
		alive = true;

		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);

		// Aqui dibujo el panel
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				control.draw(ship, g);
				control.draw(line, g);
				for (Enemy enemy : enemys) {
					control.draw(enemy, g);
				}
				for (Bullets bullet : bulletsList) {
					control.draw(bullet, g);
				}

				for (Bullets enemyBullet : enemyBulletsList) {
					control.draw(enemyBullet, g); // Dibujar las balas de los enemigos
				}
				drawScore(g);
				drawLifeBar(g);
				checkGameOver(g);
			}
		};
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(width, height));

		timer = new Timer(1800, this);
		timer.start();

		// Esto es para ir moviendo la nave.
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					ship.setMoveDirection(-1); // Mover a la izquierda
					enemy.setMoveDirection(1);

				} else if (key == KeyEvent.VK_RIGHT) {
					ship.setMoveDirection(1); // Mover a la derecha
					enemy.setMoveDirection(1);
				} else if (key == KeyEvent.VK_SPACE) {
					int centerX = ship.getX() + (120 - 40) / 2;
					Bullets newBullet = new Bullets(ship.getX() + 40, ship.getY(), 10, 1);
					bulletsList.add(newBullet);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
					ship.setMoveDirection(0); // Detener el movimiento cuando se suelta la tecla
				}
			}

		});

		timer2 = new Timer(6, this);
		timer2.start();

		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.move(ship);

		if (e.getSource() == timer) {
			generateEnemy();
		}

		// Mover y actualizar cada enemigo en la lista
		for (Enemy enemy : enemys) {
			control.move(enemy);
		}

		// Verificar colisiones entre balas y enemigos
		for (Bullets bullet : bulletsList) {
			control.move(bullet);
			for (Enemy enemy : enemys) {
				if (enemy.intersects(bullet)) {
					control.die(enemy);
					control.die(bullet);
					score += 10;
				}
			}
		}

		for (Bullets enemyBullet : enemyBulletsList) {
			control.move(enemyBullet);
			if (ship.intersects(enemyBullet)) { // Verificar colisión con la nave
				control.die(enemyBullet);
				vida -= 0.625;
			}
		}
		generateEnemyShots();

		// Eliminar enemigos y balas marcados como "muertos"
		enemys.removeIf(enemy -> !enemy.isAlive());
		bulletsList.removeIf(bullet -> !bullet.isAlive());

		repaint();
	}

	private void generateEnemy() {
		int enemyX = random.nextInt(panel.getWidth() - 30); // Posición X aleatoria
		int enemyY = 0; // Posición Y en la parte superior
		Enemy newEnemy = new Enemy(enemyX, enemyY, 3); // Crear nuevo enemigo
		enemys.add(newEnemy); // Agregar el enemigo a la lista
	}

	private void drawScore(Graphics g) {

		int panelWidth = panel.getWidth();
		int panelHeight = panel.getHeight();
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		FontMetrics fm = g.getFontMetrics();
		int textWidth = fm.stringWidth("Score: " + score);
		int x = panelWidth - textWidth - 10;
		int y = 25;
		g.drawString("Score: " + score, x, y);

	}

	private void generateEnemyShots() {
		for (Enemy enemy : enemys) {
			if (random.nextDouble() < 0.009) { // Probabilidad de disparo
				Bullets newBullet = new Bullets(enemy.getX(), enemy.getY() + 20, 5, -1); // Disparo hacia abajo
				enemyBulletsList.add(newBullet);
			}
		}
	}

	private boolean checkGameOver(Graphics g) {
		int posYLinea = line.getPosYLinea();

		for (Enemy enemy : enemys) {
			if (enemy.getY() + enemy.getHeight() - 36 >= posYLinea) {
				timer.stop();
				timer2.stop();
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 48));
				FontMetrics fm = g.getFontMetrics();
				int textWidth = fm.stringWidth("GAME OVER");
				int x = (panel.getWidth() - textWidth) / 2;
				int y = panel.getHeight() / 2;
				g.drawString("GAME OVER", x, y);
				info(g);
				return false;
			}
		}

		if (vida <= 0) {
			timer.stop();
			timer2.stop();
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 48));
			FontMetrics fm = g.getFontMetrics();
			int textWidth = fm.stringWidth("GAME OVER");
			int x = (panel.getWidth() - textWidth) / 2;
			int y = panel.getHeight() / 2;
			g.drawString("GAME OVER", x - 80, y);
			info(g);
			return false;
		}

		return true;
	}

	private void info(Graphics g) {
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		FontMetrics fm = g.getFontMetrics();
		int textWidth = fm.stringWidth("SCORE");
		int x = (panel.getWidth() - textWidth) / 2;
		int y = panel.getHeight() / 2;
		g.drawString("SCORE: " + score, x - 10, y + 80);
	}

	private void drawLifeBar(Graphics g) {
		int barWidth = 250;
		int barHeight = 20;
		int posX = 10;
		int posY = 5;
		int vidaMaxima = 100;
		int vidaActual = (int) vida;

		int vidaBarWidth = (int) ((double) vidaActual / vidaMaxima * barWidth);

		g.setColor(Color.RED);
		g.fillRect(posX, posY, vidaBarWidth, barHeight);

		g.setColor(Color.GREEN);
		g.drawRect(posX, posY, barWidth, barHeight);
	}

}
