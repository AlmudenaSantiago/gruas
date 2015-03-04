import command.CargarPedidosCommand;
import command.CargarProductosPedidoCommand;
import process.cargador.CargadorListaPedido;
import process.cargador.CargadorProductosPedido;
import process.parser.PedidoParserJson;
import process.parser.ProductoParserJson;
import view.TablaPedidosFrame;

public class Main {

    public static void main(String args[]) {
        TablaPedidosFrame tablaPedidosFrame = new TablaPedidosFrame();
        CargarPedidosCommand cargarPedidosCommand = new CargarPedidosCommand(new TablaPedidosFrame(),
                CargadorListaPedido.getInstance(), new PedidoParserJson());
        tablaPedidosFrame.setCargarPedidosCommand(cargarPedidosCommand);
        tablaPedidosFrame.setCargarProductosPedidoCommand(new CargarProductosPedidoCommand(new ProductoParserJson(), new CargadorProductosPedido()));
        tablaPedidosFrame.sincronizacion();

    }
}
