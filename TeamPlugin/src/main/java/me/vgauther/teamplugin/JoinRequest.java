package me.vgauther.teamplugin;

import org.bukkit.entity.Player;

public class JoinRequest {
    private Player requester;
    private String teamName;

    public JoinRequest(Player requester, String teamName) {
        this.requester = requester;
        this.teamName = teamName;
    }

    public Player getRequester() {
        return requester;
    }

    public String getTeamName() {
        return teamName;
    }
}
