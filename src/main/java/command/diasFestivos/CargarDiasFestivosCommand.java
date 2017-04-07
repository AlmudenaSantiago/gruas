package command.diasFestivos;

import command.gruas.*;
import java.util.Date;
import java.util.List;
import model.DiaFestivo;
import model.FestivoSemanal;
import model.gruas.Grua;
import process.cargador.diasFestivos.CargadorListaDiasFestivos;
import process.cargador.gruas.CargadorListaGruas;
import process.parser.diasFestivos.DiaFestivoParserJson;
import process.parser.diasFestivos.FestivoSemanalParserJson;
import process.parser.gruas.GruasParserJson;
import view.gruas.TablaGruas;




public class CargarDiasFestivosCommand {

  
    private final CargadorListaDiasFestivos cargadorLista;
    private DiaFestivoParserJson festivoParserJson;
    private FestivoSemanalParserJson festivoSemanal;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarDiasFestivosCommand(CargadorListaDiasFestivos cargadorLista,DiaFestivoParserJson festivoParserJson) {
        this.cargadorLista = cargadorLista;
        this.festivoParserJson = festivoParserJson;
    }
    
    
    
    public CargarDiasFestivosCommand(CargadorListaDiasFestivos cargadorLista,FestivoSemanalParserJson festivoSemanal) {
        this.cargadorLista = cargadorLista;
        this.festivoSemanal = festivoSemanal;
    }
    
  
    public  List<DiaFestivo> executeLista(){
         return festivoParserJson.parsear(cargadorLista.cargar());
      
    }
    
    
    
    public  List<FestivoSemanal> executeListaFestivosSemanal(Integer id){
        System.out.println(festivoSemanal.parsear(cargadorLista.cargarFestivosSemanal(id)));
         return festivoSemanal.parsear(cargadorLista.cargarFestivosSemanal(id));
      
    }
    
    

}

