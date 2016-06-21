
public class Character {
	protected int maxHp;
	protected int hp;
	protected int atk;
	protected int def;
	protected int hitRolls;
	
	protected String name;
	
	protected boolean dead;
	
	public void modHp(int diff)
	{
		this.hp += diff;
		return;
	}
	
	public String getName() {		return this.name;	}
	
	public void setDead(){}
	
	
}
