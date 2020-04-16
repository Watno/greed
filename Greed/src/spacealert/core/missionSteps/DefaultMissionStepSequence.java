package spacealert.core.missionSteps;

import java.util.ArrayList;
import java.util.List;

public class DefaultMissionStepSequence extends MissionStepSequence {


    public DefaultMissionStepSequence() {
        super(createMissionSteps());
    }


    private static List<IMissionStep> createMissionSteps() {
        List<IMissionStep> steps = new ArrayList<>();

        steps.add(new StartPhaseStep(1));
        steps.addAll(createTurn(1, true));
        steps.addAll(createTurn(2, true));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(3, true));

        steps.add(new StartPhaseStep(2));
        steps.addAll(createTurn(4, true));
        steps.addAll(createTurn(5, true));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(6, true));
        steps.addAll(createTurn(7, true));

        steps.add(new StartPhaseStep(3));
        steps.addAll(createTurn(8, true));
        steps.addAll(createTurn(9, false));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(10, false));
        steps.addAll(createTurn(11, false));
        steps.addAll(createTurn(12, false));

        steps.add(new RocketResolutionStep());
        steps.add(new ThreatActionsStep());

        return steps;
    }


    private static List<IMissionStep> createTurn(int turn, boolean threatAppears) {
        var result = new ArrayList<IMissionStep>();

        result.add(new StartTurnStep(turn));
        if (threatAppears) result.add(new ThreatAppearsStep());
        result.add(new PlayerActionsStep(turn));
        result.add(new ComputeDamageStep());
        result.add(new ThreatActionsStep());
        return result;
    }


}
