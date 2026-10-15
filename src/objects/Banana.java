package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Banana extends MovingElement {
	
	private final int attack = 5;
	
	public Banana(Point2D position) {
		super(position, "Banana", 2);
	}
	
	public int getAttack() {
		return attack;
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Manel) {
			Manel m = (Manel) element;
		    m.attacked(getAttack());
		}
		
	}

	@Override
	public void move() {
		Point2D atualPosition = getPosition();
		Point2D futurePosition = new Point2D(atualPosition.getX(), atualPosition.getY() + 1);
		if(futurePosition.getY() >= 10) {
			setDelete(true);
			ImageGUI.getInstance().removeImage(this);
		}
		setMoveUpAndDown(true);
		interact(futurePosition, this);
		move(futurePosition);
	}
	
	public boolean upAndDownStairs() {
		return false;
	}
	
	public boolean blockingActive() {
		return false;
	}
	
}
