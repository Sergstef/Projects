package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TableCreationController {

    @FXML
    private Button createTablesButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField baseField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        createTablesButton.setOnAction(event -> {
            createTables();
            createTablesButton.getScene().getWindow().hide();
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

    private void createTables() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(loginField.getText().trim());
        dbHandler.setPass(passwordField.getText().trim());
        dbHandler.createTables(baseField.getText().trim());
    }
}
