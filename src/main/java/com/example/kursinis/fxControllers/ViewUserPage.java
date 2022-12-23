package com.example.kursinis.fxControllers;

import com.example.kursinis.ParcelApplication;
import com.example.kursinis.model.DtoUser;
import com.example.kursinis.utilities.CallEndpoints;
import com.example.kursinis.utilities.FxUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserPage implements Initializable {
    public TableColumn<DtoUser, Integer> id;
    public TableColumn<DtoUser, String> firstName;
    public TableColumn<DtoUser, String> lastName;
    public TableColumn<DtoUser, String> email;
    public TableColumn<DtoUser, String> salary;
    public TableColumn<DtoUser, String> position;
    public TableColumn<DtoUser, String> statusColumn;
    public TableView<DtoUser> userTable;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField salaryField;
    public TextField positionField;
    public TextField statusField;
    public TextField idField;

    public void fillTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        userTable.getItems().setAll(DtoUser.getArray());
    }

    @FXML
    public void rowClicked(MouseEvent event){
        DtoUser clickedUser = userTable.getSelectionModel().getSelectedItem();

        if(clickedUser != null){
            idField.setText(String.valueOf(clickedUser.getId()));
            firstNameField.setText(clickedUser.getFirstName());
            lastNameField.setText(clickedUser.getLastName());
            emailField.setText(clickedUser.getEmail());
            salaryField.setText(clickedUser.getSalary());
            positionField.setText(clickedUser.getPosition());
            statusField.setText(clickedUser.getStatus());
        }
    }

    public void goBack(ActionEvent actionEvent) {
        FxUtils.openFxPage("manager-main-page.fxml", firstNameField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillTable();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ParcelApplication.class.getResource("view-user-page.fxml"));
    }

    public void submit(ActionEvent actionEvent) {
        ObservableList<DtoUser> currentUserTable = userTable.getItems();
        int currentUserId = Integer.parseInt(idField.getText());

        for(DtoUser user : currentUserTable){
            if(user.getId() == currentUserId){
                user.setFirstName(firstNameField.getText());
                user.setLastName(lastNameField.getText());
                user.setEmail(emailField.getText());
                user.setSalary(salaryField.getText());
                user.setPosition(positionField.getText());
                user.setStatus(statusField.getText());

                JSONObject json = new JSONObject();

                json.put("firstName", firstNameField.getText());
                json.put("lastName", lastNameField.getText());
                json.put("email", emailField.getText());
                json.put("salary", salaryField.getText());
                json.put("position", positionField.getText());
                json.put("status", statusField.getText());


                CallEndpoints.Put("http://localhost:8080/api/user/update_manager", json.toString());

                FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Update complete", "");


                userTable.setItems(currentUserTable);
                userTable.refresh();

                break;
            }
        }
    }

    public void delete(ActionEvent actionEvent) {
        ObservableList<DtoUser> currentUserTable = userTable.getItems();

        CallEndpoints.Delete("http://localhost:8080/api/user/delete?id=" + Integer.parseInt(idField.getText()));

        FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Deletion complete", "");

        currentUserTable.removeAll();
        userTable.setItems(currentUserTable);

        fillTable();
    }
}
