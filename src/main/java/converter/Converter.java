package converter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import jsonObjects.Discipline;
import jsonObjects.Grade;
import jsonObjects.PersonData;
import jsonObjects.Schedule;

import java.util.*;

public class Converter {

    private static final Gson gson = new Gson();


    public static Discipline jsonToDiscipline(JsonElement jsonObject) {

        return gson.fromJson(jsonObject, Discipline.class);
    }

    public static List<Discipline> jsonToDiscipline(JsonArray jsonArray) {

        if(!jsonArray.isEmpty()) {
            List<Discipline> disciplineList = new ArrayList<>();

            for (JsonElement jsonElement : jsonArray) {
                Discipline discipline = gson.fromJson(jsonElement, Discipline.class);
                Map<String, String> diaHoraInicio = new HashMap<>();

                for (Schedule schedule : discipline.getHorarioLista()){
                    if(Objects.isNull(discipline.getDocente())){
                        discipline.setDocente(schedule.getProfessor());
                    }

                    if (!diaHoraInicio.containsKey(schedule.getDiaDescricao())) {
                        diaHoraInicio.put(schedule.getDiaDescricao(), ajeitaHorasString(schedule));
                    }
                }

                discipline.setDiaHoraInicio(diaHoraInicio);

                disciplineList.add(discipline);
            }
            return disciplineList;
        }
        return null;
    }

    private static String ajeitaHorasString(Schedule schedule) {
        String hora = schedule.getHoraInicio().substring(0, schedule.getHoraInicio().length()-3).replace(":", "e");
        if (hora.endsWith("00")){
            return hora.substring(0, hora.length()-3);
        }
        return hora;
    }

    public static HashMap<String, List<Discipline>> montaHorarioSemana(List<Discipline> disciplineList) {

        HashMap<String, List<Discipline>> horarioAulasSemana = new HashMap<>();

        for (Discipline discipline : disciplineList) {
            discipline.getDiaHoraInicio().forEach((dia, hora) -> {
                if (!horarioAulasSemana.containsKey(dia)) {
                    List<Discipline> disciplines = new ArrayList<>();
                    disciplines.add(discipline);
                    horarioAulasSemana.put(dia, disciplines);
                } else {
                    horarioAulasSemana.get(dia).add(discipline);
                }
            });
        }

        return horarioAulasSemana;
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
