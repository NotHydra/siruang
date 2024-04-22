import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import providers.Logger;
import providers.View;

public class App extends Application {
	private static Scene scene;

	public static void main(String[] args) {
		Logger.getInstance().info("Starting application");

		View.getInstance().add("book", "models/book/Book");
		View.getInstance().add("author", "models/author/Author");
		View.getInstance().add("genre", "models/genre/Genre");

		App.launch(args);
	};

	@Override
	public void start(Stage stage) {
		scene = new Scene(App.loadView("genre"));
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
	}
}
