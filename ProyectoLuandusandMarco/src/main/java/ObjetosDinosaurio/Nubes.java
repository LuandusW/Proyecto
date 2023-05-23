package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Util.Resource;

public class Nubes {

	private BufferedImage nubeImage;
	private List<Nube> nubes;

	public Nubes() {
		nubeImage = Resource.getResourceImage("./contenido/cloud.png");
		nubes = new ArrayList<Nube>();

		Nube nube1 = new Nube();
		nube1.posX = 100;
		nube1.posY = 50;
		nubes.add(nube1);
		
		nube1 = new Nube();
		nube1.posX = 150;
		nube1.posY = 40;
		nubes.add(nube1);
		
		nube1 = new Nube();
		nube1.posX = 300;
		nube1.posY = 50;
		nubes.add(nube1);
		
		nube1 = new Nube();
		nube1.posX = 450;
		nube1.posY = 20;
		nubes.add(nube1);
		
		nube1 = new Nube();
		nube1.posX = 600;
		nube1.posY = 60;
		nubes.add(nube1);
	
	}

	public void actualizar() {
		for (Nube nube : nubes) {
			nube.posX -= 2;
		}
		
		Nube firstNube = nubes.get(0);
		if(firstNube.posX + nubeImage.getWidth() < 0 ) {
			firstNube.posX = 600;
			nubes.remove(firstNube);
			nubes.add(firstNube);
			
		}
	}

	public void draw(Graphics g) {
		for (Nube nube : nubes) {
			g.drawImage(nubeImage, (int) nube.posX, (int) nube.posY, null);
		}

	}

	private class Nube {
		float posX; // Cordenada
		float posY;
	}
}
