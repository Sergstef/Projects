package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button enterButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField whoareyouField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    public TextField getLoginField() {
        return loginField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    @FXML
    void initialize() {
        enterButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            signInUser(login, password);
            enterButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            if (whoareyouField.getText().trim().equals("Пользователь")) {
                loader.setLocation(getClass().getResource("/sample/search.fxml"));
            } else {
                loader.setLocation(getClass().getResource("/sample/adminOperations.fxml"));
            }
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

    private void signInUser(String login, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.setLogin(login);
        dbHandler.setPass(password);
        dbHandler.signInUser(login, password);
    }

}

