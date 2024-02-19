import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        java.util.Locale locale = new java.util.Locale("pt","BR");
        String date = "2024-02-15";
        Date da = new Date(date.replace('-', '/'));
        DateTime dt = new DateTime().withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Sao_Paulo")));
        String diaSemana = new SimpleDateFormat("EE", locale).format(dt.toDate()).toUpperCase();

        System.out.println(diaSemana);

//        HttpRequests httpRequests = new HttpRequests("02296120164", "@DEJunior06");

//        String day = "QUIN";
//        java.util.Locale locale = new java.util.Locale("pt","BR");
//        Date da = new Date();
//        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//        System.out.println("Date: " + da.toString());
//        DateTime dt = new DateTime(da);
//        String diaSemana = new SimpleDateFormat("EE", locale).format(dt.toDate()).toUpperCase();
//        String dia = diaSemana.substring(0, diaSemana.length()-1);
//
//        System.out.println("Dia antes do primeiro if: "+dia);
//        if (normalizar(dia).equals(day)){
//            System.out.println("1");
//        }
//
//        diaSemana = new SimpleDateFormat("EE", locale).format(dt.plusDays(1).toDate()).toUpperCase();
//        dia = diaSemana.substring(0, diaSemana.length()-1);
//
//        System.out.println("Dia dps do primeiro if: "+dia);
//
//        if (normalizar(dia).equals(day)){
//            System.out.println("2");
//        }
//
//        System.out.println("0");

//        if(httpRequests.doLogin()) {
//            httpRequests.enterPortalEstudante();
//            httpRequests.getClassSchedule();
//            httpRequests.getGrade();
//
//            if(Objects.nonNull(httpRequests.getJsonGrade())){
//                Converter.jsonToGradeWithPeriod(httpRequests.getJsonGrade(), 6);
//            }
//
//            if (Objects.nonNull(httpRequests.getJsonSchedule())) {
//                List<Discipline> disciplineList =
//                        Converter.jsonToDiscipline(httpRequests.getJsonSchedule());
//
//                if (Objects.nonNull(disciplineList)) {
//                    HashMap<String, List<Discipline>> horarioSemana = Converter.montaHorarioSemana(disciplineList);
////                    for (String dia : horarioSemana.keySet()){
////
////                        Date da = new Date();
////                        DateTime dt = new DateTime(da);
////                        String diaSemana = new SimpleDateFormat("EE").format(dt.toDate()).toUpperCase();
////                        System.out.println(dia + "\n" + horarioSemana.get(diaSemana.substring(0, diaSemana.length()-1)) + "\n");
////
////                    }
//
//                    String dataEntrada = "2024-02-10";
//
//                    Date da = new Date(dataEntrada.replace('-', '/'));
//                    DateTime dt = new DateTime(da);
//                    String diaSemana = new SimpleDateFormat("EE").format(dt.toDate()).toUpperCase();
//                    String dia = diaSemana.substring(0, diaSemana.length()-1);
//
//                    System.out.println(dia + ": " +horarioSemana.get(normalizar(dia)) + "\n");
//
////                    for (Discipline discipline : disciplineList) {
////                        System.out.println("Disciplina e horarios: " + discipline.toString() + "\n");
////                    }
//                }
//
//            }
//
////            if(Objects.nonNull(httpRequests.getJsonGrade())){
////
//////                System.out.println(Converter.jsonToGrade((httpRequests.getJsonGrade())));
////            }
//        }
    }

    public static String normalizar(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}