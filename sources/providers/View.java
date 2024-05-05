package providers;


import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
    private static View instance;

    private final Logger logger;
    private final HashMap<String, String> views = new HashMap<String, String>();

    private Scene scene;

    private View(Logger logger) {
        this.logger = logger;
    }

    public void start(Stage stage) {
        this.logger.debug("Start");

        this.scene = new Scene(this.load(Dependency.getInstance().getStartingView()));

        stage.setTitle("SIRUANG - Sistem Informasi Peminjaman Ruangan");
        stage.setResizable(false);

        stage.setScene(this.scene);
        stage.show();
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

    public void set(String view) {
        this.logger.debug("Set");

        Logger.getInstance().debug("Set");

        scene.setRoot(this.load(view));
    }

    private Parent load(String view) {
        this.logger.debug("Load");

        try {
            return (new FXMLLoader(View.class.getResource("../" + View.getInstance().get(view) + "View.fxml"))).load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
