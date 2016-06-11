public class Monster {
	private int maxHp;
	private int hp;
	private int atk;
	private int def;
	private String name;
	private boolean dead;
	
	public Monster(String name, int maxHp, int atk, int def) {

		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.name = name;

		this.dead = false;
	}
	
	public String getName() {		return this.name;	}
	
	public void setDead()
	{
		this.dead = true;
	}
}