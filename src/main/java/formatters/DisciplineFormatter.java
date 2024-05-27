package formatters;

import enums.WeekDay;
import jsonObjects.Discipline;
import org.joda.time.DateTime;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DisciplineFormatter {

    public static final String INICIO_RESPOSTA_HOJE = "Hoje você tem aula de: ";
    public static final String INICIO_RESPOSTA_AMANHA = "Amanhã você tem aula de: ";
    public static final String INICIO_RESPOSTA_GENERICA_SEMANA = "Na ? você tem aula de: ";
    public static final String INICIO_RESPOSTA_GENERICA_SABADO = "No sábado você tem aula de: ";
    public static final String INICIO_RESPOSTA_PROXIMA = "Suas próximas aulas são";
    public static final String INICIO_RESPOSTA_SEMANA_TODA = "Suas aulas da semana são";
    public static final String RESPOSTA_SEMANA_TODA_SEGUNDA = ", Segunda-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_TERCA = ", Terça-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_QUARTA = ", Quarta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_QUINTA = ", Quinta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_SEXTA = ", Sexta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_SABADO = ", Sábado: ";
    public static final String SEQUENCIA_RESPOSTA= ", DISCIPLINA das HORA_INICIO até HORA_FIM";


    public static String checkDayAndFormatterToSpeech(String day, Map<String, List<Discipline>> weekSchedule){

        String dayInFull = getDayInFully(day);
        System.out.println("\nDia gerado a partir da data entrada: " + dayInFull +"\n");

        if (!weekSchedule.containsKey(dayInFull)){
            return "Você não tem aula no dia perguntado";
        }

        return switch (dayIsTodayOrTomorrow(dayInFull)) {

            case 0 -> formatterDisciplineDayToSpeech(dayInFull, weekSchedule.get(dayInFull));

            case 1 -> //caso o dia perguntado for hoje
                    formatterDisciplineTodayToSpeech(weekSchedule.get(dayInFull), dayInFull);

            case 2 -> //caso o dia perguntado for amanhã
                    formatterDisciplineTomorrowToSpeech(weekSchedule.get(dayInFull), dayInFull);

            default -> "Você não tem aula no dia perguntado";
        };

    }

    public static String formatterDisciplineTodayToSpeech(List<Discipline> disciplineList, String dayInFull){
        System.out.println("Case 1");
        return makeSpeech(disciplineList, INICIO_RESPOSTA_HOJE, dayInFull);
    }
    private static String formatterDisciplineTomorrowToSpeech(List<Discipline> disciplineList, String dayInFull) {
        System.out.println("Case 2");
        return makeSpeech(disciplineList, INICIO_RESPOSTA_AMANHA, dayInFull);

    }

    private static String formatterDisciplineDayToSpeech(String dayInFull, List<Discipline> disciplineList) {

       System.out.println("Case 0");
       WeekDay dayComplete = WeekDay.getByAbreviado(dayInFull);

       if (Objects.nonNull(dayComplete)){

           StringBuilder speech =  new StringBuilder();

           if(dayComplete.equals(WeekDay.SABADO)){
               speech.append(INICIO_RESPOSTA_GENERICA_SABADO);
           }
           else {
               speech.append(INICIO_RESPOSTA_GENERICA_SEMANA.replace("?", dayComplete.getCompleto()));
           }

           for ( Discipline discipline : disciplineList){
               speech.append(discipline.getDisciplina()).append(" às ").append(discipline.getDiaHoraInicio().get(dayInFull)).append(" e ");
           }

           String speechString = speech.toString();
           return speechString.substring(0, speechString.length()-2);
       }

       return "Nenhuma aula foi encontrada no dia solicitado";
    }

    private static String makeSpeech(List<Discipline> disciplineList, String inicioResposta, String dayInFull) {
        StringBuilder speech =  new StringBuilder();

        speech.append(inicioResposta);

        for ( Discipline discipline : disciplineList){
            speech.append(discipline.getDisciplina())
                    .append(" às ")
                    .append(discipline.getDiaHoraInicio().get(dayInFull))
                    .append(" e ");
        }

        String speechString = speech.toString();
        return speechString.substring(0, speechString.length()-2);
    }
    public static int dayIsTodayOrTomorrow(String day){

        Locale locale = new Locale("pt","BR"); //defino que sera em portugues BR a escrita dos dias
        Date da = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); //defino a timezone da jvm para a de Sao Paulo UTC -3
        System.out.println("Date: " + da.toString());
        DateTime dt = new DateTime(da);
        String diaSemana = new SimpleDateFormat("EE", locale).format(dt.toDate()).toUpperCase(); //converto a data gerada para dia da semana abreviado em upperCase
        String dia = diaSemana.substring(0, diaSemana.length()-1);

        System.out.println("Dia antes do primeiro if: "+dia);
        if (normalizeDayName(dia).equals(day)){
            return 1;
        }

        diaSemana = new SimpleDateFormat("EE", locale).format(dt.plusDays(1).toDate()).toUpperCase();
        dia = diaSemana.substring(0, diaSemana.length()-1);

        System.out.println("Dia dps do primeiro if: "+dia);

        if (normalizeDayName(dia).equals(day)){
            return 2;
        }

        return 0;
    }

    public static String getDayInFully(String date){

        java.util.Locale locale = new java.util.Locale("pt","BR");
        Date da = new Date(date.replace('-', '/'));
        String diaSemana = new SimpleDateFormat("EE", locale).format(da).toUpperCase();

        return normalizeDayName(diaSemana.substring(0, diaSemana.length()-1));
    }

    public static String normalizeDayName(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
