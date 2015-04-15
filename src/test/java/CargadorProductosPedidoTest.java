import model.Producto;
import org.junit.Test;
import process.cargador.CargadorProductosPedido;
import process.parser.ProductoParserJson;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CargadorProductosPedidoTest {

    @Test
    public void testSolicitudDeListaDePedidos(){
        List<Producto> listaProducto = new ProductoParserJson().parsear(CargadorProductosPedido.getInstance().cargar(20));
        assertTrue(listaProducto.size() > 0);
    }
}
