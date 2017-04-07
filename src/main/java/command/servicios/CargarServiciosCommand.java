package command.servicios;

import model.Servicio;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import process.cargador.CargadorListaServicios;
import process.parser.servicios.ServicioParserJson;
import view.servicios.TablaServicios;




public class CargarServiciosCommand {

    private final CargadorListaServicios cargadorListaServicios;
    private final ServicioParserJson servicioParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarServiciosCommand(CargadorListaServicios cargadorLista,ServicioParserJson servicioParserJson) {
        this.cargadorListaServicios = cargadorLista;
        this.servicioParserJson = servicioParserJson;
    }

    
    public void execute(TablaServicios tablaServicios, String estado) throws IOException{
       tablaServicios.mostrarServicios(servicioParserJson.parsear(cargadorListaServicios.cargarServiciosPorEstado(estado)));
      }
    
    
    
    
    
     public List<Servicio> executeLista() throws IOException{
       
         return servicioParserJson.parsear(cargadorListaServicios.cargar());
      }

    public void executeFechas(Date fechaDesde, Date fechaHasta) throws IOException {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
       // HAY QUE PASARLE LA TABLA
         new TablaServicios().mostrarServicios(servicioParserJson.parsear(cargadorListaServicios.cargarServiciosFechas(fechaDesde,fechaHasta)));
     }
    
    
    
    

}

