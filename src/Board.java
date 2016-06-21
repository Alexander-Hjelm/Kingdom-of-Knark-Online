import java.awt.Point;

public class Board {

	//dimensions of grid
	private final int GRID_SIZE_X = 4;
	private final int GRID_SIZE_Y = 4;

	public Player player;
	
	//The game board, represented as a 2d array
	private Cell[][] grid;

	public Board(Point startPos) {
		//Create the player
		//maxHp, atk, def, startPos
		player = new Player(20, 4, 0, startPos);
		
		//Create the grid
		grid = new Cell[GRID_SIZE_X][GRID_SIZE_Y];
		
		//Populate the grid with rooms
		for(int i = 0; i < GRID_SIZE_X; i++){
			for(int j = 0; j < GRID_SIZE_Y; j++){
				grid[i][j] = new Cell("You are standing in a room with the default room description...");
			}
		}
		
		//test stuff
		grid[0][0].setWalkableDir(0, false);
		grid[0][1].setWalkableDir(1, false);
		
		//name, maxHp, atk, def, hitRolls
		grid[1][0].addMonster(new Monster("Sven", 5, 3, 6, 2));
		
		//Populate monster, wall, desc, item and npc-arrays from JSON
	}
	
	public void checkPlayerOutOfBounds()
	{
		//Checks and corrects the player position if out of level bounds
		
		Point deltaPos = new Point(0, 0);	//Correction vector
		
		if(player.getPos().x < 0){
			deltaPos = new Point(1, 0);
		}
		
		if(player.getPos().x >= GRID_SIZE_X){
			deltaPos = new Point(-1, 0);
		}
		
		if(player.getPos().y >= GRID_SIZE_Y){
			deltaPos = new Point(0, -1);
		}

		if(player.getPos().y < 0){
			deltaPos = new Point(0, 1);
		}
		
		player.setPos(new Point(player.getPos().x + deltaPos.x, player.getPos().y + deltaPos.y));
	}
	
	public Cell getCell(Point pos)
	{
		return grid[pos.x][pos.y];
	}
}