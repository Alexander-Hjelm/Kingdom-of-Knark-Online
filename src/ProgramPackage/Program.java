package ProgramPackage;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public final class Program {

	private static PlayerInputClass playerInput;
	private static ConsoleOutputClass consoleOutput;
	
	private static Board gameBoard;
	private static Point playerStartPos;
	private static Point endingPos;
	
	private static boolean nameSet = false;
	private static boolean bossDead = false;
	private static boolean finalChoice = false;
	
	private static String finalPrompt =
			
			"As soon as you catch the barrier flickering, you hurriedly throw yourself into the unknown. The shield is raised again, and a furious sting propagates through your back, but you are through!"
			+ "\nSakaia's menacing countenance towers high above you, alike a mad father questioning your cause. Even his image instills the fright of a thousand ices. You try your best to cover your face."
			+ "\nAt the base of his feet, you stumble upon what it was that the barrier was protecting. A leather bound tome, not too far from Tuvas dishonorable codex, judging only the cover. A large iron lock seals the book shut. Whatever it reads, you feel an immense power radiating from its secret pages, enough to bind even the most ferocious of beasts. Even the massive Knurk?"
			+ "\nYou lift the book to your fathom, carefully dusting it off. The sign of the serpent stares back at you from where it adorns the cover. You should burn the blasted thing for all you care. You even go as far as to light a match, before you are interrupted."
			+ "\n - The Discant Talae, that is."
			+ "\nYou turn around quickly. The cowled, big-nosed figure eyes you promptly, his arms resting peacefully at his sides."
			+ "\n- And you would be unwise to try to destroy it. Those who did before you always fared ill, as it contains truth that was never intended for mortal minds to share. Mere fire will only harden it's pages."
			+ "\nYou notice how more tall, hooded folk are entering behind him, floating in your peripheral vision. Kings, queens and statesmen of ages long forgotten."
			+ "\n- If you are wise, you will hand it over to my safekeeping. Or would you wish this menace to continue among people? Look around you and witness!"
			+ "\nYou hold fast against the figure, firmly grasping the thing. It won't let you go."
			+ "\n- You who study so much. You do recognize who I am, don't you?"

			+ "\n\nDo you wish to burn the book, keep it, or give it away?";
			
	private static String choice1 = 
			"To you, it doesn't seem like anything other than paper."
			+ "\nYou back up from the man, trying your best not to let your intentions show. As he finally realizes, you've already stuck the match in between the pages, and shoved the book down the hall, out of reach."
			+ "\nThe cowled figure barely reacts your actions. His eyes remain fixed, somewhere far away in the distance."
			+ "\n- As I told you."
			+ "\nYou throw a gaze over your shoulder, only to realize that the book is burning without withering. Untouched. Unbroken. How can it be!?"
			+ "\nYou back away, cowering for protection, as you realize the implications of your boldness. Before you know it, your very skin catches fire along with the tome! You hear yourself scream in fright of the devouring flame, twisting and turning about, hopping and rolling on the floor alike a maniac dancing. Everything blackens. None of this can possibly happen. The pain! The fright!"
			+ "\nYou are left on the floor, twitching at the mere brink of death, when the man decides to spare you. With one snatch of a finger, the blaze is over."
			+ "\nBefore you know it, the book is dropped in front of your face, as you lie on the floor. It is completely intact, and the seal has been broken."
			+ "\n- Paragraph seventeen, says the man, inspecting the very flame at the palm of his hand."
			+ "\nYou reluctantly grab the book, knowing you are out of options. Sitting up, you rest the Discant Talae in your lap, as you begin browse it's old, fragile pages."
			+ "\n- What can you make out of it?"
			+ "\nThe scripture is in some forgotten language, though you hear yourself reading it out loud as if it is second nature to you."
			+ "\nThe Stability-In-Growth construct is the polar counterpart of the mortal man. It deludes him into forging artificial bonds, and cultivating these for the amendment of his own individuality. He synthesizes needs, and connects these to betterment of the agglomerate. He revokes the conglomerate, and quantifies. He incorporates individuality. He alters his structure, and intent. When performed en masse, the machine decays, and halts."
			+ "\n- Well there you have it, says the man. The Stability-In-Growth construct is only an agnomen for Thorngeir's meditation. The world trees grow endlessly, and so must we all. The greed it brings is foreboding the end of your kind's existence."
			+ "\nA maniac smile forces it's way out of the man's mouth. He laughs mellowly, to your surprise, and takes the tome out of your wrinkly, old and malformed hands. Your life energy has been all absorbed by the Discant Talae, and all there is left is an old, dying husk."
			+ "\n- Do with this as you will. I do hope your curiosity has been quenced by all this."
			+ "\nWith that, him and his courtage are gone."
			+ "\n\nThe ascent back up through the keep goes with no drama, though you find yourself resting, and supporting yourself on the walls all the more frequently. You are worried you won't last long like this."
			+ "\nAs you finally exit into the wilderness, you notice strange happenings. Myriads of the deceased, that you passed by in those depths so long ago, have convened outside the walls of the keep. Many are sick and malformed, and most are without a sense of direction. The old man's gift to you, you think quietly to yourself. You need only land you staff in the soil to gain their attention. They are in need of guidance, and they know it. You walk silently by, eyeing each one, hearing their pleads. When you head for the wilderness, the mass follows."
			+ "\nThere's much work left to be done, if this world ever will be restored...";
	
	private static String choice2 = 
	
			"You gaze upon the work a long, final time. Is it not much more than mortal words, written by mortal men? You had imagined a divine artifact would have negated your existence by now."
			+ "\nYou remember why you came here now. Slaying Knurk and ending his reign was but an obstacle to your true goal. Imagine what the knowledge that this work holds could benefit you, and your peers. You barely get a chance to think about all the wonderful things you could accomplish with such power. May there be great responsibility with it, but you are ready to sacrifice your time to the greater cause. You are ready to become half the being you are, to go down in history as the benefactor of all. No, this knowledge is for your eyes alone."
			+ "\nThe man stares out into nothingness, holding his arm at his sides. He shows no intention of attack."
			+ "\nYou quickly plan ahead, trying your best not to reveal your intentions. You'd never know with shadowy figure alike this one, he could be a foul mind-trickster."
			+ "\nWithout thinking, you draw your weapon and charge at the man, running as quickly as your legs can bear you. Hour seem to pass by as that very instant unfolds, even though you are the only dynamic element in this predicament. As you dash through the man, him and his courtage are but disappeared. You look around you, but there are no other living beings in sight, weirdly enough. You stand your ground, observing every dark corner of the room, but nothing appears. You carefully make your way out, with the book held firmly under your shield-arm."
			+ "\nThe ascent back up through the keep goes with no drama. The situation only makes you fell the more energized. As you exit through the garden, you are greeted by the new day outside. The traces of the mishappening are still there, clearly, but you are high in spirit, knowing you will soon be out of there."
			+ "\nSoon enough you find your way out, and are on your way south, towards any traces of civilization you think yourself remember from the maps."

			+ "\n\nThe weeks pass on the road, and you are met with few, if any, dilemmas. Soon folk start appearing on the road, and you hear news of the surrounding countryside. What weather will become, what the traders will bring, and so forth. Joyous as ever of your newfound freedom, you settle near a small tradepost, after traveling for months on end, via foot, horse carriage and ferry. The folk there salute you as a welcome member of their commnunity, and all seems to be faring quite well."

			+ "\n\nUntil one gloomy day, when you once again discover the Discant Talae, at the bottom of your footlocker. For all these years you were never able to unfold the lock, neither for picks or with force, and you dared not consult any magician, for fear they might reckon the work and claim it for their own. The predicament nearly gnaws you apart. Why is it that you just can't know? Why can't your mere will and strength access the knowledge that these pages contain?"
			+ "\nSoon enough, your close ones begin to watch you with all the more suspicion in their eyes. People begin talking behind your back, and quieting down every time you enter the scene. Your very skin seems to slowly mutate, giving rise to impurities and blemishes. Eventually folk are downright disgusted by your sighting, and flee whenever you move about outside."
			+ "\nThen they find you one day at your estate, after someone reportedly started smelling a heavy stench of rotten meat from in there. You lay unconsious at your fireplace, having repeatedly knocked your forehead on the lock of the book, and you gained a severe infection in the due time it took for someone to show up at your door. You suffer through, but steadily sink all the more into your new self, as the old, sick and aloof madman at the town outskirts. And worst of all, someone steals the Discant Talae from out of your house, under your fever-induced coma. You are merely rottening away more and more at this point."
			+ "\nThe merchants come and go, until one of the more prominent alloy traders take the back roads through your post. His eldest son, and also the captain of his guard, is a skilled mercenary, and you observe him for quite some time. That is, until the boy decides to make a bold remark on the town weirdo, that leaves the people laughing in your precense. The situation angers you, to such a degree that you start ripping you own hair off your scalp! First, you lose the one thing you have ever cared for in life, and now this! You charge at the boy, ready to strangle his from behind, screaming out loud in your agony! But alas, before you make it, some sort of barrier blocks your access to him."
			+ "\nYou need only watch in delight, as your rage fuels a demonic stampede. Hellspawn rise from the very ground and slaughter you assailants, and everyone around them. They flee, only to be surrounded and cut down. The same foul stench you felt so long ago, now stings horribly in your nose cavities. There is no escape. The dead rack up in the hundreds. The only soul spared is the poor boy, left panting at your feet. Should you have the pleasure for yourself? No, you know better than that. Remorseless, you secretly plot how this boy could aid you in recovering the Discant Talae."
			+ "\nYou finally open your mouth to speak:"
			+ "\n- To Rowntree Keep, the old seat of Kin Hearthfell. That's where were going. If you are wise, you will follow."
			+ "\nAnd so you set out north with your reluctant companion. Rowntree Keep was after all, where this misfortune had begun, all these years ago. Perhaps there, you would find answers...";

	private static String choice3 = "You eye the portentous, cloaked figure for quite some time before you make the decision. You take heed while putting the tome down on the dirty floor panels, and kick it over to where it is within the man's grasp. For a while he observes it where it lays, then proceeds to hoist it up in his fathom."
			+ "\n- Really now, you could have bestowed the world with a lot more trouble had you been on your moody side today."
			+ "\nA chill breeze float by you both from the open hatch you had entered from."
			+ "\n- Not that that is any of my concern. Mere folk can go about their time as they wish. Matters me not. Well, you may go now."
			+ "\nSuddenly he begins to blur out of existence. Still you cannot help but to ponder. If this one really was who you had in mind, then any dealing of any sort ought to be left out. But it was the only bet you had for now. You halt him quickly, right before he performs the teleportation chant, and you plead for him to leave you with something to go on, something more substantial. After all, you are but a husk without identity at this point. Conquering the source of this terror has done nothing to restore your memory, or your spirit."
			+ "\n- Oh, I take it you deserve some kind of reward for your perilous journey."
			+ "\nThe man thinks calmly for a moment, alike an immovable rock is his face."
			+ "\n- Well then. Adorn my garments from hereon..."
			+ "\nHe loosens his cowl, and tosses it at the floor, barley out of reach."
			+ "\n- Be my manifestation here in the plane of folk. For with the Discant Talae recovered, I surely will not have time to wander the world as I always has. I fear the balance will shift, should I not."
			+ "\nYou are not sure how to take this. "
			+ "\n- Fret not, it is only temporary, until I locate a more suitable representative… Well, what do you say?"
			+ "\n\nAre you ready to wear the cloak of a god?"
			
			+ "\nAfter your relentless knocking, a stalwart woman pushes the sprint, and the door slides open."
			+ "\n- We're full tonite mista!"
			+ "\nShe promptly shuts the door and locks it, as you had observed the events from under your protective cowl. You say nothing, for that is the way. You simply turn around and proceed on your way out of the pub yard. One more thing though. As you exit through the gate, you motion your staff around a small portion of the wooden fence, and the sign of the serpent appears burnt into it. Then you are on your way again."
			
			+ "\n\nThe hags sharp voice won't let go of you. But it matters you not, for soon they will all wish with their dying breaths that they had remembered the old traditions."; 
	
	public static void main(String[] args) throws IOException, ParseException
	{
		playerInput = new PlayerInputClass();
		consoleOutput = new ConsoleOutputClass();	

		playerStartPos = new Point(3,2);
		
		endingPos = new Point(14,38);
		
		gameBoard = new Board(playerStartPos);
		
		//cheats
		//gameBoard.player.addGold(1000);
		//gameBoard.player.setMaxHp(20);
		//gameBoard.player.setHp(20);
		//gameBoard.player.setAtk(10);
		//playerStartPos = new Point(27,14);
		
		while (true)
		{
			gameLoop();
			continue;
		}
	}
	
	public static void gameLoop() throws IOException, ParseException
	{
		if (!nameSet) {
			consoleOutput.Print("Welcome to Kingdom of Knurk. Provide a name for your character: ");
			int out = playerInput.readName(gameBoard);
			if (out == 0) {
				return;
			}
			else if (out == 1) {
				nameSet = true;
			}
		}
		
		if (!bossDead) {
			Point bossPos = new Point(14, 25);
			Point finalDoorPos = new Point(14, 32);
			Point finalDoorBelowPos = new Point(14, 31);
			// Is boss dead?
			if (gameBoard.getCell(bossPos).getMonsters().get(0).getDead()) {
				// Remove all monsters
				gameBoard.removeAllMonsters();
				// Open final door
				gameBoard.getCell(finalDoorPos).setWalkableDir(1, true);
				gameBoard.getCell(finalDoorBelowPos).setWalkableDir(0, true);
				bossDead = true;
			}
		}
		
		if (gameBoard.player.getPos().equals(endingPos)) {
			finalChoice = true;
		}
		
		if (finalChoice) {
			consoleOutput.Print(finalPrompt);
			String[] choices = {"burn", "take", "give"};
			String[] results = {choice1, choice2, choice3};
			
			final Scanner input = new Scanner(System.in);
			String inputText = input.nextLine();
			
			if(inputText.contains(choices[0])) { System.out.println(results[0]); System.exit(0);}
			if(inputText.contains(choices[1])) { System.out.println(results[1]); System.exit(0);}
			if(inputText.contains(choices[2])) { System.out.println(results[2]); System.exit(0);}
			
			return;
		}
		
		//Check for stun effects
		
		//Check if player is dead
		if(gameBoard.player.getDead())
		{
			gameBoard.player.setHp(gameBoard.player.getMaxHp());
			gameBoard.player.setPos(playerStartPos);
			gameBoard.player.setActiveWeapon(null);
			gameBoard.player.setActiveArmor(null);
			gameBoard.player.setActiveAmulet(null);
			
			gameBoard.setShopActive(true);
			gameBoard.repopulateMonsters();
			gameBoard.player.setDead(false);
		}
		
		if(gameBoard.getShopActive())
		{
			consoleOutput.printShopInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
		}
		else
		{
			consoleOutput.PrintInfo(gameBoard);			//Change player start pos to player pos "!!!!!!!!!!!!!!!!!!!!!
		}
		
		if (playerInput.ReadLine(gameBoard) == 0)
		{
			System.exit(0);
		}
	}
	
	public static void Debug(String inputText)
	{
		System.out.println(inputText);
	}
}