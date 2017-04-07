       
package process.PDFs;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.ProductoPedido;

import process.cargador.CargadorProductosPedido;

import process.parser.productos.ProductoPedidoParserJson;

public class GenerarEntregaACuentaPDF {

    Pedido pedido;
    File path;
    Cliente cliente;
    String cantidadAbonada;
 

    public GenerarEntregaACuentaPDF(Pedido pedido,String cantidadAbonada,Cliente cliente, File path) {
        this.pedido = pedido;
        this.cliente = cliente;
        this.path = path;
        this.cantidadAbonada = cantidadAbonada;
    }
    
    public void createPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException{
     
        ProductoPedidoParserJson productoPedidoParser = new ProductoPedidoParserJson();
 
        List <Producto> lista =  productoPedidoParser.parsearProductoPedido(CargadorProductosPedido.getInstance().cargar(pedido.getId()));
        ProductoPedido productoPedido = new ProductoPedido();
   
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        FileOutputStream ficheroPdf;
       
        try {
           
          // String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
           ficheroPdf = new FileOutputStream(path + "/Entrega_a_Cuenta_" + pedido.getId() + ".pdf");
           PdfWriter writer = PdfWriter.getInstance(documento,ficheroPdf);
       
     
           documento.addTitle("Entrega a cuenta \n");
           documento.addAuthor("LoquatSolutions");
           documento.open();
           
          
            
           Paragraph parrafo = new Paragraph();
           parrafo.setAlignment(Paragraph.ALIGN_RIGHT);
           parrafo.setFont(FontFactory.getFont("Century",13, BaseColor.BLACK));
           parrafo.add("Entrega a cuenta: " + pedido.getId() + "\n\n" );
           parrafo.add(cliente.getNombre() + "\n");
           parrafo.add(cliente.getDomicilio() + "\n");
           parrafo.add(cliente.getNif() + "\n");
           parrafo.add(cliente.getTelefono1() + "\n");
           parrafo.add(cliente.getCorreo() + "\n");
           
           Date hoy = new Date();
           DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG); 
           String fechaHoy = formato.format(hoy);
           parrafo.add("\n\n Las Palmas a " + fechaHoy );
           
           // *************************** EMPRESA y LOGO******************************//
           
         Paragraph parrafo2 = new Paragraph();
         parrafo2.setFont(FontFactory.getFont("Century",13, BaseColor.BLACK));
         parrafo2.add("\n\n\n\n Muebles Alca ha recibido la cantidad de " +  cantidadAbonada + " euros  en referencia a \r la compra con factura nº: " + pedido.getId() + ".");

         
        
           
         String nombreLogo = "alca.png";
         Image logo;
        
         URL imagenUrl = this.getClass().getResource(nombreLogo);
         System.out.println(imagenUrl);
         logo = Image.getInstance(imagenUrl);
  
          logo.setAbsolutePosition(30f,580f);
          logo.setAlignment(Element.ALIGN_RIGHT);
          documento.add(logo);
  
         
        
            
            
            
            // añadimos los elementos creados 
    
            documento.add(parrafo);   
            documento.add(parrafo2);
           
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("NIF: B-35044551"),300,20,0);
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Comercio minorista IGIC incluido"),300,50,0);
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Todos nuestros artículos están garantizados por 2 años contra cualquier defecto de fabricación."),300,70,0);

           
            documento.close(); 
          
        } catch (DocumentException ex) {
              ex.getMessage();
         }
   
}

        
}
           
