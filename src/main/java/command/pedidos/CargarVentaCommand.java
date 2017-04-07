package command.pedidos;

import java.util.Date;

import process.cargador.CargadorListaProducto;

import process.parser.productos.ProductoParserJson;
import view.productos.JTablaCRUD;

import view.venta.JTablaVenta;





public class CargarVentaCommand {

  
    private final CargadorListaProducto cargadorListaProducto;
    private final ProductoParserJson productoParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    
    public CargarVentaCommand(CargadorListaProducto cargadorListaProducto,ProductoParserJson productoParserJson) {
        this.cargadorListaProducto = cargadorListaProducto;
        this.productoParserJson = productoParserJson;
    }

   
  

    public void execute(){
          JTablaVenta.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargar()));
      }

      
      public void executeFiltroNivel1(String categoria1){
        JTablaVenta.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargarConFiltroNivel1(categoria1)));
      }
      
     public void executeFiltroNivel2(String categoria1, String categoria2){
        JTablaVenta.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargarConFiltroNivel2(categoria1,categoria2)));
      }
      public void executeFiltroNivel3(String categoria1, String categoria2, String categoria3){
        JTablaVenta.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargarConFiltroNivel3(categoria1,categoria2,categoria3)));
      }

      public void executeFiltroNivel4(String categoria1, String categoria2, String categoria3, String categoria4){
        JTablaVenta.getInstance().mostrarProductos(productoParserJson.parsear(cargadorListaProducto.cargarConFiltroNivel4(categoria1,categoria2,categoria3,categoria4)));
      }
      

}

