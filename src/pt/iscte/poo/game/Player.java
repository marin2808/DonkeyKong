package pt.iscte.poo.game;

public class Player {
	
	private String name;
	private Time time;
	
	public Player(String name) {
		this.name = name;
		this.time = new Time(0,0);
	}
	
	public void timeCounter() {
		time = time.soma(new Time(0,1));
	}
	
	public Time getTime() {
		return time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTime(Time t) {
		time = t;
	}
	
	public String toString() {
		return name + ";" + time;
	}

}
