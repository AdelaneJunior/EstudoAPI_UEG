package org.estudo.tcc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static final Gson gson = new Gson();

    public static Discipline JsonToDiscipline(JsonElement jsonObject) {

        return gson.fromJson(jsonObject, Discipline.class);
    }

    public static List<Discipline> JsonToDiscipline(JsonArray jsonArray) {

        List<Discipline> disciplineList = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            disciplineList.add(gson.fromJson(jsonElement, Discipline.class));
        }

        return disciplineList;
    }
}
