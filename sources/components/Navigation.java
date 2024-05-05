package components;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import providers.View;

public class Navigation {
	@FXML
	void buttonPeminjamanOnAction(ActionEvent event) {
		View.getInstance().set("peminjaman");
	}

	@FXML
	void buttonRuanganOnAction(ActionEvent event) {
		View.getInstance().set("ruangan");
	}

	@FXML
	void buttonFasilitasOnAction(ActionEvent event) {
		View.getInstance().set("fasilitas");
	}

	@FXML
	void buttonPenggunaOnAction(ActionEvent event) {
		View.getInstance().set("pengguna");
	}

	@FXML
	void buttonLogoutOnAction(ActionEvent event) {

	}
}
