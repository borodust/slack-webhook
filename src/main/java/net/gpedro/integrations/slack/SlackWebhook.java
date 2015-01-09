package net.gpedro.integrations.slack;

import com.google.gson.JsonObject;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class SlackWebhook {
	private final String address;
	
	public SlackWebhook(String address) {
		if(address == null) {
			throw new IllegalArgumentException("Address cannot be null");
		}
		this.address = address;
	}

	/**
	 * @param message slack message
	 * @throws SlackApiException
	 */
	public void call(SlackMessage message) {
		if (message == null) {
			throw new IllegalArgumentException("Message cannot be null");
		}

		send(message.prepare());
	}
	
	private void send(JsonObject message) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(address)
				.post(new FormEncodingBuilder().add("payload", message.toString()).build())
				.build();

		try {
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
				throw new SlackApiException("Slack webhook failed. HTTP status code: " + response.code());
			}
		} catch (IOException e) {
			throw new SlackApiException("Unexpected error", e);
		}
	}
}
