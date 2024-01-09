package jsonObjects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Discipline {

    @SerializedName("disciplina")
    private String disciplina;

    @ToString.Exclude
    @SerializedName("mat_id")
    private String idaMateria;

    @ToString.Exclude
    @SerializedName("dof_id")
    private String idDof;

    @SerializedName("horario")
    private List<Schedule> horarioLista;
}
