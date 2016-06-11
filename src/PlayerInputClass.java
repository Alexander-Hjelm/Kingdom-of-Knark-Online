import java.util.Scanner;

public class PlayerInputClass
{
	//Instantiate scanner object for input stuff
	//Final objects can't be changed later 
	final static Scanner input = new Scanner(System.in);
	
	public int ReadLine(Board gameBoard)
	{
		String inputText = input.nextLine();
		String[] inputTextSegments = inputText.split(" ");
		
		
		switch(inputTextSegments[0])
		{
			case "exit":
				return 0;
			case "go":
				if(inputTextSegments.length > 1){
					int out = gameBoard.player.walk(inputTextSegments[1], gameBoard);
					if(out == 0)
					{
						Debug("Direction not recognized...");
					}
					break;
				}
				else{
					Debug("Command not recognized...");
					break;
				}
			default:
				Debug("Command not recognized...");
				break;
		}
		
		//Make sure the previous shift in player position didn't put us out of bounds
		gameBoard.checkPlayerOutOfBounds();
		
		return 1;
	}
	
	private static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}