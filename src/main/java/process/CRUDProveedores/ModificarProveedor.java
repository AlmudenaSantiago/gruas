/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDProveedores;

import command.proveedores.CargarProveedoresCommand;
import java.io.IOException;
import java.net.URL;
import javax.swing.JOptionPane;
import model.Proveedor;
import process.cargador.CargadorListaProveedor;
import process.parser.proveedor.ProveedorParserJson;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import view.proveedores.JTablaCRUDProveedores;

/**
 *
 * @author Aaron
 */
public class ModificarProveedor extends Modificar {
    
    String stringRespuestaPOST;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlProveedor;
    Proveedor proveedor;
    
    
    public ModificarProveedor(Integer id, String var, String valor) {
      super(id,var,valor);
    }
    
    public void modificarProveedor () throws IOException {
            prepararProveedor();
            enviarModificacion();
            actualizarTabla();
    }
    
    
    
    public void prepararProveedor () throws IOException {
        
          proveedor = new Proveedor();
          proveedor.setId(id);
          
          switch (variableAModificar) {
                            case "nombre":   
                                proveedor.setNombre(valor);
                                urlProveedor = new URL(base + "modificarNombreProveedor");
                                break;
                            case "nif":
                                proveedor.setNif(valor);
                                urlProveedor = new URL(base + "modificarNifProveedor");
                                break;
                            case "domicilio":
                                proveedor.setDomicilio(valor);
                                urlProveedor = new URL(base  + "modificarDomicilioProveedor");
                                break;
                            case "poblacion":
                                proveedor.setPoblacion(valor);
                                urlProveedor = new URL(base  + "modificarPoblacionProveedor");
                                break;
                            case "provincia":
                                proveedor.setProvincia(valor);
                                urlProveedor = new URL(base  + "modificarProvinciaProveedor");
                                break;
                            case "cp":
                                proveedor.setCp(valor);
                                 urlProveedor = new URL(base  + "modificarCpProveedor");
                                break;
                            case "pais":
                                proveedor.setPais(valor);
                                urlProveedor = new URL(base  + "modificarPaisProveedor");
                                break;
                            case "telefono1":
                                proveedor.setTelefono1(valor);
                                 urlProveedor = new URL(base  + "modificarTelefono1Proveedor");
                                break;
                            case "telefono2":
                                proveedor.setTelefono2(valor);
                                urlProveedor = new URL(base  + "modificarTelefono2Proveedor");
                                break;
                            case "correo":
                                proveedor.setCorreo(valor);
                                urlProveedor = new URL(base  + "modificarCorreoProveedor");
                                break;
                                    
                    }
             }

    public void enviarModificacion() throws IOException {
             stringRespuestaPOST = new ConexionPost(new ProveedorParserJson().devuelveJsonDeProveedor(proveedor),urlProveedor).conectar();
    
    } 
   
    public void actualizarTabla() throws IOException {
        if (stringRespuestaPOST.equals("ok"))  {
            JOptionPane.showMessageDialog (null,"Proveedor actualizado correctamente");
            CargarProveedoresCommand cargarProveedoresCommand = new CargarProveedoresCommand (CargadorListaProveedor.getInstance(), new ProveedorParserJson());
            JTablaCRUDProveedores.getInstance().setCargarProveedoresCommand(cargarProveedoresCommand);
            cargarProveedoresCommand.execute();
        }     
        else 
            JOptionPane.showMessageDialog (null,"No se ha podido modificar el proveedor");
        
    } 
    
    
}
