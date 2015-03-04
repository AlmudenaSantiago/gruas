import model.Pedido;
import org.junit.Test;
import process.parser.PedidoParserJson;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

public class PedidoParserJsonTest {

    @Test
    public void testParsearUnPedidoEnJson() {
        PedidoParserJson pedidoParserJson = new PedidoParserJson();
        List<Pedido> listaPedido = pedidoParserJson.parsear(crearUnPedidoJson());
        assertTrue(listaPedido.size() > 0);
        assertEquals(new Integer(17), listaPedido.get(0).getId());
        assertEquals("Loquat", listaPedido.get(0).getCliente());
        assertEquals("Tablet", listaPedido.get(0).getUsuario());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(0).getFechaUltimaModificacion());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(0).getFechaCreacion());
        assertEquals(2.0, listaPedido.get(0).getImporte());
    }

    @Test
    public void testParsearDosPedidosEnJson(){
        PedidoParserJson pedidoParserJson = new PedidoParserJson();
        List<Pedido> listaPedido = pedidoParserJson.parsear(crearDosPedidosJson());
        assertTrue(listaPedido.size() == 2);
        assertEquals(new Integer(18), listaPedido.get(1).getId());
        assertEquals("Loquat Solutions", listaPedido.get(1).getCliente());
        assertEquals("Ipad", listaPedido.get(1).getUsuario());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(1).getFechaUltimaModificacion());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(1).getFechaCreacion());
        assertEquals(1.0, listaPedido.get(1).getImporte());
    }

    @Test
    public void testIntentarParsearAlgoEnNoFormatoJson(){
        PedidoParserJson pedidoParserJson = new PedidoParserJson();
        //TODO hacer esta funcion
    }

    private String crearUnPedidoJson() {
        return "{\"" +
                    "pedidos\":[" +
                        "{" +
                            "\"id\":17," +
                            "\"cliente\":\"Loquat\"," +
                            "\"usuario\":\"Tablet\"," +
                            "\"updated_at\":\"2015-02-25 00:00:00\"," +
                            "\"created_at\":\"2015-02-25 00:00:00\"," +
                            "\"importe\":2" +
                        "}" +
                    "]" +
                "}";
    }

    private String crearDosPedidosJson() {
        return "{\"" +
                    "pedidos\":[" +
                        "{" +
                            "\"id\":17," +
                            "\"cliente\":\"tablet\"," +
                            "\"usuario\":\"loquat\"," +
                            "\"updated_at\":\"2015-02-25 00:00:00\"," +
                            "\"created_at\":\"2015-02-25 00:00:00\"," +
                            "\"importe\":2" +
                        "}," +
                        "{" +
                            "\"id\":18," +
                            "\"cliente\":\"Loquat Solutions\"," +
                            "\"usuario\":\"Ipad\"," +
                            "\"updated_at\":\"2015-02-25 00:00:00\"," +
                            "\"created_at\":\"2015-02-25 00:00:00\"," +
                            "\"importe\":1" +
                        "}" +
                    "]" +
                "}";
    }

}
