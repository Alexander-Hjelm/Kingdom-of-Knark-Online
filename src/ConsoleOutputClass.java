import java.awt.Point;
import java.util.ArrayList;

public class ConsoleOutputClass {

	//print room desc
	//print room contents
	//print possible walking directions
	
	public void PrintInfo(Board gameBoard){
		
		Point playerPos = gameBoard.player.getPos();
		
		String posString = String.format("You are standing in room [%d, %d]...", playerPos.x, playerPos.y);
		String descString = gameBoard.getCell(playerPos).getDesc();
		
		//Generate string with possible directions to go
		String dirString = makeDirString(gameBoard.getCell(playerPos));
		
		//Generate string describing the 
		String monsterString = makeMonsterString(gameBoard.getCell(playerPos));
		
		Print(posString);
		Print(descString);
		if (monsterString.equals("") == false) { Print(monsterString); }
		Print(dirString);
	}
	
	private void Print(String inputText){
		System.out.println(inputText);
	}
	
	private String makeDirString(Cell currentCell)
	{
		String out = "The following directions are accessible: ";
		for(int i = 0; i < 4; i++)
		{
			if(currentCell.getWalkableDirs()[i] == true)
			{
				out = out.concat(currentCell.DIR_NAMES[i]);
				if (i != 3)
				{
					 out = out.concat(", ");
				}
			}	
		}
		out = out.concat("...");
		return out;
	}
	
	private String makeMonsterString(Cell currentCell)
	{
		String out = "";
		ArrayList<Monster> monsterList = currentCell.getMonsters();
		for(int i = 0; i < monsterList.size(); i++)
		{
			out = out.concat("A menacing " + monsterList.get(i).getName() + " stares at you...");
			if (i != monsterList.size()-1)
			{
				 out = out.concat("\n");
			}
		}
		return out;
	}
}