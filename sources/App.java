<<<<<<< HEAD
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
=======
import javafx.application.Application;
import javafx.stage.Stage;

>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
import providers.Logger;
import providers.View;

public class App extends Application {
<<<<<<< HEAD
	private static Scene scene;

=======
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
	public static void main(String[] args) {
		Logger.getInstance().info("Starting application");

		View.getInstance().add("fasilitas", "models/fasilitas/Fasilitas");

		App.launch(args);
	};

	@Override
	public void start(Stage stage) {
<<<<<<< HEAD
		scene = new Scene(App.loadView("fasilitas"));
		stage.setScene(App.scene);
		stage.show();
	}

	public static void setView(String view) {
		Logger.getInstance().debug("Set View");

		scene.setRoot(App.loadView(view));
	}

	private static Parent loadView(String view) {
		try {
			return (new FXMLLoader(App.class.getResource(View.getInstance().get(view) + "View.fxml"))).load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
=======
		View.getInstance().start(stage);
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
	}
}
