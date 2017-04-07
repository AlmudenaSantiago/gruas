
package model;

import com.google.gson.annotations.SerializedName;


public class Gasto {
 

    public Integer id;
    private String nombre;
    private String categoria;
    private double importe;
    private String cantidad;
    private String precio;
    private String forma; 

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    private String proveedor; 

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }


    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public Double getImporte() {
        return importe;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    

    @SerializedName("created_at")
    private String fechaCreacion;

    
    
    
    
    
}
