package global.base;


import components.Modal;
import enums.LevelEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.authentication.LoginService;
import providers.View;

public abstract class BaseController {
	@FXML
	protected void buttonPeminjamanOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("peminjaman");
	}

	@FXML
	protected void buttonRuanganOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("ruangan");
	}

	@FXML
	protected void buttonFasilitasOnAction(ActionEvent event) {
		LoginService.getInstance().getProfile();
		if (LoginService.getInstance().getSession().get("id") == null) {
			return;
		}

		View.getInstance().set("fasilitas");
	}

	@FXML
	protected void buttonPenggunaOnAction(ActionEvent event) {
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
	protected void buttonLogoutOnAction(ActionEvent event) {
		if (Modal.getInstance().confirmation()) {
			Modal.getInstance().success("Terimakasih telah menggunakan aplikasi SIRUANG");

			LoginService.getInstance().logout();
		}
	}
}
