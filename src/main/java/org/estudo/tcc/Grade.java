package org.estudo.tcc;

import com.google.gson.annotations.SerializedName;
import lombok.ToString;

import java.util.List;

@ToString
public class Grade {

    @SerializedName("disc_cursada")
    private String disciplina;

    @SerializedName("mat_mediafinal")
    private float mediaFinal;

    @SerializedName("periodo_grade")
    private String periodo;

    @SerializedName("nota_list")
    private List<NotaList> notas;
}