package spacealert.core.boardElements.battleBots;

public class BattleBot {
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
}
