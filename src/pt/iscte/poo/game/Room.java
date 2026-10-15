package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.Banana;
import objects.Bomb;
import objects.BombPlanted;
import objects.Floor;
import objects.GameElement;
import objects.Manel;
import objects.MovingElement;
import objects.Stairs;
import objects.Timer;
import objects.Wall;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Room {
	
	private final int maxAltura = 10;
	private final int maxLargura = 10;
	
	private char[][] matrix = new char[maxLargura][maxAltura];
	private List<GameElement> list = new ArrayList<>();
	private List<GameElement> listToAddToMainList = new ArrayList<>();
	private List<Bomb> listWithBombInfo = new ArrayList<>();
	private int nivel;
	public static boolean levelCompleted = false;
	
	public Room(String file) {
		lerMapa(file);
		pintarMapa();
	}
	
	public void moveElements() {
		for(GameElement elem : list) {
			if(elem instanceof MovingElement) {
				if(ImageGUI.getInstance().wasKeyPressed() && ((MovingElement) elem).isMovedByKey()) {
					((MovingElement) elem).move();
				}else if(!((MovingElement) elem).isMovedByKey() && !ImageGUI.getInstance().wasKeyPressed()){
					((MovingElement) elem).move();
				}
			}
		}
		list.addAll(listToAddToMainList);
		listToAddToMainList.clear();
	}
	
	public void addToMainList(GameElement elem) {
		listToAddToMainList.add(elem);
	}
	
	public void lerMapa(String nome){
		nome = "rooms\\" + nome;
		int y = 0;
		Scanner input = new Scanner(System.in);
		boolean validName = false;
		while(!validName) {
			try {
	        	Scanner s = new Scanner(new File(nome));
	        	if(s.hasNextLine()) {
	        		String [] firstLine = s.nextLine().split(";");
	        		nivel = Integer.parseInt(firstLine[0].substring(1));
	        	}
				while(s.hasNextLine()) {
					String line = s.nextLine();
					if(line.length() < maxLargura) {
						for(int x=0; x < maxLargura; x++) {
							matrix[x][y] = ' ';
						}
						System.out.println("Aviso: falta caracteres, preenchendo com chão a linha " + y);
					}else {
						for(int x=0; x<line.length(); x++) {
							char c = line.charAt(x);
							if(!isValidChar(c)) {
								System.out.println("Aviso: caractere desconhico, preenchendo com chão na posição " + "(" + x + "," + y + ")");
								c = ' ';
							}
							matrix[x][y] = c;
						}
					}
					y++;		
				} 
				s.close();
				validName = true;
				if(y < maxAltura) {
					System.err.println("Erro: Faltam linhas no arquivo.");
					ImageGUI.getInstance().dispose();
					System.exit(1);
				}
	        }
			catch (FileNotFoundException e) {
				System.err.println("Erro: ficheiro n encontrado, digite o nome correto: ");
				nome = "rooms\\" + input.nextLine();
			}
		}input.close();
	}
	
	public void addElement(GameElement element) {
		list.add(element);
	}
	
	public void pintarMapa()  {

		for(int x=0; x< matrix.length; x++){
			for(int y=0; y<matrix[0].length; y++) {
				char c = matrix[x][y];	
				GameElement elemento = GameElement.criarObjeto(c, new Point2D(x,y));
				if(elemento.fundoTransparente()) {
					new Floor(new Point2D(x,y));
				}	
				addElement(elemento);
			}
		}

	}
	
	public boolean isBlocking(Point2D position) {
		
		for(GameElement elem : list) {
			if(elem.getPosition().equals(position) && elem.isBlocking()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSolid(Point2D position) {
		
		for(GameElement elem : list) {
			if(elem.getPosition().equals(position) && elem.isSolid()) {
				return true;
			}
		}
		return false;
	}
	
	public void falling() {
		
		for(GameElement elem : list) {
			if(elem instanceof MovingElement && !(elem instanceof Banana)) {
				Point2D position = elem.getPosition();
				Point2D downPosition = position.plus(new Vector2D(0, 1));
				if(!isSolid(downPosition)) {
					Point2D futurePosition = new Point2D(position.getX(), position.getY() + 1);
					elem.setPosition(futurePosition);
					((MovingElement)elem).interact(futurePosition, elem);
				}
			}
		}
	}
	
	public List<GameElement> listWithElementsInSamePosition(Point2D position){
		List<GameElement> l = new ArrayList<>();
		for(GameElement elem : list) {
			if(elem.getPosition().equals(position)) {
				l.add(elem);
			}
		}
		return l;
	}
	
	public boolean lose() {
		for(GameElement elem : list) {
			if(elem instanceof Manel && elem.getDelete()) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteAllElements() {
		list.clear();
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void deleteElement() {
		list.removeIf(elem -> elem.getDelete());
	}
	
	public void deleteElementsOneSquare(Point2D position) {
		List<Point2D> neighbourhoodPoints = position.getNeighbourhoodPoints(); 
		for(GameElement elem : list) {
			for(Point2D point : neighbourhoodPoints) {
				if(elem.getPosition().equals(point) && !(elem instanceof Wall) && !(elem instanceof Floor) && !(elem instanceof Stairs)) {
					if(elem instanceof Manel) {
						Manel m = (Manel) elem;
						m.attacked(100);
					}else {
						elem.setDelete(true);
						ImageGUI.getInstance().removeImage(elem);
					}
				}
			}
		}
	}
	
	public void plantBomb() {
		BombPlanted b = null;
		List<GameElement> l = new ArrayList<>();
		for(GameElement elem : list) {
			if(elem instanceof Manel) {
				b = new BombPlanted(elem.getPosition());
				l.add(b);
			}
		}
		list.addAll(l);
	}
	
	public void addBombInfoToList(Bomb bomb) {
		listWithBombInfo.add(bomb);
	}
	
	public boolean bombWasCatched() {
		for(Bomb bomb : listWithBombInfo) {
			if(bomb.getBombWasCatched()) {
				bomb.setBombWasCatched(false);
				return true;
			}
		}
		return false;
		
	}
	
	public void timer() {
		int ticks = ImageGUI.getInstance().getTicks();
		for(GameElement elem : list) {
			if(elem instanceof Timer) {
				if(ticks - ((Timer)elem).getTicksWhenSpawned() >= ((Timer)elem).getMaxTicks()) {
					((Timer)elem).timeFinished();
				}
			}
		}
	}
	
	public boolean isValidChar(char c) {
		
		switch(c) { 
		   
		case 'W': return true;
		case 'G': return true;
		case 'P': return true;
		case 't': return true;
		case 'S': return true;
		case 'H': return true;
		case '0': return true;
		case 'm': return true;
		case ' ': return true;
		case 's': return true;
		case 'B': return true;
		case 'b': return true;
		case 'T': return true;
		
		default: return false;
		
		}
	}

}