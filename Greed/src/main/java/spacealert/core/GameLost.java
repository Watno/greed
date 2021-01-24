package spacealert.core;

public enum GameLost {
    TRUE, FALSE;

    public GameLost then(GameLost other) {
        if (this == GameLost.TRUE || other == GameLost.TRUE) {
            return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
