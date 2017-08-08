package server;

import server.data.ChatMessage;
import server.data.User;

public interface ChreetListener {
	
	void onConnectionOpened();
	void onConnectionClosed();
	
	/**
	 * Wird aufgerufen, wenn sich der Anmeldestatus, also der angemeldete Nutzer, verändert hat
	 * @param status den aktuellen Anmeldestatus
	 * @param currentUser den aktuellen Nutzer, kann null sein
	 */
	void onAuthChanged(User.AuthStatus status, User currentUser);
	
	void onMessageReceived(ChatMessage msg);
}
