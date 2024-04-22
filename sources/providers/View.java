package providers;


import java.util.HashMap;

public class View {
    private static View instance;

    private final Logger logger;
    private final HashMap<String, String> views = new HashMap<String, String>();

    private View(Logger logger) {
        this.logger = logger;
    }

    public static View getInstance() {
        if (View.instance == null) {
            View.instance = new View(new Logger(View.class.getName()));
        }

        View.instance.logger.debug("Get Instance");

        return View.instance;
    }

    public String get(String name) {
        this.logger.debug("Get");

        return this.views.get(name);
    }

    public void add(String name, String path) {
        this.logger.debug("Add");

        this.views.put(name, path);
    }
}
