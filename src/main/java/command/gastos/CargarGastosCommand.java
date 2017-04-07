package command.gastos;

import java.util.Date;
import process.cargador.CargadorListaGasto;

import process.parser.gastos.GastoParserJson;
import view.gastos.JTablaGastos;
import view.pedidos.JTablaPedidos;





public class CargarGastosCommand {

  
    private final CargadorListaGasto cargadorListaGasto;
    private final GastoParserJson gastoParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarGastosCommand(CargadorListaGasto cargadorListaGasto,GastoParserJson gastoParserJson) {
        this.cargadorListaGasto = cargadorListaGasto;
        this.gastoParserJson = gastoParserJson;
    }

    public void execute(){
          JTablaGastos.getInstance().mostrarGastos(gastoParserJson.parsear(cargadorListaGasto.cargar()));
      }

       public void executeFechas(Date fechaDesde, Date fechaHasta) {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
       
          JTablaGastos.getInstance().mostrarGastos(gastoParserJson.parsear(cargadorListaGasto.cargarGastosFechas(fechaDesde,fechaHasta)));
     }

}

