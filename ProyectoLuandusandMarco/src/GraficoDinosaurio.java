import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {
	public static final float GRAVITY = 0.1f;
	public static final float GROUNDY = 300;
	private float x = 0;
	private float y = 0;
	private float speedY = 0;
	private Thread thread;

	public GraficoDinosaurio() {
		thread = new Thread(this);
	}

	public void empiezaJuego() {
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			if(y >= GROUNDY - 100) {
				speedY = 0;
				y = GROUNDY - 100;
			} else {
				speedY+= GRAVITY;
				y+=speedY;
			}
			speedY+=GRAVITY;
			y+=1;
			y+=speedY;
			repaint();
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(0, (int)GROUNDY, getWidth(), (int)GROUNDY);
		g.drawRect((int)x, (int)y, 50, 100);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		speedY = -4;
		y += speedY;

	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

}
