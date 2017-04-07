/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDProductos;

import process.CRUD.Modificar;
import command.productos.CargarDiasFestivosCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Producto;
import process.cargador.CargadorListaProducto;
import process.parser.productos.ProductoParserJson;
import view.productos.JTablaCRUD;

/**
 *
 * @author Aaron
 */
public class ModificarProducto extends Modificar {
    
  
    Producto producto;
    String stringRespuestaPOST;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlProducto;
   
    
   
    public ModificarProducto(Integer id, String var, String valor) {
      
      super(id,var,valor);
    }
    

    
    public void modificarProducto () throws IOException {
            prepararProducto();
            enviarModificacion();
            actualizarTabla();
    }
    
    
    
     public void prepararProducto () throws MalformedURLException, IOException {
    
               producto = new Producto();
               producto.setId(id);
              
               switch (variableAModificar) {
                   case "nombre":
                          producto.setNombre(valor);
                          urlProducto = new URL(base + "modificarNombreProducto");
                          break;
                   case "descripcion":
                         
                          producto.setDescripcion(valor);
                          urlProducto = new URL(base + "modificarDescripcionProducto");
                          break;
                    case "color":
                         
                          producto.setColor(valor);
                          urlProducto = new URL(base + "modificarAcabadoProducto");
                        break;
                   case "precio":
                          producto.setPrecio(Double.parseDouble(valor));
                          urlProducto = new URL(base + "modificarPrecioProducto");
                          break;
                   case "imagen":
                          producto.setImagen(valor);
                          urlProducto = new URL( base + "modificarImagenProducto");
                          break;
                       
                    case "stock":
                          producto.setStock(Double.parseDouble(valor));
                          urlProducto = new URL( base + "modificarStockProducto");
                          break;
                    case "precioCompra":
                          producto.setPrecioCompra(Double.parseDouble(valor));
                          urlProducto = new URL( base + "modificarPrecioCompra");
                          break;
                          
               }
                    
            }
     
     
     
     
     public void enviarModificacion () throws IOException {
          stringRespuestaPOST = new ConexionPost(new ProductoParserJson().devuelveJsonDeProducto(producto), urlProducto).conectar();
       
     }
     
     public void actualizarTabla () {
     
          if (stringRespuestaPOST.equals("ok"))  {
                       JOptionPane.showMessageDialog (null,"Producto actualizado correctamente");
                       CargarDiasFestivosCommand cargarProductosCommand = new CargarDiasFestivosCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                       JTablaCRUD.getInstance().setCargarProductosCommand(cargarProductosCommand);
                       cargarProductosCommand.execute();
                   }     
                   else JOptionPane.showMessageDialog (null,"No se ha podido modificar el producto");
     
     }
     
            
            
    
    
}
