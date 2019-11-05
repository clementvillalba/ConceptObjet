package Personnages;

import archi_concept_objet_phil.Rules;

public class Orc extends Entity {

	private static int nbOrc;
	
	public Orc() {
		this.PV = Rules.orc_PV_Max;
		this.PE = Rules.orc_PE_Max;
		this.XP = 0;
	}
	
	@Override
	public void initPV_PE() {
		// TODO Auto-generated method stub

	}

	@Override
	public void attaque(Entity ennemi) {
		ennemi.PV = ennemi.PV - 4;
	}

}
