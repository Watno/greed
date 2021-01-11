package spacealert.core.boardElements.locations;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.InternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public abstract class Location implements ILocation {
    private Collection<ICrewMember> crewMembers = new ArrayList<>();
    private Collection<InternalThreat> internalThreats = new ArrayList<>();
    private Optional<Position> position;

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
    public Collection<ICrewMember> getCrewMembers() {
        return crewMembers;
    }

    @Override
    public Optional<Position> getPosition() {
        return position;
    }

    @Override
    public void addCrewMember(ICrewMember crewMember) {
        crewMembers.add(crewMember);
    }

    @Override
    public void removeCrewMember(ICrewMember crewMember) {
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
    public void executeButton(Game game, ICrewMember crewMember, Button button) {
        if (internalThreats.stream().anyMatch(x -> x.interceptsButton(button))) {
            internalThreats.stream()
                    .filter(x -> x.interceptsButton(button))
                    .filter(InternalThreat::isSurvived)
                    .min(Comparator.comparing(Threat::getSpawnTurn))
                    .ifPresent(x -> x.interceptButtonPress(game, this));
        } else if (!isSpace()) {
            switch (button) {
                case A:
                    executeAButton(game, crewMember);
                    break;
                case B:
                    executeBButton(game, crewMember);
                    break;
                case C:
                    executeCButton(game, crewMember);
                    break;
            }
        }
    }

    private void executeAButton(Game game, ICrewMember crewMember) {
        //noinspection OptionalGetWithoutIsPresent checked in calling method
        var actualPosition = this.position.get();
        var cannon = game.getCannon(actualPosition);
        if (!cannon.usesEnergy()) {
            cannon.charge();
        } else {
            if (game.getReactor(actualPosition.getZone()).tryWithdrawOneEnergy()) {
                cannon.charge();
            }
        }
    }

    protected abstract void executeBButton(Game game, ICrewMember crewMember);

    protected abstract void executeCButton(Game game, ICrewMember crewMember);
}
