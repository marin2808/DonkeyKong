package pt.iscte.poo.game;

public class Time {
	
	private final int minutos;
	private final int segundos;
	
	public Time(int minutos, int segundos) {
		
		this.minutos=minutos;
		this.segundos=segundos;

	}
	
	public Time(String m) {
		String[] s = m.split(":");
		this.minutos = Integer.parseInt(s[0]);
		this.segundos = Integer.parseInt(s[1]);
	}
	
	public int getMinutos() {
		return minutos;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public int totalEmSegundos() {
		return minutos*60 + segundos;
	}
	
	public Time soma(Time t) {
		int total = this.totalEmSegundos() + t.totalEmSegundos();
		return new Time(total/60, total%60);
	}
	
	public Time sub(Time t) {
		int total = this.totalEmSegundos() - t.totalEmSegundos();
		return new Time(total/60, total%60);
	}
	
	public boolean eMenor(Time t) {
		return t.totalEmSegundos() < this.totalEmSegundos();	
	}
	
	public boolean eMaior(Time t) {
		return t.totalEmSegundos() > this.totalEmSegundos();	
	}
	
	public String toString() {
		return minutos + ":" + segundos;
	}

}
