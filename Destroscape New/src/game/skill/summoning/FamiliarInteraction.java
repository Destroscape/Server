package game.skill.summoning;

import game.entity.player.Player;

public class FamiliarInteraction {

	public static void interactWithFamiliar(Player c) {
		String[] chat = { "Null" };
		String name = "Null";
		for (Summoning.Familiar f : Summoning.Familiar.values()) {
			if (f.getNPCID() == c.familiarID)
				name = f.getFamiliarName();
		}
		switch (c.familiarID) {
		case 6829: // Spirit wolf
			chat = new String[] { "What are you doing?", "Danger!",
					"I smell something good! Hunting time!" };
			break;

		case 6825: // Dreadfowl
			chat = new String[] { "Attack! Fight! Annihilate!",
					"Can it be fightin' time, please?",
					"I want to fight something." };
			break;

		case 6841: // Spirit spider
			chat = new String[] { "When can I lay my eggs?",
					"Where are we going?", "Why are you not responding to me?" };
			break;

		case 6806: // Thorny snail
			chat = new String[] { "All this running around the place is fun!",
					"Okay, I have to ask, what are those things you people totter about on?" };
			break;

		case 6796: // Granite crab
			chat = new String[] { "Can I have some fish?",
					"Rock fish now, please?",
					"When can we go fishing? I want rock fish." };
			break;

		case 7331: // Spirit mosquito
			chat = new String[] { "You have lovely ankles.",
					"Have you ever tasted pirate blood?", "I'm soooo hungry!" };
			break;

		case 6831: // Desert wyrm
			chat = new String[] {
					"This is so unsafe...I should have a hard hat for this work...",
					"You can't touch me, I'm part of the union!",
					"If you have that pick, why make me dig?" };
			break;

		case 6837: // Spirit scorpion
			chat = new String[] { "Hey, boss, how about we go to the bank?",
					"Say hello to my little friend!" };
			break;

		case 7361: // Spirit tz-kih
			chat = new String[] { "Hmph, silly JalYt.",
					"Have you heard of blood bat, JalYt?" };
			break;

		case 6847: // Albino rat
			chat = new String[] {
					"Hey boss, we going to do anything wicked today?",
					" You know, boss, I don't think you're totally into this whole 'evil' thing.",
					"Hey boss, can we go and loot something now?" };
			break;

		case 6994: // Spirit kalphite
			chat = new String[] { "This activity is not optimal for us.",
					"We are growing infuriated. What is our goal?" };
			break;

		case 6871: // Compost mound
			chat = new String[] { "Oi wus just a-wonderin'...",
					"*Errr...are ye gonna eat that?",
					"Oi've gotta braand new comboine 'aarvester!" };
			break;

		case 7353:
			chat = new String[] {
					"Half a pound of tuppenny rice, half a pound of treacle...",
					"I seem to have found a paper bag.",
					"What's small, brown and blows up?" };
			break;

		case 6835:
			chat = new String[] {
					"You're vasting all that blood, can I have some?",
					"Ven are you going to feed me?" };
			break;

		case 6845:
			chat = new String[] { "*An outpouring of sanity-straining abuse*",
					"*A lambasting of visibly illustrated obscenities.*" };
			break;

		case 6808:
			chat = new String[] {
					"Vot are you doing 'ere when we could be logging and building mighty dams, alors?",
					"Pardonnez-moi - you call yourself a lumberjack?" };
			break;

		case 7370:
		case 7367:
		case 7351:
		case 7333:
			chat = new String[] { "Let's go play hide an' seek!",
					"I'm coming to tickle you!" };
			break;

		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
			chat = new String[] { "All this walking about is making me angry.",
					"Can you tell me why we're not fighting yet?",
					"Hey no-horns!" };
			break;

		case 6867:
			chat = new String[] { "All right you worthless biped, fall in!",
					"What's the matter, Private? Not enjoying the run?" };
			break;

		case 6851:
			chat = new String[] { "Awk! Gimme the rum! Gimme the rum!",
					"Awk! I'm a pirate! Awk! Yo, ho ho!" };
			break;

		case 6833:
			chat = new String[] {
					"My roots feel hurty. I thinking it be someone I eated.",
					"Hur hur hur...", "When we gonna fighting things, boss?" };
			break;

		case 6875:
		case 6877:
		case 6879:
		case 6881:
		case 6883:
		case 6885:
		case 6887:
			chat = new String[] { "Where's my brothers?", "Where's my sisters." };
			break;

		case 7377:
			chat = new String[] { "I used to be feared across five planes.",
					"I can teach you to smite your enemies with flames." };
			break;

		case 6824:
			chat = new String[] {
					"Howway, let's gaan see what's happenin' in toon.",
					"Are we gaan oot soon? I'm up fer a good walk me." };
			break;

		case 6843:
			chat = new String[] { "I'm afraid it's going to have to come off.",
					"Let's get a look at that brain of yours." };
			break;

		case 6794:
			chat = new String[] { "This is a fun little walk.",
					"Can we go to a bank now?", "I can keep this up for hours." };
			break;

		case 6818:
		case 6820:
		case 7349:
			chat = new String[] { "Ongk n'hd?", "Noslr'rh..." };
			break;

		case 6992:
			chat = new String[] { "Play play play play!",
					"Can we go over there now, pleasepleasepleasepleeeeeease?" };
			break;

		case 6991:
			chat = new String[] { "I'm the best fisherman ever!",
					"Can I look after those sharks for you?" };
			break;

		case 7363:
		case 7365:
		case 7337:
			chat = new String[] { "Hi!",
					"Your spikes are looking particularly spiky today" };
			break;

		case 6809:
			chat = new String[] { "SIIIIIILLLLLEEEEENCE!",
					"Kneel before my awesome might!" };
			break;

		case 6865:
			chat = new String[] { "When are you going to be done with that?",
					"Ah, this is the life!" };
			break;

		case 6802:
			chat = new String[] { "Do we have to do thissss right now?",
					"I'm bored, do ssssomething to entertain me..." };
			break;

		case 6827:
			chat = new String[] { "I'M STRANGER PLANT!",
					"WILL WE HAVE TO BE HERE LONG?", "I THINK I'M WILTING!" };
			break;

		case 6889:
			chat = new String[] {
					"Ladies and gentlemen, for my next trick, I shall swallow this fly!",
					"Roll up, roll up, roll up! See the greatest show on Gielinor!" };
			break;

		case 6815:
			chat = new String[] { "Hssss! Hold up a minute, there.",
					"Hssss? What are we doing in this dump?" };
			break;

		case 6813:
			chat = new String[] {
					"Where are we going and why is it not to the beach?",
					"Pass me another bunch of shrimps, mate!" };
			break;

		case 6817:
			chat = new String[] { "Squeeksqueekasqueek?", "Squeek?" };
			break;

		case 7372:
			chat = new String[] { "Bake pies, not war!", "*Chirp*" };
			break;

		case 6839:
			chat = new String[] { "Seems a bit cold master!",
					"I eat my children." };
			break;

		case 8575:
			chat = new String[] { "HOT!", "FIRE! Oh wait, that's me. HAHA!" };
			break;

		case 7345:
			chat = new String[] {
					"Let us go forth and prove our strength, Master!",
					"How many foes have you defeated, Master?" };
			break;

		case 6849:
			chat = new String[] {
					"We shall heap the helmets of the fallen into a mountain!",
					"Glory and plunder for all!" };
			break;

		case 6798:
			chat = new String[] { "*Cricket* Oh Wait!", "I am your new master!" };
			break;

		case 7335:
			chat = new String[] { "I'm burning up." };
			break;

		case 7347:
			chat = new String[] { "Is this all you apes do all day, then?",
					"This place smells odd…" };
			break;

		case 6800:
			chat = new String[] { "Groooooooooan.....",
					"Groooooooooooooooooan....." };
			break;

		case 7355:
		case 7357:
		case 7359:
			chat = new String[] { "Pick flax.", "WHERE ARE MY BROTHERS!" };
			break;

		case 6811:
			chat = new String[] { "Raaaspraaasp?", "Raaasp raaaaap raaaasp?" };
			break;

		case 6804:
			chat = new String[] { "Look at my spikes!",
					"Why aren't you looking at my spikes!" };
			break;

		case 7341:
			chat = new String[] { "I am the titan of Lava!",
					"Rocks are what I form!" };
			break;

		case 7329:
			chat = new String[] { "I’m alone, all alone I say.",
					"Oh, Guthix! I’m so alone, I have no friends!" };
			break;

		case 6822:
			chat = new String[] { "You're hurt! Let me try to heal you.",
					"I think I'm astrally projecting." };
			break;

		case 7339:
			chat = new String[] {
					"Did you know a snail can sleep up to three years?",
					"Hey mate, how are you?" };
			break;

		case 6869:
			chat = new String[] { "*sniffle*", "*squeak*" };
			break;

		case 7375:
			chat = new String[] { "My brother was always one better than me.",
					"I cannot wait until I am honored as a steel titan!" };
			break;

		case 6873: // Pack yak
			chat = new String[] { "What do you need me to carry master?",
					"Ugh. My pack is too heavy!" };
			break;

		case 7343:
			chat = new String[] {
					"Forward, master, to a battle that will waken the gods!",
					"How do you wish to meet your end, master?" };
			break;
		}
		if (chat.equals("Null") || name.equals("Null") || chat == null
				|| name == null)
			return;
		c.nextChat = 512;
		sendFamiliarChat(c, chat, name);
	}

	private static void sendFamiliarChat(Player c, String[] message,
			String familiarName) {
		c.getDH().sendNpcChat3("",
				message[(int) (Math.random() * message.length)], "",
				c.familiarID, familiarName);
	}
}