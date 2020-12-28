package carnivalOfMonsters.meta;


import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;
import carnivalOfMonsters.meta.requests.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import server.games.IUserFromGamePerspective;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class RealDecisionMaker implements IDecisionMaker {
    private IUserFromGamePerspective user;
    private ObjectMapper serializer = new ObjectMapper();

    public RealDecisionMaker(IUserFromGamePerspective user) {
        this.user = user;
        serializer.registerModule(new Jdk8Module());
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
    }

    @Override
    public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
        return user.requestTypedInput(serializer.valueToTree(new AssignLandpointsForDreamlandRequest(requiredLandpoints)), new TypeReference<>() {
        });
    }

    @Override
    public LandType chooseLandTypeForExplorer() {
        return user.requestTypedInput(serializer.valueToTree(new LandtypeForExplorerRequest()), new TypeReference<>() {
        });
    }

    @Override
    public ICard pickCardToDraft(Collection<ICard> draftstack) {
        String selectedName = user.requestTypedInput(serializer.valueToTree(new DraftRequest(draftstack)), new TypeReference<>() {
        });
        return findByName(draftstack, selectedName);
    }

    @Override
    public PlayOrKeep choosePlayOrKeep(ICanBePlayed card) {
        return user.requestTypedInput(serializer.valueToTree(new PlayOrKeepRequest(card)), new TypeReference<>() {
        });
    }

    @Override
    public Optional<ICanBePlayed> chooseKeptCardToPlay(Collection<ICanBePlayed> keptCards) {
        Optional<String> selectedName = user.requestTypedInput(serializer.valueToTree(new PlayFromKeptRequest(keptCards)), new TypeReference<>() {
        });
        if (!selectedName.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(findByName(keptCards, selectedName.get()));
    }

    @Override
    public void sendGameState(GameStateWithPrivateInfo gameState) {
        user.send(serializer.valueToTree(gameState));
    }

    private <T extends ICard> T findByName(Collection<T> cards, String name) {
        return cards.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }
}
