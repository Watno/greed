package carnivalOfMonsters.core;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import carnivalOfMonsters.core.events.Gain2Crowns;
import carnivalOfMonsters.core.events.GainHunterToken;
import carnivalOfMonsters.core.events.GainPerLandPoint;
import carnivalOfMonsters.core.events.RevealKeptMonsters;
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
import carnivalOfMonsters.core.seasons.DangerSeason;
import carnivalOfMonsters.core.seasons.LandTypeSeason;
import carnivalOfMonsters.core.seasons.Season;
import carnivalOfMonsters.core.secretGoals.ATrueGentlemanHonorsHisDebts;
import carnivalOfMonsters.core.secretGoals.AuthorityOn;
import carnivalOfMonsters.core.secretGoals.EnthusiastForLittleThings;
import carnivalOfMonsters.core.secretGoals.SevenIfSevenInLandType;
import carnivalOfMonsters.core.secretGoals.SilverSpoon;
import carnivalOfMonsters.core.secretGoals.Specialist;
import carnivalOfMonsters.core.secretGoals.TwoPerHuntToken;
import carnivalOfMonsters.core.staff.EventCoordinator;
import carnivalOfMonsters.core.staff.IntrepidExplorer;
import carnivalOfMonsters.core.staff.Jagermeister;
import carnivalOfMonsters.core.staff.MonsterTrainer;

public class CardGenerator {
	
	public static Stack<Season> createSeasons() {
		var seasons = new Stack<Season>();
		seasons.addAll(Stream.of(LandType.values()).map(x -> new LandTypeSeason(x)).collect(Collectors.toList()));
		seasons.add(new DangerSeason());
		Collections.shuffle(seasons);
		
		return seasons;
	}
	
	public static Stack<ICard> createDrawPile() {
		var drawPile = new Stack<ICard>();

		drawPile.addAll(
				// TODO deck composition
				Stream.of(

						// lands
						Stream.of(LandType.values()).map(x -> createLands(x)).flatMap(x -> x),

						// monsters
						Stream.of(LandType.values()).map(x -> createMonsters(x)).flatMap(x -> x),

						// staff
						Stream.of(LandType.values()).map(x -> createStaff(x)).flatMap(x -> x), createStaff(),

						// events
						Stream.of(LandType.values()).map(x -> createEvents(x)).flatMap(x -> x), createEvents(),

						// secret goals
						Stream.of(LandType.values()).map(x -> createSecretGoals(x)).flatMap(x -> x), createSecretGoals()

				).flatMap(x -> x).collect(Collectors.toList()));

		Collections.shuffle(drawPile);
		
		return drawPile;
	}

	public static Stack<BasicNormalLand> createStartingLands() {
		var startingLands = new Stack<BasicNormalLand>();
		
		startingLands.addAll(
			Stream.of(LandType.values())
				.map(x -> createStartingLands(x))
				.flatMap(x -> x)
				.collect(Collectors.toList()));
				
		
		Collections.shuffle(startingLands);
		
		return startingLands;
		
	}
	
	private static Stream<BasicNormalLand> createStartingLands(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.empty();
		}

		else {
			return Stream.generate(() -> new BasicNormalLand(landType)).limit(2);
		}
	}
	
	private static Stream<ICard> createLands(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.generate(() -> new Dreamlands()).limit(12).map(x -> x);
		}

		else {
			return Stream.of(Stream.generate(() -> new BasicNormalLand(landType)).limit(8),
					Stream.generate(() -> new Level2Land(landType)).limit(4),
					Stream.generate(() -> new Level3Land(landType)).limit(2)).flatMap(x -> x);

		}
	}

	private static Stream<ICard> createMonsters(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of(new OuterRealmSpider(null), new Screecher(null), new Succubus(null), new TheAncientEnemy(null));
		}

		else {
			return Stream
					.of(Stream.generate(() -> new Level1Monster(null,landType)).limit(3),
							Stream.of(new Level1Danger1Monster(null,landType), new Level1Lore1Monster(null,landType),
									new Level2Monster(null,landType), new Level2Danger1Monster(null,landType),
									new Level3Danger1Monster(null,landType), new Level4Danger2Monster(null,landType)))
					.flatMap(x -> x);

		}
	}

	private static Stream<ICard> createStaff(LandType landType) {
		return Stream.of(new AuthorityOn(landType));

	}

	private static Stream<ICard> createStaff() {
		return Stream.of(new EventCoordinator(), new IntrepidExplorer(), new IntrepidExplorer(), new IntrepidExplorer(),
				new IntrepidExplorer(), new Jagermeister(), new Jagermeister(), new MonsterTrainer());
	}

	private static Stream<ICard> createEvents(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of();
		}

		else {
			return Stream.of(new GainPerLandPoint("",landType));
		}
	}

	private static Stream<ICard> createEvents() {
		return Stream.of(new Gain2Crowns(""), new Gain2Crowns(""), new Gain2Crowns(""), new Gain2Crowns(""),
				new GainHunterToken(""), new GainHunterToken(""), new GainHunterToken(""), new GainHunterToken(""),
				new RevealKeptMonsters(""), new RevealKeptMonsters(""));
	}

	private static Stream<ICard> createSecretGoals(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of();
		}

		else {
			return Stream.of(new AuthorityOn(landType));
		}
	}

	private static Stream<ICard> createSecretGoals() {
		return Stream.of(new ATrueGentlemanHonorsHisDebts(), new EnthusiastForLittleThings(),
				new SevenIfSevenInLandType(), new SilverSpoon(), new Specialist(), new TwoPerHuntToken());
	}

}
