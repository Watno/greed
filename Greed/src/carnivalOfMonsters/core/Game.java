package carnivalOfMonsters.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;
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

public class Game {
	private Stack<ICard> drawPile;

	private List<Player> players;

	private Collection<HuntDie> huntDice;
	
	private Stack<Season> seasons;

	public Game(Collection<Player> players) {
		super();
		this.players = new ArrayList<Player>(players);
		createDrawPile();
		createDraftstacks();
		
		createSeasons();

		var random = new Random();
		huntDice = Stream.generate(() -> new HuntDie(random)).limit(3).collect(Collectors.toList());
	}

	public void run() {
		
		for (int season = 1; season <= 4; season++) {
			runSeason(season);
		}
		for (var player : players) {
			System.out.println(player.score());
		}

	}

	private void runSeason(int season) {
		var draftStacks = createDraftstacks();
		for (int round = 1; round <= 8; round++) {
			for (int player = 0; player < players.size(); player++) {
				// TODO parallel playing
				players.get(player).makeTurn(
						draftStacks.get(Math.floorMod((int) (Math.pow(-1, season)) * player + round, players.size())),
						this);
			}
		}
		for (var player : players) {
			player.allowPlayingKeptCards(this);
		}

		var royalHunters = huntDice.stream().mapToInt(x -> x.roll()).sum();
		for (var player : players) {
			player.performDangerCheck(royalHunters);
		}

		getCurrentSeason().assign(players);
		seasons.pop();
		
		for (var player : players) {
			player.moveMonstersToMenagerie();
		}

	
	}

	public Collection<ICard> draw(int numberOfCards) {
		return Stream.generate(() -> drawPile.pop()).limit(Integer.min(numberOfCards, drawPile.size()))
				.collect(Collectors.toList());
	}
	
	public Season getCurrentSeason() {
		return seasons.peek();
	}

	private List<Collection<ICard>> createDraftstacks() {
		return players.stream().map(x -> draw(8)).collect(Collectors.toList());
	}

	private void createSeasons() {
		seasons = new Stack<Season>();
		seasons.addAll(Stream.of(LandType.values()).map(x -> new LandTypeSeason(x)).collect(Collectors.toList()));
		seasons.add(new DangerSeason());
		Collections.shuffle(seasons);
	}
	
	// TODO extract
	private void createDrawPile() {
		drawPile = new Stack<ICard>();

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
	}

	private Stream<ICard> createLands(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.generate(() -> new Dreamlands()).limit(12).map(x -> x);
		}

		else {
			return Stream.of(Stream.generate(() -> new BasicNormalLand(landType)).limit(8),
					Stream.generate(() -> new Level2Land(landType)).limit(4),
					Stream.generate(() -> new Level3Land(landType)).limit(2)).flatMap(x -> x);

		}
	}

	private Stream<ICard> createMonsters(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of(new OuterRealmSpider(), new Screecher(), new Succubus(), new TheAncientEnemy());
		}

		else {
			return Stream
					.of(Stream.generate(() -> new Level1Monster(landType)).limit(3),
							Stream.of(new Level1Danger1Monster(landType), new Level1Lore1Monster(landType),
									new Level2Monster(landType), new Level2Danger1Monster(landType),
									new Level3Danger1Monster(landType), new Level4Danger2Monster(landType)))
					.flatMap(x -> x);

		}
	}

	private Stream<ICard> createStaff(LandType landType) {
		return Stream.of(new AuthorityOn(landType));

	}

	private Stream<ICard> createStaff() {
		return Stream.of(new EventCoordinator(), new IntrepidExplorer(), new IntrepidExplorer(), new IntrepidExplorer(),
				new IntrepidExplorer(), new Jagermeister(), new Jagermeister(), new MonsterTrainer());
	}

	private Stream<ICard> createEvents(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of();
		}

		else {
			return Stream.of(new GainPerLandPoint(landType));
		}
	}

	private Stream<ICard> createEvents() {
		return Stream.of(new Gain2Crowns(), new Gain2Crowns(), new Gain2Crowns(), new Gain2Crowns(),
				new GainHunterToken(), new GainHunterToken(), new GainHunterToken(), new GainHunterToken(),
				new RevealKeptMonsters(), new RevealKeptMonsters());
	}

	private Stream<ICard> createSecretGoals(LandType landType) {
		if (landType == LandType.DREAMLANDS) {
			return Stream.of();
		}

		else {
			return Stream.of(new AuthorityOn(landType));
		}
	}

	private Stream<ICard> createSecretGoals() {
		return Stream.of(new ATrueGentlemanHonorsHisDebts(), new EnthusiastForLittleThings(),
				new SevenIfSevenInLandType(), new SilverSpoon(), new Specialist(), new TwoPerHuntToken());
	}

}
