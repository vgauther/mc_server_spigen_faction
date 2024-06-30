package me.vgauther.teamplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckTeamCommand implements CommandExecutor {
    private final TeamManager teamManager;

    public CheckTeamCommand(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        String teamName = teamManager.getTeamForPlayer(player);

        if (teamName != null) {
            player.sendMessage("You are in team: " + teamName);
        } else {
            player.sendMessage("You are not in any team.");
        }

        return true;
    }
}

