package command.clientes;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
import view.clientes.JTablaCRUDClientes;
import view.clientes.JTablaCRUDClientesReducida;

public class CargarClientesCommand {
    private final CargadorListaCliente cargadorListaCliente;
    private final ClienteParserJson clienteParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarClientesCommand(CargadorListaCliente cargadorListaCliente,ClienteParserJson clienteParserJson) {
        this.cargadorListaCliente = cargadorListaCliente;
        this.clienteParserJson = clienteParserJson;
    }

    public void execute() throws Exception{
        JTablaCRUDClientes.getInstance().mostrarClientes(clienteParserJson.parsear(cargadorListaCliente.cargar()));
      }
    
    
    
     public List<Cliente> executeLista() throws Exception{
        return clienteParserJson.parsear(cargadorListaCliente.cargar());
      }
      
     
     public List<Cliente> executeListaClientesBaja() throws Exception{
         System.out.println("clientes de baja " + cargadorListaCliente.cargarClientesBaja());
         return clienteParserJson.parsear(cargadorListaCliente.cargarClientesBaja());
     }
     
    
     public List<Cliente> executeObservacionesGenerales(Integer id) {
       
        try {
            return clienteParserJson.parsearObservaciones(cargadorListaCliente.cargarObservaciones(id));
        } catch (Exception ex) {
            Logger.getLogger(CargarClientesCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
     }
     
     public void executeReducida() throws Exception{
         JTablaCRUDClientesReducida.getInstance().mostrarClientes(clienteParserJson.parsear(cargadorListaCliente.cargar()));
      }
    
     public List<Cliente> executeCliente(Integer id) throws Exception{
         return clienteParserJson.parsear(cargadorListaCliente.cargar(id));
      }
     
}


