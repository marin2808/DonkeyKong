package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Meat extends GameElement implements Timer{
	
	private int ticksWhenSpawned;
	private int ticksUntilRot = 20;
	private boolean rotted = false;
	private int value = 10;
	
	public Meat(Point2D position) {
		super(position, "GoodMeat", 1);
		ticksWhenSpawned = ImageGUI.getInstance().getTicks();
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Manel) {     
			Manel m = (Manel) element;
			if(!getRotted()) {
				if(m.getLife() < 100) {
				    m.increaseLife(getValue());
				    setDelete(true);
				    ImageGUI.getInstance().removeImage(this);
				}
			}else{
				m.attacked(getValue());
				setDelete(true);
			    ImageGUI.getInstance().removeImage(this);
			}
		}
			
	}
	
	public boolean getRotted() {
		return rotted;
	}
	
	public void setRotted(boolean rot) {
		rotted = rot;
	}

	@Override
	public void timeFinished() {
		setRotted(true);
		setName("BadMeat");
	}

	@Override
	public int getTicksWhenSpawned() {
		return ticksWhenSpawned;
	}

	@Override
	public int getMaxTicks() {
		return ticksUntilRot;
	}

}