package com.example.kursinis.utilities;

import com.example.kursinis.model.DtoComment;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class ChatReformat {
    public static List<String> comment(String response) {
        Gson g = new Gson();
        DtoComment comment = g.fromJson(response, DtoComment.class);

        System.out.println(comment);

        return null;
    }
}
