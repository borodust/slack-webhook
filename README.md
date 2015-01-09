slack-webhook
=============

Slack WebHook Integration for Java

# Basic Examples

```java

// Send simple message
SlackWebhook api = new SlackWebhook("id_1/id_2/token");
api.call(new SlackMessage("my message"));

// Send simple message with custom name
SlackWebhook api = new SlackWebhook("id_1/id_2/token");
api.call(new SlackMessage("Mafagafo", "my message"));

// Send simple message in different room
SlackWebhook api = new SlackWebhook("id_1/id_2/token");
api.call(new SlackMessage("#general", null, "my message"));

// Send simple message in different room with custom name
SlackWebhook api = new SlackWebhook("id_1/id_2/token");
api.call(new SlackMessage("#general", "Mafagafo", "my message"));

```

# Configuration

1. Go to *your_team.slack.com/services/new*
2. Search for *Incoming WebHook* and click in `Add`
3. Choose Channel to Post and press `Add Incoming WebHooks Integration`
4. See *Setup Instructions*, now you should have the WebHook URL. Pass this url as an argument to [the constructor](/src/main/java/net/gpedro/integrations/slack/SlackWebhook.java.java#L15).
