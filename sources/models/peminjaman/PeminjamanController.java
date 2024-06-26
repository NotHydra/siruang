package models.peminjaman;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import components.Modal;
import enums.LevelEnum;

// import enums.StatusEnum;

import providers.Logger;
import providers.Utility;
import global.base.BaseController;
import global.choice_box.ChoiceBoxModel;

import models.authentication.LoginService;
import models.ruangan.RuanganService;

public class PeminjamanController extends BaseController implements Initializable {
	private final static Logger logger = new Logger(PeminjamanController.class.getName());

	private final static PeminjamanService service = PeminjamanService.getInstance();
	private final static RuanganService ruanganService = RuanganService.getInstance();

	private PeminjamanDetailedModel selectedModel;

	@FXML
	private Label labelProfile;

	@FXML
	private TableView<PeminjamanDetailedModel> tableMain;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnRuangan;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnNamaPeminjam;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnWaktuMulai;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnWaktuSelesai;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnKeterangan;

	// @FXML
	// private TableColumn<PeminjamanDetailedModel, String> tableMainColumnStatus;

	@FXML
	private TableColumn<PeminjamanDetailedModel, String> tableMainColumnDibuat;

	@FXML
	private ChoiceBox<ChoiceBoxModel> choiceBoxRuangan;

	@FXML
	private TextField textFieldNamaPeminjam;

	@FXML
	private DatePicker datePickerTanggalMulai;

	@FXML
	private ChoiceBox<String> choiceBoxJamMulai;

	@FXML
	private DatePicker datePickerTanggalSelesai;

	@FXML
	private ChoiceBox<String> choiceBoxJamSelesai;

	@FXML
	private TextArea textAreaKeterangan;

	// @FXML
	// private ChoiceBox<String> choiceBoxStatus;

	@FXML
	private TextField textFieldDibuat;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.debug("Initialize");

		labelProfile.setText(LoginService.getInstance().getSession().get("nama").toString() + " - " + Utility.capitalize(((LevelEnum) LoginService.getInstance().getSession().get("level")).getValue()));

