package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.GraficoDinosaurio;
import Util.Resource;

public class Isla {
	private List<imageIsla> listImage;
	private BufferedImage imageLand1;

	public Isla(GraficoDinosaurio gd) {
		imageLand1 = Resource.getResourceImage("./contenido/land1.png");
		int numeroIsla = 600 / imageLand1.getWidth() + 2;
		listImage = new ArrayList<imageIsla>();
		for (int i = 0; i < numeroIsla; i++) {
			imageIsla imgIsla = new imageIsla();
			listImage.add(imgIsla);
		}
	}
	public void actualizar() {
		for (imageIsla imgIsla : listImage) {
			imgIsla.posX--;
		}
		
		
		imageIsla primerElemento = listImage.get(0);
		if (primerElemento.posX + imageLand1.getWidth() < 0) {
			primerElemento.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
			listImage.add(primerElemento);
			listImage.remove(0);
		}
	}
	public void draw(Graphics g) {
		for (imageIsla imgIsla : listImage) {
			g.drawImage(imageLand1, imgIsla.posX, (int) Game.GraficoDinosaurio.GROUNDY - 20, null);
		}

	}
	

	private class imageIsla {
		int posX;
		BufferedImage image;
	}
}
