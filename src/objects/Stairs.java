package objects;

import pt.iscte.poo.utils.Point2D;

public class Stairs extends GameElement{
	
	public Stairs(Point2D position) {
		super(position, "Stairs", 1);
	}
	
	public boolean isSolid() {
		return true;
	}

	@Override
	public void interact(GameElement element) {
		
		if(((MovingElement) element).upAndDownStairs()) {
			MovingElement elementm = (MovingElement)element;
			elementm.setMoveUpAndDown(true);
		}
		
	}

}
