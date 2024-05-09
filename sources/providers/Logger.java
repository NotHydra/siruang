package providers;

public class Logger {
    private static final Logger instance = new Logger("Logger");

    private final String context;

    public Logger(String context) {
        this.context = context;
    }

    public static Logger getInstance() {
        return instance;
    }

    public void info(String message) {
        System.out.println("\u001B[34m" + "[" + Utility.currentTimestamp() + "][" + this.context + "][info] " + message + "\u001B[0m");
    }

    public void debug(String message) {
        System.out.println("\u001B[32m" + "[" + Utility.currentTimestamp() + "][" + this.context + "][debug] " + message + "\u001B[0m");
    }

    public void warn(String message) {
        System.out.println("\u001B[33m" + "[" + Utility.currentTimestamp() + "][" + this.context + "][warn] " + message + "\u001B[0m");
    }

    public void error(String message) {
        System.err.println("\u001B[31m" + "[" + Utility.currentTimestamp() + "][" + this.context + "][error] " + message + "\u001B[0m");
    }
}
