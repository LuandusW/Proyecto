package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Util.Resource;

public class Cactus extends Die {

	private BufferedImage image;
	private int posX, posY;
	private Rectangle rect;
	private Dino dino;
	private boolean isPuntos = false;

	public Cactus(Dino dino) {
		this.dino = dino;
		image = Resource.getResourceImage("./contenido/cactus1.png");
		posX = 200;
		posY = 67;
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

	public void setX(int x) {
		posX = x;
	}

	public void setY(int y) {
		posY = y;
	}

	public void set(BufferedImage image) {
		this.image = image;
	}

	@Override
	public boolean salirPantalla() {
		return (posX + image.getWidth() < 0);
	}

	@Override
	public boolean isOver() {
		return (dino.getX() > posX);
	}

	@Override
	public boolean isPuntos() {
		return isPuntos;
	}

	@Override
	public void setIsPuntos(boolean isPuntos) {
		this.isPuntos = isPuntos;
	}

}
