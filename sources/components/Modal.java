package components;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import providers.Logger;

public class Modal {
    private static Modal instance;

    private final Logger logger;

    private Modal(Logger logger) {
        this.logger = logger;
    }

    public static Modal getInstance() {
        if (Modal.instance == null) {
            Modal.instance = new Modal(new Logger(Modal.class.getName()));
        }

        Modal.instance.logger.debug("Get Instance");

        return Modal.instance;
    }

    public boolean confirmation() {
        this.logger.debug("Confirmation");

        final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Notification");
        confirmation.setHeaderText("Confirmation");
        confirmation.setContentText("Are you sure?");

        confirmation.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        final java.util.Optional<ButtonType> result = confirmation.showAndWait();

        return result.isPresent() && result.get() == ButtonType.YES;
    }

    public void fail(String message) {
        this.logger.debug("Fail");

        final Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Notification");
        error.setHeaderText("Fail");
        error.setContentText(message);
        error.showAndWait();
    }
}
