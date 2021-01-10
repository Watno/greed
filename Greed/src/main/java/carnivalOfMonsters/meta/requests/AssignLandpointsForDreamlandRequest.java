package carnivalOfMonsters.meta.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignLandpointsForDreamlandRequest extends Request {
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty
    private final int level;

    public AssignLandpointsForDreamlandRequest(int level) {
        super("AssignLandpointsForDreamlandRequest");
        this.level = level;
    }
}
