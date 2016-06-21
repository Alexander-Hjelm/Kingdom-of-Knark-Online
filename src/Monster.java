public class Monster extends Character{

	public Monster(String name, int maxHp, int atk, int def, int hitRolls) {

		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.name = name;

		this.hitRolls = hitRolls;
		this.dead = false;
	}
	
	
	
	public void setDead()
	{
		this.dead = true;
	}
}