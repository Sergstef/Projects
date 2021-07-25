package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchController {

    @FXML
    private Button searchButton;

    @FXML
    private TextField baseField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField fieldField;

    @FXML
    private TextField tableField;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        searchButton.setOnAction(event -> {
            findData();
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

    private void findData() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(loginField.getText().trim());
        dbHandler.setPass(passwordField.getText().trim());
        dbHandler.findData(baseField.getText().trim(), tableField.getText().trim(), fieldField.getText().trim());
    }

}
