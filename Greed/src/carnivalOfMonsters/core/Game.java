package carnivalOfMonsters.core;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import carnivalOfMonsters.core.lands.BasicLand;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.lands.Dreamlands;
import carnivalOfMonsters.core.lands.Level2Land;
import carnivalOfMonsters.core.lands.Level3Land;
import carnivalOfMonsters.core.monsters.Level1Danger1Monster;
import carnivalOfMonsters.core.monsters.Level1Lore1Monster;
import carnivalOfMonsters.core.monsters.Level1Monster;
import carnivalOfMonsters.core.monsters.Level2Danger1Monster;
import carnivalOfMonsters.core.monsters.Level2Monster;
import carnivalOfMonsters.core.monsters.Level3Danger1Monster;
import carnivalOfMonsters.core.monsters.Level4Danger2Monster;
import carnivalOfMonsters.core.monsters.OuterRealmSpider;
import carnivalOfMonsters.core.monsters.Screecher;
import carnivalOfMonsters.core.monsters.Succubus;
import carnivalOfMonsters.core.monsters.TheAncientEnemy;

public class Game {
	
	private Stack<ICard> drawPile;
	
	public Collection<ICard> draw(int numberOfCards) {
		return Stream.generate(() -> drawPile.pop())
				.limit(Integer.min(numberOfCards, drawPile.size()))
				.collect(Collectors.toList());
	}
	
	private void createDrawPile() {
		drawPile = new Stack<ICard>();
		
		drawPile.addAll(
			//TODO deck composition
			Stream.of(
			
				//lands
				Stream.of(LandType.values())
					.map(x -> createLands(x))
					.flatMap(x -> x),
				
				//monsters
					Stream.of(LandType.values())
					.map(x -> createMonsters(x))
					.flatMap(x -> x)
			)
			.flatMap(x -> x)
			.collect(Collectors.toList())
		);
		
		Collections.shuffle(drawPile);
	}

	private Stream<ICard> createLands(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream
					.generate(() ->  new Dreamlands())
					.limit(12)
					.map(x -> x);
		}
		
		else {
			return Stream.of(
						Stream
							.generate(() -> new BasicNormalLand(landType))
							.limit(8),
						Stream
							.generate(() -> new Level2Land(landType))
							.limit(4),
						Stream
							.generate(() -> new Level3Land(landType))
							.limit(2)
					)
					.flatMap(x -> x);
					
		}
	}
	
	private Stream<ICard> createMonsters(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of(
					new OuterRealmSpider(),
					new Screecher(),
					new Succubus(),
					new TheAncientEnemy()
					);
		}
		
		else {
			return Stream.of(
						Stream
							.generate(() -> new Level1Monster(landType))
							.limit(3),
						Stream.of(
								new Level1Danger1Monster(landType),
								new Level1Lore1Monster(landType),
								new Level2Monster(landType),
								new Level2Danger1Monster(landType),
								new Level3Danger1Monster(landType),
								new Level4Danger2Monster(landType)
					))
					.flatMap(x -> x);
					
		}
	}

}
