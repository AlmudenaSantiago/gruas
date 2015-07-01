package command;

import process.cargador.CargadorListaPedido;
import process.parser.PedidoParserJson;
import view.TablaPedidosFrame;

public class CargarPedidosCommand {

    private TablaPedidosFrame tablaPedidosFrame;
    private CargadorListaPedido cargadorListaPedido;
    private PedidoParserJson pedidoParserJson;

    public CargarPedidosCommand(TablaPedidosFrame tablaPedidosFrame, CargadorListaPedido cargadorListaPedido,
                                PedidoParserJson pedidoParserJson) {
        this.tablaPedidosFrame = tablaPedidosFrame;
        this.cargadorListaPedido = cargadorListaPedido;
        this.pedidoParserJson = pedidoParserJson;
    }

    public void execute(){
        tablaPedidosFrame.mostrarPedidos(pedidoParserJson.parsear(cargadorListaPedido.cargar()));
    }
}
