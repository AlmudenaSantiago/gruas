package command.averias;

import java.util.Date;
import java.util.List;
import model.averiasYSoluciones.Averia;
import process.cargador.averias.CargadorListaAverias;
import process.parser.averias.AveriasParserJson;



public class CargarAveriasCommand {

  
    private final CargadorListaAverias cargadorListaAverias;
    private final AveriasParserJson AveriasParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarAveriasCommand(CargadorListaAverias cargadorListaAverias,AveriasParserJson AveriaParserJson) {
        this.cargadorListaAverias = cargadorListaAverias;
        this.AveriasParserJson = AveriaParserJson;
    }

    public  List<Averia> executeLista(){
         return AveriasParserJson.parsear(cargadorListaAverias.cargar());
      
    }
    
    
    /* public  void execute(TablaAverias tablaAverias){
        
       tablaAverias.mostrarAverias(AveriasParserJson.parsear(cargadorListaAverias.cargar()));
     
    }*/

}

