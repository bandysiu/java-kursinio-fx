package com.example.kursinis.fxControllers;

import com.example.kursinis.utilities.CallEndpoints;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import com.google.gson.Gson;

public class MainPage {

    public TextArea textarea;
    public TextField id;
    public TextField password;
    public TextField login;
    public TextField email;
    public TextField lastName;
    public TextField firstName;
    public TextField updateNewPassword;
    public TextField updateNewEmail;
    public TextField updateNewLogin;
    public TextField updateLogin;

    public void getuser() {

        textarea.setWrapText(true);
        String json = CallEndpoints.callGetEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/users");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();

        textarea.setText(gson.toJson(((jp.parse(json)))));
    }

    public void getshipment() {

        textarea.setWrapText(true);
        String json = CallEndpoints.callGetEndpoint("https://parceldelivery.herokuapp.com/api/v1/shipment/shipments");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();

        textarea.setText(gson.toJson(((jp.parse(json)))));
    }

    public void getvehicle() {

        textarea.setWrapText(true);
        String json = CallEndpoints.callGetEndpoint("https://parceldelivery.herokuapp.com/api/v1/vehicle/vehicles");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();

        textarea.setText(gson.toJson(((jp.parse(json)))));

    }

    public void getcommission() {

        textarea.setWrapText(true);
        String json = CallEndpoints.callGetEndpoint("https://parceldelivery.herokuapp.com/api/v1/commission/commissions");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();

        textarea.setText(gson.toJson(((jp.parse(json)))));
    }

    public void updateUser() {
        System.out.println();
        if (!updateNewLogin.getText().trim().equals("")) {
            textarea.setText(CallEndpoints.callPutEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/update?login=" + updateLogin.getText() + "&newLogin=" + updateNewLogin.getText()));
        } else if (!updateNewEmail.getText().trim().equals("")) {
            textarea.setText(CallEndpoints.callPutEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/update?login=" + updateLogin.getText() + "&newEmail=" + updateNewEmail.getText()));
        } else if (!updateNewPassword.getText().trim().equals("")) {
            textarea.setText(CallEndpoints.callPutEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/update?login=" + updateLogin.getText() + "&newPassword=" + updateNewPassword.getText()));
        } else {
            textarea.setText("500");
        }
    }

    public void createUser() {
        JSONObject json = new JSONObject();

        json.put("firstName", firstName.getText());
        json.put("lastName", lastName.getText());
        json.put("email", email.getText());
        json.put("login", login.getText());
        json.put("password", password.getText());

        textarea.setText(CallEndpoints.callPostEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/registration", json.toString()));
    }

    public void deleteUser() {
        textarea.setText(CallEndpoints.callDeleteEndpoint("https://parceldelivery.herokuapp.com/api/v1/user/delete?id=" + id.getText()));
    }
}
