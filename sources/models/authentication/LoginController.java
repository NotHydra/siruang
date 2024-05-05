package models.authentication;


import java.net.URL;
import java.util.ResourceBundle;

import components.Modal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import providers.Logger;
import providers.View;

public class LoginController implements Initializable {
	private final static Logger logger = new Logger(LoginController.class.getName());

	@FXML
	private TextField textFieldUsername;

	@FXML
	private PasswordField passwordFieldPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.debug("Initialize");
	}

	@FXML
	private void buttonLoginOnAction() {
		logger.debug("Button Login On Action");

		try {
			if (this.textFieldUsername.getText() == null || this.textFieldUsername.getText().trim().isEmpty()) {
				throw new IllegalArgumentException("Username tidak boleh kosong");
			}

			if (this.passwordFieldPassword.getText() == null || this.passwordFieldPassword.getText().trim().isEmpty()) {
				throw new IllegalArgumentException("Password tidak boleh kosong");
			}

			if (LoginService.getInstance().login(this.textFieldUsername.getText(), this.passwordFieldPassword.getText())) {
				Modal.getInstance().success("Login berhasil");

				View.getInstance().set("peminjaman");
			}
		}
		catch (Exception e) {
			Modal.getInstance().fail(e.getMessage());

			logger.error("Failed to login: " + e.getMessage());
		}
	}
}
