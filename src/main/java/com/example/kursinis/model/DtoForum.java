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
public class DtoForum {
    private Integer id;
    private String forumName;
    private Boolean isManager;

    public DtoForum(JSONObject jsonObject){
        this.id = (Integer) jsonObject.get("id");
        this.forumName = (String) jsonObject.get("forumName");
        this.isManager = (Boolean) jsonObject.get("isManager");
    }

    public static List<DtoForum> getArray() {
        return getArray(CallEndpoints.Get("http://localhost:8080/api/forum/tests"));
    }
    public static List<DtoForum> getArray(String body) {
        List<DtoForum> forums = new ArrayList<>();
        JSONArray responseArray = new JSONArray(body);
        for (int i=0;i<responseArray.length();i++){
            forums.add(new DtoForum(responseArray.getJSONObject(i)));
        }
        return forums;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }
}
