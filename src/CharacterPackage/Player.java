package CharacterPackage;
import java.awt.Point;
import ProgramPackage.Board;
import ProgramPackage.Item;

public class Player extends Character {
	
	private int gold;
	private int numberOfHealingScrolls;
	
	private Item activeWeapon;
	private Item activeArmor;
	private Item activeAmulet;
	
	public Player(String name, int maxHp, int atk, int def, int hitRolls, Point startPos) {
		super(name, maxHp, atk, def, hitRolls, startPos);
	}
	
	public int walk(String dir, Board gameBoard)
	{
		boolean[] walkableDirs = gameBoard.getCell(position).getWalkableDirs();
		
		Point deltaPos = new Point(0, 0);
		switch(dir)
		{
		case "north":
		case "n":
			if (walkableDirs[0] == true){
				deltaPos = new Point(0, 1);
			}
			break;
			
		case "south":
		case "s":
			if (walkableDirs[1] == true){
				deltaPos = new Point(0, -1);
			}
			break;
		
		case "east":
		case "e":
			if (walkableDirs[2] == true){
				deltaPos = new Point(1, 0);
			}
			break;
		
		case "west":
		case "w":
			if (walkableDirs[3] == true){
				deltaPos = new Point(-1, 0);
			}
			break;
		
		default:
			return 0;
		}
		
		if (deltaPos.x == 0 && deltaPos.y == 0)
		{
			System.out.println("You may not go this way...");
		}
		
		position = new Point(position.x + deltaPos.x, position.y + deltaPos.y);
		return 1;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfHealingScrolls()				{	return this.numberOfHealingScrolls;		}
	public void setNumberOfHealingScrolls(int newVal)	{	this.numberOfHealingScrolls = newVal;	}
	
	public int getGold()			{	return this.gold;	}
	public void addGold(int diff)	{	this.gold += diff;	}
	
	public Item getActiveWeapon()	{	return activeWeapon;	}
	public Item getActiveArmor()	{	return activeArmor;		}
	public Item getActiveAmulet()	{	return activeAmulet;	}
	
	public void setActiveWeapon(Item weapon)	{	this.activeWeapon = weapon;	}
	public void setActiveArmor(Item armor)		{	this.activeArmor = armor;	}
	public void setActiveAmulet(Item amulet)	{	this.activeAmulet = amulet;	}
	
}