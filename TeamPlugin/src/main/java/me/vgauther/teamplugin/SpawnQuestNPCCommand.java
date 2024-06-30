package me.vgauther.teamplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;

public class SpawnQuestNPCCommand implements CommandExecutor {
    private final TeamPlugin plugin;

    public SpawnQuestNPCCommand(TeamPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        BlockIterator iterator = new BlockIterator(player, 5);
        Location blockLocation = null;

        while (iterator.hasNext()) {
            blockLocation = iterator.next().getLocation();
            if (blockLocation.getBlock().getType().isSolid()) {
                blockLocation.add(0, 1, 0); // Make the NPC spawn above the solid block
                break;
            }
        }

        if (blockLocation != null) {
            Villager npc = blockLocation.getWorld().spawn(blockLocation, Villager.class);
            npc.setCustomName("Quest Giver");
            npc.setCustomNameVisible(true);
            npc.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Integer.MAX_VALUE, 10));
        } else {
            player.sendMessage("No suitable block found.");
        }
        return true;
    }
}
