package net.gpedro.integrations.slack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SlackAttachment {

	private String fallback;
	private String text;
	private String pretext;
	private String color;
	private List<SlackField> fields;

	public SlackAttachment(String fallback) {
		this.fallback = fallback;
	}

	public SlackAttachment addFields(SlackField... field) {
		if(this.fields == null) {
			this.fields = new ArrayList<SlackField>();
		}
		
		this.fields.addAll(Arrays.asList(field));
		
		return this;
	}

	private boolean isHex(String pair) {
		return pair.matches("^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
	}
	
	private JsonArray prepareFields() {
		JsonArray data = new JsonArray();
		for(SlackField field: fields) {
			data.add(field.toJson());
		}
	
		return data;
	}

	public SlackAttachment removeFields(int index) {
		this.fields.remove(index);
		return this;
	}

	public SlackAttachment setColor(String color) {
		if(color != null) {
			if(color.length() > 0 && color.charAt(0) == '#') {
				if(!isHex(color.substring(1))) {
					throw new IllegalArgumentException("Invalid Hex Color @ SlackAttachment");
				}
			} else if(!color.matches("^(good|warning|danger)$")) {
				throw new IllegalArgumentException("Invalid PreDefined Color @ SlackAttachment");
			}
		}
		
		this.color = color;
		
		return this;
	}

	public SlackAttachment setFallback(String fallback) {
		this.fallback = fallback;
		return this;
	}

	public SlackAttachment setFields(ArrayList<SlackField> fields) {
		this.fields = new ArrayList<SlackField>(fields);
		return this;
	}

	public SlackAttachment setPretext(String pretext) {
		this.pretext = pretext;
		
		return this;
	}
	
	public SlackAttachment setText(String text) {
		this.text = text;
		
		return this;
	}

	public JsonObject toJson() {
		JsonObject data = new JsonObject();
		
		if(fallback == null) {
			throw new IllegalArgumentException("Missing Fallback @ SlackAttachment");
		} else {
			data.addProperty("fallback", fallback);
		}

		if(text != null) {
			data.addProperty("text", text);
		}
		
		if(pretext != null) {
			data.addProperty("pretext", pretext);
		}
		
		if(color != null) {
			data.addProperty("color", color);
		}
		
		if(fields != null && fields.size() > 0) {
			data.add("fields", prepareFields());
		}
		
		return data;
	}
	
}
