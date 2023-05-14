import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import ObjetosDinosaurio.Dino;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {

	private static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 300;
	
	
	private Thread thread;
	private Dino dino;

	public GraficoDinosaurio() {
		thread = new Thread(this);
		dino = new Dino();
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				dino.update();
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
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(0, (int)GROUNDY, getWidth(), (int) GROUNDY);
		dino.draw(g);
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
