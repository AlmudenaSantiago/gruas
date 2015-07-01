import model.Pedido;
import org.junit.Test;
import process.cargador.CargadorListaPedido;
import process.parser.CargadorListaPedido;
import process.parser.PedidoParserJson;

import java.util.List;
import static junit.framework.TestCase.*;

public class CargadorListapedidoTest {

    @Test
    public void testSolicitudDeListaDePedidos(){
        String listaDePedidosEnJson;
        CargadorListaPedido cargadorListaPedido = new CargadorListaPedido();
        listaDePedidosEnJson = cargadorListaPedido.cargar();
        PedidoParserJson pedidoParserJson = new PedidoParserJson();
        List<Pedido> listaPedido = pedidoParserJson.parsear(listaDePedidosEnJson);
        assertTrue(listaPedido.size() > 0);
    }

}
