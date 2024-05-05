package models.pengguna;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import components.Navigation;
import components.Modal;

import enums.LevelEnum;

import providers.Logger;

public class PenggunaController extends Navigation implements Initializable {
	private final static Logger logger = new Logger(PenggunaController.class.getName());

	private final static PenggunaService service = PenggunaService.getInstance();

	private PenggunaModel selectedModel;

	@FXML
	private Label labelProfile;

	@FXML
	private TableView<PenggunaModel> tableMain;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnNama;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnUsername;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnAktif;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnLevel;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnDibuat;

	@FXML
	private TableColumn<PenggunaModel, String> tableMainColumnDiubah;

	@FXML
	private TextField textFieldNama;

	@FXML
	private TextField textFieldUsername;

	@FXML
	private ChoiceBox<String> choiceBoxAktif;

	@FXML
	private PasswordField passwordFieldPassword;

	@FXML
	private PasswordField passwordFieldKonfirmasiPassword;

	@FXML
	private TextField textFieldDibuat;

	@FXML
	private TextField textFieldDiubah;

	@FXML
	private PasswordField passwordFieldPasswordBaru;

	@FXML
	private PasswordField passwordFieldKonfirmasiPasswordBaru;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("initialize");

		tableMainColumnNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
		tableMainColumnUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
		tableMainColumnAktif.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAktif() ? "Aktif" : "Tidak Aktif"));
		tableMainColumnLevel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLevel().value));
		tableMainColumnDibuat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDibuat().toString()));
		tableMainColumnDiubah.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiubah().toString()));

		tableMain.setItems(FXCollections.observableArrayList(service.find()));

		choiceBoxAktif.getItems().addAll(new String[] { "Pilih Status Aktif", "Aktif", "Tidak Aktif" });
		choiceBoxAktif.setValue(choiceBoxAktif.getItems().get(0));
	}

	public void tableReload() {
		logger.debug("Table Reload");

		tableMain.setItems(FXCollections.observableArrayList(service.find()));
	}

	@FXML
	void tableMainItemClick(MouseEvent event) {
		logger.debug("Table Main Item Click");

		this.selectedModel = tableMain.getSelectionModel().getSelectedItem();

		textFieldNama.setText(this.selectedModel.getNama());
		textFieldUsername.setText(this.selectedModel.getUsername());
		choiceBoxAktif.setValue(this.selectedModel.getAktif() ? "Aktif" : "Tidak Aktif");
		textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
		textFieldDiubah.setText(this.selectedModel.getDiubah().toString());
	}

	@FXML
	void buttonTambahOnAction(ActionEvent event) {
		logger.debug("Button Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				if (choiceBoxAktif.getValue() == null || choiceBoxAktif.getValue().equals("Pilih Status Aktif")) {
					throw new Exception("Status Aktif tidak boleh kosong");
				}

				if (passwordFieldPassword.getText() == null || passwordFieldPassword.getText().trim().isEmpty()) {
					throw new Exception("Password tidak boleh kosong");
				}

				if (passwordFieldKonfirmasiPassword.getText() == null || passwordFieldKonfirmasiPassword.getText().trim().isEmpty()) {
					throw new Exception("Konfirmasi Password tidak boleh kosong");
				}

				if (!passwordFieldPassword.getText().equals(passwordFieldKonfirmasiPassword.getText())) {
					throw new Exception("Password dan Konfirmasi Password harus sama");
				}

				service.add(new PenggunaModel(
						textFieldNama.getText(),
						textFieldUsername.getText(),
						passwordFieldPassword.getText(),
						(choiceBoxAktif.getValue() == "Aktif"),
						LevelEnum.OPERATOR));

				this.tableReload();

				tableMain.getSelectionModel().select(tableMain.getItems().size() - 1);
				this.selectedModel = tableMain.getItems().get(tableMain.getItems().size() - 1);

				textFieldNama.setText(this.selectedModel.getNama());
				textFieldUsername.setText(this.selectedModel.getUsername());
				choiceBoxAktif.setValue(this.selectedModel.getAktif() ? "Aktif" : "Tidak Aktif");
				passwordFieldPassword.clear();
				passwordFieldKonfirmasiPassword.clear();
				textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
				textFieldDiubah.setText(this.selectedModel.getDiubah().toString());
			}
			catch (Exception e) {
				Modal.getInstance().fail(e.getMessage());

				logger.error(e.getMessage());
			}
		}
	}

	@FXML
	void buttonUbahOnAction(ActionEvent event) {
		logger.debug("Button Ubah On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null) {
				try {
					if (this.selectedModel.getLevel() == LevelEnum.ADMINISTRATOR) {
						throw new Exception("Administrator tidak bisa diubah");
					}

					if (choiceBoxAktif.getValue() == null || choiceBoxAktif.getValue().equals("Pilih Status Aktif")) {
						throw new Exception("Status Aktif tidak boleh kosong");
					}

					service.change(
							this.selectedModel.getId(),
							new PenggunaModel(
									textFieldNama.getText(),
									textFieldUsername.getText(),
									choiceBoxAktif.getValue() == "Aktif",
									this.selectedModel.getLevel()));

					final int selectedIndex = tableMain.getSelectionModel().getSelectedIndex();

					this.tableReload();

					tableMain.getSelectionModel().select(selectedIndex);
					this.selectedModel = tableMain.getItems().get(selectedIndex);

					textFieldNama.setText(this.selectedModel.getNama());
					textFieldUsername.setText(this.selectedModel.getUsername());
					choiceBoxAktif.setValue(this.selectedModel.getAktif() ? "Aktif" : "Tidak Aktif");
					textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
					textFieldDiubah.setText(this.selectedModel.getDiubah().toString());
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih pengguna yang ingin diubah");
			}
		}
	}

	@FXML
	void buttonUbahPasswordOnAction(ActionEvent event) {
		logger.debug("Button Ubah Password On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null) {
				try {
					if (this.selectedModel.getLevel() == LevelEnum.ADMINISTRATOR) {
						throw new Exception("Administrator tidak bisa diubah password");
					}

					if (passwordFieldPasswordBaru.getText() == null || passwordFieldPasswordBaru.getText().trim().isEmpty()) {
						throw new Exception("Password Baru tidak boleh kosong");
					}

					if (passwordFieldKonfirmasiPasswordBaru.getText() == null || passwordFieldKonfirmasiPasswordBaru.getText().trim().isEmpty()) {
						throw new Exception("Konfirmasi Password Baru tidak boleh kosong");
					}

					if (!passwordFieldPasswordBaru.getText().equals(passwordFieldKonfirmasiPasswordBaru.getText())) {
						throw new Exception("Password Baru dan Konfirmasi Password Baru harus sama");
					}

					service.changePassword(
							this.selectedModel.getId(),
							passwordFieldPasswordBaru.getText());

					final int selectedIndex = tableMain.getSelectionModel().getSelectedIndex();

					this.tableReload();

					tableMain.getSelectionModel().select(selectedIndex);
					this.selectedModel = tableMain.getItems().get(selectedIndex);

					textFieldNama.setText(this.selectedModel.getNama());
					textFieldUsername.setText(this.selectedModel.getUsername());
					choiceBoxAktif.setValue(this.selectedModel.getAktif() ? "Aktif" : "Tidak Aktif");
					textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
					textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

					passwordFieldPasswordBaru.clear();
					passwordFieldKonfirmasiPasswordBaru.clear();
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih pengguna yang ingin diubah passwordnya");
			}
		}
	}

	@FXML
	void buttonHapusOnAction(ActionEvent event) {
		logger.debug("Button Hapus On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null) {
				try {
					if (this.selectedModel.getLevel() == LevelEnum.ADMINISTRATOR) {
						throw new Exception("Administrator tidak bisa dihapus");
					}

					service.remove(this.selectedModel.getId());

					this.tableReload();

					textFieldNama.clear();
					textFieldUsername.clear();
					choiceBoxAktif.setValue(choiceBoxAktif.getItems().get(0));
					passwordFieldPassword.clear();
					passwordFieldKonfirmasiPassword.clear();
					textFieldDibuat.clear();
					textFieldDiubah.clear();
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih pengguna yang ingin dihapus");
			}
		}
	}
}
