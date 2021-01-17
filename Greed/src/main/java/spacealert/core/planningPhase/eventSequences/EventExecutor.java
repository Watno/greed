package spacealert.core.planningPhase.eventSequences;

import spacealert.core.planningPhase.PlanningPhase;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventExecutor {
    private static final ScheduledThreadPoolExecutor timer = new ScheduledThreadPoolExecutor(1);

    public CompletableFuture<Void> run(EventSequence eventSequence, PlanningPhase planningPhase) {
        var completionState = new CompletableFuture<Void>();
        for (var event : eventSequence.getEvents()) {
            event.addToSchedule(this, planningPhase);
        }
        timer.schedule(() -> completionState.complete(null), eventSequence.getLength().getSeconds(), TimeUnit.SECONDS);
        return completionState;
    }

    public void schedule(Duration triggerTime, Runnable event) {
        timer.schedule(event, triggerTime.getSeconds(), TimeUnit.SECONDS);
    }

}
