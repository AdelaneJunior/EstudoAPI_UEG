package jsonObjects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class Grade {

    @SerializedName("disc_cursada")
    private String disciplina;

    @SerializedName("mat_mediafinal")
    private Float mediaFinal;

    @SerializedName("periodo_grade")
    private String periodo;

    @SerializedName("nota_list")
    private List<NotaList> notas;
}
