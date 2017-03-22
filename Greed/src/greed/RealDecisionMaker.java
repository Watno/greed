package greed;

public class RealDecisionMaker implements DecisionMaker {
	GreedConnection connection;
	GreedGame theGame;
	GreedPlayer thePlayer;
	
	public RealDecisionMaker(GreedConnection connection, GreedGame theGame, GreedPlayer thePlayer) {
		this.connection = connection;
		this.theGame = theGame;
		this.thePlayer = thePlayer;
		connection.setDecider(this);
	}

	@Override
	public int pickDraftIndex() {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("draftPile", false, "Pick a card to draft"));
	}

	@Override
	public int pickHandIndex() {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("hand", false, "Pick a card to play"));
	}

	@Override
	public int pickThugIndex(String reason) {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("thug", false, "Pick a Thug"+reason+"!"));
	}

	@Override
	public int pickHoldingIndex(String reason) {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("holding", false, "Pick a Holding"+reason+"!"));
	}

	@Override
	public int pickHandIndexOptional(String reason) {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("hand", true, "You may pick a card from your Hand"+reason+"!"));
	}

	@Override
	public int pickThugIndexOptional(String reason) {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("thug", true, "You may pick a Thug"+reason+"!"));
	}

	@Override
	public int pickHoldingIndexOptional(String reason) {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("holding", true, "You may pick a Holding"+reason+"!"));
	}

	@Override
	public boolean makeYesNoChoice() {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("YesNo", true, "Do you want to or not?"))==0; //yes is 0, no is expected to be -1
	}
	
	@Override
	public void sendGameState(String gameState) {
		connection.sendState(gameState);
	}
	
	public void replaceByBot() {
		thePlayer.replaceByBot();//still need to handle currently awaited decision by old decider
	}


}
