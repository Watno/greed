package carnivalOfMonsters.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Card implements ICard {
    @JsonProperty
    private final String name;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty
    private final String type;

    protected Card(String type, String name) {
        super();
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }
}
