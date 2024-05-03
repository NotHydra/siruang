package models.contoh.book;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import components.Modal;

import providers.Logger;

import global.choice_box.ChoiceBoxModel;

import models.contoh.genre.GenreModel;
import models.contoh.genre.GenreService;

public class BookController implements Initializable {
    private final static Logger logger = new Logger(BookController.class.getName());

    private final static BookService service = BookService.getInstance();
    private final static GenreService genreService = GenreService.getInstance();

    private BookDetailedModel selectedModel;
    private GenreModel selectedGenreModel;

    @FXML
    private TableView<BookDetailedModel> tableViewBook;

    @FXML
    private TableColumn<BookDetailedModel, String> tableColumnTitle;

    @FXML
    private TableColumn<BookDetailedModel, String> tableColumnDescription;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TableView<GenreModel> tableViewGenre;

    @FXML
    private TableColumn<GenreModel, String> tableColumnGenreName;

    @FXML
    private ChoiceBox<ChoiceBoxModel> choiceBoxGenre;

    @FXML
    private Button buttonGenreAdd;

    @FXML
    private Button buttonGenreRemove;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Initialize");

        // Book
        tableColumnTitle.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getTitle()));
        tableColumnDescription.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getDescription()));

        tableViewBook.setItems(FXCollections.observableArrayList(service.findDetailed()));

        // Book -> Genre
        tableColumnGenreName.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getName()));

        tableViewGenre.setItems(null);

        choiceBoxGenre.setDisable(true);
        buttonGenreAdd.setDisable(true);
        buttonGenreRemove.setDisable(true);

        choiceBoxGenre.getItems().addAll(genreService.findChoiceBox());
        choiceBoxGenre.setValue(choiceBoxGenre.getItems().get(0));
    }

    public void tableReload() {
        logger.debug("Table Reload");

        tableViewBook.setItems(FXCollections.observableArrayList(service.findDetailed()));
    }

    @FXML
    public void tableItemClick(MouseEvent event) {
        logger.debug("Table Item Click");

        try {
            if (event.getSource() == tableViewBook) {
                this.selectedModel = tableViewBook.getSelectionModel().getSelectedItem();
                this.selectedGenreModel = null;

                // Book
                textFieldTitle.setText(this.selectedModel.getTitle());
                textFieldDescription.setText(this.selectedModel.getDescription());

                // Book -> Genre
                tableViewGenre.setItems(FXCollections.observableArrayList(this.selectedModel.getGenres()));

                choiceBoxGenre.setDisable(false);
                buttonGenreAdd.setDisable(false);
                buttonGenreRemove.setDisable(false);
            }
            else if (event.getSource() == tableViewGenre) {
                this.selectedGenreModel = tableViewGenre.getSelectionModel().getSelectedItem();
            }
        }
        catch (Exception e) {
        }
    }

    @FXML
    public void buttonAddEvent(ActionEvent event) {
        logger.debug("Button Add Event");

        if (Modal.getInstance().confirmation()) {
            try {
                service.add(new BookModel(
                        textFieldTitle.getText(),
                        textFieldDescription.getText()));

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
                            new BookModel(
                                    textFieldTitle.getText(),
                                    textFieldDescription.getText()));

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

                    textFieldTitle.clear();
                    textFieldDescription.clear();

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
    public void buttonGenreAddEvent(ActionEvent event) {
        logger.debug("Button Genre Add Event");

        if (Modal.getInstance().confirmation()) {
            try {
                if (choiceBoxGenre.getValue().getId() <= 0) {
                    throw new IllegalArgumentException("Selected genre cannot be empty");
                }

                for (GenreModel genre : this.selectedModel.getGenres()) {
                    if (choiceBoxGenre.getValue().getId() == genre.getId()) {
                        throw new IllegalArgumentException("Selected genre already exists");
                    }
                }

                service.addGenre(this.selectedModel.getId(), choiceBoxGenre.getValue().getId());

                this.selectedModel = service.findIdDetailed(this.selectedModel.getId());

                this.tableReload();
                tableViewGenre.setItems(FXCollections.observableArrayList(this.selectedModel.getGenres()));
            }
            catch (Exception e) {
                Modal.getInstance().fail(e.getMessage());

                logger.error(e.getMessage());
            }
        }
    }

    @FXML
    public void buttonGenreRemoveEvent(ActionEvent event) {
        logger.debug("Button Genre Remove Event");

        if (this.selectedGenreModel != null) {
            if (Modal.getInstance().confirmation()) {
                try {
                    service.removeGenre(this.selectedModel.getId(), this.selectedGenreModel.getId());

                    this.selectedModel = service.findIdDetailed(this.selectedModel.getId());
                    this.selectedGenreModel = null;

                    this.tableReload();
                    tableViewGenre.setItems(FXCollections.observableArrayList(this.selectedModel.getGenres()));
                }
                catch (Exception e) {
                    Modal.getInstance().fail(e.getMessage());

                    logger.error(e.getMessage());
                }
            }
        }
    }

}