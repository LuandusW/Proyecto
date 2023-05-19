package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ObjetosDinosaurio.Cactus;
import ObjetosDinosaurio.DieDino;
import ObjetosDinosaurio.Dino;
import ObjetosDinosaurio.Isla;
import ObjetosDinosaurio.Nubes;
import Util.Resource;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {
	public static final int JUEGO_STATUS = 0;
	public static final int JUEGO_EMPIEZA = 1;
	public static final int JUEGO_ACABA = 2;
	private static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 110;

	private Thread thread;
	private Dino dino;
	private Isla isla;
	private Nubes nubes;
	private DieDino diedino;
	private int puntos;
<<<<<<< HEAD
	private Dinosaurio dn;
	private int statusGame = JUEGO_STATUS;
	private boolean saltar = true;

	private BufferedImage imgGameOver;
	private BufferedImage imgReplay;

	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
		// posicion que inicia dinosaurio
		dino.setX(50);
		dino.setY(60);
		isla = new Isla(this);
		nubes = new Nubes();
		diedino = new DieDino(dino, this);
		imgGameOver = Resource.getResourceImage("./contenido/gameover_text.png");
		imgReplay = Resource.getResourceImage("./contenido/replay_button.png");
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				actualizar();
				repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dn.log.debug(e);
			}
		}
	}

	public void actualizar() {
		switch (statusGame) {
		case JUEGO_EMPIEZA:
			dino.actualizar();
			isla.actualizar();
			nubes.actualizar();
			diedino.actualizar();
			if (!dino.getVida()) {
				statusGame = JUEGO_ACABA;
			}
			break;

		}

	}

	public void plusPuntos(int puntos) {
		this.puntos += puntos;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (statusGame) {
		case JUEGO_STATUS:
			dino.draw(g);
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			break;
		case JUEGO_EMPIEZA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			break;
		case JUEGO_ACABA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			if (statusGame == JUEGO_ACABA) {
				g.drawImage(imgGameOver, 180, 40, null);
				g.drawImage(imgReplay, 260, 60, null);
			}
			break;

		}

	}

	private void restartGame() {
		dino.setVida(true);
		dino.setX(50);
		dino.setY(60);
		diedino.reset();
	}

	public void noPuedeSaltar() {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if (dino.getY() == 67) {
				saltar = false;
			}
			if (statusGame == JUEGO_STATUS) {
				statusGame = JUEGO_EMPIEZA;
			} else if (statusGame == JUEGO_EMPIEZA) {
				if (dino.getY() < 67) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						saltar = false;
					}
				}
				if (dino.getY() == 67 ) {
					saltar = true;
					dino.jump();
					
				}
			} else if (statusGame == JUEGO_ACABA) {
				restartGame();
				statusGame = JUEGO_EMPIEZA;
				puntos = 0;
=======

	private int statusGame = JUEGO_STATUS;

	private BufferedImage imgGameOver;
	private BufferedImage imgReplay;
	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
		// posicion que inicia dinosaurio
		dino.setX(50);
		dino.setY(60);
		isla = new Isla(this);
		nubes = new Nubes();
		diedino = new DieDino(dino,this);
		imgGameOver = Resource.getResourceImage("./contenido/gameover_text.png");
		imgReplay = Resource.getResourceImage("./contenido/replay_button.png");
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				actualizar();
				repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void actualizar() {
		switch (statusGame) {
		case JUEGO_EMPIEZA:
			dino.actualizar();
			isla.actualizar();
			nubes.actualizar();
			diedino.actualizar();
			if (!dino.getVida()) {
				statusGame = JUEGO_ACABA;
			}
			break;

		}

	}
	
	public void plusPuntos(int puntos) {
		this.puntos += puntos;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
//		g.setColor(Color.black);
//		g.drawLine(0, (int) GROUNDY, getWidth(), (int) GROUNDY);

		switch (statusGame) {
		case JUEGO_STATUS:
			dino.draw(g);
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			break;
		case JUEGO_EMPIEZA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			g.drawString("Puntos: " + String.valueOf(puntos), 500, 20);
			break;
		case JUEGO_ACABA:
			nubes.draw(g);
			isla.draw(g);
			dino.draw(g);
			diedino.draw(g);
			if (statusGame == JUEGO_ACABA) {
				g.drawImage(imgGameOver, 180, 40, null);
				g.drawImage(imgReplay, 260, 60, null);
			}
			break;

		}

	}
	
	private void restartGame() {
		dino.setVida(true);
		dino.setX(50);
		dino.setY(60);
		diedino.reset();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if (statusGame == JUEGO_STATUS) {
				statusGame = JUEGO_EMPIEZA;
			} else if (statusGame == JUEGO_EMPIEZA) {
				dino.jump();
			} else if (statusGame == JUEGO_ACABA) {
				restartGame();
				statusGame = JUEGO_STATUS;
>>>>>>> branch 'master' of https://github.com/LuandusW/Proyecto
			}
			break;
		}
	}

}
