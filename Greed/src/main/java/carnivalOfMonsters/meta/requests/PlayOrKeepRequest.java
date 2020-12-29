package carnivalOfMonsters.meta.requests;

import carnivalOfMonsters.core.ICard;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayOrKeepRequest extends Request {
    @JsonProperty
    private ICard card;

    public PlayOrKeepRequest(ICard card) {
        super("PlayOrKeepRequest");
        this.card = card;
    }
}
