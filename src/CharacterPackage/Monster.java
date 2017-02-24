package CharacterPackage;

import java.awt.Point;

public class Monster extends Character {

	private String monsterType;
	private boolean stun;
	
	public Monster(String name, String type, int maxHp, int atk, int def, int hitRolls, Point startPos, int stunInt) {
		super(name, maxHp, atk, def, hitRolls, startPos);
		this.monsterType = type;
		if (stunInt == 0) {
			this.stun = false;
		}
		else if (stunInt == 1) {
			this.stun = true;
		}
		
	}
	
	public String getType()	{ return this.monsterType; }
	public boolean getStun() { return this.stun; }
}