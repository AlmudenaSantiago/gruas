/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.proveedores;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.Proveedor;
import process.cargador.CargadorListaProveedor;
import process.parser.proveedor.ProveedorParserJson;
import view.proveedores.JTablaCRUDProveedores;

public class CargarProveedoresCommand {
    
    private final CargadorListaProveedor cargadorListaProveedor;
    private final ProveedorParserJson proveedorParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarProveedoresCommand(CargadorListaProveedor cargadorListaProveedor,ProveedorParserJson proveedorParserJson) {
        this.cargadorListaProveedor = cargadorListaProveedor;
        this.proveedorParserJson = proveedorParserJson;
    }

    public void execute() throws IOException{
        JTablaCRUDProveedores.getInstance().mostrarProveedores(proveedorParserJson.parsear(cargadorListaProveedor.cargar()));
      }
      
    
    public List<Proveedor> executeProveedor(Integer id) throws IOException {
        return proveedorParserJson.parsear(cargadorListaProveedor.cargarProveedor(id));
      }
      
    
    
}
