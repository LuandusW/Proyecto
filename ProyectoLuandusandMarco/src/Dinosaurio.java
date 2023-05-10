import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Dinosaurio extends JFrame {

	private GraficoDinosaurio graficoDinosaurio;

	public Dinosaurio() {
		super("Dinosaurio");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graficoDinosaurio = new GraficoDinosaurio();
		add(graficoDinosaurio);
		addKeyListener(graficoDinosaurio);
	}
	
	public void empiezaJuego() {
		graficoDinosaurio.empiezaJuego();
	}

	public static void main(String[] args) {
		Dinosaurio dn = new Dinosaurio();
		dn.setVisible(true);
		dn.empiezaJuego();
	}

	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./contenido/cactus1.png"));
			g.drawImage(image, 100, 100, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
