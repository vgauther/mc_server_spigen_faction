package me.vgauther.teamplugin;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Quest {
    private Map<Material, Integer> requiredBlocks;
    private Map<Material, Integer> collectedBlocks;

    public Quest() {
        this.requiredBlocks = requiredBlocks;
        this.collectedBlocks = new HashMap<>();
        for (Material material : requiredBlocks.keySet()) {
            collectedBlocks.put(material, 0);
        }
    }

    public Map<Material, Integer> getRequiredBlocks() {
        return requiredBlocks;
    }

    public Map<Material, Integer> getCollectedBlocks() {
        return collectedBlocks;
    }

    public boolean addCollectedBlock(Material material) {
        if (requiredBlocks.containsKey(material)) {
            int required = requiredBlocks.get(material);
            int collected = collectedBlocks.get(material);
            if (collected < required) {
                collectedBlocks.put(material, collected + 1);
                return true;
            }
        }
        return false;
    }

    public boolean isCompleted() {
        for (Material material : requiredBlocks.keySet()) {
            if (collectedBlocks.get(material) < requiredBlocks.get(material)) {
                return false;
            }
        }
        return true;
    }
}
