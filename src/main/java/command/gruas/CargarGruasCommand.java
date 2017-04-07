package command.gruas;

import java.util.Date;
import java.util.List;
import model.gruas.Grua;
import process.cargador.gruas.CargadorListaGruas;
import process.parser.gruas.GruasParserJson;
import view.gruas.TablaGruas;




public class CargarGruasCommand {

  
    private final CargadorListaGruas cargadorListaGruas;
    private final GruasParserJson gruasParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarGruasCommand(CargadorListaGruas cargadorListaGruas,GruasParserJson gruaParserJson) {
        this.cargadorListaGruas = cargadorListaGruas;
        this.gruasParserJson = gruaParserJson;
    }

    public  List<Grua> executeLista(){
         return gruasParserJson.parsear(cargadorListaGruas.cargar());
      
    }
    
    
     public  void execute(TablaGruas tablaGruas){
        
       tablaGruas.mostrarGruas(gruasParserJson.parsear(cargadorListaGruas.cargar()));
     
    }

}

