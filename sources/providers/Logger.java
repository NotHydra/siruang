package providers;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final Logger instance = new Logger("Logger");

    private final String context;

    public Logger(String context) {
        this.context = context;
    }

    public static Logger getInstance() {
        return instance;
    }

    private String currentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public void info(String message) {
        System.out.println("\u001B[34m" + "[" + this.currentTimestamp() + "][" + this.context + "][info] " + message + "\u001B[0m");
    }

    public void debug(String message) {
        System.out.println("\u001B[32m" + "[" + this.currentTimestamp() + "][" + this.context + "][debug] " + message + "\u001B[0m");
    }

    public void warn(String message) {
        System.out.println("\u001B[33m" + "[" + this.currentTimestamp() + "][" + this.context + "][warn] " + message + "\u001B[0m");
    }

    public void error(String message) {
        System.err.println("\u001B[31m" + "[" + this.currentTimestamp() + "][" + this.context + "][error] " + message + "\u001B[0m");
    }

}
