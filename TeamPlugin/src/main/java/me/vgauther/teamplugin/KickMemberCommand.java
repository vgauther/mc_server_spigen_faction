package me.vgauther.teamplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickMemberCommand implements CommandExecutor {
    private TeamManager teamManager;

    public KickMemberCommand(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /kickMember <playerName>");
            return true;
        }
        Player player = (Player) sender;
        Player target = sender.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("Player not found.");
            return true;
        }

        Team team = teamManager.getTeamForPlayer(player);
        if (team == null) {
            player.sendMessage("You are not in any team.");
            return true;
        }

        if (!team.isLeader(player)) {
            player.sendMessage("Only the team leader can kick members.");
            return true;
        }

        if (!team.isMember(target)) {
            player.sendMessage("Player is not in your team.");
            return true;
        }

        if (team.removeMember(target)) {
            player.sendMessage("Player " + target.getName() + " has been kicked from the team.");
            target.sendMessage("You have been kicked from the team " + team.getName() + ".");
        } else {
            player.sendMessage("Failed to kick the player.");
        }

        return true;
    }
}
