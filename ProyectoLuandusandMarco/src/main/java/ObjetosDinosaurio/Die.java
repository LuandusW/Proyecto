package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Die {

	public abstract Rectangle getBound();

	public abstract void draw(Graphics g);

	public abstract void actualizar();

	public abstract boolean salirPantalla();

	public abstract boolean isOver();

	public abstract boolean isPuntos();

	public abstract void setIsPuntos(boolean isPuntos);
}
