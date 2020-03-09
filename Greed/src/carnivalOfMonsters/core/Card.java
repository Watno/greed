package carnivalOfMonsters.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Card implements ICard {
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;

    protected Card(String type, String name) {
        super();
        this.name = name;
        this.type = type;
    }

}
