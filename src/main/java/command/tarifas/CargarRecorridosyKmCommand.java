package command.tarifas;

import java.util.List;
import model.RecorridosyKm;
import process.cargador.recorridosykm.CargadorListaRecorridosyKm;
import process.parser.recorridosykm.RecorridosyKmParserJson;




public class CargarRecorridosyKmCommand {

  
    private final CargadorListaRecorridosyKm cargadorLista;
    private final RecorridosyKmParserJson recorridosykmparserjson;

    
    
    public CargarRecorridosyKmCommand(CargadorListaRecorridosyKm cargadorLista,RecorridosyKmParserJson municipioParserJson) {
        this.cargadorLista = cargadorLista;
        this.recorridosykmparserjson = municipioParserJson;
    }

    public  List<RecorridosyKm> executeLista(Integer idCliente){
         return recorridosykmparserjson.parsear(cargadorLista.cargar(idCliente));
      
    }
    
    
    

}

