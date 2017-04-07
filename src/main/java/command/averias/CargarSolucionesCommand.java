package command.averias;

import java.util.Date;
import java.util.List;
import model.averiasYSoluciones.Solucion;
import process.cargador.averias.CargadorListaSolucion;
import process.parser.averias.SolucionParserJson;



public class CargarSolucionesCommand {

  
    private final CargadorListaSolucion cargadorListaSoluciones;
    private final SolucionParserJson solucionParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarSolucionesCommand(CargadorListaSolucion cargadorListaSoluciones,SolucionParserJson solucionParserJson) {
        this.cargadorListaSoluciones = cargadorListaSoluciones;
        this.solucionParserJson = solucionParserJson;
    }

    public  List<Solucion> executeLista(){
         return solucionParserJson.parsear(cargadorListaSoluciones.cargar());
      
    }
    
    
    /* public  void execute(TablaAverias tablaAverias){
        
       tablaAverias.mostrarAverias(AveriasParserJson.parsear(cargadorListaAverias.cargar()));
     
    }*/

}

