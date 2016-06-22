package CharacterPackage;
import java.awt.Point;
import ProgramPackage.Board;

public class Player extends Character {
	
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
			if (walkableDirs[0] == true){
				deltaPos = new Point(0, 1);
			}
			break;
			
		case "south":
			if (walkableDirs[1] == true){
				deltaPos = new Point(0, -1);
			}
			break;
		
		case "east":
			if (walkableDirs[2] == true){
				deltaPos = new Point(1, 0);
			}
			break;
		
		case "west":
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
	

}