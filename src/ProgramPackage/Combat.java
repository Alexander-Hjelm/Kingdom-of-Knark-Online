package ProgramPackage;
import java.util.ArrayList;
import java.util.Random;

import CharacterPackage.Character;
import CharacterPackage.Monster;
import CharacterPackage.Player;

public class Combat {

	public void attack(Character charA, Character charB)
	{
		int totalDamage = 0;
		//charA attacks charB
		final int ATK_VAL = throwDTwelve();
		if(ATK_VAL <= charA.getAtk())
		{
			//Passed hit check
			for(int i = 0; i < charA.getHitRolls(); i++)
			{
				final int DICE_RES = throwDTwelve();
				if(DICE_RES >= charB.getDef())
				{
					//Passed damage check
					totalDamage += 1;
				}
			}
			charB.modHp(-totalDamage);
			Debug("Hit for " + Integer.toString(totalDamage) + " damage! " + charB.getName() + "'s hp is now: (" + charB.getHp() + " / " + charB.getMaxHp() + ")...");
			
			if (charB.getHp() <= 0)
			{
				charB.setDead();
				Debug(charB.getName() + " died...");
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
