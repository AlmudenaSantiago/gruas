import model.Pedido;
import org.junit.Test;
import process.cargador.CargadorListaPedido;
import process.parser.PedidoParserJson;

import java.util.List;
import static junit.framework.TestCase.*;

public class CargadorListaPedidoTest {

    @Test
    public void testSolicitudDeListaDePedidos(){
        List<Pedido> listaPedido = new PedidoParserJson().parsear(CargadorListaPedido.getInstance().cargar());
        assertTrue(listaPedido.size() > 0);
    }

}
