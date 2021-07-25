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

public class CarController {

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
    private TextField ownerField;

    @FXML
    private TextField carField;

    @FXML
    private TextField saleField;

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
        dbHandler.addDataToCars(baseField.getText().trim(), carField.getText().trim(),
                ownerField.getText().trim(),
                Integer.parseInt(saleField.getText().trim()));
    }
}
