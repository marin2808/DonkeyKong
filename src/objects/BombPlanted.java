package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class BombPlanted extends Bomb implements Timer{
	
	private int ticksWhenPlanted;
	private int maxTicks = 5;

	public BombPlanted(Point2D position) {
		super(position);
		setBombWasCatched(false);
		setName("BombPlanted");
		ticksWhenPlanted = ImageGUI.getInstance().getTicks();
	}
	
	@Override
	public void interact(GameElement element) {
		if(element instanceof MovingElement && !(element instanceof Banana)) {
			explode();
		}
	}
	
	public void explode() {
		GameEngine.getInstance().getRoom().deleteElementsOneSquare(getPosition());
		setDelete(true);
		ImageGUI.getInstance().removeImage(this);
	}
	
	public boolean isBlocking() {
		return true;
	}

	@Override
	public void timeFinished() {
		explode();
	}

	@Override
	public int getTicksWhenSpawned() {
		return ticksWhenPlanted;
	}

	@Override
	public int getMaxTicks() {
		return maxTicks;
	}

}
