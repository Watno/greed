package carnivalOfMonsters.core;

import carnivalOfMonsters.core.events.*;
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
        seasons.addAll(Stream.of(
                new LandTypeSeason("Wald", LandType.ENCHANTEDFOREST),
                new LandTypeSeason("Höhlen", LandType.CAVES),
                new LandTypeSeason("Tiefsee", LandType.DEPTHS),
                new LandTypeSeason("Wolkenlande", LandType.AERIE),
                new LandTypeSeason("Dunkellande", LandType.DARKLANDS),
                new DangerSeason("Gefahr")
        ).collect(Collectors.toList()));
        Collections.shuffle(seasons);

        return seasons;
    }

    public static Stack<ICard> createDrawPile() {
        var drawPile = new Stack<ICard>();

        drawPile.addAll(
                Stream.of(
                        createLands(),
                        createMonsters(),
                        createStaff(),
                        createEvents(),
                        createSecretGoals()
                ).flatMap(x -> x).collect(Collectors.toList()));

        Collections.shuffle(drawPile);

        return drawPile;
    }

    public static Stack<BasicNormalLand> createStartingLands() {
        var startingLands = new Stack<BasicNormalLand>();

        startingLands.addAll(
                Stream.of(
                        new BasicNormalLand("Startwald", LandType.ENCHANTEDFOREST),
                        new BasicNormalLand("Startwald", LandType.ENCHANTEDFOREST),
                        new BasicNormalLand("Starthöhle", LandType.CAVES),
                        new BasicNormalLand("Starthöhle", LandType.CAVES),
                        new BasicNormalLand("Startwolkenlande", LandType.AERIE),
                        new BasicNormalLand("Startwolkenlande", LandType.AERIE),
                        new BasicNormalLand("Starttiefsee", LandType.DEPTHS),
                        new BasicNormalLand("Starttiefsee", LandType.DEPTHS),
                        new BasicNormalLand("Startdunkelland", LandType.DARKLANDS),
                        new BasicNormalLand("Startdunkelland", LandType.DARKLANDS))
                        .collect(Collectors.toList()));

        Collections.shuffle(startingLands);

        return startingLands;

    }

    private static Stream<ICard> createLands() {
        return Stream.of(
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),
                new Dreamlands("Traumlande"),

                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Wald", LandType.ENCHANTEDFOREST),
                new Level2Land("tiefer Wald", LandType.ENCHANTEDFOREST),
                new Level2Land("tiefer Wald", LandType.ENCHANTEDFOREST),
                new Level3Land("sehr tiefer Wald", LandType.ENCHANTEDFOREST),

                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new BasicNormalLand("Höhlen", LandType.CAVES),
                new Level2Land("tiefe Höhlen", LandType.CAVES),
                new Level2Land("tiefe Höhlen", LandType.CAVES),
                new Level3Land("sehr tiefe Höhlen", LandType.CAVES),

                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new BasicNormalLand("Wolkenlande", LandType.AERIE),
                new Level2Land("hohe Wolkenlande", LandType.AERIE),
                new Level2Land("hohe Wolkenlande", LandType.AERIE),
                new Level3Land("sehr hohe Wolkenlande", LandType.AERIE),

                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new BasicNormalLand("Tiefsee", LandType.DEPTHS),
                new Level2Land("tiefe Tiefsee", LandType.DEPTHS),
                new Level2Land("tiefe Tiefsee", LandType.DEPTHS),
                new Level3Land("sehr tiefe Tiefsee", LandType.DEPTHS),

                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new BasicNormalLand("Dunkellande", LandType.DARKLANDS),
                new Level2Land("dunkle Dunkellande", LandType.DARKLANDS),
                new Level2Land("dunkle Dunkellande", LandType.DARKLANDS),
                new Level3Land("sehr dunkle Dunkellande", LandType.DARKLANDS)
        );
    }

    private static Stream<ICard> createMonsters() {
        return Stream.of(
                new Level1Monster("kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level1Monster("kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level1Monster("kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level1Monster("kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level1Danger1Monster(" gefährliches kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level1Lore1Monster("schlaues kleines Waldmonster", LandType.ENCHANTEDFOREST),
                new Level2Monster("Waldmonster", LandType.ENCHANTEDFOREST),
                new Level2Monster("Waldmonster", LandType.ENCHANTEDFOREST),
                new Level2Danger1Monster("gefährliches Waldmonster", LandType.ENCHANTEDFOREST),
                new Level2Lore1Monster("schlaues Waldmonster", LandType.ENCHANTEDFOREST),
                new Level3Danger1Monster("großes Waldmonster", LandType.ENCHANTEDFOREST),
                new Level3Danger1Monster("großes Waldmonster", LandType.ENCHANTEDFOREST),
                new Level3Danger2Monster("gefährliches großes Waldmonster", LandType.ENCHANTEDFOREST),
                new Level4Danger2Monster("sehr großes Waldmonster", LandType.ENCHANTEDFOREST),

                new Level1Monster("kleines Höhlenmonster", LandType.CAVES),
                new Level1Monster("kleines Höhlenmonster", LandType.CAVES),
                new Level1Monster("kleines Höhlenmonster", LandType.CAVES),
                new Level1Monster("kleines Höhlenmonster", LandType.CAVES),
                new Level1Danger1Monster(" gefährliches kleines Höhlenmonster", LandType.CAVES),
                new Level1Lore1Monster("schlaues kleines Höhlenmonster", LandType.CAVES),
                new Level2Monster("Höhlenmonster", LandType.CAVES),
                new Level2Monster("Höhlenmonster", LandType.CAVES),
                new Level2Danger1Monster("gefährliches Höhlenmonster", LandType.CAVES),
                new Level2Lore1Monster("schlaues Höhlenmonster", LandType.CAVES),
                new Level3Danger1Monster("großes Höhlenmonster", LandType.CAVES),
                new Level3Danger1Monster("großes Höhlenmonster", LandType.CAVES),
                new Level3Danger2Monster("gefährliches großes Höhlenmonster", LandType.CAVES),
                new Level4Danger2Monster("sehr großes Höhlenmonster", LandType.CAVES),

                new Level1Monster("kleines Wolkenmonster", LandType.AERIE),
                new Level1Monster("kleines Wolkenmonster", LandType.AERIE),
                new Level1Monster("kleines Wolkenmonster", LandType.AERIE),
                new Level1Monster("kleines Wolkenmonster", LandType.AERIE),
                new Level1Danger1Monster(" gefährliches kleines Wolkenmonster", LandType.AERIE),
                new Level1Lore1Monster("schlaues kleines Wolkenmonster", LandType.AERIE),
                new Level2Monster("Wolkenmonster", LandType.AERIE),
                new Level2Monster("Wolkenmonster", LandType.AERIE),
                new Level2Danger1Monster("gefährliches Wolkenmonster", LandType.AERIE),
                new Level2Lore1Monster("schlaues Wolkenmonster", LandType.AERIE),
                new Level3Danger1Monster("großes Wolkenmonster", LandType.AERIE),
                new Level3Danger1Monster("großes Wolkenmonster", LandType.AERIE),
                new Level3Danger2Monster("gefährliches großes Wolkenmonster", LandType.AERIE),
                new Level4Danger2Monster("sehr großes Wolkenmonster", LandType.AERIE),

                new Level1Monster("kleines Dunkelmonster", LandType.DARKLANDS),
                new Level1Monster("kleines Dunkelmonster", LandType.DARKLANDS),
                new Level1Monster("kleines Dunkelmonster", LandType.DARKLANDS),
                new Level1Monster("kleines Dunkelmonster", LandType.DARKLANDS),
                new Level1Danger1Monster(" gefährliches kleines Dunkelmonster", LandType.DARKLANDS),
                new Level1Lore1Monster("schlaues kleines Dunkelmonster", LandType.DARKLANDS),
                new Level2Monster("Dunkelmonster", LandType.DARKLANDS),
                new Level2Monster("Dunkelmonster", LandType.DARKLANDS),
                new Level2Danger1Monster("gefährliches Dunkelmonster", LandType.DARKLANDS),
                new Level2Lore1Monster("schlaues Dunkelmonster", LandType.DARKLANDS),
                new Level3Danger1Monster("großes Dunkelmonster", LandType.DARKLANDS),
                new Level3Danger1Monster("großes Dunkelmonster", LandType.DARKLANDS),
                new Level3Danger2Monster("gefährliches großes Dunkelmonster", LandType.DARKLANDS),
                new Level4Danger2Monster("sehr großes Dunkelmonster", LandType.DARKLANDS),

                new Level1Monster("kleines Wassermonster", LandType.DEPTHS),
                new Level1Monster("kleines Wassermonster", LandType.DEPTHS),
                new Level1Monster("kleines Wassermonster", LandType.DEPTHS),
                new Level1Monster("kleines Wassermonster", LandType.DEPTHS),
                new Level1Danger1Monster(" gefährliches kleines Wassermonster", LandType.DEPTHS),
                new Level1Lore1Monster("schlaues kleines Wassermonster", LandType.DEPTHS),
                new Level2Monster("Wassermonster", LandType.DEPTHS),
                new Level2Monster("Wassermonster", LandType.DEPTHS),
                new Level2Danger1Monster("gefährliches Wassermonster", LandType.DEPTHS),
                new Level2Lore1Monster("schlaues Wassermonster", LandType.DEPTHS),
                new Level3Danger1Monster("großes Wassermonster", LandType.DEPTHS),
                new Level3Danger1Monster("großes Wassermonster", LandType.DEPTHS),
                new Level3Danger2Monster("gefährliches großes Wassermonster", LandType.DEPTHS),
                new Level4Danger2Monster("sehr großes Wassermonster", LandType.DEPTHS),

                new Screecher("Screecher"),
                new Succubus("Succubus"),
                new Dreamlands5("Traummonster 5"),
                new Dreamlands6("Traummonster 6"),
                new Dreamlands7("Traummonster 7"),
                new OuterRealmSpider("Outer Realm Spider"),
                new Dreamlands9("Traummonster 9"),
                new TheAncientEnemy("The Ancient Enemy")
        );
    }

    private static Stream<ICard> createEvents() {
        return Stream.of(
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new Gain2Crowns("2 Kronen"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new GainHunterToken("1 Jägermarker"),
                new RevealKeptMonsters("aufgehobene Monster"),
                new RevealKeptMonsters("aufgehobene Monster"),
                new Gain2PerStaff("2/Mitarbeiter"),
                new GainPerLandPoint("1/Wald", LandType.ENCHANTEDFOREST),
                new GainPerLandPoint("1/Höhle", LandType.CAVES),
                new GainPerLandPoint("1/Wolkenland", LandType.AERIE),
                new GainPerLandPoint("1/Tiefsee", LandType.DEPTHS),
                new GainPerLandPoint("1/Dunkelland", LandType.DARKLANDS)
        );
    }

    private static Stream<ICard> createStaff() {
        return Stream.of(
                new EventCoordinator("Eventkoordinator"),
                new IntrepidExplorer("Mutiger Entdecker"),
                new IntrepidExplorer("Mutiger Entdecker"),
                new IntrepidExplorer("Mutiger Entdecker"),
                new IntrepidExplorer("Mutiger Entdecker"),
                new Jagermeister("Jägermeister"),
                new Jagermeister("Jägermeister"),
                new MonsterTrainer("Monstertrainer"),
                new LandTypeExpert("Waldexperte", LandType.ENCHANTEDFOREST),
                new LandTypeExpert("Höhlenexperte", LandType.CAVES),
                new LandTypeExpert("Wolkenexperte", LandType.AERIE),
                new LandTypeExpert("Tiefseeexperte", LandType.DEPTHS),
                new LandTypeExpert("Dunkellandexperte", LandType.DARKLANDS)
        );
    }

    private static Stream<ICard> createSecretGoals() {
        return Stream.of(
                new ATrueGentlemanHonorsHisDebts("2/Kredit"),
                new EnthusiastForLittleThings("1/Level 1"),
                new ObviousObsession("7 für 7 gleiche Länder"),
                new SilverSpoon("Silberlöffel (4)"),
                new Specialist("wenig Landtypen"),
                new ExplorerBeyondTheLastFrontier("2/entferntes Land"),
                new SolidEquipment("2/Jägermarker"),
                new Steward("2/Ziel"),
                new EmployerOfTheYear("3/Mitarbeiter"),
                new BigGameHunter("2/3 für Level 3/4"),
                new DangerSeeker("1/Gefahr"),
                new JackOfAllTrades("7 für 2 in allen Farben"),
                new SeekerBeyondTheGateOfDreams("2/Traumland"),
                new ThriftIsAVirtue("6 wenn kein Kredit"),
                new SecondRowIsGoodEnough("7  wenn zweiter"),

                new AuthorityOn("2/Waldmonster", LandType.ENCHANTEDFOREST),
                new AuthorityOn("2/Höhlenmonster", LandType.CAVES),
                new AuthorityOn("2/Tiefseemonster", LandType.DEPTHS),
                new AuthorityOn("2/Wolkenmonster", LandType.AERIE),
                new AuthorityOn("2/Dunkelmonster", LandType.DARKLANDS),
                new AuthorityOn("2/Traummonster", LandType.DREAMLANDS)
        );
    }
}
