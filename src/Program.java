import java.awt.Point;

public final class Program {

	private static PlayerInputClass playerInput;
	private static ConsoleOutputClass consoleOutput;
	
	private static Board gameBoard;
	private static Point playerStartPos;
	
	public static void main(String[] args)
	{
		playerInput = new PlayerInputClass();
		consoleOutput = new ConsoleOutputClass();
				

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
	}
	
	public static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}
