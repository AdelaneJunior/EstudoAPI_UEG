package formatters;

import jsonObjects.Discipline;

public class DisciplineFormatter {

    public static final String INICIO_RESPOSTA_HOJE = "Hoje você tem aula de: ";
    public static final String INICIO_RESPOSTA_PROXIMA = "Suas próximas aulas são";
    public static final String INICIO_RESPOSTA_SEMANA_TODA = "Suas aulas da semana são";
    public static final String RESPOSTA_SEMANA_TODA_SEGUNDA = ", Segunda-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_TERCA = ", Terça-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_QUARTA = ", Quarta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_QUINTA = ", Quinta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_SEXTA = ", Sexta-feira: ";
    public static final String RESPOSTA_SEMANA_TODA_SABADO = ", Sábado: ";

    public static final String SEQUENCIA_RESPOSTA= ", DISCIPLINA das HORA_INICIO até HORA_FIM";

    public static String formatterDisciplineTodayToSpeech(Discipline discipline){

        StringBuilder speech =  new StringBuilder();

        speech.append(INICIO_RESPOSTA_HOJE);

        return speech.toString();
    }

}
