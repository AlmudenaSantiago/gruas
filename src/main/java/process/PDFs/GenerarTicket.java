
package view;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;
import model.Producto;
import model.ProductoPedido;
import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoPedidoParserJson;
public class GenerarTicket {
 
    Date hoy = new Date();
    DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG); 
    String fechaHoy = formato.format(hoy);
    
    ProductoPedidoParserJson productoPedidoParser = new ProductoPedidoParserJson();
    ProductoPedido productoPedido = new ProductoPedido();
   
   
    
  private String contenidoDelTicket = 
    "FARO RELOJES Y COMPLEMENTOS \n"+
    "C/ General Vives nº19 :  \n " +
    "Las Palmas  \n " +
    "NIF: 44739055H \n" +
    "=============================\n"+
    "Caja #1 - Ticket # {{ticket}}\n"+
    "LE ATENDIO: Alberto \n"+
    "Fecha: " +  fechaHoy + "\n"+
    "=============================\n"+
    "{{items}}\n"+
    "=============================\n"+
    "SUBTOTAL: {{subTotal}}\n"+
    "IGIC: {{tax}}\n"+
    "TOTAL: {{total}}\n\n"+
    "RECIBIDO: {{recibo}}\n"+
    "CAMBIO: {{change}}\n\n"+
    "=============================\n"+
    "GRACIAS POR SU COMPRA...\n"+
    "ESPERAMOS SU VISITA NUEVAMENTE\n"+
    "\n"+
    "\n";
    
  //El constructor que setea los valores a la instancia
  public GenerarTicket(Integer idPedido, String items, Double subTotal, Double recibido,Double cambio) {
      
    List <Producto> lista =  productoPedidoParser.parsearProductoPedido(CargadorProductosPedido.getInstance().cargar(idPedido));
    for (Producto lista1 : lista) {
        items = "Id. "+  lista1.getId().toString() + "\n" + "Art. " + lista1.getNombre() + "\n" +  "PVP. " + lista1.getPrecio() + "\n\n";
    }         
             
    System.out.println("ok");
          
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{ticket}}", idPedido.toString());
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{items}}", items);
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{subTotal}}", subTotal.toString());
    Double igic = subTotal*0.07;

    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{tax}}", igic.toString());
    Double total = subTotal + igic;
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{total}}", total.toString() );
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{recibo}}", recibido.toString());
    this.contenidoDelTicket = this.contenidoDelTicket.replace("{{change}}", cambio.toString());
    
    System.out.println(contenidoDelTicket);
  }
    
  public void print() {
    //Especificamos el tipo de dato a imprimir
    //Tipo: bytes; Subtipo: autodetectado
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    //obtenemos el servicio de impresion por defatul
    //Si no quieres ver el dialogo de seleccionar impresora usa esto
    //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    
    
    //Con esto mostramos el dialogo para seleccionar impresora
    //Si quieres ver el dialogo de seleccionar impresora usalo
    //Solo mostrara las impresoras que soporte arreglo de bits
    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
      
    //Creamos un arreglo de tipo byte
    byte[] bytes;

    //convertimos el string(cuerpo del ticket) a bytes tal como
    //lo maneja la impresora
    bytes = this.contenidoDelTicket.getBytes();

    //Creamos un documento a imprimir, a el se le appendeara
    //el arreglo de bytes
    Doc doc = new SimpleDoc(bytes,flavor,null);
      
    //Creamos un trabajo de impresión
    DocPrintJob job = service.createPrintJob();

    //Imprimimos dentro de un try de a huevo
    try {
      //El metodo print imprime
      job.print(doc, null);
    } catch (Exception er) {
      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
    }
  }

}
