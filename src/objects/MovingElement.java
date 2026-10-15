package objects;

import java.util.List;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public abstract class MovingElement extends GameElement {
	
	private boolean moveUpAndDown = false;

	public MovingElement(Point2D position, String name, int layer) {
		super(position, name, layer);
	}
	
	public abstract void move();
	
	public boolean getMoveUpAndDown() {
		return moveUpAndDown;
	}
	
	public void setMoveUpAndDown(boolean m) {
		moveUpAndDown = m;
	}
	
	public boolean isMovedByKey() {
		return false;
	}
	
	public boolean upAndDownStairs() {
		return true;
	}
	
	public boolean blockingActive() {
		return true;
	}
	
	public void move(Point2D position) {
		
		Room room = GameEngine.getInstance().getRoom();
		if(isWithInLimits(position) && (!room.isBlocking(position) || !blockingActive())) {
			if((position.getY() == getPosition().getY() + 1 || position.getY() == getPosition().getY() - 1) && getMoveUpAndDown()){
				setPosition(position);
			}else if(position.getX() == getPosition().getX() + 1 || position.getX() == getPosition().getX() - 1) {
				setPosition(position);
			}
			if (!room.isSolid(getPosition())) {
	            setMoveUpAndDown(false);
	        }
		}    
	}
	
	public void interact(Point2D position, GameElement elem) {
		Room room = GameEngine.getInstance().getRoom();
		List<GameElement> list = room.listWithElementsInSamePosition(position);
		for(GameElement element : list) {
			if(element.getPosition().equals(position)) {
				elem.interact(element);
				if(!(element instanceof MovingElement)){
				element.interact(elem);
				}
			}
		}
	}
}
