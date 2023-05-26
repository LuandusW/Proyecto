package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.Dinosaurio;
import Game.GraficoDinosaurio;
import Util.Resource;

public class DieDino {

	private List<Die> die;
	private Random random;
	private BufferedImage imgCactus1, imgCactus2;
	private Dino dino;
	private GraficoDinosaurio gd;
	private Dinosaurio dn;


	public DieDino(Dino dino, GraficoDinosaurio gd) {
		this.gd = gd;
		this.dino = dino;
		die = new ArrayList<Die>();
		imgCactus1 = Resource.getResourceImage("./contenido/cactus1.png");
		imgCactus2 = Resource.getResourceImage("./contenido/cactus2.png");
		Cactus cactus = new Cactus(dino);
		random = new Random();
		die.add(getRandomCactus());

	}

	public void actualizar() {
		for (Die d : die) {
			d.actualizar();
			if (d.isOver() && !d.isPuntos()) {
				gd.plusPuntos(10);
				d.setIsPuntos(true);

			}
			if (d.getBound().intersects(dino.getBound())) {
				dino.setVida(false);
				dn.log.info("Se murio");
			}
		}

		Die cactusActual = die.get(0);
		if (cactusActual.salirPantalla()) {
			die.add(getRandomCactus());
			die.remove(cactusActual);
		}

	}

	public void draw(Graphics g) {
		for (Die d : die) {
			d.draw(g);
		}
	}
	
	public void reset() {
		die.clear();
		die.add(getRandomCactus());
	}

	private Cactus getRandomCactus() {
		Cactus cactus;
		cactus = new Cactus(dino);
		cactus.setX(600);
		if (random.nextBoolean()) {
			cactus.set(imgCactus1);
		} else {
			cactus.set(imgCactus2);
		}
		return cactus;

	}

	public boolean fueraPantalla() {
		return false;
	}

}
