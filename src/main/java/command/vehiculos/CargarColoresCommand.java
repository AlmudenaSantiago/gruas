package command.vehiculos;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.vehiculos.ColorVehiculo;
import process.cargador.vehiculos.CargadorListaColor;
import process.parser.vehiculos.ColorParserJson;








public class CargarColoresCommand {

  
    private final CargadorListaColor cargadorListaColores;
    private final ColorParserJson coloresParseJson;

    
    
    public CargarColoresCommand(CargadorListaColor cargadorListaColor,ColorParserJson colorParserJson) {
        this.cargadorListaColores = cargadorListaColor;
        this.coloresParseJson = colorParserJson;
    }

    public List<ColorVehiculo> execute() throws IOException{
       return  coloresParseJson.parsear(cargadorListaColores.cargar());
       
      }



}