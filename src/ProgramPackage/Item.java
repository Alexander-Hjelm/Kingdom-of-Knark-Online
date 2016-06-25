package ProgramPackage;

public class Item {

	private String name;
	private int cost;
	
	private int addedMaxHp;
	private int addedAtk;
	private int addedDef;
	private int addedHitRolls;
	
	public static enum Type {
		WEAPON, ARMOR, AMULET
	}
	
	private Type itemType;
	
	public Item(String name, int cost, int maxHp, int atk, int def, int hitRolls, Type type)
	{
		this.cost = cost;
		this.name = name;
		this.addedMaxHp = maxHp;
		this.addedAtk = atk;
		this.addedDef = def;
		this.addedHitRolls = hitRolls;
		this.itemType = type;
	}
	
	public String getName()		{	return this.name;			}
	public int getCost()		{	return this.cost;		}
	public int getMaxHp()		{	return this.addedMaxHp;		}
	public int getAtk()			{	return this.addedAtk;		}
	public int getDef()			{	return this.addedDef;		}
	public int getHitRolls()	{	return this.addedHitRolls;	}
	public Type getItemType()	{	return this.itemType;		}
}
