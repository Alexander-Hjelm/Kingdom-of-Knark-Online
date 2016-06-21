package CharacterPackage;

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
	
	public String getName() 	{	return this.name;		}
	
	public int getAtk() 		{	return this.atk;		}
	public int getDef()			{	return this.def;		}
	public int getHitRolls()	{	return this.hitRolls;	}
	public int getHp()			{	return this.hp;			}
	public int getMaxHp()		{	return this.maxHp;		}
	
	public boolean getDead()	{	return this.dead;	}
	public void setDead()		{	this.dead = true;	}
}
