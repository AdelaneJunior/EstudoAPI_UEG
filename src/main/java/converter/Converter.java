package converter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import jsonObjects.Discipline;
import jsonObjects.Grade;
import jsonObjects.PersonData;

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

    /**
     * Recebe o jsonArray com todas as disciplinas e notas e as converte para uma lista de Grade que possui os dados
     * da disciplina e a lista com notas da primeira e segunda VA
     * @param jsonArray Json com todos dos dados de disciplinas realizadas pelo estudante
     * @return List de Grade preenchido ou vazio caso não tenha nada no jsonArray
     */
    public static List<Grade> jsonToGrade(JsonArray jsonArray){
        List<Grade> grades = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            grades.add(gson.fromJson(jsonElement, Grade.class));
        }

        return grades;

    }

    /**
     * Filtra as disciplinas e retornas apenas a do periodo passado no segundo parametro
     * @param jsonArray Json com todos dos dados de disciplinas realizadas pelo estudante
     * @param periodo int referente a qual perido deve ser filtrado as disciplinas
     * @return List de GradeList de Grade, preenchido caso tenha materias do perido solicitado, vazio caso n ache nenhuma
     */
    public static List<Grade> jsonToGradeWithPeriod(JsonArray jsonArray, int periodo){

        List<Grade> grades = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            if(jsonElement.toString().contains("\"periodo_grade\":\""+ periodo+"\"")){
                grades.add(gson.fromJson(jsonElement, Grade.class));
            }
        }

        return grades;
    }

    public static List<Grade> jsonToGradeWithName(JsonArray jsonArray, String disciplina){

        List<Grade> grades = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            if(jsonElement.toString().contains("\"disc_cursada\":\""+ disciplina+"\"")){
                grades.add(gson.fromJson(jsonElement, Grade.class));
            }
        }
        return  null;
    }
}
