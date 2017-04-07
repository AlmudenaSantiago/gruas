package model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pedido {

    public Integer id;
    private String cliente;
    private String vendedor;
    private Integer clienteId;
    public String estado;
    public String abonado;
    private Double importe;
    private String forma;
    private Double cantidadPendiente;
    private String descuento;

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
    
      

    public List<ProductoPedido> getProductos() {
        return productos;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    
    
    
    
    public Double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }
    
    
    
    // nueva
    private List<ProductoPedido> productos;

    public List<ProductoPedido> getLista() {
        return productos;
    }

    public void setLista(List<ProductoPedido> lista) {
        this.productos = lista;
    }
    
    
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setAbonado(String abonado) {
        this.abonado = abonado;
    }

    public String getCliente() {
        return cliente;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public String getAbonado() {
        return abonado;
    }
    
        

    @SerializedName("updated_at")
    private String fechaUltimaModificacion;

    @SerializedName("created_at")
    private String fechaCreacion;

   
    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    
     public void setEstado(String estado) {
        this.estado = estado;
    }

     public String getEstado() {
        return estado;
    }

    public void setMesa(String mesa) {
        this.cliente = mesa;
    }
     
    public String getMesa() {
        return cliente;
    }

     public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    

    public String getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

      public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getFechaCreacion() {
        return fechaCreacion;
    }

      public void setImporte(Double importe) {
        this.importe = importe;
    }
    
    public Double getImporte() {
        return importe;
    }

  
    
}
