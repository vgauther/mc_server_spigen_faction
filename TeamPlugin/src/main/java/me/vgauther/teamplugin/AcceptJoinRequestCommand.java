package me.vgauther.teamplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptJoinRequestCommand implements CommandExecutor {
    private TeamManager teamManager;

    public AcceptJoinRequestCommand(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage("Usage: /acceptJoinRequest <playerName> <teamName>");
            return true;
        }
        Player player = (Player) sender;
        Player requester = sender.getServer().getPlayer(args[0]);
        String teamName = args[1];

        if (requester == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        if (teamManager.acceptJoinRequest(player, requester, teamName)) {
            player.sendMessage("Player " + requester.getName() + " has been accepted into team " + teamName + ".");
            requester.sendMessage("You have been accepted into team " + teamName + ".");
        } else {
            player.sendMessage("Failed to accept join request. Are you a member of this team?");
        }

        return true;
    }
}
