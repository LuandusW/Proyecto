package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Die {

	public abstract Rectangle getBound();
	public abstract void draw(Graphics g);
	public abstract void actualizar();

}
