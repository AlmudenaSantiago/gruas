
package command.pedidos;

import java.util.Date;
import process.cargador.CargadorListaPlatosServidos;



import view.Ranking.JTablaRanking;


public class CargarPlatosServidosCommand {

    private final CargadorListaPlatosServidos cargadorListaPlatosServidos;
 
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarPlatosServidosCommand(CargadorListaPlatosServidos cargadorListaPlatosServidos) {
        this.cargadorListaPlatosServidos = cargadorListaPlatosServidos;
    
    }
    
    public void executeFechas(Date fechaDesde, Date fechaHasta) {
        
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
       
         JTablaRanking.getInstance().mostrarPlatosServidos(cargadorListaPlatosServidos.cargarListaPlatosServidosPorFecha(fechaDesde,fechaHasta));
    }

   
   

    public void execute(){
          JTablaRanking.getInstance().mostrarPlatosServidos(cargadorListaPlatosServidos.cargar());
         
    }

}


