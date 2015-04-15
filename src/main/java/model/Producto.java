package model;

import com.google.gson.annotations.SerializedName;

public class Producto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Double precio;
    private Integer categoria;

    @SerializedName("pivot")
    private ProductoPedido productoPedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public ProductoPedido getProductoPedido() {
        return productoPedido;
    }

    public void setProductoPedido(ProductoPedido productoPedido) {
        this.productoPedido = productoPedido;
    }
}
