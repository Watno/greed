package spacealert.core.missionSteps;

import java.util.ArrayList;
import java.util.List;

public class DefaultMissionStepSequence extends MissionStepSequence {

	
	protected DefaultMissionStepSequence() {
		super(createMissionSteps());
	}

	
	private static List<IMissionStep> createMissionSteps() { 
		List<IMissionStep> steps = new ArrayList<IMissionStep>();
		
		steps.addAll(createTurn(1));
		steps.addAll(createTurnWithComputerMaintenance(2,1));
		steps.addAll(createTurn(3));
		
		steps.addAll(createTurn(4));
		steps.addAll(createTurnWithComputerMaintenance(5,2));
		steps.addAll(createTurn(6));
		steps.addAll(createTurn(7));
		
		steps.addAll(createTurn(8));
		steps.addAll(createTurnWithComputerMaintenance(9,3));
		steps.addAll(createTurn(10));
		steps.addAll(createTurn(11));
		steps.addAll(createTurn(12));
		s
		steps.add(new RocketResolutionStep());
		steps.add(new ThreatActionsStep());
		
		return steps;
	}
	 
	
	private static List<IMissionStep> createTurn(int turn) {
		return List.<IMissionStep>of(
				new ThreatAppearsStep(turn),
				new PlayerActionsStep(turn),
				new ComputeDamageStep(),
				new ThreatActionsStep()
				);
	}
	
	private static List<IMissionStep> createTurnWithComputerMaintenance(int turn, int phase) {
		return List.<IMissionStep>of(
				new ThreatAppearsStep(turn),
				new PlayerActionsStep(turn),
				new ComputeDamageStep(),
				new ComputerMaintenanceCheckStep(phase, turn),
				new ThreatActionsStep()
				);
	}

}
