package command.vehiculos;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.vehiculos.Vehiculo;
import process.cargador.vehiculos.CargadorListaVehiculo;

import process.parser.vehiculos.VehiculoParserJson;








public class CargarVehiculosCommand {

  
    private final CargadorListaVehiculo cargadorListaVehiculo;
    private final VehiculoParserJson parser;

    
    
    public CargarVehiculosCommand(CargadorListaVehiculo cargadorListaVehiculo,VehiculoParserJson parser) {
        this.cargadorListaVehiculo = cargadorListaVehiculo;
        this.parser = parser;
    }

    public List<Vehiculo> execute() throws IOException{
       return  parser.parsear(cargadorListaVehiculo.cargar());
       
    }
    
    public List<Vehiculo> executePorId(Integer id) throws IOException {
         return  parser.parsear(cargadorListaVehiculo.cargarVehiculosPorId(id));
    }

    public List<Vehiculo> executePorMarca(Integer idMarca) throws IOException {
         return  parser.parsear(cargadorListaVehiculo.cargarVehiculosPorMarca(idMarca));
    }

   public List<Vehiculo> executePorMarcaYModelo(Integer idMarca, Integer idModelo) throws IOException {
         return  parser.parsear(cargadorListaVehiculo.cargarVehiculosPorMarcaYModelo(idMarca, idModelo));
    }
  
    

}