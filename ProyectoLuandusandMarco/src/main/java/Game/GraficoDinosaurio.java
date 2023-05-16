package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import ObjetosDinosaurio.Cactus;
import ObjetosDinosaurio.DieDino;
import ObjetosDinosaurio.Dino;
import ObjetosDinosaurio.Isla;
import ObjetosDinosaurio.Nubes;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {

	private static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 110;

	private Thread thread;
	private Dino dino;
	private Isla isla;
	private Nubes nubes;
	private DieDino diedino;

	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
		// posicion que inicia dinosaurio
		dino.setX(50);
		isla = new Isla(this);
		nubes = new Nubes();
		diedino = new DieDino();
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				dino.actualizar();
				isla.actualizar();
				nubes.actualizar();
				diedino.actualizar();
				repaint();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(0, (int) GROUNDY, getWidth(), (int) GROUNDY);
		nubes.draw(g);
		isla.draw(g);
		dino.draw(g);
		diedino.draw(g);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		dino.jump();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
