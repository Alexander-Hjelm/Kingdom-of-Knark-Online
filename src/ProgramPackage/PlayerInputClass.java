package ProgramPackage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import CharacterPackage.Monster;

public class PlayerInputClass
{
	//Instantiate scanner object for input stuff
	//Final objects can't be changed later 
	final static Scanner input = new Scanner(System.in);
	private ConsoleOutputClass consoleOutput = new ConsoleOutputClass();
	
	private Combat combat = new Combat();
	private HealingScroll healingScroll = new HealingScroll();
	
	public int ReadLine(Board gameBoard)
	{
		String inputText = input.nextLine();
		String[] inputTextSegments = inputText.split(" ", 2);
		
		if(gameBoard.getShopActive())
		{
			switch(inputTextSegments[0])
			{
				case "exit":
					return 0;
				case "buy":
					//******** BUY IEMS ********
					//Check if the desired item exists
					String itemName = inputTextSegments[1];
					ArrayList<Item> availableItems = gameBoard.getAvailableItems();
					for (int i = 0; i < availableItems.size(); i++)
					{
						Item item = availableItems.get(i);
						if (item.getName().equals(itemName))
						{
							//Item was found, now check if player can afford it
							if(gameBoard.player.getGold() >= item.getCost())
							{
								switch(item.getItemType().toString())
								{
								case ("WEAPON"):
									gameBoard.player.setActiveWeapon(item);
									break;
								case ("ARMOR"):
									gameBoard.player.setActiveArmor(item);
									break;
								case ("AMULET"):
									gameBoard.player.setActiveAmulet(item);
									break;
								}
								gameBoard.player.modGold(-item.getCost());
								Debug("You bought a " + item.getName() + "!");
							}
							else
							{
								Debug("You can not afford that!");
							}
							break;
						}
					}				
					
					Training training = new Training();
					
					//******** BUY TRAINING ********
					int cost = 0;
					
					switch (itemName)
					{
					case "Training: Vitality":
						cost = training.getTrainingVitCost(gameBoard.player);
						if (gameBoard.player.getGold() >= cost)
						{
							training.trainVit(gameBoard.player);
						}
						else
						{	Debug("You can not afford training!");	}
						break;
					
					case "Training: Accuracy":
						cost = training.getTrainingAccCost(gameBoard.player);
						if (gameBoard.player.getGold() >= cost)
						{
							training.trainAcc(gameBoard.player);
						}
						else
						{	Debug("You can not afford training!");	}
						break;
					
					case "Training: Toughness":
						cost = training.getTrainingDefCost(gameBoard.player);
						if (gameBoard.player.getGold() >= cost)
						{
							training.trainDef(gameBoard.player);
						}
						else
						{	Debug("You can not afford training!");	}
						break;
					
					case "Training: Weapon skill":
						cost = training.getTrainingHitRollsCost(gameBoard.player);
						if (gameBoard.player.getGold() >= cost)
						{
							training.trainHitRolls(gameBoard.player);
						}
						else
						{	Debug("You can not afford training!");	}
						break;
						
						//******** BUY HEALING SCROLL ********
					case "Healing Scroll":
						cost = healingScroll.getCost(gameBoard.player);
						if (gameBoard.player.getGold() >= cost)
						{
							gameBoard.player.setNumberOfHealingScrolls(
								gameBoard.player.getNumberOfHealingScrolls() +1 );
							Debug("You bought a scroll of healing! you now have: "
								+ gameBoard.player.getNumberOfHealingScrolls());
						}
						else
						{	Debug("You can not afford training!");	}
						break;
						
					default:
						break;
					}
					

					
					
					break;
				case "leave":
					gameBoard.setShopActive(false);
					if(gameBoard.player.getActiveAmulet() != null)
					{
						gameBoard.player.setHp(
								gameBoard.player.getHp() + 
								gameBoard.player.getActiveAmulet().getMaxHp());
					}
					break;
				case "status":
					consoleOutput.printPlayerInfo(gameBoard.player);
					break;
				default:
					Debug("Command not recognized...");
					break;
			}			
		}
		else
		{
			switch(inputTextSegments[0])
			{
				case "exit":
					return 0;
				case "go":
					if(inputTextSegments.length > 1){
						allMonstersAttack(gameBoard);
						Cell oldCell = gameBoard.getCell(gameBoard.player.getPos());
						int out = gameBoard.player.walk(inputTextSegments[1], gameBoard);
						Cell newCell = gameBoard.getCell(gameBoard.player.getPos());
						
						for(int i = 0; i < oldCell.getMonsters().size(); i++)
						{
							Random random = new Random();
							if (random.nextInt(100) > 40)
							{
								Monster monster = oldCell.getMonsters().get(i);
								newCell.addMonster(monster);
								oldCell.removeMonster(monster);
							}
						}
						
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
				case "attack":
					if(inputTextSegments.length > 1){
						String targetName = inputTextSegments[1];
						ArrayList<Monster> monsterList = gameBoard.getCell(gameBoard.player.getPos()).getMonsters();
						
						boolean attacked = false;	//Did we attack something?
						
						for(int i = 0; i < monsterList.size(); i++)
						{
							Monster currentMonster = monsterList.get(i);
							if(currentMonster.getName().equals(targetName) && !currentMonster.getDead())
							{
								attacked = true;
								final Monster target = currentMonster;
								final Combat combat = new Combat();
								combat.attack(gameBoard.player, target);
								allMonstersAttack(gameBoard);
								break;
							}				
						}
						if(!attacked)
						{
							Debug("Target (" + targetName + ") is non-existent or out of range...");
						}
					}
					break;
					
				case "heal":
					if(gameBoard.player.getNumberOfHealingScrolls() > 0)
					{
						healingScroll.heal(gameBoard.player);
						allMonstersAttack(gameBoard);
					}
					else
					{
						Debug("You are out of healing scrolls!");
					}
					break;
				case "status":
					consoleOutput.printPlayerInfo(gameBoard.player);
					break;
				
				case "examine":
					consoleOutput.findMonsterAndPrintInfo(inputTextSegments[1], gameBoard.getCell(gameBoard.player.getPos()));
					break;
				default:
					Debug("Command not recognized...");
					break;
			}
		}
		
		//Make sure the previous shift in player position didn't put us out of bounds
		gameBoard.checkPlayerOutOfBounds();
		
		return 1;
	}
	
	private void allMonstersAttack(Board gameBoard)
	{
		combat.allMonstersAttack(gameBoard.player, gameBoard.getCell(gameBoard.player.getPos()).getMonsters());
	}
	
	private static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}