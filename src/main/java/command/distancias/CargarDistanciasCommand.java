package command.distancias;

import java.util.Date;
import java.util.List;
import model.Distancia;
import process.cargador.distancias.CargadorListaDistancias;
import process.parser.distancias.DistanciaParserJson;




public class CargarDistanciasCommand {

  
    private final CargadorListaDistancias cargadorLista;
    private DistanciaParserJson parser;
    
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarDistanciasCommand(CargadorListaDistancias cargadorLista,DistanciaParserJson parser) {
        this.cargadorLista = cargadorLista;
        this.parser = parser;
    }
    
    
    
 
    
  
    public  List<Distancia> executeLista(){
         return parser.parsear(cargadorLista.cargar());
      
    }
    
    
    
   
    
    

}

