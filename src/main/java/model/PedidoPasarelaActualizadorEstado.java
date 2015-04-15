package model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PedidoPasarelaActualizadorEstado implements Serializable {

    private String id;
    private String estado;

    public PedidoPasarelaActualizadorEstado(Integer id, String estado) {
        this.id = id.toString();
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

     public String getEstado() {
        return estado;
    }

}
