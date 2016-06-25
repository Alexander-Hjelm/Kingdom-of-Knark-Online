package ProgramPackage;

import java.util.ArrayList;
import CharacterPackage.Monster;
import ProgramPackage.Item.Type;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

	public ArrayList<Monster> getMonsters(String path) throws IOException, ParseException
	{
		JSONObject jsonObject = parse(path);
		JSONArray monsterJsonArray = getJSONArray(jsonObject);
		
		ArrayList<Monster> out = new ArrayList<Monster>();
		
		for(int i = 0; i < monsterJsonArray.size(); i++)
		{
			//String name, String type, int maxHp, int atk, int def, int hitRolls, Point startPos
			
			JSONObject currentElement = (JSONObject) monsterJsonArray.get(i);
			out.add(new Monster(
					currentElement.get("name").toString(),
					currentElement.get("type").toString(),
					(int) (long) currentElement.get("maxHp"),
					(int) (long) currentElement.get("atk"),
					(int) (long) currentElement.get("def"),
					(int) (long) currentElement.get("hitRolls"),
					new Point((int) (long)currentElement.get("startX"), (int) (long)currentElement.get("startY"))			
					));
		}
		return out;
	}
	
	public ArrayList<Item> getItems(String path) throws IOException, ParseException
	{
		JSONObject jsonObject = parse(path);
		JSONArray itemJsonArray = getJSONArray(jsonObject);
		
		ArrayList<Item> out = new ArrayList<Item>();
		
		for(int i = 0; i < itemJsonArray.size(); i++)
		{
			//String name, int cost, int maxHp, int atk, int def, int hitRolls, Type type
			
			JSONObject currentElement = (JSONObject) itemJsonArray.get(i);
			
			Item.Type type = null;
			
			String typeString = currentElement.get("type").toString();
			switch (typeString)
			{
				case("weapon"):
				{
					type = Item.Type.WEAPON;
					break;
				}
				case("armor"):
				{
					type = Item.Type.ARMOR;
					break;
				}
				case("amulet"):
				{
					type = Item.Type.AMULET;
					break;
				}
			}
			
			out.add(new Item(
					currentElement.get("name").toString(),
					(int) (long) currentElement.get("cost"),
					(int) (long) currentElement.get("hp"),
					(int) (long) currentElement.get("atk"),
					(int) (long) currentElement.get("def"),
					(int) (long) currentElement.get("hitRolls"),
					type			
					));
		}
		return out;
	}
	
	public void setRoomDescs(String path, Cell[][] grid) throws IOException, ParseException
	{
		JSONObject jsonObject = parse(path);
		JSONArray descsJsonArray = getJSONArray(jsonObject);
		
		for(int i = 0; i < descsJsonArray.size(); i++)
		{
			JSONObject currentElement = (JSONObject) descsJsonArray.get(i);
			int x = (int) (long) currentElement.get("X");
			int y = (int) (long) currentElement.get("Y");
			
			grid[x][y].setDesc( currentElement.get("desc").toString() );
			
		}
		return;
	}
	
	public void setRoomWalls(String path, Cell[][] grid) throws IOException, ParseException
	{
		JSONObject jsonObject = parse(path);
		JSONArray wallJsonArray = getJSONArray(jsonObject);
		
		for(int i = 0; i < wallJsonArray.size(); i++)
		{
			JSONObject currentElement = (JSONObject) wallJsonArray.get(i);
			int x = (int) (long) currentElement.get("X");
			int y = (int) (long) currentElement.get("Y");
			String dir = currentElement.get("dir").toString();
			Cell currentCell = grid[x][y];
			
			//setWalkableDir: {"north", "south", "east", "west"};
			if (dir.contains("n"))
			{
				currentCell.setWalkableDir(0, false);
				grid[x][y+1].setWalkableDir(1, false);
			}
			if (dir.contains("s"))
			{
				currentCell.setWalkableDir(1, false);
				grid[x][y-1].setWalkableDir(0, false);
			}
			if (dir.contains("e"))
			{
				currentCell.setWalkableDir(2, false);
				grid[x+1][y].setWalkableDir(3, false);
			}
			if (dir.contains("w"))
			{
				currentCell.setWalkableDir(3, false);
				grid[x-1][y].setWalkableDir(2, false);
			}	
		}
		return;
	}
	
	private JSONObject parse(String path) throws IOException, ParseException
	{
		try {
			FileReader fileReader = new FileReader(path);
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);
			return jsonObject;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private JSONArray getJSONArray(JSONObject jsonObject)
	{
		return (JSONArray) jsonObject.get("Array");
	}
	
}
