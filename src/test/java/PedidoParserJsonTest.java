import exception.ExceptionPedidoParserJson;
import model.Pedido;
import org.junit.Test;
import process.parser.pedidos.ServicioParserJson;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

public class PedidoParserJsonTest {

    @Test
    public void testParsearUnPedidoEnJson() {
        ServicioParserJson pedidoParserJson = new ServicioParserJson();
        List<Pedido> listaPedido = pedidoParserJson.parsear(crearUnPedidoJson());
        assertTrue(listaPedido.size() > 0);
        assertEquals(new Integer(17), listaPedido.get(0).getId());

        assertEquals("2015-02-25 00:00:00", listaPedido.get(0).getFechaUltimaModificacion());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(0).getFechaCreacion());
        assertEquals(2.0, listaPedido.get(0).getImporte());
    }

    @Test
    public void testParsearDosPedidosEnJson(){
        ServicioParserJson pedidoParserJson = new ServicioParserJson();
        List<Pedido> listaPedido = pedidoParserJson.parsear(crearDosPedidosJson());
        assertTrue(listaPedido.size() == 2);
        assertEquals(new Integer(18), listaPedido.get(1).getId());

        assertEquals("2015-02-25 00:00:00", listaPedido.get(1).getFechaUltimaModificacion());
        assertEquals("2015-02-25 00:00:00", listaPedido.get(1).getFechaCreacion());
        assertEquals(1.0, listaPedido.get(1).getImporte());
    }

    @Test(expected = ExceptionPedidoParserJson.class)
    public void testIntentarParsearAlgoEnNoFormatoJson(){
        new ServicioParserJson().parsear("No formato JSON");
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

                            "\"updated_at\":\"2015-02-25 00:00:00\"," +
                            "\"created_at\":\"2015-02-25 00:00:00\"," +
                            "\"importe\":2" +
                        "}," +
                        "{" +
                            "\"id\":18," +
    
                            "\"updated_at\":\"2015-02-25 00:00:00\"," +
                            "\"created_at\":\"2015-02-25 00:00:00\"," +
                            "\"importe\":1" +
                        "}" +
                    "]" +
                "}";
    }

}
