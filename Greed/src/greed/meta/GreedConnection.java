package greed.meta;

import org.webbitserver.WebSocketConnection;

import greed.meta.lobby.Table;


public class GreedConnection {
	private WebSocketConnection connection;
	private String name = "defaultname";
	private Table table = null;
	private boolean ready = false;
	private RealDecisionMaker decider = null;
	
	
	
	public GreedConnection(WebSocketConnection connection) {
		this.connection = connection;
	}
	
	
	public synchronized int requestInput(String request) {
		connection.send(request);
	    try{
	    	wait();
	    	connection.send(JSONGenerator.createPrompt("empty", false, ""));
	    	return (Integer) connection.data("input");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
	    return -1;
	}
	
	public synchronized void resign() {
		if (decider!=null) {
			decider.replaceByBot();
			decider=null;
			table.removePlayer(this);
			connection.data("input",0);
			notify();
		}
	}
	
	public void sendState(String state) {
		connection.send(state);
	}
	
	public void sendLobby(String lobby) {
		connection.send(lobby);
	}
	
	public void allowReturnToLobby() {
		connection.send(JSONGenerator.createPrompt("returnToLobby", false, "Game Over"));
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Table getTable() {
		return table;
	}


	public void setTable(Table table) {
		this.table = table;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	public void setReady(boolean ready) {
		this.ready=ready;
		if (ready) {
			boolean starting=true;
			for (GreedConnection player : table.getPlayers()) {
				starting &=player.isReady();
			}
			if (starting) {
				table.startGame();
			}
		}
	}


	public RealDecisionMaker getDecider() {
		return decider;
	}


	public void setDecider(RealDecisionMaker decider) {
		this.decider = decider;
	}
}
