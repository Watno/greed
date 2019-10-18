package spacealert.core.boardElements.battleBots;

public class BattleBot {
    private boolean active;

    public void activate() {
        active = true;
    }

    public boolean isActive() {
        return active;
    }
}
