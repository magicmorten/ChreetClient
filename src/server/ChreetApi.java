package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.data.ChatMessage;
import server.data.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ChreetApi {
	
	private static final String URL = "ws://192.168.60.7:8080/stream";
	
	private URI uri;
	private WebsocketHandler handler;
	private List<ChreetListener> listeners = new ArrayList<>();
	
	private JsonParser parser = new JsonParser();
	
	private boolean logDebugMessages = true;
	
	public ChreetApi(URI uri) {
		this.uri = uri;
		handler = new WebsocketHandler(this);
	}
	
	public URI getServerUri() {
		return uri;
	}
	
	public void startUp() {
		handler.connect();
	}
	
	public void addListener(ChreetListener listener) {
		this.listeners.add(listener);
	}
	
	public void sendPacket(JsonObject packet, String packetType) {
		if (!packet.has("packet_type")) {
			packet.addProperty("packet_type", packetType);
		}
		
		String toSend = packet.toString();
		if (logDebugMessages)
			System.out.println("TX: " + toSend);
		
		handler.send(toSend);
	}
	
	public void login(String user, String password) {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("version", 1);
		obj.addProperty("user", user);
		obj.addProperty("pass", password);
		
		sendPacket(obj, "auth_login");
	}
	
	public void sendChatMessage(String msg) {
		JsonObject obj = new JsonObject();
		obj.addProperty("content", msg);
		sendPacket(obj, "send_msg");
	}
	
	void handleIncomingPacket(String message) {
		if (logDebugMessages)
			System.out.println("RX: " + message);
		
		JsonObject obj = parser.parse(message).getAsJsonObject();
		String packetType = obj.get("packet_type").getAsString();
		switch (packetType) {
			case "AUTH_CHANGED":
				User.AuthStatus status = User.AuthStatus.valueOf(obj.get("status").getAsString());
				User user = null;
				if (obj.get("user").isJsonObject()) {
					user = User.parseFromJson(obj.get("user").getAsJsonObject());
				}
				broadcastAuthChanged(status, user);
				break;
			case "RECEIVED_MSG":
				ChatMessage msg = ChatMessage.parseFromJson(obj.get("message").getAsJsonObject());
				broadcastChatMessageReceived(msg);
				break;
			case "ACK": //ignore for now
				break;
		}
	}
	
	//Sending events to listeners
	void broadcastConnectionEstablished() {
		if (logDebugMessages)
			System.out.println("Connection established");
		listeners.forEach(ChreetListener::onConnectionOpened);
	}
	
	void broadcastConnectionClosed() {
		if (logDebugMessages)
			System.out.println("Connection closed");
		listeners.forEach(ChreetListener::onConnectionClosed);
	}
	
	private void broadcastAuthChanged(User.AuthStatus status, User user) {
		listeners.forEach((l) -> l.onAuthChanged(status, user));
	}
	
	private void broadcastChatMessageReceived(ChatMessage msg) {
		listeners.forEach((l) -> l.onMessageReceived(msg));
	}
}
