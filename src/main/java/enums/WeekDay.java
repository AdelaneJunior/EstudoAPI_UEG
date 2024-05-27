package enums;

import java.util.Arrays;

public enum WeekDay {

    SEGUNDA("SEG", "Segunda-feira"),
    TERCA("TER", "Terça-feira"),
    QUARTA("QUA", "Quarta-feira"),
    QUINTA("QUI", "Quinta-feira"),
    SEXTA("SEX", "Sexta-feira"),
    SABADO("SAB", "Sábado");

    private final String abreviado;
    private final String completo;
    WeekDay(final String abreviado, final String completo){
        this.abreviado = abreviado;
        this.completo = completo;
    }

    public String getAbreviado() {
        return abreviado;
    }

    public String getCompleto() {
        return completo;
    }

    public static WeekDay getByAbreviado(final String abreviado) {
        return Arrays.stream(values()).filter(value ->
                value.getAbreviado().equals(abreviado)).findFirst().orElse(null);
    }
}
