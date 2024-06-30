package me.vgauther.teamplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TeamPlugin extends JavaPlugin {
    private TeamManager teamManager;
    private QuestManager questManager;

    @Override
    public void onEnable() {
        teamManager = new TeamManager();
        questManager = new QuestManager();
        this.getCommand("createTeam").setExecutor(new CreateTeamCommand(teamManager, questManager));
        this.getCommand("joinTeam").setExecutor(new JoinTeamCommand(teamManager));
        this.getCommand("spawnQuestNPC").setExecutor(new SpawnQuestNPCCommand(this));
        this.getCommand("checkTeam").setExecutor(new CheckTeamCommand(teamManager));
        this.getCommand("kickMember").setExecutor(new KickMemberCommand(teamManager));
        this.getCommand("acceptJoinRequest").setExecutor(new AcceptJoinRequestCommand(teamManager));
        getServer().getPluginManager().registerEvents(new NPCInteractListener(questManager, teamManager), this);
        getLogger().info("TeamPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TeamPlugin has been disabled.");
    }
}
