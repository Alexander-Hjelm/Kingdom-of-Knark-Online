package CharacterPackage;

import java.awt.Point;

public class Monster extends Character {

	private String monsterType;
	
	public Monster(String name, String type, int maxHp, int atk, int def, int hitRolls, Point startPos) {
		super(name, maxHp, atk, def, hitRolls, startPos);
		this.monsterType = type;
	}
	
	public String getType()	{ return this.monsterType; }
}