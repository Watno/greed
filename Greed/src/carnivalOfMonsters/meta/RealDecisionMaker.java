package carnivalOfMonsters.meta;


import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.IUserFromGamePerspective;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class RealDecisionMaker implements IDecisionMaker {
    private IUserFromGamePerspective user;
    private ObjectMapper serializer = new ObjectMapper();

    public RealDecisionMaker(IUserFromGamePerspective user) {
        this.user = user;
    }

    @Override
    public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
        //TODO
        return null;
    }

    @Override
    public LandType chooseLandTypeForExplorer() {
        //TODO
        return user.requestTypedInput(serializer.createObjectNode());
    }

    @Override
    public ICard pickCardToDraft(Collection<ICard> draftstack) {
        String selectedName = user.requestTypedInput(serializer.valueToTree(draftstack));
        return findByName(draftstack, selectedName);
    }

    @Override
    public PlayOrKeep choosePlayOrKeep(ICanBePlayed card) {
        return user.requestTypedInput(serializer.valueToTree(card));
    }

    @Override
    public Optional<ICanBePlayed> chooseKeptCardToPlay(Collection<ICanBePlayed> keptCards) {
        Optional<String> selectedName = user.requestTypedInput(serializer.valueToTree(keptCards));
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
