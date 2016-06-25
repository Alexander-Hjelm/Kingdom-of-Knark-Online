package ProgramPackage;

import java.util.Random;

import CharacterPackage.Player;

public class HealingScroll {

	private int costHpRatio = 12;
	private double healRatio = 0.4;
	
	public int getCost(Player player)
	{		
		return player.getMaxHp() * this.costHpRatio;
	}
	
	public void heal(Player player)
	{
		if (player.getNumberOfHealingScrolls() > 0)
		{
			Random random = new Random();
			
			int healAmount = (int) (player.getMaxHp() * healRatio);
		    int randDiff = random.nextInt( (int) ( healAmount * 0.3) ) + 1;
		    randDiff = randDiff - randDiff/2;
		    healAmount = healAmount + randDiff;
			
		    int actualHpDiff = player.getMaxHp() - player.getHp();
		    
		    int actualHealAmont = Math.min( healAmount, actualHpDiff);
		    
		    player.modHp( actualHealAmont );
		    player.setNumberOfHealingScrolls( player.getNumberOfHealingScrolls() - 1 );
		    
		    System.out.println("Healed for " + actualHealAmont + " pts!");
		    System.out.println("Your hp is now: (" + player.getHp() + " / " + player.getMaxHp() + ").");
		    System.out.println("You have " + player.getNumberOfHealingScrolls() + " healing scrolls left.");
		    
		}
	}
}
