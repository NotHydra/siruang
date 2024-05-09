package models.ruangan;


import java.net.URL;
import java.util.ResourceBundle;

import components.Modal;
import enums.LevelEnum;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import providers.Logger;
import providers.Utility;
import global.base.BaseController;
import global.choice_box.ChoiceBoxModel;
import models.authentication.LoginService;
import models.fasilitas.FasilitasModel;
import models.fasilitas.FasilitasService;

public class RuanganController extends BaseController implements Initializable {
	private final static Logger logger = new Logger(RuanganController.class.getName());

	private final static RuanganService service = RuanganService.getInstance();
	private final static FasilitasService fasilitasService = FasilitasService.getInstance();

	private RuanganDetailedModel selectedModel;
	private FasilitasModel selectedFasilitasModel;

	@FXML
	private Label labelProfile;

	@FXML
	private TableView<RuanganDetailedModel> tableMain;

	@FXML
	private TableColumn<RuanganDetailedModel, String> tableMainColumnNama;

	@FXML
	private TableColumn<RuanganDetailedModel, String> tableMainColumnDeskripsi;

	@FXML
	private TableColumn<RuanganDetailedModel, String> tableMainColumnKapasitas;

	@FXML
	private TableColumn<RuanganDetailedModel, String> tableMainColumnDibuat;

	@FXML
	private TableColumn<RuanganDetailedModel, String> tableMainColumnDiubah;

	@FXML
	private TextField textFieldNama;

	@FXML
	private TextArea textAreaDeskripsi;

	@FXML
	private TextField textFieldKapasitas;

	@FXML
	private TextField textFieldDibuat;

	@FXML
	private TextField textFieldDiubah;

	@FXML
	private TableView<FasilitasModel> tableFasilitas;

	@FXML
	private TableColumn<FasilitasModel, String> tableFasilitasColumnNama;

	@FXML
	private TableColumn<FasilitasModel, String> tableFasilitasColumnKeterangan;

	@FXML
	private ChoiceBox<ChoiceBoxModel> choiceBoxFasilitas;

	@FXML
	private Button buttonFasilitasTambah;

	@FXML
	private Button buttonFasilitasHapus;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		logger.debug("Initialize");

		labelProfile.setText(LoginService.getInstance().getSession().get("nama").toString() + " - " + Utility.capitalize(((LevelEnum) LoginService.getInstance().getSession().get("level")).getValue()));

