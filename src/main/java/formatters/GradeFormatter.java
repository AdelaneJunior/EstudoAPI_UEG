package formatters;

import constants.Ordinals;
import jsonObjects.Grade;

import java.util.List;

public class GradeFormatter {

    public static String INITIAL_ANSWER = "Suas notas no ? período foram";
    public static String SEQUENCE_ANSWER = ", NOTAFINAL em DISCIPLINA ";

    public static String formatterGradeToSpeech(Grade grade, int periodoInt){

        StringBuilder resposta = new StringBuilder();
        String periodo = Ordinals.getOrdinalByNumber(periodoInt);
        resposta.append(INITIAL_ANSWER.replace("?", periodo));

        resposta.append(SEQUENCE_ANSWER.replace("NOTAFINAL", grade.getMediaFinal().toString()).replace("DISCIPLINA", grade.getDisciplina()));

        return resposta.toString();
    }

    public static String formatterGradeToSpeech(List<Grade> gradeList, int periodoInt){

        StringBuilder resposta = new StringBuilder();
        String periodo = Ordinals.getOrdinalByNumber(periodoInt);
        resposta.append(INITIAL_ANSWER.replace("?", periodo));

        for(Grade grade : gradeList){
            if(grade.getDisciplina().charAt(grade.getDisciplina().length() - 1) == 'I' &&
                    grade.getDisciplina().charAt(grade.getDisciplina().length() - 2) == ' ' ){
              grade.setDisciplina(grade.getDisciplina().substring(0, grade.getDisciplina().length()-1)+" Um");
            }
            resposta.append(SEQUENCE_ANSWER.replace("NOTAFINAL", grade.getMediaFinal().toString()).replace("DISCIPLINA", grade.getDisciplina()));
        }

        return resposta.toString();
    }
}
