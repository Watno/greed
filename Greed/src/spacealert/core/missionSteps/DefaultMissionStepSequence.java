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
		steps.addAll(createTurn(1));
        steps.addAll(createTurnWithComputerMaintenance(2));
		steps.addAll(createTurn(3));

        steps.add(new StartPhaseStep(2));
		steps.addAll(createTurn(4));
        steps.addAll(createTurnWithComputerMaintenance(5));
		steps.addAll(createTurn(6));
		steps.addAll(createTurn(7));

        steps.add(new StartPhaseStep(3));
		steps.addAll(createTurn(8));
        steps.addAll(createTurnWithComputerMaintenance(9));
		steps.addAll(createTurn(10));
		steps.addAll(createTurn(11));
		steps.addAll(createTurn(12));

		steps.add(new RocketResolutionStep());
		steps.add(new ThreatActionsStep());
		
		return steps;
	}
	 
	
	private static List<IMissionStep> createTurn(int turn) {
		return List.of(
                new StartTurnStep(turn),
				new ThreatAppearsStep(turn),
				new PlayerActionsStep(turn),
				new ComputeDamageStep(),
				new ThreatActionsStep()
				);
	}

    private static List<IMissionStep> createTurnWithComputerMaintenance(int turn) {
		return List.of(
                new StartTurnStep(turn),
				new ThreatAppearsStep(turn),
				new PlayerActionsStep(turn),
				new ComputeDamageStep(),
                new ComputerMaintenanceCheckStep(),
				new ThreatActionsStep()
				);
	}

}
