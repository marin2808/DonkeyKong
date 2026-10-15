package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends MovingElement implements Attackable {
	
	private int attack = 5;
	
	public Bat(Point2D position) {
		super(position,"Bat", 2);
	}
	
	public int getAttack() {
		return attack;
	}

	@Override
	public void interact(GameElement element) {
		if(element instanceof Manel) {
			Manel m = (Manel)element;
			m.attacked(getAttack());
			setDelete(true);
			ImageGUI.getInstance().removeImage(this);
		}
	}

	@Override
	public boolean isAttackableByManel() {
		return true;
	}

	@Override
	public void attacked(int damage) {
		setDelete(true);
		ImageGUI.getInstance().removeImage(this);
	}

	@Override
	public void move() {		
		Point2D futurePosition = getPosition().plus(Direction.random().asVector());
		Point2D downPosition = new Point2D(getPosition().getX(), getPosition().getY() + 1);		
		interact(downPosition, this);
		move(downPosition);
		if(futurePosition.getX() == getPosition().getX() + 1 || futurePosition.getX() == getPosition().getX() - 1) {
			interact(futurePosition, this);
			move(futurePosition);
		}
	}
	
	public boolean attackYourself() {
		return true;
	}
	
	public boolean isBlocking() {
		return true;
	}
	
}
