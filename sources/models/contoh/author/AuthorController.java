package models.contoh.author;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import components.Modal;

import providers.Logger;

import global.choice_box.ChoiceBoxModel;

import models.contoh.book.BookService;

public class AuthorController implements Initializable {
	private final static Logger logger = new Logger(AuthorController.class.getName());

	private final static AuthorService service = AuthorService.getInstance();
	private final static BookService bookService = BookService.getInstance();

	private AuthorDetailedModel selectedModel;

	@FXML
	private TableView<AuthorDetailedModel> tableViewAuthor;

	@FXML
	private TableColumn<AuthorDetailedModel, String> tableColumnName;

	@FXML
	private TableColumn<AuthorDetailedModel, String> tableColumnBookTitle;

	@FXML
	private TableColumn<AuthorDetailedModel, String> tableColumnBookDescription;

	@FXML
	private TextField textFieldName;

	@FXML
	private ChoiceBox<ChoiceBoxModel> choiceBoxBook;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		logger.debug("Initialize");

		tableColumnName.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getName()));
		tableColumnBookTitle.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getBook().getTitle()));
		tableColumnBookDescription.setCellValueFactory(model -> new SimpleStringProperty(model.getValue().getBook().getDescription()));

		tableViewAuthor.setItems(FXCollections.observableArrayList(service.findDetailed()));

		choiceBoxBook.getItems().addAll(bookService.findChoiceBox());
		choiceBoxBook.setValue(choiceBoxBook.getItems().get(0));
	}

	public void tableReload() {
		logger.debug("Table Reload");

		tableViewAuthor.setItems(FXCollections.observableArrayList(service.findDetailed()));
	}

	@FXML
	public void tableItemClick(MouseEvent event) {
		logger.debug("Table Item Click");

		try {
			this.selectedModel = tableViewAuthor.getSelectionModel().getSelectedItem();

			textFieldName.setText(this.selectedModel.getName());
			choiceBoxBook.setValue(choiceBoxBook.getItems().stream()
					.filter(item -> item.getId() == this.selectedModel.getBook().getId())
					.findFirst()
					.orElse(null));
		}
		catch (Exception e) {
		}
	}

	@FXML
	public void buttonAddEvent(ActionEvent event) {
		logger.debug("Button Add Event");

		if (Modal.getInstance().confirmation()) {
			try {
				if (choiceBoxBook.getValue().getId() <= 0) {
					throw new IllegalArgumentException("Selected book cannot be empty");
				}

				service.add(new AuthorModel(
						textFieldName.getText(),
						choiceBoxBook.getValue().getId()));

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
					if (choiceBoxBook.getValue().getId() <= 0) {
						throw new IllegalArgumentException("Selected book cannot be empty");
					}

					service.change(
							this.selectedModel.getId(),
							new AuthorModel(
									textFieldName.getText(),
									choiceBoxBook.getValue().getId()));

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
					choiceBoxBook.setValue(choiceBoxBook.getItems().get(0));

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
