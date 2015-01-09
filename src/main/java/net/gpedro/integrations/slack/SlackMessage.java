package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SlackMessage {

	private List<SlackAttachment> attach = new ArrayList<SlackAttachment>();
	private String channel  = null;
	private String icon     = null;
	private JsonObject slackMessage = new JsonObject();
	
	private String text     = null;
	private String username = null;
	
	public SlackMessage(String text) {
		this.text = text;
	}
	
	public SlackMessage(String username, String text) {
		this(text);
		this.username = username;
	}

	public SlackMessage(String channel, String username, String text) {
		this(username, text);
		this.channel = channel;
	}
	
	public SlackMessage addAttachments(SlackAttachment... otherAttachments) {
		this.attach.addAll(Arrays.asList(otherAttachments));
		
		return this;
	}
	
	public JsonObject prepare() {
		if(channel != null) {
			slackMessage.addProperty("channel", channel);
		}

		if(username != null) {
			slackMessage.addProperty("username", username);
		}
		
		if(icon != null) {
			if(icon.contains("http")) {
				slackMessage.addProperty("icon_url", icon);
			} else {
				slackMessage.addProperty("icon_emoji", icon);
			}
		}
		
		if(text == null) {
			throw new IllegalArgumentException("Missing Text field @ SlackMessage");
		} else {
			slackMessage.addProperty("text", text);
		}
		
		if(attach != null && attach.size() > 0) {
			slackMessage.add("attachments", this.prepareAttach());
		}
		
		return slackMessage;
	}
	
	private JsonArray prepareAttach() {
		JsonArray attachs = new JsonArray();
		for(SlackAttachment attach: this.attach) {
			attachs.add(attach.toJson());
		}
		
		return attachs;
	}

	public SlackMessage removeAttachment(int index) {
		this.attach.remove(index);
		return this;
	}
	
	public SlackMessage setAttachments(ArrayList<SlackAttachment> attach) {
		this.attach = new ArrayList<SlackAttachment>(attach);
		return this;
	}

	public SlackMessage setChannel(String channel) {
		this.channel = channel;
		return this;
	}

	// http://www.emoji-cheat-sheet.com/
	public SlackMessage setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public SlackMessage setText(String message) {
		this.text = message;
		return this;
	}

	public SlackMessage setUsername(String username) {
		this.username = username;
		return this;
	}
}
