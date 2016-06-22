package CharacterPackage;

import java.awt.Point;

public class Character {
	protected int maxHp;
	protected int hp;
	protected int atk;
	protected int def;
	protected int hitRolls;
	
	protected String name;
	
	protected boolean dead;
	
	protected Point position;
	
	public Character(String name, int maxHp, int atk, int def, int hitRolls, Point startPos)
	{
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.hitRolls = hitRolls;
		this.position = startPos;
		
		this.dead = false;
	}
	
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
	
	public Point getPos() {	return position;}
	public void setPos(Point newPos)	{	this.position = newPos;}	
}
