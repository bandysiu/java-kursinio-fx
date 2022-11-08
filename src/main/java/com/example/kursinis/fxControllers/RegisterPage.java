package com.example.kursinis.fxControllers;

import com.example.kursinis.utilities.CallEndpoints;
import com.example.kursinis.utilities.FxUtils;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;
import org.json.JSONObject;

public class RegisterPage {

    public TextField name;
    public TextField surname;
    public TextField email;
    public TextField login;
    public PasswordField password;
    public PasswordField repeatPassword;

    public void cancel() {
        FxUtils.openFxPage("login-page.fxml", name);
    }

    public void submit() throws IOException {

        if (name.getText().isBlank()
                || surname.getText().isBlank()
                || email.getText().isBlank()
                || login.getText().isBlank()
                || password.getText().isBlank()) {
            FxUtils.alert(Alert.AlertType.WARNING, "Warning", "Missing fields", "Please fill out all fields");
        } else if (!Objects.equals(password.getText(), repeatPassword.getText())) {
            FxUtils.alert(Alert.AlertType.WARNING, "Warning", "Passwords must match", "");
        } else {
            String loginResponse = CallEndpoints.callGetEndpoint("http://localhost:8080/api/v1/user/users?login=" + login.getText());
            String emailResponse = CallEndpoints.callGetEndpoint("http://localhost:8080/api/v1/user/users?email=" + email.getText());

            if (loginResponse.length() != 2) {
                FxUtils.alert(Alert.AlertType.ERROR, "Error", "Login name already exists", "Please choose another login name");
            } else if (emailResponse.length() != 2) {
                FxUtils.alert(Alert.AlertType.ERROR, "Error", "Email is already registered", "Please choose another email");
            } else {

                JSONObject json = new JSONObject();

                json.put("firstName", name.getText());
                json.put("lastName", surname.getText());
                json.put("email", email.getText());
                json.put("login", login.getText());
                json.put("password", password.getText());

                CallEndpoints.callPostEndpoint("http://localhost:8080/api/v1/user/registration", json.toString());

                FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Registration complete", "You will now we redirected to main page");
                FxUtils.openFxPage("main-page.fxml", login);
            }
        }
    }
}