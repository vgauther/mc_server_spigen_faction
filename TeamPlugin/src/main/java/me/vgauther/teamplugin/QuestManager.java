package me.vgauther.teamplugin;

import java.util.HashMap;
import java.util.Map;

public class QuestManager {
    private Map<String, Quest> quests;

    public QuestManager() {
        quests = new HashMap<>();
    }

    public void createQuestForTeam(String teamName) {
        Quest quest = new Quest();
        quests.put(teamName, quest);
    }

    public Quest getQuestForTeam(String teamName) {
        return quests.get(teamName);
    }
}
