package ProgramPackage;

import CharacterPackage.Player;

public class Training {

	public void trainVit(Player player)
	{
		player.addGold(-getTrainingVitCost(player));
		player.setMaxHp(	player.getMaxHp() + 1	);
		player.setHp(	player.getHp() + 1	);
	}
	
	public void trainAcc(Player player)
	{
		player.addGold(-getTrainingAccCost(player));
		player.setAtk(	player.getAtk() + 1	);
	}
	
	public void trainDef(Player player)
	{
		player.addGold(-getTrainingDefCost(player));
		player.setDef(	player.getDef() + 1	);
	}
	
	public void trainHitRolls(Player player)
	{
		player.addGold(-getTrainingHitRollsCost(player));
		player.setHitRolls(	player.getHitRolls() + 1	);
	}
	
	
	public int getTrainingVitCost(Player player)
	{
		int hp = player.getMaxHp();
		int cost = (int) (50* Math.exp(0.15*hp));
		return cost;
	}
	
	public int getTrainingAccCost(Player player)
	{
		int acc = player.getAtk();
		int cost = (int) (150* Math.exp(0.3*acc));
		return cost;
	}
	
	public int getTrainingDefCost(Player player)
	{
		int def = player.getDef();
		int cost = (int) (180* Math.exp(0.32*def));
		return cost;
	}
	
	public int getTrainingHitRollsCost(Player player)
	{
		int hitRolls = player.getHitRolls();
		int cost = (int) (100* Math.exp(0.4*hitRolls));
		return cost;
	}
}
