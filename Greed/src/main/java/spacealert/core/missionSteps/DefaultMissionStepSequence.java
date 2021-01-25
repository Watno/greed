package spacealert.core.missionSteps;

import spacealert.core.threats.templates.Threat;

import java.util.ArrayList;
import java.util.List;

public class DefaultMissionStepSequence extends MissionStepSequence {

    public DefaultMissionStepSequence(List<List<Threat>> threatsBySpawn) {
        super(createMissionSteps(threatsBySpawn));
    }

    private static List<MissionStep> createMissionSteps(List<List<Threat>> threatsBySpawn) {
        List<MissionStep> steps = new ArrayList<>();

        steps.add(new StartPhaseStep(1));
        steps.addAll(createTurn(1, true, threatsBySpawn));
        steps.addAll(createTurn(2, true, threatsBySpawn));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(3, true, threatsBySpawn));

        steps.add(new StartPhaseStep(2));
        steps.addAll(createTurn(4, true, threatsBySpawn));
        steps.addAll(createTurn(5, true, threatsBySpawn));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(6, true, threatsBySpawn));
        steps.addAll(createTurn(7, true, threatsBySpawn));

        steps.add(new StartPhaseStep(3));
        steps.addAll(createTurn(8, true, threatsBySpawn));
        steps.addAll(createTurn(9, false, threatsBySpawn));
        steps.add(new ComputerMaintenanceCheckStep());
        steps.addAll(createTurn(10, false, threatsBySpawn));
        steps.addAll(createTurn(11, false, threatsBySpawn));
        steps.addAll(createTurn(12, false, threatsBySpawn));

        steps.add(new RocketResolutionStep());
        steps.add(new ThreatActionsStep());

        return steps;
    }


    private static List<MissionStep> createTurn(int turn, boolean threatAppears, List<List<Threat>> threatsBySpawn) {
        var result = new ArrayList<MissionStep>();

        result.add(new StartTurnStep(turn));
        if (threatAppears) result.add(new ThreatAppearsStep(threatsBySpawn.get(turn - 1)));
        result.add(new PlayerActionsStep(turn));
        result.add(new ComputeDamageStep());
        result.add(new ThreatActionsStep());
        return result;
    }


}
