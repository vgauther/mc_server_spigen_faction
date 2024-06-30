package me.vgauther.teamplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TeamManager {
    private Map<String, Team> teams;
    private Map<Player, Team> playerTeamMap;
    private Map<String, Set<JoinRequest>> joinRequests;

    public TeamManager() {
        this.teams = new HashMap<>();
        this.playerTeamMap = new HashMap<>();
        this.joinRequests = new HashMap<>();
    }

    public boolean createTeam(String teamName, Player leader) {
        if (teams.containsKey(teamName)) {
            return false;
        }
        Team team = new Team(teamName, leader);
        teams.put(teamName, team);
        playerTeamMap.put(leader, team);
        joinRequests.put(teamName, new HashSet<>());
        return true;
    }

    public boolean requestJoinTeam(String teamName, Player player) {
        Team team = teams.get(teamName);
        if (team == null) {
            return false;
        }
        JoinRequest request = new JoinRequest(player, teamName);
        joinRequests.get(teamName).add(request);
        return true;
    }

    public boolean acceptJoinRequest(Player player, Player requester, String teamName) {
        Team team = teams.get(teamName);
        if (team != null && team.isMember(player)) {
            Set<JoinRequest> requests = joinRequests.get(teamName);
            for (JoinRequest request : requests) {
                if (request.getRequester().equals(requester)) {
                    requests.remove(request);
                    team.addMember(requester);
                    playerTeamMap.put(requester, team);
                    return true;
                }
            }
        }
        return false;
    }

    public Team getTeamForPlayer(Player player) {
        return playerTeamMap.get(player);
    }

    public boolean removePlayerFromTeam(Player player) {
        Team team = playerTeamMap.remove(player);
        if (team != null) {
            return team.removeMember(player);
        }
        return false;
    }

    public Set<JoinRequest> getJoinRequestsForTeam(String teamName) {
        return joinRequests.get(teamName);
    }
}
