package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class DonkeyKong extends MovingElement implements Attackable{
	
	private int life;
	private int attack;

	public DonkeyKong(Point2D position) {
		super(position, "DonkeyKong", 2);
		this.life = 100;
		this.attack = 5;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getLife() {
		return life;
	}
	
	public void attacked(int damage) {
		life = life - damage;
		if(getLife() <= 0) {
			life = 0;
			setDelete(true); 
			ImageGUI.getInstance().removeImage(this); 
		}
		ImageGUI.getInstance().setStatusMessage("Donkey was attacked: " + getLife() + "/100");
	}
	
	public void move() {
		double random = Math.random() * 10;
		Room room = GameEngine.getInstance().getRoom();		
		Point2D futurePosition = getPosition().plus(Direction.random().asVector());
		interact(futurePosition, this);
		move(futurePosition);
		if(random <= 3) {
			Banana banana = new Banana(getPosition());
			room.addToMainList(banana);                
		}
	}
	
	public boolean isBlocking() {
		return true;
	}
	
	public boolean isAttackableByManel() {
		return true;
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Manel) {
			Manel m = (Manel) element;
			m.attacked(attack);
		}
		
	}
	
	public boolean attackYourself() {
		return false;
	}
	

}
