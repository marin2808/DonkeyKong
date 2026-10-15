package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends GameElement {
	
	private int attack = 5;
	
	public Trap(Point2D position) {
		super(position, "Trap", 1);
	}
	
	public int getAttack() {
		return attack;
	}
	
	public boolean fundoTransparente() {
		return false;
	}

	@Override
	public void interact(GameElement element) {
		if(element instanceof Manel) {
			Manel m = (Manel) element;
			m.attacked(getAttack());
		    System.out.println("Diminuir vida trap");
		}
	}

}
