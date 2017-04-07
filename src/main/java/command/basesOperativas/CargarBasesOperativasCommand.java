package command.basesOperativas;

import command.serviciosEspeciales.*;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import model.basesOperativas.BaseOperativa;
import model.serviciosEspeciales.ServicioEspecial;
import process.cargador.CargadorListaBasesOperativas;
import process.cargador.serviciosEspeciales.CargadorListaServiciosEspeciales;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;




public class CargarBasesOperativasCommand {

    private final CargadorListaBasesOperativas cargadorLista;
    private final BaseOperativaParserJson parser;

    
    public CargarBasesOperativasCommand(CargadorListaBasesOperativas cargadorLista, BaseOperativaParserJson parser) {
        this.cargadorLista = cargadorLista;
        this.parser = parser;
    }

   
    
  
    
    
    
    public List<BaseOperativa> executeLista(Integer idCliente) throws IOException{
       
        return parser.parsear(cargadorLista.cargarBaseOperativaPorCliente(idCliente));
    }


    public List<BaseOperativa> executeListaTodasLasBases() throws IOException{
       
        return parser.parsear(cargadorLista.cargar());
    }    

  


}

