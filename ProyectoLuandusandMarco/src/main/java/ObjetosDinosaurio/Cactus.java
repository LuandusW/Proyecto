package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Util.Resource;

public class Cactus extends Die {

	private BufferedImage image;
	private int posX, posY;
	private Rectangle rect;

	public Cactus() {
		image = Resource.getResourceImage("./contenido/cactus1.png");
		posX = 200;
		posY = 65;
		rect = new Rectangle();
	}

	public void actualizar() {
		posX -= 2;
		rect.x = posX;
		rect.y = posY;
		rect.width = image.getWidth();
		rect.height = image.getHeight();
	}

	@Override
	public Rectangle getBound() {
		return rect;

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, null);
	}

}
