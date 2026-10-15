package pt.iscte.poo.game;

import java.io.File;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private int lastTickProcessed = 0;
	private static GameEngine INSTANCE;
	private boolean finishedGame = false;
	private Player player;
	private Ranking ranking;
	
	public GameEngine() {
		String name = ImageGUI.getInstance().askUser("Name:");
		player = new Player(name);
		ranking = new Ranking(new File("teste.txt"));
		currentRoom = new Room("room0.txt");
		ImageGUI.getInstance().update();
	}
	
	public static GameEngine getInstance() {
		if (INSTANCE == null)
            INSTANCE = new GameEngine();
        return INSTANCE;
	}
	
	public Room getRoom() {
		return currentRoom;
	}
	
	public void changeMap() {
		if(Room.levelCompleted == true) {
			ImageGUI.getInstance().clearImages();
			currentRoom.deleteAllElements();
			int nextNivel = currentRoom.getNivel() + 1;
			String file = "room" + nextNivel + ".txt";
			System.out.println(file);
			currentRoom = new Room(file);
			System.out.println(nextNivel);
			Room.levelCompleted = false;
		}else if(currentRoom.lose()) {
			ImageGUI.getInstance().clearImages();
			currentRoom.deleteAllElements();
			String file = "room" + 0 + ".txt";
			currentRoom = new Room(file);
		}
	}
	
	public void setFinishedGame(boolean finished) {
		this.finishedGame = finished;
	}
	
	public void addPlayerToRanking() {
		if(finishedGame) {
			ranking.addPlayer(player);
			finishedGame = false;
		}
	}

	@Override
	public void update(Observed source){
		
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			System.out.println("Keypressed " + k);
			if (Direction.isDirection(k)) {
				System.out.println("Direction! ");
				currentRoom.moveElements();
			}else if(k == 66 && currentRoom.bombWasCatched()) {
				currentRoom.plantBomb();
			}
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			currentRoom.moveElements();
			currentRoom.falling();
			player.timeCounter();
			currentRoom.timer();
			processTick(); 
		}
		changeMap();
		currentRoom.deleteElement();
		addPlayerToRanking();
		ImageGUI.getInstance().update();
	}

	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;
	}



}
