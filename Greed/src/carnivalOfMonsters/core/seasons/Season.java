package carnivalOfMonsters.core.seasons;

import carnivalOfMonsters.core.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Season extends Card implements ITriggerOnPlay, ICanBeScored {
    protected Season(String name) {
        super("Season", name);
    }

    @Override
    public void trigger(Player player, ICanBePlayed card) {
        player.gainCrowns(2);
    }

    @Override
    public int score(Player player) {
        return 3;
    }

    public void assign(Collection<Player> players) {
        var playerToAssign = determinePlayerToAssign(players);
        playerToAssign.ifPresent(player -> player.assignTrophy(this));
    }

    private Optional<Player> determinePlayerToAssign(Collection<Player> players) {
        Comparator<Player> comparator = Comparator.comparing(this::getCompareValue);
        List<Player> orderedPlayers = players.stream()
                .sorted(comparator.reversed())
                .collect(Collectors.toList());

        if (getCompareValue(orderedPlayers.get(0)) == getCompareValue(orderedPlayers.get(1))) {
            return Optional.empty();
        }

        return Optional.of(orderedPlayers.get(0));
    }

    protected abstract int getCompareValue(Player player);

}
