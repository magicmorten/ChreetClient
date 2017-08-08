package server.data;

import com.google.gson.JsonObject;

public class ChatMessage {

	private long id;
	
	private User sender;
	private long sendTime; //Unix-Zeitstempel an dem die Nachricht versendet wurde
	
	private String content;
	
	public ChatMessage(long id, User sender, String content, long sendTime) {
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.sendTime = sendTime;
	}
	
	public static ChatMessage parseFromJson(JsonObject obj) {
		long id = obj.get("id").getAsLong();
		
		User sender = User.parseFromJson(obj.get("sender").getAsJsonObject());
		long sendTime = obj.get("sent_at").getAsLong();
		String content = obj.get("content").getAsString();
		
		return new ChatMessage(id, sender, content, sendTime);
	}
	
	public long getId() {
		return id;
	}
	
	public long getSendTime() {
		return sendTime;
	}
	
	public User getSender() {
		return sender;
	}
	
	public String getContent() {
		return content;
	}
	
	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("id", getId());
		obj.add("sender", getSender().toJson());
		obj.addProperty("sent_at", getSendTime());
		obj.addProperty("content", getContent());
		
		return obj;
	}
}
