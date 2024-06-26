package models.fasilitas;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.authentication.LoginService;
import components.Modal;
import enums.LevelEnum;
import global.base.BaseController;
import providers.Logger;
import providers.Utility;

public class FasilitasController extends BaseController implements Initializable {
	private final static Logger logger = new Logger(FasilitasController.class.getName());

	private final static FasilitasService service = FasilitasService.getInstance();

	private FasilitasModel selectedModel;

	@FXML
	private Label labelProfile;

	@FXML
	private TableView<FasilitasModel> tableMain;

	@FXML
	private TableColumn<FasilitasModel, String> tableMainColumnNama;

	@FXML
	private TableColumn<FasilitasModel, String> tableMainColumnKeterangan;

	@FXML
	private TableColumn<FasilitasModel, String> tableMainColumnDibuat;

	@FXML
	private TableColumn<FasilitasModel, String> tableMainColumnDiubah;

	@FXML
	private TextField textFieldNama;

	@FXML
	private TextArea textAreaKeterangan;

	@FXML
	private TextField textFieldDibuat;

	@FXML
	private TextField textFieldDiubah;

	@FXML
	private TextField textFieldKeterangan;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.debug("Initialize");

		labelProfile.setText(LoginService.getInstance().getSession().get("nama").toString() + " - " + Utility.capitalize(((LevelEnum) LoginService.getInstance().getSession().get("level")).getValue()));

		tableMainColumnNama.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getNama()));
		tableMainColumnKeterangan.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getKeterangan()));
		tableMainColumnDibuat.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDibuat().toString()));
		tableMainColumnDiubah.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDiubah().toString()));

		tableMain.setItems(FXCollections.observableArrayList(service.find()));
	}

	private void tableReload() {
		logger.debug("Table Reload");

		tableMain.setItems(FXCollections.observableArrayList(service.find()));
	}

	@FXML
	private void tableMainItemClick(MouseEvent event) {
		logger.debug("Table Main Item Click");

		try {
			this.selectedModel = tableMain.getSelectionModel().getSelectedItem();

			textFieldNama.setText(this.selectedModel.getNama());
			textAreaKeterangan.setText(this.selectedModel.getKeterangan());
			textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
			textFieldDiubah.setText(this.selectedModel.getDiubah().toString());
		}
		catch (Exception e) {
		}
	}

	@FXML
	private void buttonTambahOnAction(ActionEvent event) {
		logger.debug("Button Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				service.add(new FasilitasModel(
						textFieldNama.getText(),
						textAreaKeterangan.getText()));

				this.tableReload();

				tableMain.getSelectionModel().select(tableMain.getItems().size() - 1);
				this.selectedModel = tableMain.getItems().get(tableMain.getItems().size() - 1);

				textFieldNama.setText(this.selectedModel.getNama());
				textAreaKeterangan.setText(this.selectedModel.getKeterangan());
				textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
				textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

				Modal.getInstance().success("Fasilitas berhasil ditambahkan");
			}
			catch (Exception e) {
				Modal.getInstance().fail(e.getMessage());

				logger.error(e.getMessage());
			}
		}
	}

	@FXML
	private void buttonUbahOnAction(ActionEvent event) {
		logger.debug("Button Ubah On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null) {
				try {
					service.change(
							this.selectedModel.getId(),
							new FasilitasModel(
									textFieldNama.getText(),
									textAreaKeterangan.getText()));

					final int selectedIndex = tableMain.getSelectionModel().getSelectedIndex();

					this.tableReload();

					tableMain.getSelectionModel().select(selectedIndex);
					this.selectedModel = tableMain.getItems().get(selectedIndex);

					textFieldNama.setText(this.selectedModel.getNama());
					textAreaKeterangan.setText(this.selectedModel.getKeterangan());
					textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
					textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

					Modal.getInstance().success("Fasilitas berhasil diubah");
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih fasilitas yang ingin diubah");
			}
		}
	}

	@FXML
	private void buttonHapusOnAction(ActionEvent event) {
		logger.debug("Button Hapus On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null) {
				try {
					service.remove(this.selectedModel.getId());

					this.selectedModel = null;

					textFieldNama.clear();
					textAreaKeterangan.clear();
					textFieldDibuat.clear();
					textFieldDiubah.clear();

					this.tableReload();

					Modal.getInstance().success("Fasilitas berhasil dihapus");
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih fasilitas yang ingin dihapus");
			}
		}
	}
}
