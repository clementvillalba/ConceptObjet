package archi_concept_objet_phil;

import java.util.ArrayList;
import java.util.Random;

import Personnages.Elfe;
import Personnages.Entity;
import Personnages.Gobelin;
import Personnages.Humain;
import Personnages.Orc;

public class WorldMap {
	
	Random rand = new Random();
	
	private ArrayList<Entity> entities;
	
	private Case[][] worldMap;
	private ArrayList<Case> worldMapList;
	
	public WorldMap() {
		
		worldMap = new Case[Rules.worldMap_maxX][Rules.worldMap_maxY];
		worldMapList = new ArrayList<Case>();
		generation();
		positionnementEntity();
		for(int i = 0; i < Rules.worldMap_maxX; i++) {
			for(int j = 0; j < Rules.worldMap_maxY; j++) {
				worldMapList.add(worldMap[i][j]);
			}
		}
		entities = new ArrayList<Entity>();
	}
	
	public void playOneTurn() {
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).play();
		}
		
	}
	
	public boolean isGameOver() {
		//waw
		return true;
	}
        
	public Case getCase(int x,int y){
		return worldMap[x][y];
	}  
	
	public void displayWorldMap() {
		System.out.println("Affichage de la carte:");
		//Première coordonnée X de la première case
		int posX = worldMapList.get(0).getX();
		for (Case mapCase : worldMapList) {
			if(mapCase.getX()!=posX) { //Si le X change on change de ligne
				System.out.println();
				posX+=1;
			}
			if(mapCase.getEntity()==null) {
				mapCase.displayCase();
			}
			else {
				System.out.print(mapCase.getEntity().toString());
			}
		}
		System.out.println();
	} 
	
	public boolean checkingCase(int a, int b) {
		if(this.getCase(a, b).getCaseType()==CaseType.OBSTACLE) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void generation(){
		
		for(int i = 0; i < Rules.worldMap_maxX; i++) {
			for(int j = 0; j < Rules.worldMap_maxY; j++) {
				if(i<Rules.worldMap_sizeSafeZone && j<Rules.worldMap_sizeSafeZone) {
					worldMap[i][j]=new Case(i,j,CaseType.HUMAN);
				}
				else if (i<Rules.worldMap_sizeSafeZone && j>(Rules.worldMap_maxX-(1+Rules.worldMap_sizeSafeZone))) {
					worldMap[i][j]=new Case(i,j,CaseType.ELVE);
				}
				else if (i>(Rules.worldMap_maxY-(1+Rules.worldMap_sizeSafeZone)) && j<Rules.worldMap_sizeSafeZone) {
					worldMap[i][j]=new Case(i,j,CaseType.GOBLIN);
				}
				else if (i>(Rules.worldMap_maxY-(1+Rules.worldMap_sizeSafeZone)) && j>(Rules.worldMap_maxX-(1+Rules.worldMap_sizeSafeZone))) {
					worldMap[i][j]=new Case(i,j,CaseType.ORC);
				}
				else {
					
					if(rand.nextInt(20)==10) {
						worldMap[i][j]=new Case(i,j,CaseType.OBSTACLE);
					}
					else {
						worldMap[i][j]=new Case(i,j,CaseType.WASTELAND);
					}
					
				}
				//worldMap[i][j] = new Case(i,j);
				//worldMapList.add(worldMap[i][j]);
			}
		}
		//return worldMap;
	}
	
	public void positionnementEntity() {
		worldMap[0][0].setEntity(new Humain());
		for(int i = 0; i < Rules.worldMap_maxX; i++) {
			for(int j = 0; j < Rules.worldMap_maxY; j++) {
				switch (worldMap[i][j].getCaseType()) {
				case HUMAN:
					if (rand.nextInt(8)==2) {
						worldMap[i][j].setEntity(new Humain());
					}
					break;
				case ELVE:
					if (rand.nextInt(8)==3) {
						worldMap[i][j].setEntity(new Elfe());
					}
					break;
				case ORC:
					if (rand.nextInt(8)==4) {
						worldMap[i][j].setEntity(new Orc());
					}
					break;
				case GOBLIN:
					if (rand.nextInt(8)==5) {
						worldMap[i][j].setEntity(new Gobelin());
					}
					break;
				default:
					break;
				}
			}
		}
	}
	

	
}
