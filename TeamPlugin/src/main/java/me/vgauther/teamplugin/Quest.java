package me.vgauther.teamplugin;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;

public class Quest {
    private Map<Material, Integer> requiredBlocks;
    private Map<Material, Integer> collectedBlocks;

    public Quest() {
        requiredBlocks = new HashMap<>();
        collectedBlocks = new HashMap<>();

        // Ajouter des blocs requis pour l'exemple
        requiredBlocks.put(Material.DIAMOND, 1);
        requiredBlocks.put(Material.GOLD_BLOCK, 1);
        requiredBlocks.put(Material.IRON_BLOCK, 1);
        requiredBlocks.put(Material.EMERALD_BLOCK, 1);
        requiredBlocks.put(Material.REDSTONE_BLOCK, 1);

        for (Material material : requiredBlocks.keySet()) {
            collectedBlocks.put(material, 0);
        }
    }

    public boolean addCollectedBlock(Material material) {
        if (!requiredBlocks.containsKey(material)) return false;
        collectedBlocks.put(material, collectedBlocks.get(material) + 1);
        return isCompleted();
    }

    public boolean isCompleted() {
        for (Material material : requiredBlocks.keySet()) {
            if (collectedBlocks.get(material) < requiredBlocks.get(material)) {
                return false;
            }
        }
        return true;
    }

    public Map<Material, Integer> getRequiredBlocks() {
        return requiredBlocks;
    }

    public Map<Material, Integer> getCollectedBlocks() {
        return collectedBlocks;
    }
}
