package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement{
	
	public Wall(Point2D position) {
		super(position, "Wall", 1);
	}
	
	public boolean fundoTransparente() {
		return false;
	}
	
	public boolean isBlocking() {
		return true;
	}
	
	public boolean isSolid() {
		return true;
	}

	@Override
	public void interact(GameElement element) {
		//Não faz nada quando interage
		
	}
		
}
