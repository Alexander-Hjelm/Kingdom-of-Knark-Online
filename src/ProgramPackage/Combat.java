package ProgramPackage;
import java.util.ArrayList;
import java.util.Random;

import CharacterPackage.Character;
import CharacterPackage.Monster;
import CharacterPackage.Player;

public class Combat {

	public void attack(Character charA, Character charB)
	{
		int charAAtk = charA.getAtk();
		int charBDef = charB.getDef();
		int charAHitRolls = charA.getHitRolls();
		
		// If attacking character is a player, account for equipped weapon
		if(charB instanceof Monster && charA instanceof Player)
		{
			Player player = (Player) charA;
			if (player.getActiveWeapon() != null)
			{
				charAAtk += player.getActiveWeapon().getAtk();
				charAHitRolls += player.getActiveWeapon().getHitRolls();
			}
		}
		
		// If defending character is a player, account for equipped armor
		if(charB instanceof Player && charA instanceof Monster)
		{
			Player player = (Player) charB;
			if (player.getActiveArmor() != null)
			{
			charBDef += player.getActiveArmor().getDef();
			}
		}
		
		int totalDamage = 0;
		//charA attacks charB
		final int ATK_VAL = throwDTwelve();
				
		if(ATK_VAL <= charAAtk)
		{
			//Passed hit check
			for(int i = 0; i < charAHitRolls; i++)
			{
				final int DICE_RES = throwDTwelve();
				if(DICE_RES >= charB.getDef())
				{
					//Passed damage check
					totalDamage += 1;
				}
			}
			//Crit check
			if (ATK_VAL == 12)
			{
				Debug("CRITICAL HIT!");
				totalDamage *= 2;
			}
			charB.modHp(-totalDamage);
			Debug("Hit for " + Integer.toString(totalDamage) + " damage! " + charB.getName() + "'s hp is now: (" + charB.getHp() + " / " + charB.getMaxHp() + ")...");
			
			if (charB.getHp() <= 0)
			{
				charB.setDead(true);
				Debug(charB.getName() + " died...");
				
				if(charB instanceof Monster && charA instanceof Player)
				{
					//Add gold to player's inventory
					int diff =
							charB.getMaxHp() * 2 +
							charB.getAtk() * 3 +
							charB.getDef() * 3 +
							charB.getHitRolls() * 3;
					
					Random randomGenerator = new Random();
				    int randDiff = randomGenerator.nextInt( (int) ( diff * 0.7) ) + 1;
					
				    randDiff = randDiff - randDiff/2;
				    diff = diff + randDiff;
				    
					Player player = (Player) charA;
					player.addGold(diff);
					Debug(charA.getName() + " recived " + diff + " gold pieces!");
				}
			}
			
			return;
		}
		
		Debug("Hit failed!");
		return;
		
	}
	
	public void allMonstersAttack(Player player, ArrayList<Monster> monsterList)
	{
		//let all monsters in the current room attack the player
		for(int i = 0; i < monsterList.size(); i++)
		{
			Monster currentMonster = monsterList.get(i);
			if(!currentMonster.getDead())
			{
				Debug(currentMonster.getName() + " attacks!");
				attack(currentMonster, player);
			}
		}
		return;
	}
	
	private int throwDTwelve()
	{
		//Generate random number in range 1-12
		Random randomGenerator = new Random();
	    return randomGenerator.nextInt(12) + 1;
	}
	
	private static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}
