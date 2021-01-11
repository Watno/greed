package spacealert.core.boardElements;

import spacealert.core.ICrewMember;

public class Gravolift implements IDamageable {
    private boolean isDamaged = false;
    private boolean wasUsed = false;

    private boolean causesDelay() {
        return isDamaged || wasUsed;
    }

    public void getUsedBy(ICrewMember crewMember) {
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
