package com.example.kursinis.utilities;

import com.example.kursinis.model.DtoComment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ChatReformat {
    public static List<String> comment(String response) {

        List<String> comments = new ArrayList<>();
        response = response.replace("[", "");
        response = response.replace("]", "");
        String[] lines = response.split("},");
        for (String line: lines){
            response = response.replace("{", "");
            response = response.replace("}", "");
            String[] temp = line.split(",");
            String comment = "[" + temp[1] + "]<" + temp[2] + ">:" + temp[4] + "\n";
            comments.add(comment);
        }
        return comments;
    }
}
