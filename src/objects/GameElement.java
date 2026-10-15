package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile, Interactable{
	
	private Point2D position;
	private String name;
	private int layer;
	private boolean delete = false;
	
	public GameElement(Point2D position, String name, int layer) {
		this.name = name;
		this.layer = layer;
		this.position = position;
		ImageGUI.getInstance().addImage(this);
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static GameElement criarObjeto(char c, Point2D p) {
			
		switch(c) { 
	   
		case 'W': return new Wall(p);
		case 'G': return new DonkeyKong(p);
		case 'P': return new Princess(p);
		case 't': return new Trap(p);
		case 'S': return new Stairs(p);
		case 'H': return new Manel(p);
		case '0': return new Door(p);
		case 'm': return new Meat(p);
		case ' ': return new Floor(p);
		case 's': return new Sword(p);
		case 'B': return new Bat(p);
		case 'b': return new Bomb(p);
		case 'T': return new HiddenTrap(p);
		
		default: throw new IllegalArgumentException("Null image");
		
		}
	}
	
	public boolean fundoTransparente() {
		return true;
	}
	
	public boolean isBlocking() {
		return false;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isWithInLimits(Point2D futurePosition) {
		return futurePosition.getX() >= 0 && futurePosition.getY() >= 0 && futurePosition.getX() < 10 && futurePosition.getY() < 10;
	}
	
	public String toString() {
		return name + ":" + position;
	}

	public boolean getDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
}
