package me.vgauther.teamplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinTeamCommand implements CommandExecutor {
    private TeamManager teamManager;

    public JoinTeamCommand(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /joinTeam <teamName>");
            return true;
        }
        Player player = (Player) sender;
        String teamName = args[0];

        if (teamManager.requestJoinTeam(teamName, player)) {
            player.sendMessage("Join request sent to team " + teamName + ". Wait for approval.");
        } else {
            player.sendMessage("Team " + teamName + " does not exist.");
        }
        return true;
    }
}
