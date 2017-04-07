package command.vehiculos;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.vehiculos.Marca;
import process.cargador.vehiculos.CargadorListaMarca;
import process.parser.vehiculos.MarcaParserJson;








public class CargarMarcasCommand {

  
    private final CargadorListaMarca cargadorListaMarca;
    private final MarcaParserJson marcaParseJson;

    
    
    public CargarMarcasCommand(CargadorListaMarca cargadorListaMarca,MarcaParserJson marcaParserJson) {
        this.cargadorListaMarca = cargadorListaMarca;
        this.marcaParseJson = marcaParserJson;
    }

    public List<Marca> execute() throws IOException{
       return  marcaParseJson.parsear(cargadorListaMarca.cargar());
       
      }



}