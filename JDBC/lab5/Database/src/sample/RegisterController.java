package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private Button goHomeButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField identityField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void initialize() {
        goHomeButton.setOnAction(event -> {
            String identity = identityField.getText().trim();
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            registerUser(identity, login, password);
            goHomeButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));
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

    private void registerUser(String identity, String login, String password) {
        User user = new User(identity, login, password);
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.createUser(user);
    }

}
