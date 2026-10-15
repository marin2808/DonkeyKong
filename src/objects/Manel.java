package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends MovingElement implements Attackable {
	
	private int life = 100;
	private int attack = 10;
	private int lives = 3;
	private Point2D initialPosition;

	
	public Manel(Point2D initialPosition){
		super(initialPosition,"JumpMan", 2);
		this.initialPosition = initialPosition;
	}

	public void move() {
		int key = ImageGUI.getInstance().keyPressed();
		Direction pressed = Direction.directionFor(key);
		Point2D futurePosition = getPosition().plus(pressed.asVector());
		interact(futurePosition, this);
	    move(futurePosition);
	}
	
	public Point2D getInitialPosition() {
		return initialPosition;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void increaseAttack(int att) {
		ImageGUI.getInstance().setStatusMessage("Manel´s attack increased: " + getAttack() + " + " + att);
		attack = attack + att;
	}
	
	public int getLife() {
		return life;
	}
	
	public void increaseLife(int value) {
		if(getLife() < 100) {
			life = life + value;
			if(getLife() > 100) {
				life = 100;
			}
			ImageGUI.getInstance().setStatusMessage("Manel´s life increased: " + life);
		}
	}
	
	public void attacked(int damage) {
		life = life - damage;
		if(getLife() <= 0) {
			lives = lives - 1;
			if(getLives() > 0) {
				life = 100;
				setPosition(getInitialPosition());	
				ImageGUI.getInstance().setStatusMessage("Manel lost one live: " + lives + "/3");
			}else{
				life = 0;
				setDelete(true); 
				ImageGUI.getInstance().removeImage(this); 
				ImageGUI.getInstance().showMessage("Game Over", "GAME OVER");
			}
		}
		ImageGUI.getInstance().setStatusMessage("Manel was attacked: " + life + "/100");
	}
	
	public boolean isBlocking() {
		return true;
	}
	
	public boolean isMovedByKey() {
		return true;
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Attackable) {
			if(((Attackable)element).isAttackableByManel()) {
				((Attackable)element).attacked(attack);
				if(((Attackable)element).attackYourself()){
					attacked(((Attackable)element).getAttack());
				}
			}
		}
	}

	@Override
	public boolean isAttackableByManel() {
		return false;
	}
	
	public boolean attackYourself() {
		return false;
	}
	
	
}
