package ProgramPackage;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import CharacterPackage.Monster;
import CharacterPackage.Player;

public class Board {

	//dimensions of grid
	private final int GRID_SIZE_X = 100;
	private final int GRID_SIZE_Y = 100;
	private boolean shopActive = true;
	
	private JsonParser jsonParser = new JsonParser();
	
	public Player player;
	private ArrayList<Item> availableItems = new ArrayList<Item>();
	
	//The game board, represented as a 2d array
	private Cell[][] grid;

	public Board(Point startPos) throws IOException, ParseException {
		//Create the player
		//String name, int maxHp, int atk, int def, int hitRolls, Point startPos
		player = new Player("playerName", 3, 1, 1, 1, startPos);
		
		//Create the grid
		grid = new Cell[GRID_SIZE_X][GRID_SIZE_Y];
		
		//Populate the grid with rooms
		for(int i = 0; i < GRID_SIZE_X; i++){
			for(int j = 0; j < GRID_SIZE_Y; j++){
				grid[i][j] = new Cell("You are standing in a room with the default room description...");
				tryAndSetLevelBounds(i, j, grid[i][j]);
			}
		}
		
		//test stuff
		//grid[0][0].setWalkableDir(0, false);
		//grid[0][1].setWalkableDir(1, false);
		
		//String name, int maxHp, int atk, int def, int hitRolls, Point startPos
		//grid[1][0].addMonster(new Monster("Sven", 2, 3, 6, 2, startPos));
		//grid[1][0].addMonster(new Monster("Martin", 2, 3, 6, 2, startPos));
		//grid[1][0].addMonster(new Monster("Olof", 2, 3, 6, 2, startPos));
		
		this.repopulateMonsters();
		
		//Populate walls from JSON
		jsonParser.setRoomWalls("gameData/walls.json", grid);
		
		//Populate item-array from JSON
		this.availableItems = jsonParser.getItems("gameData/items.json");
		
		//Populate desc-array from JSON
		jsonParser.setRoomDescs("gameData/roomDescs.json", grid);
	}
	
	public void tryAndSetLevelBounds(int x, int y, Cell cell)
	{
		//"north", "south", "east", "west"
		if (x == 0)					{	cell.addWall(3);	}
		if (x == GRID_SIZE_X - 1)	{	cell.addWall(2);	}
		if (y == 0)					{	cell.addWall(1);	}
		if (y == GRID_SIZE_Y - 1)	{	cell.addWall(0);	}
		
		return;
	}
	
	public void repopulateMonsters() throws IOException, ParseException
	{
		//Clear alla monster-arrays
		for(int i = 0; i < GRID_SIZE_X; i++){
			for(int j = 0; j < GRID_SIZE_Y; j++){
				grid[i][j].clearMonsters();
			}
		}
		
		//Populate all monster-arrays from JSON
		ArrayList<Monster> monsterImportList = jsonParser.getMonsters("gameData/monsters.json");
		for(int i = 0; i < monsterImportList.size(); i++)
		{
			Monster monster = monsterImportList.get(i);
			grid[monster.getPos().x][monster.getPos().y].addMonster(monster);
		}
	}
	
	public boolean getShopActive()
	{
		return shopActive;
	}
	
	public void setShopActive(boolean shopActive)
	{
		this.shopActive = shopActive;
	}
	
	public void checkPlayerOutOfBounds()
	{
		//Checks and corrects the player position if out of level bounds
		
		Point deltaPos = new Point(0, 0);	//Correction vector
		
		if(player.getPos().x < 0){
			deltaPos = new Point(1, 0);
		}
		
		if(player.getPos().x > GRID_SIZE_X){
			deltaPos = new Point(-1, 0);
		}
		
		if(player.getPos().y > GRID_SIZE_Y){
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
	
	public ArrayList<Item> getAvailableItems()	{	return this.availableItems;	}
}