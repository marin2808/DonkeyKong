package objects;

import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {
	
	public Floor(Point2D position) {
		super(position, "Floor", 0);
	}
	
	public boolean fundoTransparente() {
		return false;
	}

	@Override
	public void interact(GameElement element) {
		//Não faz nada quando interage
	}

}
