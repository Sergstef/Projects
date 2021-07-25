package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseDeletionController {

    @FXML
    private Button deleteButton;

    @FXML
    private TextField baseNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        deleteButton.setOnAction(event -> {
            String name = baseNameField.getText().trim();
            deleteDatabase(name);
            deleteButton.getScene().getWindow().hide();
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

    private void deleteDatabase(String name) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(loginField.getText().trim());
        dbHandler.setPass(passwordField.getText().trim());
        dbHandler.deleteBase(name);
    }

}
