package com.example.kursinis.fxControllers;

import com.example.kursinis.utilities.FxUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ManagerMainPage {

    public TextField redirect;

    public void openUsers(ActionEvent actionEvent) {
        FxUtils.openFxPage("view-user-page.fxml", redirect);
    }

    public void openVehicles(ActionEvent actionEvent) {
    }

    public void openShipments(ActionEvent actionEvent) {
    }

    public void openCommissions(ActionEvent actionEvent) {
    }

    public void openForum(ActionEvent actionEvent) {
        FxUtils.openFxPage("forum-page-managers.fxml", redirect);
    }

    public void openAccount(ActionEvent actionEvent) {
    }

    public void logOut(ActionEvent actionEvent) {
    }
}
