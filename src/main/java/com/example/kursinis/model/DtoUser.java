package com.example.kursinis.model;

import com.example.kursinis.utilities.CallEndpoints;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String salary;
    private String position;
    private String status;
    private String login;

    public DtoUser(JSONObject jsonObject) {
        this.id = (Integer) jsonObject.get("id");
        this.firstName = (String) jsonObject.get("firstName");
        this.lastName = (String) jsonObject.get("lastName");
        this.email = (String) jsonObject.get("email");
        this.salary = (String) jsonObject.get("salary");
        this.position = (String) jsonObject.get("position");
        this.status = (String) jsonObject.get("status");
        this.login = (String) jsonObject.get("login");
    }

    public static List<DtoUser> getArray() {
        return getArray(CallEndpoints.Get("http://localhost:8080/api/user/users"));
    }
    public static List<DtoUser> getArray(String body) {
        List<DtoUser> users = new ArrayList<>();
        JSONArray responseArray = new JSONArray(body);
        for (int i=0;i<responseArray.length();i++){
            users.add(new DtoUser(responseArray.getJSONObject(i)));
        }
        return users;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
