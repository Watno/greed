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
                new LandTypeSeason("Saison des Verwunschenen Waldes", LandType.ENCHANTEDFOREST),
                new LandTypeSeason("Saison der Höhlen", LandType.CAVES),
                new LandTypeSeason("Saison der Tiefsee", LandType.DEPTHS),
                new LandTypeSeason("Saison der Wolkenlande", LandType.AERIE),
                new LandTypeSeason("Saison der Dunkellande", LandType.DARKLANDS),
                new DangerSeason("Saison der Gefahren")
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
                        new BasicNormalLand("Pfad in den verwunschenen Wald", LandType.ENCHANTEDFOREST),
                        new BasicNormalLand("Pfad in den verwunschenen Wald", LandType.ENCHANTEDFOREST),
                        new BasicNormalLand("Eingang zu den Höhlen", LandType.CAVES),
                        new BasicNormalLand("Eingang zu den Höhlen", LandType.CAVES),
                        new BasicNormalLand("Pass zu den Wolkenlanden", LandType.AERIE),
                        new BasicNormalLand("Pass zu den Wolkenlanden", LandType.AERIE),
                        new BasicNormalLand("Ausläufer der Tiefsee", LandType.DEPTHS),
                        new BasicNormalLand("Ausläufer der Tiefsee", LandType.DEPTHS),
                        new BasicNormalLand("Portal zu den Dunkellanden", LandType.DARKLANDS),
                        new BasicNormalLand("Portal zu den Dunkellanden", LandType.DARKLANDS))
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

                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
                new BasicNormalLand("Verwunschener Wald", LandType.ENCHANTEDFOREST),
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
                new Level1Monster("Dryade", LandType.ENCHANTEDFOREST),
                new Level1Monster("Dryade", LandType.ENCHANTEDFOREST),
                new Level1Monster("Dryade", LandType.ENCHANTEDFOREST),
                new Level1Monster("Dryade", LandType.ENCHANTEDFOREST),
                new Level1Danger1Monster("Schluchtenspinne", LandType.ENCHANTEDFOREST),
                new Level1Lore1Monster("Moosgnom", LandType.ENCHANTEDFOREST),
                new Level2Monster("Schrat", LandType.ENCHANTEDFOREST),
                new Level2Monster("Schrat", LandType.ENCHANTEDFOREST),
                new Level2Danger1Monster("Waldtroll", LandType.ENCHANTEDFOREST),
                new Level2Lore1Monster("Zwielichtkatze", LandType.ENCHANTEDFOREST),
                new Level3Danger1Monster("Baumwandler", LandType.ENCHANTEDFOREST),
                new Level3Danger1Monster("Baumwandler", LandType.ENCHANTEDFOREST),
                new Level3Danger2Monster("Wyvern", LandType.ENCHANTEDFOREST),
                new Level4Danger2Monster("Wendigo", LandType.ENCHANTEDFOREST),

                new Level1Monster("Niederer Wurm", LandType.CAVES),
                new Level1Monster("Niederer Wurm", LandType.CAVES),
                new Level1Monster("Niederer Wurm", LandType.CAVES),
                new Level1Monster("Niederer Wurm", LandType.CAVES),
                new Level1Danger1Monster("Basilisk", LandType.CAVES),
                new Level1Lore1Monster("Fleichfressender Pilz", LandType.CAVES),
                new Level2Monster("Gespensterwolf", LandType.CAVES),
                new Level2Monster("Gespensterwolf", LandType.CAVES),
                new Level2Danger1Monster("Großer Wurm", LandType.CAVES),
                new Level2Lore1Monster("Schlammspinne", LandType.CAVES),
                new Level3Danger1Monster("Höhlentroll", LandType.CAVES),
                new Level3Danger1Monster("Höhlentroll", LandType.CAVES),
                new Level3Danger2Monster("Der Namenlose", LandType.CAVES),
                new Level4Danger2Monster("Magmaschrecken", LandType.CAVES),

                new Level1Monster("Cumuluskäfer", LandType.AERIE),
                new Level1Monster("Cumuluskäfer", LandType.AERIE),
                new Level1Monster("Cumuluskäfer", LandType.AERIE),
                new Level1Monster("Cumuluskäfer", LandType.AERIE),
                new Level1Danger1Monster("Harpie", LandType.AERIE),
                new Level1Lore1Monster("Gewitterrochen", LandType.AERIE),
                new Level2Monster("Schwertflügler", LandType.AERIE),
                new Level2Monster("Schwertflügler", LandType.AERIE),
                new Level2Danger1Monster("Phoenix", LandType.AERIE),
                new Level2Lore1Monster("Stiller Beobachter", LandType.AERIE),
                new Level3Danger1Monster("Greif", LandType.AERIE),
                new Level3Danger1Monster("Greif", LandType.AERIE),
                new Level3Danger2Monster("Gipfelspinne", LandType.AERIE),
                new Level4Danger2Monster("Quetzalcoatl", LandType.AERIE),

                new Level1Monster("Ghoul", LandType.DARKLANDS),
                new Level1Monster("Ghoul", LandType.DARKLANDS),
                new Level1Monster("Ghoul", LandType.DARKLANDS),
                new Level1Monster("Ghoul", LandType.DARKLANDS),
                new Level1Danger1Monster("Nachttroll", LandType.DARKLANDS),
                new Level1Lore1Monster("Schattenkatze", LandType.DARKLANDS),
                new Level2Monster("Wandelnder Schatten", LandType.DARKLANDS),
                new Level2Monster("Wandelnder Schatten", LandType.DARKLANDS),
                new Level2Danger1Monster("Dibbuk", LandType.DARKLANDS),
                new Level2Lore1Monster("Flüsterer", LandType.DARKLANDS),
                new Level3Danger1Monster("Flammenspinne", LandType.DARKLANDS),
                new Level3Danger1Monster("Flammenspinne", LandType.DARKLANDS),
                new Level3Danger2Monster("Nephilim", LandType.DARKLANDS),
                new Level4Danger2Monster("Nosferatu", LandType.DARKLANDS),

                new Level1Monster("Fischwesen", LandType.DEPTHS),
                new Level1Monster("Fischwesen", LandType.DEPTHS),
                new Level1Monster("Fischwesen", LandType.DEPTHS),
                new Level1Monster("Fischwesen", LandType.DEPTHS),
                new Level1Danger1Monster("Tiefseespinne", LandType.DEPTHS),
                new Level1Lore1Monster("Kelpie", LandType.DEPTHS),
                new Level2Monster("Schalenbeißer", LandType.DEPTHS),
                new Level2Monster("Schalenbeißer", LandType.DEPTHS),
                new Level2Danger1Monster("Hydra", LandType.DEPTHS),
                new Level2Lore1Monster("Mondhai", LandType.DEPTHS),
                new Level3Danger1Monster("Seeriese", LandType.DEPTHS),
                new Level3Danger1Monster("Seeriese", LandType.DEPTHS),
                new Level3Danger2Monster("Riesenkrake", LandType.DEPTHS),
                new Level4Danger2Monster("Leviathan", LandType.DEPTHS),

                new Screecher("Kreischer"),
                new Succubus("Sukkubus"),
                new Dreamlands5("Nachtmahr"),
                new Dreamlands6("Traumfänger"),
                new Dreamlands7("Mondbestie"),
                new OuterRealmSpider("Außerweltliche Spinne"),
                new Dreamlands9("Pazuzu"),
                new TheAncientEnemy("Uralter Feind")
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
