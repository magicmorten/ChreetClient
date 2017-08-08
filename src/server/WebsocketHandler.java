package server;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebsocketHandler extends WebSocketClient {
	
	private ChreetApi api;
	
	WebsocketHandler(ChreetApi api) {
		super(api.getServerUri());
		this.api = api;
	}
	
	public void onOpen(ServerHandshake serverHandshake) {
		api.broadcastConnectionEstablished();
	}
	
	@Override
	public void onMessage(String s) {
		api.handleIncomingPacket(s);
	}
	
	
	public void onClose(int i, String s, boolean b) {
		api.broadcastConnectionClosed();
	}
	
	public void onError(Exception e) {
		e.printStackTrace();
	}
}
