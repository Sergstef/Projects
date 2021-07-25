package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class baseCreationController {

    @FXML
    private Button baseCreationButton;

    @FXML
    private TextField baseNameField;

    @FXML
    private TextField adminPassField;

    @FXML
    private TextField adminNameField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        baseCreationButton.setOnAction(event -> {
            String name = baseNameField.getText().trim();
            createDatabase(name);
            baseCreationButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/adminOperations.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void createDatabase(String name) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(adminNameField.getText().trim());
        dbHandler.setPass(adminPassField.getText().trim());
        dbHandler.createBase(name);
    }

}
