package command.serviciosEspeciales;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import model.serviciosEspeciales.ServicioEspecial;
import process.cargador.serviciosEspeciales.CargadorListaServiciosEspeciales;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;




public class CargarServiciosEspecialesCommand {

    private final CargadorListaServiciosEspeciales cargadorLista;
    private final ServicioEspecialParserJson parser;

    
    public CargarServiciosEspecialesCommand(CargadorListaServiciosEspeciales cargadorLista, ServicioEspecialParserJson parser) {
        this.cargadorLista = cargadorLista;
        this.parser = parser;
    }

    
  
    
    public List<ServicioEspecial> executeLista(Integer idCliente) throws IOException{
     
         return parser.parsear(cargadorLista.cargarServicioEspecialPorCliente(idCliente));
    }

    
     public List<ServicioEspecial> executeListaTodosLosServiciosEspeciales() throws IOException{
         
         return parser.parsear(cargadorLista.cargar());
    }

    
    
  


}

