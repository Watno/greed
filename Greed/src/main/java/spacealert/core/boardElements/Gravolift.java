package spacealert.core.boardElements;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.ICrewMemberFromBoardStatePerspective;

public class Gravolift implements IDamageable {
    @JsonProperty
    private boolean isDamaged = false;
    private boolean wasUsed = false;

    private boolean causesDelay() {
        return isDamaged || wasUsed;
    }

    public void getUsedBy(ICrewMemberFromBoardStatePerspective crewMember) {
        if (causesDelay()) {
            crewMember.delay();
        }
        wasUsed = true;
    }

    public void resetUsage() {
        wasUsed = false;
    }

    @Override
    public void damage() {
        isDamaged = true;
    }
}
