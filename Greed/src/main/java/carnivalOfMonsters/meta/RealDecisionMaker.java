package carnivalOfMonsters.meta;


import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;
import carnivalOfMonsters.meta.requests.*;
import com.fasterxml.jackson.core.type.TypeReference;
import server.games.DisconnectedException;
import server.games.IUserFromGamePerspective;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class RealDecisionMaker implements IDecisionMaker {
    private final IUserFromGamePerspective user;
    private final String name;
    private Optional<IDecisionMaker> replacement = Optional.empty();
    private GameStateWithPrivateInfo latestGameState;

    public RealDecisionMaker(IUserFromGamePerspective user) {
        this.user = user;
        this.name = user.getName();
    }

    @Override
    public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
        if (replacement.isPresent()) return replacement.get().assignLandpoints(requiredLandpoints);
        try {
            return requestTypedInput(new AssignLandpointsForDreamlandRequest(requiredLandpoints), new TypeReference<>() {});
        } catch (DisconnectedException e) {
            replaceWithBot();
            return assignLandpoints(requiredLandpoints);
        }
    }

    @Override
    public LandType chooseLandTypeForExplorer() {
        if (replacement.isPresent()) return replacement.get().chooseLandTypeForExplorer();
        try {
            return requestTypedInput(new LandtypeForExplorerRequest(), new TypeReference<>() {});
        } catch (DisconnectedException e) {
            replaceWithBot();
            return chooseLandTypeForExplorer();
        }
    }

    @Override
    public ICard pickCardToDraft(Collection<ICard> draftstack) {
        if (replacement.isPresent()) return replacement.get().pickCardToDraft(draftstack);
        try {
            String selectedName = requestTypedInput(new DraftRequest(draftstack), new TypeReference<>() {});
            return findByName(draftstack, selectedName);
        } catch (DisconnectedException e) {
            replaceWithBot();
            return pickCardToDraft(draftstack);
        }
    }

    @Override
    public PlayOrKeep choosePlayOrKeep(ICanBePlayed card) {
        if (replacement.isPresent()) return replacement.get().choosePlayOrKeep(card);
        try {
            return requestTypedInput(new PlayOrKeepRequest(card), new TypeReference<>() {});
        } catch (DisconnectedException e) {
            replaceWithBot();
            return choosePlayOrKeep(card);
        }
    }

    @Override
    public Optional<ICanBePlayed> chooseKeptCardToPlay(Collection<ICanBePlayed> keptCards) {
        if (replacement.isPresent()) return replacement.get().chooseKeptCardToPlay(keptCards);
        try {
            Optional<String> selectedName = requestTypedInput(new PlayFromKeptRequest(keptCards), new TypeReference<>() {
            });
            return selectedName.map(s -> findByName(keptCards, s));
        } catch (DisconnectedException e) {
            replaceWithBot();
            return chooseKeptCardToPlay(keptCards);
        }
    }

    @Override
    public void sendGameState(GameStateWithPrivateInfo gameState) {
        this.latestGameState = gameState;
        replacement.ifPresent(x -> x.sendGameState(gameState));
        user.send(gameState);
    }

    private <T extends ICard> T findByName(Collection<T> cards, String name) {
        //noinspection OptionalGetWithoutIsPresent
        return cards.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    private <T> T requestTypedInput(Object request, TypeReference<T> requestedType) throws DisconnectedException {
        try {
            return user.requestTypedInput(request, requestedType);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void replaceWithBot() {
        replacement = Optional.of(new MockDecisionMaker(name));
        replacement.get().sendGameState(latestGameState);
    }
}
