package org.estudo.tcc;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonData {

    @SerializedName("acu_id")
    private String idPessoa;
}
