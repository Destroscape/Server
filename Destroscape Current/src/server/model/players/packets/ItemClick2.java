package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		
		if (!c.getItems().playerHasItem(itemId,1))
			return;

		switch (itemId) {
		case 11256:
            c.givereward();
			c.sendMessage("<col=1532693>You loot the impling jar!</col>");
            break;
			
		case 11238:
        c.givereward1();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        
		return;
		case 11240:
        c.givereward2();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        
		return;
		case 11242:
         c.givereward3();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        
		return;
		case 11244:
c.givereward4();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        
		return;
		case 11246:
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        c.givereward5();
		return;
		case 11248:
		c.givereward6();
        c.sendMessage("<col=1532693>You loot the impling jar!</col>");
		return;
		case 11250:
c.givereward7();
        c.sendMessage("<col=1532693>You loot the impling jar!</col>");
		return;
		case 11252:
c.givereward8();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
        
		return;
		case 11254:
c.givereward9();
            c.sendMessage("<col=1532693>You loot the impling jar!</col>");
		return;
			case 11283:
			case 11284:
			case 11285:
			c.sendMessage("Your shield has "+c.dfsCount+" charges");
			break;

			case 11694:

				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11696:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11698:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11700:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId);
			break;
		}

	}

}
