package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.InternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public abstract class Location implements ILocation {
    private final Collection<ICrewMemberFromBoardStatePerspective> crewMembers = new ArrayList<>();
    private final Collection<InternalThreat> internalThreats = new ArrayList<>();
    private final Optional<Position> position;

    Location(Position position) {
        this(Optional.of(position));
    }

    Location(Optional<Position> position) {
        this.position = position;
    }

    @Override
    public boolean isSpace() {
        return position.isEmpty();
    }

    @Override
    public Collection<ICrewMemberFromBoardStatePerspective> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public Optional<Position> getPosition() {
        return position;
    }

    @Override
    public void addCrewMember(ICrewMemberFromBoardStatePerspective crewMember) {
        crewMembers.add(crewMember);
    }

    @Override
    public void removeCrewMember(ICrewMemberFromBoardStatePerspective crewMember) {
        var wasPresent = crewMembers.remove(crewMember);
        if (!wasPresent) {
            throw new IllegalStateException("expected CrewMember is not here.");
        }
    }


    @Override
    public void addInternalThreat(InternalThreat threat) {
        internalThreats.add(threat);
    }

    @Override
    public void removeInternalThreat(InternalThreat threat) {
        var wasPresent = internalThreats.remove(threat);
        if (!wasPresent) {
            throw new IllegalStateException("expected Threat is not here.");
        }
    }

    @Override
    public Collection<InternalThreat> getInternalThreats() {
        return internalThreats;
    }

    public Optional<Zone> getZone() {
        return position.map(Position::getZone);
    }

    @Override
    public void executeButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember, Button button) {
        if (internalThreats.stream().anyMatch(x -> x.interceptsButton(button))) {
            internalThreats.stream()
                    .filter(x -> x.interceptsButton(button))
                    .filter(InternalThreat::isSurvived)
                    .min(Comparator.comparing(Threat::getSpawnTurn))
                    .ifPresent(x -> x.interceptButtonPress(boardState, this));
        } else if (!isSpace()) {
            switch (button) {
                case A -> executeAButton(boardState, crewMember);
                case B -> executeBButton(boardState, crewMember);
                case C -> executeCButton(boardState, crewMember);
            }
        }
    }

    private void executeAButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        //noinspection OptionalGetWithoutIsPresent checked in calling method
        var actualPosition = this.position.get();
        var cannon = boardState.getCannon(actualPosition);
        if (!cannon.usesEnergy()) {
            cannon.charge();
        } else {
            if (boardState.getReactor(actualPosition.getZone()).tryWithdrawOneEnergy()) {
                cannon.charge();
            }
        }
    }

    protected abstract void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember);

    protected abstract void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember);
}