		// Ruangan
		tableMainColumnNama.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getNama()));
		tableMainColumnDeskripsi.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDeskripsi()));
		tableMainColumnKapasitas.setCellValueFactory(model -> new SimpleStringProperty(Integer.toString(model.getValue().getKapasitas())));
		tableMainColumnDibuat.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDibuat().toString()));
		tableMainColumnDiubah.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDiubah().toString()));

		tableMain.setItems(FXCollections.observableArrayList(service.findDetailed()));

		// Ruangan -> Fasilitas
		tableFasilitasColumnNama.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getNama()));
		tableFasilitasColumnKeterangan.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getKeterangan()));

		tableFasilitas.setItems(FXCollections.observableArrayList(new FasilitasModel[0]));

		choiceBoxFasilitas.setDisable(true);
		buttonFasilitasTambah.setDisable(true);
		buttonFasilitasHapus.setDisable(true);

		choiceBoxFasilitas.getItems().addAll(fasilitasService.findChoiceBox());
		choiceBoxFasilitas.setValue(choiceBoxFasilitas.getItems().get(0));
	}

	private void tableReload() {
		logger.debug("Table Reload");

		tableMain.setItems(FXCollections.observableArrayList(service.findDetailed()));
	}

	@FXML
	private void tableMainItemClick(MouseEvent event) {
		logger.debug("Table Main Item Click");

		try {
			if (event.getSource() == tableMain) {
				this.selectedModel = tableMain.getSelectionModel().getSelectedItem();
				this.selectedFasilitasModel = null;

				// Ruangan
				textFieldNama.setText(this.selectedModel.getNama());
				textAreaDeskripsi.setText(this.selectedModel.getDeskripsi());
				textFieldKapasitas.setText(Integer.toString(this.selectedModel.getKapasitas()));
				textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
				textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

				// Ruangan -> Fasilitas
				tableFasilitas.setItems(FXCollections.observableArrayList(this.selectedModel.getFasilitas()));

				choiceBoxFasilitas.setDisable(false);
				buttonFasilitasTambah.setDisable(false);
				buttonFasilitasHapus.setDisable(false);
			}
			else if (event.getSource() == tableFasilitas) {
				this.selectedFasilitasModel = tableFasilitas.getSelectionModel().getSelectedItem();
			}
		}
		catch (Exception e) {
		}
	}

	@FXML
	private void buttonTambahOnAction(ActionEvent event) {
		logger.debug("Button Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				if (textFieldKapasitas.getText().trim().isEmpty()) {
					throw new IllegalArgumentException("Kapasitas tidak boleh kosong");
				}

				if (textFieldKapasitas.getText().matches("\\d+") == false) {
					throw new IllegalArgumentException("Kapasitas harus berupa angka bulat positif");
				}

				service.add(new RuanganModel(
						textFieldNama.getText(),
						textAreaDeskripsi.getText(),
						Integer.parseInt(textFieldKapasitas.getText())));

				this.tableReload();

				tableMain.getSelectionModel().select(tableMain.getItems().size() - 1);
				this.selectedModel = tableMain.getItems().get(tableMain.getItems().size() - 1);

				textFieldNama.setText(this.selectedModel.getNama());
				textAreaDeskripsi.setText(this.selectedModel.getDeskripsi());
				textFieldKapasitas.setText(Integer.toString(this.selectedModel.getKapasitas()));
				textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
				textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

				Modal.getInstance().success("Ruangan berhasil ditambahkan");
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
					if (textFieldKapasitas.getText().trim().isEmpty()) {
						throw new IllegalArgumentException("Kapasitas tidak boleh kosong");
					}

					if (textFieldKapasitas.getText().matches("\\d+") == false) {
						throw new IllegalArgumentException("Kapasitas harus berupa angka bulat positif");
					}

					service.change(
							this.selectedModel.getId(),
							new RuanganModel(
									textFieldNama.getText(),
									textAreaDeskripsi.getText(),
									Integer.parseInt(textFieldKapasitas.getText())));

					final int selectedIndex = tableMain.getSelectionModel().getSelectedIndex();

					this.tableReload();

					tableMain.getSelectionModel().select(selectedIndex);
					this.selectedModel = tableMain.getItems().get(selectedIndex);

					textFieldNama.setText(this.selectedModel.getNama());
					textAreaDeskripsi.setText(this.selectedModel.getDeskripsi());
					textFieldKapasitas.setText(Integer.toString(this.selectedModel.getKapasitas()));
					textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
					textFieldDiubah.setText(this.selectedModel.getDiubah().toString());

					Modal.getInstance().success("Ruangan berhasil diubah");
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih ruangan yang ingin diubah");
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
					textAreaDeskripsi.clear();
					textFieldKapasitas.clear();
					textFieldDibuat.clear();
					textFieldDiubah.clear();

					this.tableReload();

					tableFasilitas.setItems(FXCollections.observableArrayList(new FasilitasModel[0]));

					choiceBoxFasilitas.setValue(choiceBoxFasilitas.getItems().get(0));

					choiceBoxFasilitas.setDisable(true);
					buttonFasilitasTambah.setDisable(true);
					buttonFasilitasHapus.setDisable(true);

					Modal.getInstance().success("Ruangan berhasil dihapus");
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih ruangan yang ingin dihapus");
			}
		}
	}

	@FXML
	private void buttonFasilitasTambahOnAction(ActionEvent event) {
		logger.debug("Button Fasilitas Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				if (choiceBoxFasilitas.getValue().getId() <= 0) {
					throw new Exception("Fasilitas tidak boleh kosong");
				}

				for (FasilitasModel fasilitas : this.selectedModel.getFasilitas()) {
					if (choiceBoxFasilitas.getValue().getId() == fasilitas.getId()) {
						throw new Exception("Fasilitas sudah ada");
					}
				}

				service.addFasilitas(this.selectedModel.getId(), choiceBoxFasilitas.getValue().getId());

				this.selectedModel = service.findIdDetailed(this.selectedModel.getId());

				this.tableReload();
				tableFasilitas.setItems(FXCollections.observableArrayList(this.selectedModel.getFasilitas()));

				Modal.getInstance().success("Fasilitas ruangan berhasil ditambahkan");
			}
			catch (Exception e) {
				Modal.getInstance().fail(e.getMessage());

				logger.error(e.getMessage());
			}
		}
	}

	@FXML
	private void buttonFasilitasHapusOnAction(ActionEvent event) {
		logger.debug("Button Fasilitas Hapus On Action");

		if (Modal.getInstance().confirmation()) {
			if (this.selectedModel != null && this.selectedFasilitasModel != null) {

				try {
					service.removeFasilitas(this.selectedModel.getId(), this.selectedFasilitasModel.getId());

					this.selectedModel = service.findIdDetailed(this.selectedModel.getId());
					this.selectedFasilitasModel = null;

					this.tableReload();
					tableFasilitas.setItems(FXCollections.observableArrayList(this.selectedModel.getFasilitas()));

					Modal.getInstance().success("Fasilitas ruangan berhasil dihapus");
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
