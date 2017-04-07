package command.productos;

import java.util.Date;
import process.parser.productos.ProductoParserJson;
import process.cargador.CargadorListaProducto;
import view.productos.JTablaCRUD;

public class CargarDiasFestivosCommand {
 private final CargadorListaProducto cargadorListaProducto;
    private final ProductoParserJson productoParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarDiasFestivosCommand(CargadorListaProducto cargadorListaProducto,ProductoParserJson productoParserJson) {
        this.cargadorListaProducto = cargadorListaProducto;
        this.productoParserJson = productoParserJson;
    }

    public void execute(){
        JTablaCRUD.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargar()));
      }
   
    
    
}


