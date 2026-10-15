package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Princess extends GameElement {
	
	public Princess(Point2D position) {
		super(position, "Princess", 2);
	}

	@Override
	public void interact(GameElement element) {
		
		if(element instanceof Manel) {
			GameEngine.getInstance().setFinishedGame(true);
			ImageGUI.getInstance().setStatusMessage("O jogo acabou");
		}
		
	}
	
	public boolean isBlocking() {
		return true;
	}

}
