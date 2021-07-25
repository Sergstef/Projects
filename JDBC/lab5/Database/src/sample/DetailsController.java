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

public class DetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField baseField;

    @FXML
    private Button insertButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField sellerField;

    @FXML
    private TextField detailField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField costField1;

    @FXML
    private Button homeButton;

    @FXML
    void initialize() {
        insertButton.setOnAction(event -> {
            addData();
            insertButton.getScene().getWindow().hide();
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
        dbHandler.addDataToDetails(baseField.getText().trim(), detailField.getText().trim(),
                sellerField.getText().trim(),
                Integer.parseInt(costField1.getText().trim()),
                Integer.parseInt(maxField.getText().trim()));
    }
}

