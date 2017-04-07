package command.pedidos;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.Pedido;
import process.cargador.CargadorListaPedido;
import process.parser.pedidos.PedidoParserJson;
import view.pedidos.JTablaPedidos;
import view.vendedores.JTablaComisiones;


public class CargarPedidosCommand {

    private final CargadorListaPedido cargadorListaPedido;
    private final PedidoParserJson pedidoParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarPedidosCommand(CargadorListaPedido cargadorListaPedido,PedidoParserJson pedidoParserJson) {
        this.cargadorListaPedido = cargadorListaPedido;
        this.pedidoParserJson = pedidoParserJson;
    }

      public Pedido executePedido(Integer id) throws IOException {
          return pedidoParserJson.parsearPedido(cargadorListaPedido.cargarPedido(id));
      }
      
   
     public void executeFinalizados(){
          JTablaPedidos.getInstance().mostrarPedidos(pedidoParserJson.parsear(cargadorListaPedido.cargarPedidosFinalizados()));
      }

    public void execute(){
       
          JTablaPedidos.getInstance().mostrarPedidos(pedidoParserJson.parsear(cargadorListaPedido.cargar()));
      }

    public void executeFechas(Date fechaDesde, Date fechaHasta) {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
       
          JTablaPedidos.getInstance().mostrarPedidos(pedidoParserJson.parsear(cargadorListaPedido.cargarPedidosFechas(fechaDesde,fechaHasta)));
     }

      public void executeFechasYVendedor(String vendedor, Date fechaDesde, Date fechaHasta) {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
         JTablaComisiones.getInstance().mostrarPedidos(pedidoParserJson.parsearPedidosVendedor(cargadorListaPedido.cargarPedidosFechasYVendedor(vendedor,fechaDesde,fechaHasta)));
     }

    
    
    
}

