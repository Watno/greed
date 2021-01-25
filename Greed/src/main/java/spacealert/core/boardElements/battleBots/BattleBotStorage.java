package spacealert.core.boardElements.battleBots;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class BattleBotStorage {
    @JsonProperty
    private Optional<BattleBot> battleBot = Optional.of(new BattleBot());

    public Optional<BattleBot> tryTakeBattleBotFrom() {
        var result = battleBot;
        battleBot = Optional.empty();
        return result;
    }

    public int score() {
        return battleBot.map(BattleBot::score).orElse(0);
    }
}
