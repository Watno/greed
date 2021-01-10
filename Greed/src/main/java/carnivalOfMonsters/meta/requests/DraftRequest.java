package carnivalOfMonsters.meta.requests;

import carnivalOfMonsters.core.ICard;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class DraftRequest extends Request {
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty
    private final Collection<ICard> cards;

    public DraftRequest(Collection<ICard> cards) {
        super("DraftRequest");
        this.cards = cards;
    }
}
