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

public class FixController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField baseField;

    @FXML
    private Button searchButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField garageField;

    @FXML
    private TextField carField;

    @FXML
    private TextField detailsField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField totalField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        searchButton.setOnAction(event -> {
            addData();
            searchButton.getScene().getWindow().hide();
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

    private void addData() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(loginField.getText().trim());
        dbHandler.setPass(passwordField.getText().trim());
        dbHandler.addDataToFix(baseField.getText().trim(), carField.getText().trim(),
                Integer.parseInt(garageField.getText().trim()),
                Integer.parseInt(detailsField.getText().trim()),
                Integer.parseInt(numberField.getText().trim()),
                Integer.parseInt(totalField.getText().trim()));
    }
}

