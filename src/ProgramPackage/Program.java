package ProgramPackage;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public final class Program {

	private static PlayerInputClass playerInput;
	private static ConsoleOutputClass consoleOutput;
	
	private static Board gameBoard;
	private static Point playerStartPos;
	
	public static void main(String[] args) throws IOException, ParseException
	{
		playerInput = new PlayerInputClass();
		consoleOutput = new ConsoleOutputClass();	

		playerStartPos = new Point(0,0);
		gameBoard = new Board(playerStartPos);
		
		//cheats
		//gameBoard.player.modGold(10000);
		//gameBoard.player.setMaxHp(20);
		//gameBoard.player.setHp(20);
		//gameBoard.player.setNumberOfHealingScrolls(3);
		
		while (true)
		{
			gameLoop();
			continue;
		}		
	}
	
	public static void gameLoop() throws IOException, ParseException
	{
		//Check if player is dead
		if(gameBoard.player.getDead())
		{
			gameBoard.player.setHp(gameBoard.player.getMaxHp());
			gameBoard.player.setPos(playerStartPos);
			gameBoard.player.setActiveWeapon(null);
			gameBoard.player.setActiveArmor(null);
			gameBoard.player.setActiveAmulet(null);
			
			gameBoard.setShopActive(true);
			gameBoard.repopulateMonsters();
			gameBoard.player.setDead(false);
		}
		
		if(gameBoard.getShopActive())
		{
			consoleOutput.printShopInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
		}
		else
		{
			consoleOutput.PrintInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
		}
		
		if (playerInput.ReadLine(gameBoard) == 0)
		{
			System.exit(0);
		}
	}
	
	public static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}