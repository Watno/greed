package spacealert.core.triggeredEffects;

public interface ICanHaveTriggeredEffectsAttached {
    void attach(TriggeredEffect effect);

    void remove(TriggeredEffect effect);
}
