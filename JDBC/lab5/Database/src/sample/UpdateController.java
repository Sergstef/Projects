package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button updateButton;

    @FXML
    private TextField baseField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField tableField;

    @FXML
    private TextField fieldField;

    @FXML
    private TextField valueField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        updateButton.setOnAction(event -> {
            updateData();
            updateButton.getScene().getWindow().hide();
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

    private void updateData() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(loginField.getText().trim());
        dbHandler.setPass(passwordField.getText().trim());
        dbHandler.updateData(baseField.getText().trim(), tableField.getText().trim(),
                fieldField.getText().trim(),
                valueField.getText().trim());
    }
}