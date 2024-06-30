package me.vgauther.teamplugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TeamManager {
    private Map<String, Set<Player>> teams;

    public TeamManager() {
        teams = new HashMap<>();
    }

    public boolean createTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            return false; // L'équipe existe déjà
        }
        teams.put(teamName, new HashSet<>());
        return true;
    }

    public boolean joinTeam(String teamName, Player player) {
        Set<Player> team = teams.get(teamName);
        if (team == null) {
            return false; // L'équipe n'existe pas
        }
        team.add(player);
        return true;
    }

    public Map<String, Set<Player>> getTeams() {
        return teams;
    }

    public String getTeamForPlayer(Player player) {
        for (Map.Entry<String, Set<Player>> entry : teams.entrySet()) {
            if (entry.getValue().contains(player)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
