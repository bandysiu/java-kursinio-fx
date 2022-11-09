package com.example.kursinis.fxControllers;

import com.example.kursinis.utilities.CallEndpoints;
import com.example.kursinis.utilities.FxUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {

    public TextField loginField;
    public PasswordField passwordField;

    public void validateLogin() {

        if(!loginField.getText().isBlank() || !passwordField.getText().isBlank()){

            String response = CallEndpoints.callGetEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/users?login=" + loginField.getText());

            if (response.length() == 2){
                FxUtils.alert(Alert.AlertType.ERROR, "Error", "Incorrect login or password", "Please check your credentials");
            } else {
                String[] attributes = response.split(",");
                attributes[5] = attributes[5].substring(12, attributes[5].length() - 1);

                if (attributes[5].equals(passwordField.getText())){
                    FxUtils.openFxPage("main-page.fxml", loginField);
                } else {
                    FxUtils.alert(Alert.AlertType.ERROR, "Error", "Incorrect login or password", "Please check your credentials");
                }
            }
        } else {
            FxUtils.alert(Alert.AlertType.ERROR, "Error", "Missing fields", "Login and Password cannot be empty");
        }
    }

    public void register() {
        FxUtils.openFxPage("register-page.fxml", loginField);
    }
}
