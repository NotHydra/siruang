package models.fasilitas;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FasilitasController implements Initializable {

	@FXML
	private Label labelProfile;

	@FXML
	private TableView<?> tableMain;

	@FXML
	private TableColumn<?, ?> tableMainColumnDibuat;

	@FXML
	private TableColumn<?, ?> tableMainColumnDiubah;

	@FXML
	private TableColumn<?, ?> tableMainColumnKeterangan;

	@FXML
	private TableColumn<?, ?> tableMainColumnNama;

	@FXML
	private TextField textFieldDibuat;

	@FXML
	private TextField textFieldDiubah;

	@FXML
	private TextField textFieldKeterangan;

	@FXML
	private TextField textFieldNama;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onActionFasilitas(ActionEvent event) {

	}

	@FXML
	void onActionHapus(ActionEvent event) {

	}

	@FXML
	void onActionLogout(ActionEvent event) {

	}

	@FXML
	void onActionPeminjaman(ActionEvent event) {

	}

	@FXML
	void onActionPengguna(ActionEvent event) {

	}

	@FXML
	void onActionRuangan(ActionEvent event) {

	}

	@FXML
	void onActionTambah(ActionEvent event) {

	}

	@FXML
	void onActionUbah(ActionEvent event) {

	}

	@FXML
	void tableMainItemClick(MouseEvent event) {

	}
}