package ProgramPackage;
import java.awt.Point;
import java.util.ArrayList;

import CharacterPackage.Monster;
import CharacterPackage.Player;

public class ConsoleOutputClass {

	//print room desc
	//print room contents
	//print possible walking directions
	
	private Training training = new Training();
	private HealingScroll healingScroll = new HealingScroll();
	
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
		
		//Check for stun effects, if none, print possible directions to walk in
		for (Monster monster: gameBoard.getCell(playerPos).getMonsters()) {
			if (monster.getStun() && !monster.getDead()) {
				return;
			}
		}
		Print(dirString);
	}
	
	public void printPlayerInfo(Player player) {
		String outStr = "You are: " + player.getName() + ".\n\n"
				+ "Your stats:\n"
				+ "\nHp:\t\t" + player.getHp() + "/" + player.getMaxHp()
				+ "\nAttack:\t\t" + player.getAtk()
				+ "\nDefense:\t" + player.getDef()
				+ "\nHit Rolls:\t" + player.getHitRolls()
				+ "\n\nYou current gold:\t" + player.getGold()
				+ "\n\nYou current equipment:\n";
		
		if (player.getActiveWeapon() != null)
		{
			Item weapon = player.getActiveWeapon();
			outStr += weapon.getName()
					+ " :: atk: " + weapon.getAtk()
					+ ", hit rolls: " + weapon.getHitRolls()
					+ "\n";
		}
		
		if (player.getActiveArmor() != null)
		{
			Item armor = player.getActiveArmor();
			outStr += armor.getName()
					+ " :: def: " + armor.getDef()
					+ "\n";
		}
		if (player.getActiveAmulet() != null)
		{
			Item amulet = player.getActiveAmulet();
			outStr += amulet.getName()
					+ " :: hp: " + amulet.getMaxHp()
					+ "\n\n";
		}
		
		// Number of healing scrolls
		outStr += "\nNumber of healing scrolls: " + player.getNumberOfHealingScrolls();
		outStr += "\n";
		
		Print(outStr);
	}
	
	public void printShopInfo(Board gameBoard)
	{
		Print("Welcome to the shop! Feel free to buy anything you like! \n>> Type \"leave\" to exit the shop, and start the game... \n>> Type \"help\" to see possible commands.");
	
		//Print available items
		ArrayList<Item> itemList = gameBoard.getAvailableItems();
		for (int i = 0; i < itemList.size(); i++)
		{
			Item currentItem = itemList.get(i);
			if (currentItem.getCost() <= gameBoard.player.getGold()) {
				Print("->> " + currentItem.getName() + " :: " + currentItem.getCost() + " gold.");
			}
		}
		
		//Print available training
		Print("->> Training: Vitality :: (+1 hp) :: " + training.getTrainingVitCost(gameBoard.player) + " gold." + "\t\t[permanent]");
		if (gameBoard.player.getAtk() < 4) {
			Print("->> Training: Accuracy :: (+1 atk) :: " + training.getTrainingAccCost(gameBoard.player) + " gold." + "\t\t[permanent]");
		}
		if (gameBoard.player.getDef() < 4) {
			Print("->> Training: Toughness :: (+1 def) :: " + training.getTrainingDefCost(gameBoard.player) + " gold." + "\t\t[permanent]");
		}
		Print("->> Training: Weapon skill :: (+1 hit roll) :: " + training.getTrainingHitRollsCost(gameBoard.player) + " gold." + "\t\t[permanent]");
		
		//Print available healing scroll
		Print("->> Healing Scroll :: (+1) :: " + healingScroll.getCost(gameBoard.player) + " gold.");
	}
	
	public void findMonsterAndPrintInfo(String inputName, Cell currentCell)
	{
		ArrayList<Monster> monsterList = currentCell.getMonsters();
		Monster monster = null;
		for (int i = 0; i < monsterList.size(); i++)
		{
			if (monsterList.get(i).getName().equals(inputName))
			{
				monster = monsterList.get(i);
				break;
			}
			else
			{
				Print("Could not locate " + inputName);
				return;
			}
		}
				
		String outStr =
				"You see the " + monster.getType() + ", who's name is " + monster.getName() + "\n\n"
				+ "His/Her stats are:\n"
				+ "\nHp:\t\t" + monster.getHp() + "/" + monster.getMaxHp()
				+ "\nAttack:\t\t" + monster.getAtk()
				+ "\nDefense:\t" + monster.getDef()
				+ "\nHit Rolls:\t" + monster.getHitRolls()
				+ "\n";
		
		Print(outStr);
	}
	
	public void Print(String inputText){
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
			if(!monsterList.get(i).getDead())
			{
				String monsterTypeAndName = monsterList.get(i).getType() + " \'" + monsterList.get(i).getName() + "\'";
				out = out.concat("The menacing " + monsterTypeAndName + " readies to attack...");
				if (i != monsterList.size()-1)
				{
					 out = out.concat("\n");
				}
			}
		}
		return out;
	}

	public void printHelp() {
		String helpMessage = ""
				+ "---> Commands:"
				+ "\nIn shop:"
				+ "\n-> buy <item name> \t\t Buys the specified item and assigns it to the player inventory. Training items are permanent status effects."
				+ "\n-> leave \t\t\t Exit the shop and start the game."
				+ "\n-> status \t\t\t Displays statistics of the player."
				+ "\n-> exit \t\t\t Exit the game."
				+ "\n-> help \t\t\t Displays all possible commands."
				+ "\nIn game:"
				+ "\n-> go <north/south/east/west> \t Walk in the specified direction."
				+ "\n-> go <n/s/e/w> \t\t Walk in the specified direction."
				+ "\n-> attack <monster name> \t Attacks the specified monster, if present."
				+ "\n-> heal \t\t\t Heals the player, consuming one healing scroll."
				+ "\n-> status \t\t\t Displays statistics of the player."
				+ "\n-> examine <monster name> \t Displays statistics of the specified monster, if present."
				+ "\n-> exit \t\t\t Exit the game.";
		Print(helpMessage);
		
	}
}