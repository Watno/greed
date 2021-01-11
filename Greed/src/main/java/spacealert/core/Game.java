package spacealert.core;

import spacealert.core.actionCards.ActionBoard;
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

public class Game implements ICanHaveTriggeredEffectsAttached {
    private Collection<ICrewMember> crewMembers;
    private StationLayout stationLayout;
    private Map<Zone, Gravolift> gravolifts;
    private Map<Zone, Reactor> reactors;
    private Map<Zone, Shield> shields;
    private Map<Position, Cannon> cannons;
    private Map<Zone, Trajectory> trajectories;
    private Map<Zone, List<IDamageable>> damagetokens;
    private Trajectory internalTrajectory;
    private Space space;
    private ArrayList<Rocket> availableRockets = new ArrayList<>(List.of(new Rocket(), new Rocket(), new Rocket()));


    private ArrayList<List<Threat>> threatsBySpawn;
    private ArrayList<Threat> activeThreats = new ArrayList<>();
    private ArrayList<Threat> destroyedThreats = new ArrayList<>();
    private ArrayList<Threat> survivedThreats = new ArrayList<>();

    private Set<Integer> mouseJuggles = new HashSet<>();
    private ArrayList<Integer> visualConfirmations = new ArrayList<>(Collections.nCopies(12, 0));
    private int currentTurn;
    private int currentPhase;
    private Optional<Rocket> nextTurnRocket = Optional.empty();
    private Optional<Rocket> thisTurnRocket = Optional.empty();

    private Random random = new Random();

    public Game() {
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

    public Game(Collection<ActionBoard> actionBoards, List<List<Threat>> threatsBySpawn) {
        this();

        crewMembers = actionBoards.stream()
                .map(x -> new CrewMember(stationLayout.getStation(new Position(Deck.UPPER, Zone.WHITE)), x))
                .collect(Collectors.toList());

        for (var crewMember : crewMembers) {
            var x = stationLayout.getStation(new Position(Deck.UPPER, Zone.WHITE));
            x.addCrewMember(crewMember);
        }
        this.threatsBySpawn = new ArrayList<>(threatsBySpawn);
    }


    public Collection<ICrewMember> getCrewMembers() {
        return crewMembers;
    }

    public boolean mouseJuggled() {
        return mouseJuggles.contains(currentPhase);
    }

    public void juggleMouse() {
        mouseJuggles.add(currentPhase);
    }

    public void delayAllCrewMembers() {
        for (ICrewMember crewMember : crewMembers) {
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

    public List<Threat> getSurvivedThreats() {
        return List.copyOf(survivedThreats);
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
        int index = currentTurn - 1;
        visualConfirmations.set(index, visualConfirmations.get(index) + 1);
	}

    public void recordAsSurvived(Threat threat) {
        activeThreats.remove(threat);
        survivedThreats.add(threat);
    }

    public void recordAsDestroyed(Threat threat) {
        activeThreats.remove(threat);
        destroyedThreats.add(threat);
    }

    public GameLost spawnThreats() {
        for (var threat : threatsBySpawn.get(currentTurn - 1)) {
            activeThreats.add(threat);
            var gameLost = threat.spawn(this, currentTurn);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

    public List<DamageSource> getDamageSources() {
        var damageSources = new ArrayList<DamageSource>();
        damageSources.addAll(cannons.values());
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

    private List<TriggeredEffect> triggeredEffects = new ArrayList<>() {
    };

    @Override
    public void attach(TriggeredEffect effect) {
        triggeredEffects.add(effect);
    }

    @Override
    public void remove(TriggeredEffect effect) {
        triggeredEffects.remove(effect);
    }
}
