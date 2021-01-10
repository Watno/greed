package carnivalOfMonsters.meta.requests;

import carnivalOfMonsters.core.ICanBePlayed;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class PlayFromKeptRequest extends Request {
    @JsonProperty
    private final Collection<ICanBePlayed> cards;

    public PlayFromKeptRequest(Collection<ICanBePlayed> cards) {
        super("PlayFromKeptRequest");
        this.cards = cards;
    }
}
