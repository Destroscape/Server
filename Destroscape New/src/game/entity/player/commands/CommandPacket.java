package game.entity.player.commands;

import game.entity.player.Player;
import game.entity.player.commands.impl.AdministratorCommands;
import game.entity.player.commands.impl.DefaultCommands;
import game.entity.player.commands.impl.DonatorCommands;
import game.entity.player.commands.impl.ModeratorCommands;
import game.entity.player.commands.impl.OwnerCommands;
//import game.entity.player.commands.impl.StaffCommands;

import java.util.ArrayList;
import java.util.List;

import game.net.packets.PacketType;

public class CommandPacket implements PacketType {
	
	private List<CommandParent> commands = new ArrayList<CommandParent>();
	
	public CommandPacket() {
		commands.add(0, new OwnerCommands());
		commands.add(1, new AdministratorCommands());
		commands.add(2, new ModeratorCommands());
		commands.add(3, new DonatorCommands());
		commands.add(4, new DefaultCommands());
	}

    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
    	String command = c.getInStream().readString();
    	
    	switch (c.playerRights) {
    	case 4:
    	case 5:
    	case 6:
    		commands.get(3).handleCommand(c, command);
    	case 3:
    		commands.get(0).handleCommand(c, command);
    	case 2:
    		commands.get(1).handleCommand(c, command);
    	case 1:
    		commands.get(2).handleCommand(c, command);
    	default:
    		commands.get(4).handleCommand(c, command);
    	}
    	
    }
}