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

	private int statusGame = JUEGO_STATUS;

	private BufferedImage imgGameOver;

	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
		// posicion que inicia dinosaurio
		dino.setX(50);
		isla = new Isla(this);
		nubes = new Nubes();
		diedino = new DieDino(dino,this);
		imgGameOver = Resource.getResourceImage("./contenido/gameover_text.png");
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
				Thread.sleep(20);
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
		g.setColor(Color.black);
		g.drawLine(0, (int) GROUNDY, getWidth(), (int) GROUNDY);

		switch (statusGame) {
		case JUEGO_STATUS:
			dino.draw(g);
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
				g.drawImage(imgGameOver, 200, 50, null);
			}
			break;

		}

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
				statusGame = JUEGO_STATUS;
			}
			break;
		}
	}

}
