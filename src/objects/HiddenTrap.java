package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class HiddenTrap extends Trap{
	
	private Wall wall;
	
	public HiddenTrap(Point2D position) {
		super(position);
		wall = new Wall(getPosition());
		wall.setLayer(2);
	}

	public Wall getWall() {
		return wall;
	}

	@Override
	public void interact(GameElement element) {
		if(element instanceof Manel) {
			Manel m = (Manel) element;
			m.attacked(getAttack());
			ImageGUI.getInstance().removeImage(getWall());
		}
	}

}
