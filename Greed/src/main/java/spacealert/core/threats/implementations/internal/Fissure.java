package spacealert.core.threats.implementations.internal;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Interceptors;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.InternalThreat;
import spacealert.core.triggeredEffects.FissureXEffect;
import spacealert.core.triggeredEffects.FissureYEffect;
import spacealert.core.triggeredEffects.TriggeredEffect;

import java.util.Optional;

public class Fissure extends InternalThreat {
    public Fissure() {
        super(2, 2, 4, 8, new Position(Deck.UPPER, Zone.WHITE));
    }

    @Override
    public boolean alwaysGetsTargetedBy(DamageSource damageSource) {
        return (damageSource instanceof Interceptors);
    }

    @Override
    public void assignDamageTo(Game game, int damage, DamageSource source) {
        takeDamage(game, 1);
    }

    private Optional<FissureXEffect> triggeredXEffect = Optional.empty();
    private Optional<FissureYEffect> triggeredYEffect = Optional.empty();

    @Override
    protected GameLost doXAction(Game game) {
        //noinspection OptionalGetWithoutIsPresent
        triggeredXEffect = Optional.of(new FissureXEffect(locations.get(0).getZone().get()));
        game.attach(triggeredXEffect.get());
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        triggeredXEffect.ifPresent(TriggeredEffect::remove);
        if (triggeredYEffect.isEmpty()) {
            triggeredYEffect = Optional.of(new FissureYEffect());
            game.attach(triggeredYEffect.get());
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return GameLost.TRUE;
    }

    @Override
    protected GameLost onDestroyed(Game game) {
        triggeredXEffect.ifPresent(TriggeredEffect::remove);
        triggeredYEffect.ifPresent(TriggeredEffect::remove);
        return GameLost.FALSE;
    }
}
