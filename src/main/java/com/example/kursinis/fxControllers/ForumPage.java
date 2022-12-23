package com.example.kursinis.fxControllers;

import com.example.kursinis.ParcelApplication;
import com.example.kursinis.model.DtoForum;
import com.example.kursinis.utilities.CallEndpoints;
import com.example.kursinis.utilities.ChatReformat;
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

public class ForumPage implements Initializable {

    public TableColumn<DtoForum, Integer> id;
    public TableColumn<DtoForum, String> forumName;
    public TableColumn<DtoForum, Boolean> isManager;
    public TableView<DtoForum> forumTable;
    public TextField idField;
    public TextField nameField;
    public TextField isManagerField;
    public TextArea chatBox;
    public TextField messageSpace;

    public void submit(ActionEvent actionEvent) {
        ObservableList<DtoForum> currentForumTable = forumTable.getItems();
        int currentForumId = Integer.parseInt(idField.getText());

        for(DtoForum forum: currentForumTable){
            if(forum.getId() == currentForumId){
                forum.setForumName(nameField.getText());
                forum.setIsManager(Boolean.valueOf(isManagerField.getText()));

                JSONObject json = new JSONObject();

                json.put("forumName", nameField.getText());
                json.put("isManager", isManagerField.getText());

                CallEndpoints.Put("http://localhost:8080/api/forum/update", json.toString());

                FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Update complete", "");

                forumTable.setItems(currentForumTable);
                forumTable.refresh();

                break;
            }
        }
    }

    public void delete(ActionEvent actionEvent) {
        ObservableList<DtoForum> currentForumTable = forumTable.getItems();

        CallEndpoints.Delete("http://localhost:8080/api/forum/delete?id=" + Integer.parseInt(idField.getText()));

        FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Deletion complete", "");

        currentForumTable.removeAll();
        forumTable.setItems(currentForumTable);

        fillTable();
    }

    public void create(ActionEvent actionEvent) {
        if(nameField.getText() != null && isManagerField.getText() != null){
            JSONObject json = new JSONObject();

            json.put("forumName", nameField.getText());
            json.put("isManager", isManagerField.getText());

            CallEndpoints.Post("http://localhost:8080/api/forum/create", String.valueOf(json));

            FxUtils.alert(Alert.AlertType.INFORMATION, "Success", "Creation complete", "");

            fillTable();
        }
    }

    public void submitComment(ActionEvent actionEvent) {

    }

    public void goBack(ActionEvent actionEvent) {
        FxUtils.openFxPage("manager-main-page.fxml", nameField);
    }

    @FXML
    public void rowClicked(MouseEvent event){
        DtoForum clickedForum = forumTable.getSelectionModel().getSelectedItem();

        if(clickedForum != null){
            idField.setText(String.valueOf(clickedForum.getId()));
            nameField.setText(clickedForum.getForumName());
            isManagerField.setText(Boolean.toString(clickedForum.getIsManager()));

            String commentResponse = CallEndpoints.Get("http://localhost:8080/api/comment/comments?forumId=" + idField.getText());

            ChatReformat.comment(commentResponse);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillTable();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ParcelApplication.class.getResource("forum-page.fxml"));
    }

    public void fillTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        forumName.setCellValueFactory(new PropertyValueFactory<>("forumName"));
        isManager.setCellValueFactory(new PropertyValueFactory<>("isManager"));

        forumTable.getItems().setAll(DtoForum.getArray());
    }


}
