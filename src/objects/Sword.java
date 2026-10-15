package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement{

	public Sword(Point2D position) {
		super(position, "Sword", 1);
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Manel) {
			Manel m = (Manel) element;
		    m.increaseAttack(5);
		    setDelete(true);
		    ImageGUI.getInstance().removeImage(this);
		    System.out.println("Aumentar attack");
		}
		
	}
	
}