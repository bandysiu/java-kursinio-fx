package com.example.kursinis.fxControllers;

import com.example.kursinis.utilities.CallEndpoints;
import com.example.kursinis.utilities.FxUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class LoginPage {

    public TextField loginField;
    public PasswordField passwordField;

    public void validateLogin() {

        if (!loginField.getText().isBlank() || !passwordField.getText().isBlank()) {

            String response = CallEndpoints.Get("http://localhost:8080/api/user/users?login=" + loginField.getText());
            String[] attributes = response.split(",");
            attributes[5] = attributes[5].substring(12, attributes[5].length() - 1);

            if (response.length() != 2 && attributes[5].equals(passwordField.getText())) {
                if (attributes[6].equals("\"position\":\"USER\"") || attributes[6].equals("\"position\":\"DRIVER\"")){
                    FxUtils.openFxPage("main-page.fxml", loginField);
                } else {
                    FxUtils.openFxPage("manager-main-page.fxml", loginField);
                }
            } else {
                FxUtils.alert(Alert.AlertType.ERROR, "Error", "Incorrect login or password", "Please check your credentials");
            }
        } else {
            FxUtils.alert(Alert.AlertType.ERROR, "Error", "Missing fields", "Login and Password cannot be empty");
        }
    }

    public void register() {
        FxUtils.openFxPage("register-page.fxml", loginField);
    }
}
