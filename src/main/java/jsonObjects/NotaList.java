package jsonObjects;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotaList {

    @SerializedName("titulo")
    private String va;

    @SerializedName("nota")
    private float nota;

    @SerializedName("nota_peso")
    private float notaComPeso;
}
