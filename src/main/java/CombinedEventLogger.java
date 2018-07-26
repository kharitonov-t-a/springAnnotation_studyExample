import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    private Collection loggers;

    public CombinedEventLogger(Collection loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (Object logger: loggers) {
            ((EventLogger)logger).logEvent(event);
        }
    }
}
