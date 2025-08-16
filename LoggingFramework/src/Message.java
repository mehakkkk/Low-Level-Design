import java.sql.Timestamp;

public class Message {
    String text;
    long timestamp;
    Level logLevel;

    public Message(Level logLevel, String text) {
        this.logLevel = logLevel;
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    @Override
    public String toString() {
        return "[" +
                "logLevel=" + logLevel +'\'' +
                "text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ']';
    }
}
