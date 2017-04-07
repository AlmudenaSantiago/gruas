package command.diasFestivos;


import command.gruas.*;
import java.util.Date;
import java.util.List;
import model.DiaFestivo;
import model.FestivoSemanal;
import model.HorarioNocturno;
import model.gruas.Grua;
import process.cargador.diasFestivos.CargadorListaNocturno;

import process.cargador.gruas.CargadorListaGruas;
import process.parser.diasFestivos.DiaFestivoParserJson;
import process.parser.diasFestivos.FestivoSemanalParserJson;
import process.parser.diasFestivos.NocturnoParserJson;
import process.parser.gruas.GruasParserJson;
import view.gruas.TablaGruas;




public class CargarNocturnoCommand {

  
    private final CargadorListaNocturno cargadorLista;
    private NocturnoParserJson nocturno;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarNocturnoCommand(CargadorListaNocturno cargadorLista,NocturnoParserJson nocturnoParserJson) {
        this.cargadorLista = cargadorLista;
        this.nocturno = nocturnoParserJson;
    }
    
    

    
    
    public  List<HorarioNocturno> executeListaNocturno(Integer idCliente){
        System.out.println("findeeeeeeeee"   + cargadorLista.cargarNocturno(idCliente));
         return nocturno.parsear(cargadorLista.cargarNocturno(idCliente));
      
    }
    
    

}

