package command.municipios;

import java.util.Date;
import java.util.List;
import model.Municipio;
import process.cargador.municipios.CargadorListaMunicipio;

import process.parser.diasFestivos.FestivoSemanalParserJson;
import process.parser.municipios.MunicipioParserJson;




public class CargarMunicipiosCommand {

  
    private final CargadorListaMunicipio cargadorLista;
    private MunicipioParserJson parser;

    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarMunicipiosCommand(CargadorListaMunicipio cargadorLista,MunicipioParserJson parser) {
        this.cargadorLista = cargadorLista;
        this.parser= parser;
    }
    
    
   
  
    public  List<Municipio> executeLista(){
        System.out.println(cargadorLista.cargar());
        return parser.parsear(cargadorLista.cargar());
      
    }
    

}

