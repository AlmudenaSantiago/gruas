package model;

import com.google.gson.annotations.SerializedName;
import javax.swing.JTextField;

public class Producto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double stock;
    private Double precio;
     private Double precioCompra;
    private int categoria1_id;
    private int categoria2_id;
    private int categoria3_id;
    private int categoria4_id;
    private String color;
    private String imagen;
    private Integer cantidad;
    private String categoria1_nombre;
    private String categoria2_nombre;
    private String categoria3_nombre;
    private String categoria4_nombre;
    private String proveedor;
    private Integer idProveedor;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    
    
    
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    
    
    public String getCategoria1_nombre() {
        return categoria1_nombre;
    }

    public String getCategoria2_nombre() {
        return categoria2_nombre;
    }

    public String getCategoria3_nombre() {
        return categoria3_nombre;
    }

    public String getCategoria4_nombre() {
        return categoria4_nombre;
    }

    public void setCategoria1_nombre(String categoria1_nombre) {
        this.categoria1_nombre = categoria1_nombre;
    }

    public void setCategoria2_nombre(String categoria2_nombre) {
        this.categoria2_nombre = categoria2_nombre;
    }

    public void setCategoria3_nombre(String categoria3_nombre) {
        this.categoria3_nombre = categoria3_nombre;
    }

    public void setCategoria4_nombre(String categoria4_nombre) {
        this.categoria4_nombre = categoria4_nombre;
    }
    
    public int getCategoria2_id() {
        return categoria2_id;
    }

    public int getCategoria3_id() {
        return categoria3_id;
    }

    public int getCategoria4_id() {
        return categoria4_id;
    }

    public String getColor() {
        return color;
    }

    public void setCategoria2_id(int categoria2) {
        this.categoria2_id = categoria2;
    }

    public void setCategoria3_id(int categoria3) {
        this.categoria3_id = categoria3;
    }

    public void setCategoria4_id(int categoria4) {
        this.categoria4_id = categoria4;
    }

    public void setColor(String color) {
        this.color = color;
    }

     
     
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }
    
  

     
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

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCategoria1_id() {
        return categoria1_id;
    }

    public void setCategoria1_id(int categoria) {
        this.categoria1_id = categoria;
    }

    public ProductoPedido getProductoPedido() {
        return productoPedido;
    }

    public void setProductoPedido(ProductoPedido productoPedido) {
        this.productoPedido = productoPedido;
    }


}
