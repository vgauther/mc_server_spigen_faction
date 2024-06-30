package me.vgauther.teamplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCInteractListener implements Listener {
    private QuestManager questManager;
    private TeamManager teamManager;

    public NPCInteractListener(QuestManager questManager, TeamManager teamManager) {
        this.questManager = questManager;
        this.teamManager = teamManager;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            Villager villager = (Villager) event.getRightClicked();
            if ("Gérard".equals(villager.getCustomName())) {
                Player player = event.getPlayer();
                Inventory inventory = Bukkit.createInventory(player, 27, "Quest Items");
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if ("Quest Items".equals(event.getView().getTitle())) {
            Player player = (Player) event.getPlayer();
            Inventory inventory = event.getInventory();
            String teamName = teamManager.getTeamForPlayer(player);
            if (teamName != null) {
                Quest quest = questManager.getQuestForTeam(teamName);
                if (quest != null) {
                    for (ItemStack item : inventory.getContents()) {
                        if (item != null) {
                            if (quest.addCollectedBlock(item.getType())) {
                                item.setAmount(0); // Clear item stack if added to quest
                            } else {
                                // Return items that are not needed or are excess
                                player.getInventory().addItem(item);
                            }
                        }
                    }
                    player.sendMessage(getQuestStatusMessage(quest));
                }
            }
        }
    }

    private String getQuestStatusMessage(Quest quest) {
        StringBuilder message = new StringBuilder("Blocks still needed:\n");
        for (Material material : quest.getRequiredBlocks().keySet()) {
            int required = quest.getRequiredBlocks().get(material);
            int collected = quest.getCollectedBlocks().get(material);
            if (collected < required) {
                message.append(material.name()).append(": ").append(required - collected).append("\n");
            }
        }
        return message.toString();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Villager) {
            Villager villager = (Villager) event.getEntity();
            if ("Gérard".equals(villager.getCustomName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Villager) {
            Villager villager = (Villager) event.getEntity();
            if ("Gérard".equals(villager.getCustomName())) {
                event.setCancelled(true);
            }
        }
    }
}
