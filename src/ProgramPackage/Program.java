package ProgramPackage;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public final class Program {

	private static PlayerInputClass playerInput;
	private static ConsoleOutputClass consoleOutput;
	private static Combat combat;
	
	private static Board gameBoard;
	private static Point playerStartPos;
	
	public static void main(String[] args) throws IOException, ParseException
	{
		playerInput = new PlayerInputClass();
		consoleOutput = new ConsoleOutputClass();
		combat = new Combat();		

		playerStartPos = new Point(0,0);
		gameBoard = new Board(playerStartPos);
		
		while (true)
		{
			gameLoop();
			continue;
		}		
	}
	
	public static void gameLoop()
	{
		combat.allMonstersAttack(gameBoard.player, gameBoard.getCell(gameBoard.player.getPos()).getMonsters());
		
		//Check if player is dead
		if(gameBoard.player.getDead())
		{
			System.exit(0);
		}
		
		consoleOutput.PrintInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
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
