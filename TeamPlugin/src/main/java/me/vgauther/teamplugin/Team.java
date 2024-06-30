package me.vgauther.teamplugin;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private String name;
    private Player leader;
    private Set<Player> members;

    public Team(String name, Player leader) {
        this.name = name;
        this.leader = leader;
        this.members = new HashSet<>();
        this.members.add(leader);
    }

    public String getName() {
        return name;
    }

    public Player getLeader() {
        return leader;
    }

    public boolean isLeader(Player player) {
        return player.equals(leader);
    }

    public boolean addMember(Player player) {
        return members.add(player);
    }

    public boolean removeMember(Player player) {
        return members.remove(player);
    }

    public boolean isMember(Player player) {
        return members.contains(player);
    }

    public Set<Player> getMembers() {
        return members;
    }
}
