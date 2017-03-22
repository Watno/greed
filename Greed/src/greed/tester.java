package greed;

import java.util.ArrayList;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

public class tester {

	public static void main(String[] args) {
//    	GreedGame theGame = new GreedGame(3);
//    	theGame.startGame();
	ArrayList<GreedConnection> connections = new ArrayList<GreedConnection>();
	    WebServer webServer = WebServers.createWebServer(8080);
	    webServer.add(new StaticFileHandler("/static-files"));
	    WebsocketHandler handler = new WebsocketHandler(connections);
	    webServer.add("/websocket-echo", handler);
	    webServer.start();
	    
//	    while (true) {
//	    	System.out.println("waiting for player");
//		    synchronized (connections) {
//	            try{
//	                connections.wait();
//	            }catch(InterruptedException e){
//	                e.printStackTrace();
//	            }
//		    }
//	    	new Thread (new GreedThread(connections.get(connections.size()-1))).start();
//	    }
	}

}
