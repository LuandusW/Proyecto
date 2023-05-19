package Game;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.apache.log4j.Logger;

public class Dinosaurio extends JFrame {
	public static Logger log = Logger.getLogger(Dinosaurio.class);
	private GraficoDinosaurio gd;

	public Dinosaurio() {
		super("Dinosaurio");
		setSize(600, 175);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gd = new GraficoDinosaurio();
		add(gd);
		addKeyListener(gd);
	}

	public void empiezaJuego() {
		gd.empiezaJuego();
		log.info("Empieza Dinosaurio");
=======

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Dinosaurio extends JFrame {

	private GraficoDinosaurio gd;

	public Dinosaurio() {
		super("Dinosaurio");
		setSize(600, 175);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gd = new GraficoDinosaurio();
		add(gd);
		addKeyListener(gd);
	}

	public void empiezaJuego() {
		gd.empiezaJuego();
>>>>>>> branch 'master' of https://github.com/LuandusW/Proyecto
	}

	public static void main(String[] args) {
		Dinosaurio dn = new Dinosaurio();
		dn.setVisible(true);
		dn.empiezaJuego();
	}

}
