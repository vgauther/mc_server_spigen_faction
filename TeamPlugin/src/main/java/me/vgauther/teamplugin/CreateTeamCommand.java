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
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("Usage: /createTeam <teamName>");
            return false;
        }

        String teamName = args[0];
        if (teamManager.createTeam(teamName)) {
            questManager.createQuestForTeam(teamName);
            sender.sendMessage("Team " + teamName + " created successfully with a new quest.");
        } else {
            sender.sendMessage("Team " + teamName + " already exists.");
        }
        return true;
    }
}
