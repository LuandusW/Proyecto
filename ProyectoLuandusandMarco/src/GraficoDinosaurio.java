import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GraficoDinosaurio extends JPanel implements Runnable, KeyListener {

	private int x = 0;
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
			//System.out.println(x++);
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Tecla presionada");

	}

	@Override
	public void keyReleased(KeyEvent e) {

		System.out.println("Tecla presionada");

	}

}
