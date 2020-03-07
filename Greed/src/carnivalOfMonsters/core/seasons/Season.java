package carnivalOfMonsters.core.seasons;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.ITriggerOnPlay;
import carnivalOfMonsters.core.Player;

public abstract class Season implements ITriggerOnPlay {
	@Override
	public void trigger(Player player, ICanBePlayed card) {
		player.gainCrowns(2);
	}
	
	public void assign(Collection<Player> players) {
		var playerToAssign = determinePlayerToAssign(players);
		if (playerToAssign.isPresent()) {
			playerToAssign.get().assignTrophy(this);
		}
	}
	
	private Optional<Player> determinePlayerToAssign(Collection<Player> players){
		Comparator<Player> comparator = Comparator.comparing(x -> getCompareValue(x));
		List<Player> orderedPlayers = players.stream()
			.sorted(comparator.reversed())
			.collect(Collectors.toList());
		
		if (getCompareValue(orderedPlayers.get(0)) == getCompareValue(orderedPlayers.get(1))) {
			return Optional.empty();
		}
		
		return Optional.of(orderedPlayers.get(0));
	};
	
	protected abstract int getCompareValue(Player player);

}
