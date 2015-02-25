import com.google.gson.annotations.SerializedName;

public class Pedido {

    private Integer id;
    private String cliente;
    private String usuario;

    @SerializedName("updated_at")
    private String fechaUltimaModificacion;

    @SerializedName("created_at")
    private String fechaCreacion;
    private Double importe;

    public Integer getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public Double getImporte() {
        return importe;
    }
}
