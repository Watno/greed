package spacealert.core.planningPhase.eventSequences.premades;

import spacealert.core.Phase;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.planningPhase.eventSequences.EventSequence;
import spacealert.core.planningPhase.eventSequences.IThreatSource;
import spacealert.core.planningPhase.eventSequences.events.manifestations.*;

import java.util.Arrays;

public class Mission1 extends EventSequence {
    public Mission1(IThreatSource threatSource) {
        super(Arrays.asList(
                new StartPhaseOneEvent(),
                new ExternalThreatAppearsEvent(0, 10, 2, true, Zone.WHITE, threatSource),
                new ExternalThreatAppearsEvent(1, 50, 4, false, Zone.BLUE, threatSource),
                new IncomingDataEvent(2, 20),
                new CommunicationsDownEvent(2, 50, 10),
                new DataTransferEvent(3, 5),
                new PhaseEndsEvent(3, 45, Phase.ONE),

                new IncomingDataEvent(3, 50),
                new InternalThreatAppearsEvent(5, 0, 5, false, threatSource),
                new DataTransferEvent(4, 25),
                new DataTransferEvent(4, 25),
                new ExternalThreatAppearsEvent(4, 50, 6, false, Zone.BLUE, threatSource),
                new CommunicationsDownEvent(5, 20, 15),
                new ExternalThreatAppearsEvent(5, 50, 7, true, Zone.RED, threatSource),
                new DataTransferEvent(6, 35),
                new PhaseEndsEvent(7, 30, Phase.TWO),

                new CommunicationsDownEvent(7, 50, 20),
                new DataTransferEvent(8, 20),
                new CommunicationsDownEvent(9, 15, 10),
                new PhaseEndsEvent(10, 0, Phase.THREE)
        ));
    }
}