package command.gruas;

import java.util.Date;
import java.util.List;
import model.gruas.Gruista;
import process.cargador.gruas.CargadorListaGruistas;
import process.parser.gruistas.GruistasParserJson;




public class CargarGruistasCommand {

  
    private final CargadorListaGruistas cargadorListaGruistas;
    private final GruistasParserJson gruistaParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarGruistasCommand(CargadorListaGruistas cargadorListaGruistas,GruistasParserJson gruistaParserJson) {
        this.cargadorListaGruistas = cargadorListaGruistas;
        this.gruistaParserJson = gruistaParserJson;
    }

    public  List<Gruista> executeLista(){
         return gruistaParserJson.parsear(cargadorListaGruistas.cargar());
      
    }
    
    
    /* public  void execute(TablaGruistas tablaGruistas){
        
       tablaGruistas.mostrarGruas(gruistaParserJson.parsear(cargadorListaGruistas.cargar()));
     
    }*/

}

