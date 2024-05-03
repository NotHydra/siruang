package models.contoh.genre;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import components.Modal;

import providers.Logger;

public class GenreController implements Initializable {
    private final static Logger logger = new Logger(GenreController.class.getName());
    
    private final static GenreService service = GenreService.getInstance();

    private GenreModel selectedModel;

    @FXML
    private TableView<GenreModel> tableViewGenre;

    @FXML
    private TableColumn<GenreModel, String> tableColumnName;

    @FXML
    private TextField textFieldName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Initialize");

        tableColumnName.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getName()));

        tableViewGenre.setItems(FXCollections.observableArrayList(service.find()));
    }

    public void tableReload() {
        logger.debug("Table Reload");

        tableViewGenre.setItems(FXCollections.observableArrayList(service.find()));
    }

    @FXML
    public void tableItemClick(MouseEvent event) {
        logger.debug("Table Item Click");

        try {
            this.selectedModel = tableViewGenre.getSelectionModel().getSelectedItem();

            textFieldName.setText(this.selectedModel.getName());
        }
        catch (Exception e) {
        }
    }

    @FXML
    public void buttonAddEvent(ActionEvent event) {
        logger.debug("Button Add Event");

        if (Modal.getInstance().confirmation()) {
            try {
                service.add(new GenreModel(textFieldName.getText()));

                this.tableReload();
            }
            catch (Exception e) {
                Modal.getInstance().fail(e.getMessage());

                logger.error(e.getMessage());
            }
        }
    }

    @FXML
    public void buttonChangeEvent(ActionEvent event) {
        logger.debug("Button Change Event");

        if (this.selectedModel != null) {
            if (Modal.getInstance().confirmation()) {
                try {
                    service.change(
                            this.selectedModel.getId(),
                            new GenreModel(textFieldName.getText()));

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
    public void buttonRemoveEvent(ActionEvent event) {
        logger.debug("Button Remove Event");

        if (this.selectedModel != null) {
            if (Modal.getInstance().confirmation()) {
                try {
                    service.remove(this.selectedModel.getId());

                    this.selectedModel = null;

                    textFieldName.clear();

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