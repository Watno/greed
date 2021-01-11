package spacealert.core.triggeredEffects;

import java.util.ArrayList;
import java.util.List;

public abstract class TriggeredEffect {
    private List<ICanHaveTriggeredEffectsAttached> hosts = new ArrayList<>();

    public void attachTo(ICanHaveTriggeredEffectsAttached host) {
        hosts.add(host);
        host.attach(this);
    }

    public void remove() {
        for (var host : hosts) {
            host.remove(this);
        }
        hosts.clear();
    }

}
