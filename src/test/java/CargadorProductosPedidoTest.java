import model.Producto;
import org.junit.Test;
import process.cargador.CargadorProductosPedido;
import process.parser.ProductoParserJson;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CargadorProductosPedidoTest {

    @Test
    public void testSolicitudDeListaDePedidos(){
        String listaDePedidosEnJson;
        CargadorProductosPedido cargadorProductosPedido = new CargadorProductosPedido();
        listaDePedidosEnJson = cargadorProductosPedido.cargar(20);
        ProductoParserJson productoParserJson = new ProductoParserJson();
        List<Producto> listaProducto = productoParserJson.parsear(listaDePedidosEnJson);
        assertTrue(listaProducto.size() > 0);
    }
}
