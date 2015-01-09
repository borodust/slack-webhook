package net.gpedro.integrations.slack;

/**
 * @author Pavel Korolev &lt;dev&#64borodust.org&gt;
 */
public class SlackApiException extends RuntimeException {
    public SlackApiException(String message) {
        super(message);
    }

    public SlackApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
