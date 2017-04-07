package command.avisos;

import java.io.IOException;
import java.util.List;
import model.Aviso;
import process.cargador.avisos.CargadorListaAvisos;
import process.parser.avisos.AvisoParserJson;




public class CargarAvisosCommand {

    private final CargadorListaAvisos cargadorLista;
    private final AvisoParserJson parser;

    
    public CargarAvisosCommand(CargadorListaAvisos cargadorLista, AvisoParserJson parser) {
        this.cargadorLista = cargadorLista;
        this.parser = parser;
    }

   
    
    
    public List<Aviso> executeListaPorCliente(Integer idCliente) throws IOException{
       
        return parser.parsear(cargadorLista.cargarAvisosPorCliente(idCliente));
    }

    public List<Aviso> executeListaPorId(Integer id) throws IOException{
       
        return parser.parsear(cargadorLista.cargarAvisosPorId(id));
    }
    
    public List<Aviso> executeListaPorExpediente(String expediente) throws IOException{
       
        return parser.parsear(cargadorLista.cargarAvisosPorExpediente(expediente));
    }
    
    public List<Aviso> executeListaPorPoliza(Integer poliza) throws IOException{
       
        return parser.parsear(cargadorLista.cargarAvisosPorPoliza(poliza));
    }
    
      
    public List<Aviso> executeListaPorMatricula(String matricula) throws IOException {
       
        return parser.parsear(cargadorLista.cargarAvisosPorMatricula(matricula));
    }
      

    public List<Aviso> executeListaTodos() throws IOException{
       
        return parser.parsear(cargadorLista.cargar());
    }    

  


}

