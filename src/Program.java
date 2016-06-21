import java.awt.Point;
import java.util.ArrayList;

public final class Program {

	private static PlayerInputClass playerInput;
	private static ConsoleOutputClass consoleOutput;
	private static Combat combat;
	
	private static Board gameBoard;
	private static Point playerStartPos;
	
	public static void main(String[] args)
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
		consoleOutput.PrintInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
		if (playerInput.ReadLine(gameBoard) == 0)
		{
			System.exit(0);
		}
		combat.allMonstersAttack(gameBoard.player, gameBoard.getCell(gameBoard.player.getPos()).getMonsters());
	}
	
	public static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}
