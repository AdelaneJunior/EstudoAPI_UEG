package jsonObjects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
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
    @ToString.Exclude
    private List<Schedule> horarioLista;

    private String docente;

    private Map<String, String> diaHoraInicio = new HashMap<>();

}
