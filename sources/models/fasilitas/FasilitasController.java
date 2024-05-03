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

import components.Modal;

import providers.Logger;
import providers.View;

public class FasilitasController implements Initializable {
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

	@FXML
	void onActionFasilitas(ActionEvent event) {
	}

	@FXML
	void buttonPeminjamanOnAction(ActionEvent event) {

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

	}

	@FXML
	void buttonLogoutOnAction(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.debug("Initialize");

		tableMainColumnNama.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getNama()));
		tableMainColumnKeterangan.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getKeterangan()));
		tableMainColumnDibuat.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDibuat().toString()));
		tableMainColumnDiubah.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDiubah().toString()));

		tableMain.setItems(FXCollections.observableArrayList(service.find()));
	}

	public void tableReload() {
		logger.debug("Table Reload");

		tableMain.setItems(FXCollections.observableArrayList(service.find()));
	}

	@FXML
	void tableMainItemClick(MouseEvent event) {
		logger.debug("Table Main Item Click");

		try

		{
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
	void buttonTambahOnAction(ActionEvent event) {
		logger.debug("Button Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				service.add(new FasilitasModel(
						textFieldNama.getText(),
						textAreaKeterangan.getText()));

				this.tableReload();
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

		if (this.selectedModel != null) {
			if (Modal.getInstance().confirmation()) {
				try {
					service.change(
							this.selectedModel.getId(),
							new FasilitasModel(
									textFieldNama.getText(),
									textAreaKeterangan.getText()));

					this.tableReload();
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
		}
	}

	@FXML
	void buttonHapusOnAction(ActionEvent event) {
		logger.debug("Button Hapus On Action");

		if (this.selectedModel != null) {
			if (Modal.getInstance().confirmation()) {
				try {
					service.remove(this.selectedModel.getId());

					this.selectedModel = null;

					textFieldNama.clear();
					textAreaKeterangan.clear();
					textFieldDibuat.clear();
					textFieldDiubah.clear();

					this.tableReload();
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
		}
	}
}
