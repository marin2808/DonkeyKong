package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageGUI;

public class Ranking {
	
	private List<Player> ranking = new ArrayList<>();
	private File file;
	
	public Ranking(File file) {
		this.file = file;
	}
	
	public void addPlayer(Player p) {
		ranking.add(p);
		readFileToRanking();
		saveRanking();
		showRanking();
	}
	
	public void saveRanking() {
		
		try {
			PrintWriter writer = new PrintWriter(file);
            for (Player player : ranking) {
            	writer.println(player);
            }
            writer.close();
		}
        catch (FileNotFoundException e) {
			System.err.println("Ficheiro não encontrado");
		}
	}
	
	public void readFileToRanking() {
		
		try {
			Scanner s = new Scanner(file);
			while(s.hasNextLine()) {
				String [] line = s.nextLine().split(";");
				Player p = new Player(line[0]);
				p.setTime(new Time(line[1]));
				ranking.add(p);
			}
			ranking.sort((a,b) -> a.getTime().totalEmSegundos() - b.getTime().totalEmSegundos());
			s.close();
		}catch (FileNotFoundException e) {
			System.err.println("Ficheiro não encontrado");
		}
	}
	
	public String toString() {
		String line = "";
		int i = 0;
		for(Player p : ranking) {
			i++;
			if(i<=10) {
				line = line + p + "\n";
			}
		}
		return line;
	}
	
	public void showRanking() {
		ImageGUI.getInstance().showMessage("Top 10", toString());
	}

}
