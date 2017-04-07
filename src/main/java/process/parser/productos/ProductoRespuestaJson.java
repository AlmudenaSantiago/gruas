package process.parser.productos;


import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoRespuestaJson {

    private List<Producto> productos;

    public ProductoRespuestaJson() {
        productos = new ArrayList<Producto>();
    }

    public List<Producto> getProductos() {
          return productos;
    }
}
