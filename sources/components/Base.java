package components;


import enums.LevelEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.authentication.LoginService;
import providers.View;

public abstract class Base {
	@FXML
	void buttonPeminjamanOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("peminjaman");
	}

	@FXML
	void buttonRuanganOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("ruangan");
	}

	@FXML
	void buttonFasilitasOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("fasilitas");
	}

	@FXML
	void buttonPenggunaOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		if (((LevelEnum) LoginService.getInstance().getSession().get("level")) == LevelEnum.OPERATOR) {
			Modal.getInstance().fail("Operator tidak memiliki akses ke menu pengguna");

			return;
		}

		View.getInstance().set("pengguna");
	}

	@FXML
	void buttonLogoutOnAction(ActionEvent event) {
		if (Modal.getInstance().confirmation()) {
			Modal.getInstance().success("Terimakasih telah menggunakan aplikasi SIRUANG");

			LoginService.getInstance().logout();
		}
	}
}
