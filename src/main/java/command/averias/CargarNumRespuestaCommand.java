package command.averias;

import java.util.Date;
import java.util.List;
import model.averiasYSoluciones.NumRespuesta;
import process.cargador.averias.CargadorListaNumRespuesta;


import process.parser.averias.NumRespuestaParserJson;



public class CargarNumRespuestaCommand {

  
    private final CargadorListaNumRespuesta cargadorListaNumRespuesta;
    private final NumRespuestaParserJson parser;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarNumRespuestaCommand(CargadorListaNumRespuesta cargadorListaNumRespuesta,NumRespuestaParserJson solucionParserJson) {
        this.cargadorListaNumRespuesta = cargadorListaNumRespuesta;
        this.parser = solucionParserJson;
    }

    public  List<NumRespuesta> executeLista(){
         return parser.parsear(cargadorListaNumRespuesta.cargar());
      
    }
    
    
    /* public  void execute(TablaAverias tablaAverias){
        
       tablaAverias.mostrarAverias(AveriasParserJson.parsear(cargadorListaAverias.cargar()));
     
    }*/

}

