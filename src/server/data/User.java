package server.data;

import com.google.gson.JsonObject;

public class User {
	
	public enum AuthStatus {
		SUCCESS, WRONG_CREDENTIALS, LOCATION_BLOCKED, SERVER_ERROR
	}
	
	private long id;
	private String username;
	
	public User(long id, String username) {
		this.id = id;
		this.username = username;
	}
	
	public static User parseFromJson(JsonObject obj) {
		long id = obj.get("id").getAsLong();
		String username = obj.get("username").getAsString();
		
		return new User(id, username);
	}
	
	public long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("id", getId());
		obj.addProperty("username", getUsername());
		
		return obj;
	}

}
