package me.vgauther.teamplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateTeamCommand implements CommandExecutor {
    private TeamManager teamManager;
    private QuestManager questManager;

    public CreateTeamCommand(TeamManager teamManager, QuestManager questManager) {
        this.teamManager = teamManager;
        this.questManager = questManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can create teams.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /createTeam <teamName>");
            return true;
        }
        Player player = (Player) sender;
        String teamName = args[0];

        if (teamManager.createTeam(teamName, player)) {
            questManager.createQuestForTeam(teamName); // Create quest for the team
            player.sendMessage("Team " + teamName + " created and you are the leader!");
        } else {
            player.sendMessage("A team with that name already exists.");
        }
        return true;
    }
}
