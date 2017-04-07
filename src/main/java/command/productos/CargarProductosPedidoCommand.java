

package command.productos;

import java.util.ArrayList;
import java.util.List;
import model.Producto;
import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoParserJson;
import process.parser.productos.ProductoPedidoParserJson;
import view.detallesPedidoEnFicha.JTablaPedido;
import view.pedidos.JTablaPedidos;

public class CargarProductosPedidoCommand {

    private ProductoPedidoParserJson productoPedidoParserJson;
    private ProductoParserJson productoParserJson;
    private CargadorProductosPedido cargadorProductosPedido;

    
    public CargarProductosPedidoCommand(ProductoPedidoParserJson productoPedidoParserJson, CargadorProductosPedido cargadorProductosPedido) {
        this.productoPedidoParserJson = productoPedidoParserJson;
        this.cargadorProductosPedido = cargadorProductosPedido;
    }

    
    public void execute(Integer idPedido) {
         JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getjTablaDetalleComanda().mostrarListaProductos(productoPedidoParserJson.parsearProductoPedido(cargadorProductosPedido.cargar(idPedido)));
         
    }
    
    public void executeTablaEnFicha(Integer idPedido, JTablaPedido tabla) {
       
        tabla.mostrarListaProductos(productoPedidoParserJson.parsearProductoPedido(cargadorProductosPedido.cargar(idPedido)));
    }
    
    
}
