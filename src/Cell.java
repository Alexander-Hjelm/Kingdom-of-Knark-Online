import java.util.ArrayList;

public class Cell {
	private String desc; 
	private ArrayList<Monster> monsterList = new ArrayList<Monster>();
	private boolean[] walkableDirs = new boolean[4];	//which directions are walkable?
	public final String[] DIR_NAMES = {"north", "south", "east", "west"};
	 
	public Cell(String desc){
		this.desc = desc;
		for(int i = 0; i < 4; i++)
		{
			this.walkableDirs[i] = true;
		}
	}
	
	public String getDesc(){
		return desc;
	}
	
	public boolean[] getWalkableDirs()
	{
		return this.walkableDirs;
	}
	
	public void setWalkableDir(int i, boolean onOff)
	{
		//Sets or desets a wall in the direction of i, depending on the value of onOff
		if(i >= 0 && i <= 3)
		{
			this.walkableDirs[i] = onOff;
		}
	}
	
	public void addMonster(Monster monster)
	{
		monsterList.add(monster);
	}
	
	public ArrayList<Monster> getMonsters()
	{
		return this.monsterList;
	}
}