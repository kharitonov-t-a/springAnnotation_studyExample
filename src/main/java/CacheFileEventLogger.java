import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new ArrayList<Event>();

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cacheSize == cache.size()) {
            writeEventsFromCache();
            cache.clear();
        }


    }

    private void writeEventsFromCache() {
        for (Event newEvent : cache) {
            super.logEvent(newEvent);
        }
    }

    private void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
