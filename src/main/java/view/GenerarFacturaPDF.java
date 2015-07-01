package view;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import static com.sun.media.jfxmediaimpl.MediaUtils.error;
import java.awt.Font;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.Pedido;
import model.Producto;
import model.ProductoPedido;
import process.cargador.CargadorListaPedido;
import process.cargador.CargadorProductosPedido;
import process.parser.PedidoParserJson;
import process.parser.ProductoParserJson;

public class GenerarFacturaPDF {

    Pedido pedido;
    File path;
 //   public static String archivo=System.getProperty("user.dir")+"/archivo.pdf";

    public GenerarFacturaPDF(Pedido pedido, File path) {
        this.pedido = pedido;
        this.path = path;
    }
    
    public void createPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException{
        
        CargadorProductosPedido cargadorProductosPedido = new CargadorProductosPedido();
        ProductoParserJson productoParser = new ProductoParserJson();
        List <Producto> lista =  productoParser.parsear(cargadorProductosPedido.cargar(pedido.getId()));
        ProductoPedido productoPedido = new ProductoPedido();
        
        
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        FileOutputStream ficheroPdf;
       
        try {
            System.out.println("La ruta pasada a generar es " + path);
          // String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
           ficheroPdf = new FileOutputStream(path + "/Factura_Pedido" + pedido.getId() + ".pdf");
           PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
     
           documento.addTitle("Factura de pedido \n");
           documento.addAuthor("LoquatSolutions");
           documento.open();
            
           Paragraph parrafo = new Paragraph();
           parrafo.setAlignment(Paragraph.ALIGN_LEFT);
           parrafo.setFont(FontFactory.getFont("Sans",20,Font.BOLD, BaseColor.DARK_GRAY));
           parrafo.add("Factura: " + pedido.getId() + "\n\n" );
           
           Paragraph nombreEmpresa = new Paragraph();
           nombreEmpresa.setAlignment(Paragraph.ALIGN_RIGHT);
           nombreEmpresa.setFont(FontFactory.getFont("Sans",20,Font.BOLD, BaseColor.LIGHT_GRAY));
           nombreEmpresa.add("Caracoles Canarios");
         
           Paragraph tituloTabla = new Paragraph();
           tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
           tituloTabla.setFont(FontFactory.getFont("Sans",11,Font.BOLD, BaseColor.GRAY));
     
           tituloTabla.add("Detalle del pedido \n\n");
           
           Paragraph listaProductos = new Paragraph();
           listaProductos.setAlignment(Paragraph.ALIGN_LEFT);
           listaProductos.setFont(FontFactory.getFont("Sans",11,Font.BOLD, BaseColor.GRAY));
           listaProductos.add("\n\n Detalle de la lista de productos \n");
           for (int i=0;i<lista.size();i++) {
               listaProductos.setFont(FontFactory.getFont("Sans",10,Font.ITALIC, BaseColor.BLACK));
               listaProductos.add("Producto:" + lista.get(i).getId() + "       Cantidad:" + lista.get(i).getProductoPedido().getCantidad() + "\n");
               listaProductos.add("Nombre Producto:" + lista.get(i).getNombre() + "\n");      
               listaProductos.add("Precio Producto:" + lista.get(i).getPrecio() + "\n");
               listaProductos.add("\n\n");
               listaProductos.setFont(FontFactory.getFont("Sans",11,Font.BOLD, BaseColor.GRAY));
           }
           
            PdfPTable table = new PdfPTable(3);      
            
            table.addCell("Usuario");
            table.addCell("Cliente");
            table.addCell("Importe total");
            
            Paragraph cliente = new Paragraph();
            cliente.add(pedido.getCliente());
            PdfPCell celdaCliente = new PdfPCell(cliente);
           
            Paragraph usuario = new Paragraph();
            usuario.add(pedido.getUsuario());
            PdfPCell celdaUsuario = new PdfPCell(usuario);
            
            table.addCell(celdaUsuario);
            table.addCell(celdaCliente);
            
            PdfPCell celdaFinal = new PdfPCell(new Paragraph(pedido.getImporte() + "€"));
            celdaFinal.setColspan(3);
            table.addCell(celdaFinal);
          
            documento.add(nombreEmpresa);
            documento.add(parrafo);        

            documento.add(tituloTabla);
            documento.add(table);
            documento.add(listaProductos);
            
            documento.close(); 
          
        } catch (DocumentException ex) {
              ex.getMessage();
         }
   
}

}
           
