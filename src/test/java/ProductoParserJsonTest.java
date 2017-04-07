import exception.ExceptionProductoParserJson;
import model.Producto;
import org.junit.Test;
import process.parser.productos.ProductoParserJson;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ProductoParserJsonTest {

    @Test
    public void testParsearListaDeProductosDeUnPedido(){
        List<Producto> listProducto = new ProductoParserJson().parsear(crearListaProductoPedido());
        assertTrue(listProducto.size() == 2);
    }

    @Test(expected = ExceptionProductoParserJson.class)
    public void testIntentarParsearAlgoEnNoFormatoJson(){
        new ProductoParserJson().parsear("NO JSON");
    }

    private String crearListaProductoPedido() {
        return "[" +
                    "{" +
                        "\"id\":1," +
                        "\"nombre\":\"Caracol Cabrilla (Congelado)\"," +
                        "\"descripcion\":\"Caracol Cabrilla (Congelado)\"," +
                        "\"stock\":10," +
                        "\"precio\":1.00," +
                        "\"categoria\":1," +
                        "\"pivot\":" +
                                        "{" +
                                            "\"pedido_id\": 20," +
                                            "\"producto_id\": 1," +
                                            "\"cantidad\": 2.00" +
                                            "\"variaciones\": fria" +
                                        "}" +
                    "}," +
                    "{" +
                        "\"id\":2," +
                        "\"nombre\":\"Caracol Cabrilla (VIVO)\"," +
                        "\"descripcion\":\"Caracol Cabrilla (VIVO)\"," +
                        "\"stock\":10," +
                        "\"precio\":1.00," +
                        "\"categoria\":1," +
                        "\"pivot\": {" +
                                        "\"pedido_id\":20," +
                                        "\"producto_id\":2," +
                                        "\"cantidad\":3.00" +
                                        "\"variaciones\": fria" +
                                    "}"+
                    "}"+
                "]";
    }
}
