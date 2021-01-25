package spacealert.core.boardElements.battleBots;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleBot {
    @JsonProperty
    private boolean active = true;

    public void activate() {
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void disable() {
        active = false;
    }

    public int score() {
        return active ? 0 : -1;
    }
}
