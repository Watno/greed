package spacealert.core;

import spacealert.core.boardElements.Gravolift;
import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.Structure;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.damageSources.cannons.*;
import spacealert.core.boardElements.energyBuckets.reactors.CentralReactor;
import spacealert.core.boardElements.energyBuckets.reactors.LateralReactor;
import spacealert.core.boardElements.energyBuckets.reactors.Reactor;
import spacealert.core.boardElements.energyBuckets.shields.CentralShield;
import spacealert.core.boardElements.energyBuckets.shields.LateralShield;
import spacealert.core.boardElements.energyBuckets.shields.Shield;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.locations.Space;
import spacealert.core.boardElements.locations.StationLayout;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.Trajectory;
import spacealert.core.threats.templates.Threat;
import spacealert.core.triggeredEffects.ICanHaveTriggeredEffectsAttached;
import spacealert.core.triggeredEffects.ModifyDamageToShipEffect;
import spacealert.core.triggeredEffects.TriggeredEffect;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardState implements ICanHaveTriggeredEffectsAttached {
    private Collection<ICrewMemberFromBoardStatePerspective> crewMembers;
    private final StationLayout stationLayout;
    private final Map<Zone, Gravolift> gravolifts;
    private final Map<Zone, Reactor> reactors;
    private final Map<Zone, Shield> shields;
    private final Map<Position, Cannon> cannons;
    private final Map<Zone, Trajectory> trajectories;
    private final Map<Zone, List<IDamageable>> damagetokens;
    private final Trajectory internalTrajectory;
    private final Space space;
    private final ArrayList<Rocket> availableRockets = new ArrayList<>(List.of(new Rocket(), new Rocket(), new Rocket()));

    private final ArrayList<Threat> activeThreats = new ArrayList<>();
    private final ArrayList<Threat> destroyedThreats = new ArrayList<>();
    private final ArrayList<Threat> survivedThreats = new ArrayList<>();

    private final Set<Integer> mouseJuggles = new HashSet<>();
    private int currentTurnVisualConfirmations = 0;
    private final List<Integer> visualConfirmations = Stream.generate(() -> 0).limit(3).collect(Collectors.toList());
    private int currentTurn;
    private int currentPhase;
    private Optional<Rocket> nextTurnRocket = Optional.empty();
    private Optional<Rocket> thisTurnRocket = Optional.empty();

    private final Random random = new Random();

    public BoardState() {
        gravolifts = Map.of(
                Zone.RED, new Gravolift(),
                Zone.WHITE, new Gravolift(),
                Zone.BLUE, new Gravolift()
        );
        reactors = Map.of(
                Zone.RED, new LateralReactor(),
                Zone.WHITE, new CentralReactor(),
                Zone.BLUE, new LateralReactor()
        );
        shields = Map.of(
                Zone.RED, new LateralShield(),
                Zone.WHITE, new CentralShield(),
                Zone.BLUE, new LateralShield()
        );
        cannons = Map.of(
                new Position(Deck.UPPER, Zone.RED), new LateralHeavyCannon(Zone.RED),
                new Position(Deck.UPPER, Zone.WHITE), new CentralHeavyCannon(),
                new Position(Deck.UPPER, Zone.BLUE), new LateralHeavyCannon(Zone.BLUE),
                new Position(Deck.LOWER, Zone.RED), new LightCannon(Zone.RED),
                new Position(Deck.LOWER, Zone.WHITE), new PulseCannon(),
                new Position(Deck.LOWER, Zone.BLUE), new LightCannon(Zone.BLUE)
        );

        damagetokens = Arrays.stream(Zone.values()).collect(Collectors.toMap(x -> x, this::getDamagetokens));

        space = new Space();
        stationLayout = new StationLayout();

        var allTrajectories = Trajectory.all();
        trajectories = Map.of(
                Zone.RED, allTrajectories.remove(0),
                Zone.WHITE, allTrajectories.remove(0),
                Zone.BLUE, allTrajectories.remove(0)
        );

        internalTrajectory = allTrajectories.remove(0);
    }

    private List<IDamageable> getDamagetokens(Zone zone) {
        var tokens = new ArrayList<IDamageable>();
        tokens.add(cannons.get(new Position(Deck.UPPER, zone)));
        tokens.add(cannons.get(new Position(Deck.LOWER, zone)));
        tokens.add(shields.get(zone));
        tokens.add(reactors.get(zone));
        tokens.add(gravolifts.get(zone));
        tokens.add(new Structure());

        return tokens;
    }

    public BoardState(Collection<CrewMember> crewMembers) {
        this();

        this.crewMembers = new ArrayList<>(crewMembers);

        for (var crewMember : crewMembers) {
            crewMember.moveTo(stationLayout.getStation(new Position(Deck.UPPER, Zone.WHITE)));
        }
    }


    public Collection<ICrewMemberFromBoardStatePerspective> getCrewMembers() {
        return crewMembers;
    }

    public boolean mouseJuggled() {
        return mouseJuggles.contains(currentPhase);
    }

    public void juggleMouse() {
        mouseJuggles.add(currentPhase);
    }

    public void delayAllCrewMembers() {
        for (ICrewMemberFromBoardStatePerspective crewMember : crewMembers) {
            crewMember.delay();
        }
    }

    public ILocation getStation(Position position) {
        return stationLayout.getStation(position);
    }

    public Gravolift getGravolift(Zone zone) {
        return gravolifts.get(zone);
    }

    public Reactor getReactor(Zone zone) {
        return reactors.get(zone);
    }

    public Shield getShield(Zone zone) {
        return shields.get(zone);
    }

    public Cannon getCannon(Position position) {
        return cannons.get(position);
    }

    public Trajectory getTrajectory(Zone zone) {
        return trajectories.get(zone);
    }

    public Trajectory getInternalTrajectory() {
        return internalTrajectory;
    }

    public Space getSpace() {
        return space;
    }

    public List<Threat> getActiveThreats() {
        return List.copyOf(activeThreats);
    }

    public List<Threat> getDestroyedThreats() {
        return List.copyOf(destroyedThreats);
    }


    public Optional<ILocation> getAdjacentInDirection(ILocation location, Direction direction) {
        return stationLayout.getAdjacentInDirection(location, direction);
    }

    public void resetAtEndOfActionPhase() {
        for (var gravolift : gravolifts.values()) {
            gravolift.resetUsage();
        }
        currentTurnVisualConfirmations = 0;
    }

    public void startPhase(int phase) {
        currentPhase = phase;
    }

    public void startTurn(int turn) {
        currentTurn = turn;
    }

    public List<Rocket> getAvailableRockets() {
        return List.copyOf(availableRockets);
    }

    public boolean tryLaunchRocket() {
        if (!availableRockets.isEmpty() && nextTurnRocket.isEmpty()) {
            nextTurnRocket = Optional.of(availableRockets.remove(0));
            return true;
        } else {
            return false;
        }
    }

    public void advanceRockets() {
        thisTurnRocket = nextTurnRocket;
        nextTurnRocket = Optional.empty();
    }

    public void addVisualConfirmation() {
        currentTurnVisualConfirmations++;
        visualConfirmations.set(currentPhase - 1, Integer.max(currentTurnVisualConfirmations, visualConfirmations.get(currentPhase - 1)));
    }

    public void recordAsSurvived(Threat threat) {
        activeThreats.remove(threat);
        survivedThreats.add(threat);
    }

    public void recordAsDestroyed(Threat threat) {
        activeThreats.remove(threat);
        destroyedThreats.add(threat);
    }

    public GameLost spawnThreats(Collection<Threat> threats) {
        for (var threat : threats) {
            activeThreats.add(threat);
            var gameLost = threat.spawn(this, currentTurn);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

    public List<DamageSource> getDamageSources() {
        var damageSources = new ArrayList<DamageSource>(cannons.values());
        thisTurnRocket.ifPresent(damageSources::add);
        space.getInterceptors().ifPresent(damageSources::add);
        return damageSources;
    }

    public Optional<Rocket> getCurrentTurnRocket() {
        return thisTurnRocket;
    }

    public GameLost damage(Zone zone, int amount) {
        for (var effect : triggeredEffects.stream().filter(x -> x instanceof ModifyDamageToShipEffect).map(x -> (ModifyDamageToShipEffect) x).collect(Collectors.toList())) {
            amount = effect.getModifiedDamage(zone, amount);
        }
        for (int i = 0; i < amount; i++) {
            var gameLost = damage(zone);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

    private GameLost damage(Zone zone) {
        var availableDamageTokens = damagetokens.get(zone);
        if (availableDamageTokens.isEmpty()) return GameLost.TRUE;
        var index = random.nextInt(availableDamageTokens.size());
        var randomDamageToken = availableDamageTokens.remove(index);
        randomDamageToken.damage();
        return GameLost.FALSE;
    }

    public int applyShieldsToDamage(Zone zone, int amount) {
        return shields.get(zone).blockDamage(amount);
    }

    public Collection<Shield> getShields() {
        return shields.values();
    }

    public int score() {
        return survivedThreats.stream().mapToInt(Threat::getPointsForSurviving).sum()
                + destroyedThreats.stream().mapToInt(Threat::getPointsForDestroying).sum()
                + scoreVisualConfirmations()
                + scoreDamageTokens()
                + crewMembers.stream().mapToInt(ICrewMemberFromBoardStatePerspective::score).sum();
    }

    private int scoreDamageTokens() {
        var damagePerZone = damagetokens.values().stream().mapToInt(x -> 6 - x.size()).boxed().collect(Collectors.toList());
        return -(damagePerZone.stream().mapToInt(x -> x).max().orElse(0) + damagePerZone.stream().mapToInt(x -> x).sum());
    }

    private int scoreVisualConfirmations() {
        return visualConfirmations.stream().mapToInt(this::scoreVisualConfirmation).sum();
    }

    private int scoreVisualConfirmation(int numberOfConfirmations) {
        return switch (numberOfConfirmations) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> 5;
            case 5 -> 7;
            default -> throw new IllegalArgumentException();
        };
    }

    private final List<TriggeredEffect> triggeredEffects = new ArrayList<>();

    @Override
    public void attach(TriggeredEffect effect) {
        triggeredEffects.add(effect);
    }

    @Override
    public void remove(TriggeredEffect effect) {
        triggeredEffects.remove(effect);
    }
}