		tableMainColumnRuangan.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getRuangan().getNama()));
		tableMainColumnNamaPeminjam.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getNamaPeminjam()));
		tableMainColumnWaktuMulai.setCellValueFactory(model -> new SimpleStringProperty(Utility.formatDateTime(model.getValue().getWaktuMulai())));
		tableMainColumnWaktuSelesai.setCellValueFactory(model -> new SimpleStringProperty(Utility.formatDateTime(model.getValue().getWaktuSelesai())));
		tableMainColumnKeterangan.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getKeterangan()));
		// tableMainColumnStatus.setCellValueFactory(model -> new
		// SimpleStringProperty(Utility.capitalize(model.getValue().getStatus().value)));
		tableMainColumnDibuat.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDibuat().toString()));

		tableMain.setItems(FXCollections.observableArrayList(service.findDetailed()));

		choiceBoxRuangan.getItems().addAll(ruanganService.findChoiceBox());
		choiceBoxRuangan.setValue(choiceBoxRuangan.getItems().get(0));

		choiceBoxJamMulai.getItems().addAll(Utility.availableTime);
		choiceBoxJamMulai.setValue(choiceBoxJamMulai.getItems().get(0));

		choiceBoxJamSelesai.getItems().addAll(Utility.availableTime);
		choiceBoxJamSelesai.setValue(choiceBoxJamSelesai.getItems().get(0));

		// choiceBoxStatus.getItems().addAll(new String[] { "Pilih Status", "Belum
		// Direspon", "Ditolak", "Diterima" });
		// choiceBoxStatus.setValue(choiceBoxStatus.getItems().get(0));
	}

	private void tableReload() {
		logger.debug("Table Reload");

		tableMain.setItems(FXCollections.observableArrayList(service.findDetailed()));
	}

	@FXML
	private void tableMainItemClick(MouseEvent event) {
		logger.debug("Table Main Item Click");

		try {
			this.selectedModel = tableMain.getSelectionModel().getSelectedItem();

			choiceBoxRuangan.setValue(choiceBoxRuangan.getItems().stream()
					.filter(item -> item.getId() == this.selectedModel.getRuangan().getId())
					.findFirst()
					.orElse(null));
			textFieldNamaPeminjam.setText(this.selectedModel.getNamaPeminjam());
			datePickerTanggalMulai.setValue(this.selectedModel.getWaktuMulai().toLocalDate());
			choiceBoxJamMulai.setValue(Utility.formatDateTimeToTime(this.selectedModel.getWaktuMulai()));
			datePickerTanggalSelesai.setValue(this.selectedModel.getWaktuSelesai().toLocalDate());
			choiceBoxJamSelesai.setValue(Utility.formatDateTimeToTime(this.selectedModel.getWaktuSelesai()));
			textAreaKeterangan.setText(this.selectedModel.getKeterangan());
			// choiceBoxStatus.setValue(Utility.capitalize(this.selectedModel.getStatus().value));
			textFieldDibuat.setText(this.selectedModel.getDibuat().toString());
		}
		catch (Exception e) {
		}
	}

	@FXML
	private void buttonTambahOnAction(ActionEvent event) {
		logger.debug("Button Tambah On Action");

		if (Modal.getInstance().confirmation()) {
			try {
				if (choiceBoxRuangan.getValue().getId() <= 0) {
					throw new Exception("Ruangan tidak boleh kosong");
				}

				if (datePickerTanggalMulai.getValue() == null) {
					throw new Exception("Tanggal mulai tidak boleh kosong");
				}

				if (datePickerTanggalSelesai.getValue() == null) {
					throw new Exception("Tanggal selesai tidak boleh kosong");
				}

				if (!service.isAvailable(
						choiceBoxRuangan.getValue().getId(),
						datePickerTanggalMulai.getValue().atTime(Integer.parseInt(choiceBoxJamMulai.getValue().split(":")[0]), Integer.parseInt(choiceBoxJamMulai.getValue().split(":")[1])),
						datePickerTanggalSelesai.getValue().atTime(Integer.parseInt(choiceBoxJamSelesai.getValue().split(":")[0]), Integer.parseInt(choiceBoxJamSelesai.getValue().split(":")[1])))) {
					throw new Exception("Ruangan tidak tersedia pada waktu tersebut");
				}

				// if (choiceBoxStatus.getValue() == null ||
				// choiceBoxStatus.getValue().equals("Pilih Status")) {
				// throw new Exception("Status tidak boleh kosong");
				// }

				service.add(new PeminjamanModel(
						choiceBoxRuangan.getValue().getId(),
						1,
						textFieldNamaPeminjam.getText(),
						datePickerTanggalMulai.getValue().atTime(Integer.parseInt(choiceBoxJamMulai.getValue().split(":")[0]), Integer.parseInt(choiceBoxJamMulai.getValue().split(":")[1])),
						datePickerTanggalSelesai.getValue().atTime(Integer.parseInt(choiceBoxJamSelesai.getValue().split(":")[0]), Integer.parseInt(choiceBoxJamSelesai.getValue().split(":")[1])),
						textAreaKeterangan.getText()
				// StatusEnum.valueToEnum(choiceBoxStatus.getValue().toLowerCase())
				));

				this.tableReload();

				tableMain.getSelectionModel().select(tableMain.getItems().size() - 1);
				this.selectedModel = tableMain.getItems().get(tableMain.getItems().size() - 1);

				choiceBoxRuangan.setValue(choiceBoxRuangan.getItems().stream()
						.filter(item -> item.getId() == this.selectedModel.getRuangan().getId())
						.findFirst()
						.orElse(null));
				textFieldNamaPeminjam.setText(this.selectedModel.getNamaPeminjam());
				datePickerTanggalMulai.setValue(this.selectedModel.getWaktuMulai().toLocalDate());
				choiceBoxJamMulai.setValue(Utility.formatDateTimeToTime(this.selectedModel.getWaktuMulai()));
				datePickerTanggalSelesai.setValue(this.selectedModel.getWaktuSelesai().toLocalDate());
				choiceBoxJamSelesai.setValue(Utility.formatDateTimeToTime(this.selectedModel.getWaktuSelesai()));
				textAreaKeterangan.setText(this.selectedModel.getKeterangan());
				// choiceBoxStatus.setValue(Utility.capitalize(this.selectedModel.getStatus().value));
				textFieldDibuat.setText(this.selectedModel.getDibuat().toString());

				Modal.getInstance().success("Peminjaman berhasil ditambahkan");
			}
			catch (Exception e) {
				Modal.getInstance().fail(e.getMessage());

				logger.error(e.getMessage());
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

					choiceBoxRuangan.setValue(choiceBoxRuangan.getItems().get(0));
					textFieldNamaPeminjam.clear();
					datePickerTanggalMulai.setValue(null);
					choiceBoxJamMulai.setValue(choiceBoxJamMulai.getItems().get(0));
					datePickerTanggalSelesai.setValue(null);
					choiceBoxJamSelesai.setValue(choiceBoxJamSelesai.getItems().get(0));
					textAreaKeterangan.clear();
					// choiceBoxStatus.setValue(null);
					textFieldDibuat.clear();

					this.tableReload();

					Modal.getInstance().success("Peminjaman berhasil dihapus");
				}
				catch (Exception e) {
					Modal.getInstance().fail(e.getMessage());

					logger.error(e.getMessage());
				}
			}
			else {
				Modal.getInstance().fail("Pilih peminjaman yang ingin dihapus");
			}
		}
	}
}
