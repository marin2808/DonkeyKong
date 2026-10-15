package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {
	
	public Door(Point2D position) {
		super(position, "DoorClosed", 1);
	}
		
	public boolean fundoTransparente() {
		return false;
	}

	@Override
	public void interact(GameElement element) {
		if(element instanceof Manel) {
			setName("DoorOpen");
			Room.levelCompleted = true;
		}
	}

}