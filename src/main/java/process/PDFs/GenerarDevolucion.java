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

public class GenerarDevolucion {

    Pedido pedido;
    File path;
    Cliente cliente;
    List <ProductoPedido> pp;
    List <Producto> lista;
    Double contadorImporteTotal;

    public GenerarDevolucion(Pedido pedido,List<Producto> lista, Cliente cliente,File path) {
        this.contadorImporteTotal = 0.0;
        System.out.println(pedido);
        this.pedido = pedido;
        this.lista = lista;
        this.pp = pp;
        this.cliente = cliente;
        this.path = path;
    }
    
    public void createPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException{
     
     
   
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        FileOutputStream ficheroPdf;
       
        try {
           
          // String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
           ficheroPdf = new FileOutputStream(path + "/Abono_" + pedido.getId() + ".pdf");
           PdfWriter writer = PdfWriter.getInstance(documento,ficheroPdf);
       
     
           documento.addTitle("Abono de pedido \n");
           documento.addAuthor("LoquatSolutions");
           documento.open();
           
          
            
           Paragraph parrafo = new Paragraph();
           parrafo.setAlignment(Paragraph.ALIGN_RIGHT);
           parrafo.setFont(FontFactory.getFont("Century",13, BaseColor.BLACK));
           parrafo.add("Abono: " + pedido.getId() + "\n\n" );
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
           
           Paragraph nombreEmpresa = new Paragraph();
           String nombreLogo = "alca.png";
           Image logo;

            URL imagenUrl = this.getClass().getResource(nombreLogo);
            System.out.println(imagenUrl);
            logo = Image.getInstance(imagenUrl);

             logo.setAbsolutePosition(30f,580f);
             logo.setAlignment(Element.ALIGN_RIGHT);
             documento.add(logo);
  
         
           
           //******************************* TABLA **********************************//
           Paragraph tituloTabla = new Paragraph();
           tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
           tituloTabla.setFont(FontFactory.getFont("Century",11,Font.BOLD, BaseColor.GRAY));
     
          
           
           PdfPTable table = new PdfPTable(5);      
           float[] medidaCeldas = {9.0f, 20.00f, 9.0f, 9.0f, 9.0f};
           table.setWidths(medidaCeldas);
           table.setWidthPercentage(100);
           
            table.addCell("Código"); 
            table.addCell("Mercancía");
            table.addCell("Cantidad");
            table.addCell("Precio");
            table.addCell("Total");
            
            
    
            for (Producto lista1 : lista) {

             
                Paragraph idProducto = new Paragraph();
                idProducto.add(lista1.getId().toString());
                PdfPCell celdaIdProducto = new PdfPCell(idProducto);
              
                Paragraph nombreProducto = new Paragraph();
                nombreProducto.add(lista1.getNombre());
                PdfPCell celdaNombreProducto = new PdfPCell(nombreProducto);
             
                Paragraph cantidad = new Paragraph();
                cantidad.add(lista1.getProductoPedido().getCantidad().toString());
                cantidad.setAlignment(Paragraph.ALIGN_LEFT);
                PdfPCell celdaCantidad = new PdfPCell(cantidad);
              
                Paragraph precio = new Paragraph();
                precio.setAlignment(Paragraph.ALIGN_LEFT);
                precio.add("-" + lista1.getPrecio().toString() + " €");
                PdfPCell celdaPrecio = new PdfPCell(precio);
     
                Paragraph totalLinea = new Paragraph();
                totalLinea.setAlignment(Paragraph.ALIGN_LEFT);
                totalLinea.add(("-" + lista1.getPrecio() * lista1.getProductoPedido().getCantidad()) + " €");
                PdfPCell celdaTotal = new PdfPCell(totalLinea);

                table.addCell(celdaIdProducto);
                table.addCell(celdaNombreProducto);
                table.addCell(celdaCantidad);
                table.addCell(celdaPrecio);
                table.addCell(celdaTotal);
                
                contadorImporteTotal += lista1.getPrecio()*lista1.getProductoPedido().getCantidad();
            }
           
       

            PdfPCell celdaFinal = new PdfPCell(new Paragraph("TOTAL"));
            celdaFinal.setColspan(4);
            celdaFinal.setBorderColor(BaseColor.BLACK);
        
            
            
            Paragraph importeTotal = new Paragraph("-" + contadorImporteTotal + " €");
            importeTotal.setFont(FontFactory.getFont("Century",Font.BOLD));
            PdfPCell celdaImporteTotal = new PdfPCell(importeTotal);
           
            table.addCell(celdaFinal);
            table.addCell(celdaImporteTotal);
         
            
            // añadimos los elementos creados 
            documento.add(nombreEmpresa);
            documento.add(parrafo);   
            documento.add(celdaFinal);
            documento.add(celdaImporteTotal);
            documento.add(tituloTabla);
            documento.add(table);
           
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("NIF: B-35044551"),300,20,0);
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Comercio minorista IGIC incluido"),300,50,0);
             ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Todos nuestros artículos están garantizados por 2 años contra cualquier defecto de fabricación."),300,70,0);

           
            documento.close(); 
          
        } catch (DocumentException ex) {
              ex.getMessage();
         }
   
}

        
}
           
