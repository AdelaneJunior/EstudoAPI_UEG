package formatters;

import constants.Ordinals;
import jsonObjects.Grade;

import java.util.List;

public class GradeFormatter {

    public static String INITIAL_ANSWER = "Suas notas no ? período foram";
    public static String SIMPLE_SEQUENCE_ANSWER = ", NOTAFINAL em DISCIPLINA ";
    public static String DETAILED_SEQUENCE_ANSWER = ", DISCIPLINA, v.a 1: NOTAVA1, v.a 2: NOTAVA2 ";


    public static String formatterGradeToSpeech(Grade grade, int periodoInt){

        StringBuilder resposta = iniciaResposta(periodoInt);

        resposta.append(SIMPLE_SEQUENCE_ANSWER.replace("NOTAFINAL",
                grade.getMediaFinal().toString()).replace("DISCIPLINA", grade.getDisciplina()));

        return resposta.toString();
    }

    public static String formatterGradeToSpeech(List<Grade> gradeList, int periodoInt){

        StringBuilder resposta = iniciaResposta(periodoInt);

        for(Grade grade : gradeList){
            romanOneToWordOne(grade);
            resposta.append(
                    SIMPLE_SEQUENCE_ANSWER.replace("NOTAFINAL", grade.getMediaFinal().toString())
                            .replace("DISCIPLINA", grade.getDisciplina()));
        }

        return resposta.toString();
    }

    public static String formatterDetailedGradeToSpeech(List<Grade> gradeList, int periodoInt) {

        StringBuilder resposta = iniciaResposta(periodoInt);

        for(Grade grade : gradeList){
            romanOneToWordOne(grade);
            resposta.append(
                    DETAILED_SEQUENCE_ANSWER.replace("DISCIPLINA", grade.getDisciplina())
                            .replace("NOTAVA1", grade.getNotas().get(0).getNota().toString())
                            .replace("NOTAVA2", grade.getNotas().get(1).getNota().toString())
            );
        }

        return resposta.toString();
    }

    private static StringBuilder iniciaResposta(int periodoInt) {
        StringBuilder resposta = new StringBuilder();
        String periodo = Ordinals.getOrdinalByNumber(periodoInt);
        resposta.append(INITIAL_ANSWER.replace("?", periodo));
        return resposta;
    }

    private static void romanOneToWordOne(Grade grade) {
        if(grade.getDisciplina().charAt(grade.getDisciplina().length() - 1) == 'I' &&
                grade.getDisciplina().charAt(grade.getDisciplina().length() - 2) == ' ' ){
            grade.setDisciplina(grade.getDisciplina().substring(0, grade.getDisciplina().length()-1)+" Um");
        }
    }
}
