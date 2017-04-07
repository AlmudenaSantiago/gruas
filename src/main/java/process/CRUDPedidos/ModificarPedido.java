/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDPedidos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Pedido;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.parser.pedidos.PedidoParserJson;

/**
 *
 * @author Aaron
 */
public class ModificarPedido extends Modificar {
    
  
    Pedido pedido;
    String stringRespuestaPOST;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlPedido;
   
    
   
    public ModificarPedido(Integer id, String var, String valor) {
      
      super(id,var,valor);
    }
    

    
    public void modificarPedido () throws IOException {
            prepararPedido();
            enviarModificacion();
            actualizarTabla();
    }
    
    
    
     public void prepararPedido () throws MalformedURLException, IOException {
    
               pedido = new Pedido();
               pedido.setId(id);
              
               switch (variableAModificar) {
                   case "descuento":
                          pedido.setDescuento(valor);
                          urlPedido = new URL(base + "modificarDescuentoPedido");
                          break;
                   case "importe":
                         pedido.setImporte(Double.parseDouble(valor));
                         urlPedido = new URL(base + "modificarImportePedido");
               }
                    
            }
     
     
     
     
     public void enviarModificacion () throws IOException {
          stringRespuestaPOST = new ConexionPost(new PedidoParserJson().devuelveJsonDePedido(pedido), urlPedido).conectar();
       
     }
     
     public void actualizarTabla () {
     
          if (stringRespuestaPOST.equals("ok"))  {
                       JOptionPane.showMessageDialog (null,"Pedido modificado correctamente");
                    }     
                   else JOptionPane.showMessageDialog (null,"No se ha podido modificar el pedido");
     
     }
     
            
            
    
    
}
