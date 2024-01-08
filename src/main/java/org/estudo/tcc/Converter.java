package org.estudo.tcc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static final Gson gson = new Gson();


    public static Discipline jsonToDiscipline(JsonElement jsonObject) {

        return gson.fromJson(jsonObject, Discipline.class);
    }

    public static List<Discipline> jsonToDiscipline(JsonArray jsonArray) {

        if(!jsonArray.isEmpty()) {
            List<Discipline> disciplineList = new ArrayList<>();

            for (JsonElement jsonElement : jsonArray) {
                disciplineList.add(gson.fromJson(jsonElement, Discipline.class));
            }
            return disciplineList;
        }
        return null;
    }

    public static String getAcuId(JsonElement jsonElement){

       PersonData data = gson.fromJson(jsonElement, PersonData.class);

       return data.getIdPessoa();
    }

    public static List<Grade> jsonToGrade(JsonArray jsonArray){
        List<Grade> grades = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            grades.add(gson.fromJson(jsonElement, Grade.class));
        }

        return grades;

    }
}
