package ProgramPackage;

import java.util.ArrayList;
import CharacterPackage.Monster;

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
			//String name, int maxHp, int atk, int def, int hitRolls, Point startPos
			
			JSONObject currentElement = (JSONObject) monsterJsonArray.get(i);
			out.add(new Monster(
					currentElement.get("name").toString(),
					(int) (long) currentElement.get("maxHp"),
					(int) (long) currentElement.get("atk"),
					(int) (long) currentElement.get("def"),
					(int) (long) currentElement.get("hitRolls"),
					new Point((int) (long)currentElement.get("startX"), (int) (long)currentElement.get("startY"))			
					));
		}
		return out;
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
