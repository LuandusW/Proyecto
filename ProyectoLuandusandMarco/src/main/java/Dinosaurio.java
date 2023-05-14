import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Dinosaurio extends JFrame {

	private GraficoDinosaurio gd;

	public Dinosaurio() {
		super("Dinosaurio");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gd = new GraficoDinosaurio();
		add(gd);
		addKeyListener(gd);
	}

	public void empiezaJuego() {
		gd.empiezaJuego();
	}

	public static void main(String[] args) {
		Dinosaurio dn = new Dinosaurio();
		dn.setVisible(true);
		dn.empiezaJuego();
	}

}
