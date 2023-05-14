package ObjetosDinosaurio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.log4j.lf5.util.Resource;

import Util.Animacion;

public class Dino {
	private static final float GRAVITYY = 0.1f;
	public static final float GROUNDYY = 300;
	private float x = 0;
	private float y = 0;
	private float speedY = 0;
	private Animacion correr;
	
	

	public Dino() {
		correr = new Animacion(200);
		correr.addFrame(Util.Resource.getResouceImage("./contenido/main-character1.png"));
		correr.addFrame(Util.Resource.getResouceImage("./contenido/main-character2.png"));
	}

	public void update() {
		correr.updateFrame();
		// all these line code for jumping
		if (y >= GROUNDYY - correr.getFrame().getHeight()) {
			speedY = 0;
			y = GROUNDYY - correr.getFrame().getHeight();
		} else {
			speedY += GRAVITYY;
			y += speedY;
		}
		speedY += GRAVITYY;
		y += speedY;
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int) x, (int) y, correr.getFrame().getWidth(), correr.getFrame().getHeight());
		g.drawImage(correr.getFrame(), (int) x, (int) y, null);
	}

	public void jump() {
		speedY = -4;
		y += speedY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

}
