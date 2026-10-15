package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends GameElement{
	
	private boolean bombWasCatched = false;

	public Bomb(Point2D position) {
		super(position, "Bomb", 1);
	}
	
	public boolean getBombWasCatched() {
		return bombWasCatched;
	}
	
	public void setBombWasCatched(boolean catched) {
		bombWasCatched = catched;
	}

	@Override
	public void interact(GameElement element) {
		if(element instanceof Manel) {
			setBombWasCatched(true);
			setDelete(true);
			GameEngine.getInstance().getRoom().addBombInfoToList(this);
		    ImageGUI.getInstance().removeImage(this);
			System.out.println("Bomba foi apanhada");
		}
	}

}
