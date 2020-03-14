package carnivalOfMonsters.core;

import carnivalOfMonsters.core.events.Gain2Crowns;
import carnivalOfMonsters.core.events.GainHunterToken;
import carnivalOfMonsters.core.events.GainPerLandPoint;
import carnivalOfMonsters.core.events.RevealKeptMonsters;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.lands.Dreamlands;
import carnivalOfMonsters.core.lands.Level2Land;
import carnivalOfMonsters.core.lands.Level3Land;
import carnivalOfMonsters.core.monsters.*;
import carnivalOfMonsters.core.seasons.DangerSeason;
import carnivalOfMonsters.core.seasons.LandTypeSeason;
import carnivalOfMonsters.core.seasons.Season;
import carnivalOfMonsters.core.secretGoals.*;
import carnivalOfMonsters.core.staff.*;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardGenerator {

    public static Stack<Season> createSeasons() {
        var seasons = new Stack<Season>();
        seasons.addAll(Stream.of(LandType.values()).map(landType -> new LandTypeSeason("TODO", landType)).collect(Collectors.toList()));
        seasons.add(new DangerSeason("TODO"));
        Collections.shuffle(seasons);

        return seasons;
    }

    public static Stack<ICard> createDrawPile() {
        var drawPile = new Stack<ICard>();

        drawPile.addAll(
                // TODO deck composition
                Stream.of(

                        // lands
                        Stream.of(LandType.values()).map(CardGenerator::createLands).flatMap(x -> x),

                        // monsters
                        Stream.of(LandType.values()).map(CardGenerator::createMonsters).flatMap(x -> x),

                        // staff
                        Stream.of(LandType.values()).map(CardGenerator::createStaff).flatMap(x -> x), createStaff(),

                        // events
                        Stream.of(LandType.values()).map(CardGenerator::createEvents).flatMap(x -> x), createEvents(),

                        // secret goals
                        Stream.of(LandType.values()).map(CardGenerator::createSecretGoals).flatMap(x -> x), createSecretGoals()

                ).flatMap(x -> x).collect(Collectors.toList()));

        Collections.shuffle(drawPile);

        return drawPile;
    }

    public static Stack<BasicNormalLand> createStartingLands() {
        var startingLands = new Stack<BasicNormalLand>();

        startingLands.addAll(
                Stream.of(LandType.values())
                        .map(CardGenerator::createStartingLands)
                        .flatMap(x -> x)
                        .collect(Collectors.toList()));


        Collections.shuffle(startingLands);

        return startingLands;

    }

    private static Stream<BasicNormalLand> createStartingLands(LandType landType) {
        if (landType == LandType.DREAMLANDS) {
            return Stream.empty();
        } else {
            return Stream.generate(() -> new BasicNormalLand("Starting " + landType, landType)).limit(2);
        }
    }

    private static Stream<ICard> createLands(LandType landType) {
        if (landType == LandType.DREAMLANDS) {
            return Stream.generate(() -> new Dreamlands("TODO")).limit(12).map(x -> x);
        } else {
            return Stream.of(Stream.generate(() -> new BasicNormalLand(landType.toString(), landType)).limit(8),
                    Stream.generate(() -> new Level2Land("Level 2" + landType, landType)).limit(4),
                    Stream.generate(() -> new Level3Land("Level 3" + landType, landType)).limit(2)).flatMap(x -> x);

        }
    }

    private static Stream<ICard> createMonsters(LandType landType) {
        if (landType == LandType.DREAMLANDS) {
            return Stream.of(new OuterRealmSpider("OuterRealmSpider"), new Screecher("Screecher"), new Succubus("Succubus"), new TheAncientEnemy("TheAncientEnemy"));
        } else {
            return Stream
                    .of(Stream.generate(() -> new Level1Monster("Level 1 " + landType, landType)).limit(3),
                            Stream.of(new Level1Danger1Monster("Lv1D1 " + landType, landType), new Level1Lore1Monster("LvL1 " + landType, landType),
                                    new Level2Monster("Lv2 " + landType, landType), new Level2Danger1Monster("Lv2D1 " + landType, landType),
                                    new Level3Danger1Monster("Lv3D1 " + landType, landType), new Level4Danger2Monster("Lv4D2 " + landType, landType)))
                    .flatMap(x -> x);

        }
    }

    private static Stream<ICard> createStaff(LandType landType) {
        return Stream.of(new LandTypeExpert("LandTypeExpert " + landType, landType));
    }

    private static Stream<ICard> createStaff() {
        return Stream.of(new EventCoordinator("EventCoordinator"), new IntrepidExplorer("IntrepidExplorer"), new IntrepidExplorer("IntrepidExplorer"), new IntrepidExplorer("IntrepidExplorer"),
                new IntrepidExplorer("IntrepidExplorer"), new Jagermeister("Jagermeister"), new Jagermeister("Jagermeister"), new MonsterTrainer("MonsterTrainer"));
    }

    private static Stream<ICard> createEvents(LandType landType) {
        if (landType == LandType.DREAMLANDS) {
            return Stream.of();
        } else {
            return Stream.of(new GainPerLandPoint("GainPerLandPoint" + landType, landType));
        }
    }

    private static Stream<ICard> createEvents() {
        return Stream.of(new Gain2Crowns("Gain2Crowns"), new Gain2Crowns("Gain2Crowns"), new Gain2Crowns("Gain2Crowns"), new Gain2Crowns("Gain2Crowns"),
                new GainHunterToken("GainHunterToken"), new GainHunterToken("GainHunterToken"), new GainHunterToken("GainHunterToken"), new GainHunterToken("GainHunterToken"),
                new RevealKeptMonsters("RevealKeptMonsters"), new RevealKeptMonsters("RevealKeptMonsters"));
    }

    private static Stream<ICard> createSecretGoals(LandType landType) {
        if (landType == LandType.DREAMLANDS) {
            return Stream.of();
        } else {
            return Stream.of(new AuthorityOn("Authority on" + landType, landType));
        }
    }

    private static Stream<ICard> createSecretGoals() {
        return Stream.of(new ATrueGentlemanHonorsHisDebts("ATrueGentlemanHonorsHisDebts"), new EnthusiastForLittleThings("EnthusiastForLittleThings"),
                new SevenIfSevenInLandType("SevenIfSevenInLandType"), new SilverSpoon("SilverSpoon"), new Specialist("Specialist"), new TwoPerHuntToken("TwoPerHuntToken"));
    }

}
