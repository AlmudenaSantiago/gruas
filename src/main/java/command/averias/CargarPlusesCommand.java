package command.averias;

import java.util.Date;
import java.util.List;
import model.averiasYSoluciones.Plus;
import process.cargador.averias.CargadorListaPlus;
import process.parser.averias.PlusParserJson;



public class CargarPlusesCommand {

  
    private final CargadorListaPlus cargadorLista;
    private final PlusParserJson plusParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarPlusesCommand(CargadorListaPlus cargadorListaAverias,PlusParserJson parserJson) {
        this.cargadorLista = cargadorListaAverias;
        this.plusParserJson = parserJson;
    }

    public  List<Plus> executeLista(){
         return plusParserJson.parsear(cargadorLista.cargar());
      
    }
    
    
    /* public  void execute(TablaAverias tablaAverias){
        
       tablaAverias.mostrarAverias(AveriasParserJson.parsear(cargadorListaAverias.cargar()));
     
    }*/

}

