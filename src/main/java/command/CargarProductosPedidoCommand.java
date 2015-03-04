package command;

import model.Producto;
import process.cargador.CargadorProductosPedido;
import process.parser.ProductoParserJson;
import view.ListaProductoFrame;

import java.util.List;

public class CargarProductosPedidoCommand {

    private ProductoParserJson productoParserJson;
    private CargadorProductosPedido cargadorProductosPedido;

    public CargarProductosPedidoCommand(ProductoParserJson productoParserJson, CargadorProductosPedido cargadorProductosPedido) {
        this.productoParserJson = productoParserJson;
        this.cargadorProductosPedido = cargadorProductosPedido;
    }

    public void execute(Integer idPedido) {
        List<Producto> listaProductos = productoParserJson.parsear(cargadorProductosPedido.cargar(idPedido));
        new ListaProductoFrame(listaProductos);
    }
}
