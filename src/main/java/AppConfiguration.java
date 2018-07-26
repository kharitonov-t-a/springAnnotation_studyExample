import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.*;

@Configuration
public class AppConfiguration {

    public String id = "1";
    public String name = "John Smith";
    public String greeting = "Hello!";
    public Map<EventType, EventLogger> loggerMap = new HashMap<EventType, EventLogger>();
    public Date Date;
    public  ConsoleEventLogger consoleEventLogger;
    public FileEventLogger fileEventLogger;
    public CacheFileEventLogger cacheFileEventLogger;
    public CombinedEventLogger combinedEventLogger;
    public Collection<EventLogger> loggers = new ArrayList<EventLogger>();


    @Bean(name = "app")
    public App app() {
        Date = new Date();
        consoleEventLogger = consoleEventLogger();
        fileEventLogger = fileEventLogger();
        cacheFileEventLogger = cacheFileEventLogger();
        combinedEventLogger = combinedEventLogger();
        loggerMap.put(EventType.INFO, consoleEventLogger);
        loggerMap.put(EventType.ERROR, fileEventLogger);
        return new App(client(), combinedEventLogger, loggerMap);
    }

    @Bean(name = "client")
    public Client client() {
        Client client = new Client(id, name);
        client.setGreeting(greeting);
        return client;
    }

    @Bean(name = "event")
    @Scope("prototype")
    public Event event(){
        return new Event(Date, DateFormat.getDateTimeInstance());
    }

    @Bean(name = "consoleEventLogger")
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(initMethod = "init", name = "fileEventLogger")
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("log.txt");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy", name = "cacheFileEventLogger")
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("log.txt", 2);
    }

    @Bean(name = "combinedEventLogger")
    public CombinedEventLogger combinedEventLogger() {
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return new CombinedEventLogger(loggers);
    }


}
