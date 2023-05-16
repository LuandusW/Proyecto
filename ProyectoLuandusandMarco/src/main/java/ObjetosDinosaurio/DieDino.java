package ObjetosDinosaurio;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class DieDino {
	private List<Die> die;

	public DieDino() {
		die = new ArrayList<Die>();
		Cactus cactus = new Cactus();
		die.add(cactus);
	}

	public void actualizar() {
		for (Die d : die) {
			d.actualizar();
		}
		
		Die firstDie = die.get(0);
		//if(firstDie.i)
	}

	public void draw(Graphics g) {
		for (Die d : die) {
			d.draw(g);
		}
	}
	
	public boolean fueraPantalla() {
		return false;
	}


}
