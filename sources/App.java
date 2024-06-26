
import javafx.application.Application;
import javafx.stage.Stage;

import providers.Logger;
import providers.View;

public class App extends Application {
	public static void main(String[] args) {
		Logger.getInstance().info("Starting application");

		View.getInstance().add("login", "models/authentication/Login");
		View.getInstance().add("peminjaman", "models/peminjaman/Peminjaman");
		View.getInstance().add("ruangan", "models/ruangan/Ruangan");
		View.getInstance().add("fasilitas", "models/fasilitas/Fasilitas");
		View.getInstance().add("pengguna", "models/pengguna/Pengguna");

		App.launch(args);
	};

	@Override
	public void start(Stage stage) {
		View.getInstance().start(stage);
	}
}
