package jsonObjects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Schedule {

    @SerializedName("dia_desc_red")
    private String diaDescricao;

    @ToString.Exclude
    @SerializedName("dia_id")
    private String diaId;

    @SerializedName("hor_ini")
    private String horaInicio;

    @SerializedName("hor_fim")
    private String horaFim;

    @SerializedName("professor")
    private String professor;

    @SerializedName("sala")
    private String sala;

}
