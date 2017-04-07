package process.PDFs;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Categoria;
import model.Producto;
import model.Proveedor;
import process.cargador.CargadorListaProducto;
import process.cargador.CargadorListaProveedor;

import process.parser.productos.ProductoParserJson;
import process.parser.proveedor.ProveedorParserJson;


public class GenerarInventarioProveedores {

    File path;
    List<Categoria> lista;
    
    double contadorTotalCosto;
    int contadorTotalStock;
  
    int cont = 0;
    private PdfPTable tableP;
    Document documento; 
    Date hoy = new Date();
    DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG); 
    String fechaHoy = formato.format(hoy);



    public GenerarInventarioProveedores (File path) {
        this.path = path;
    }
    
    public void createPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException{
      
           documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
           FileOutputStream ficheroPdf;
           ficheroPdf = new FileOutputStream(path + "/Inventario_" + fechaHoy + "_op.pdf");
           PdfWriter writer = PdfWriter.getInstance(documento,ficheroPdf);
           writer.setInitialLeading(20);
           escribirCabecera();
           
           List<Proveedor> listaProveedores = new ProveedorParserJson().parsear(new CargadorListaProveedor().cargar());
           Iterator<Proveedor> itProveedor = listaProveedores.iterator();
                       
           while (itProveedor.hasNext())  {
                  System.out.println("AAUN NO");
                  Proveedor proveedor = itProveedor.next();
                  List<Producto> listaProductos = new ProductoParserJson().parsear(new CargadorListaProducto().cargarConFiltroProveedor(proveedor.getId()));
                  escribirRegistros(listaProductos, proveedor.getNombre());
           }
           
           escribirTotales();
           documento.close(); 
}
    
    
    public void escribirCabecera() throws DocumentException {
    
           documento.addTitle("Inventario \n");
           documento.addAuthor("LoquatSolutions");
           documento.open();
        
           Paragraph parrafo = new Paragraph();
           parrafo.setAlignment(Paragraph.ALIGN_RIGHT);
           parrafo.setFont(FontFactory.getFont("Sans",13,Font.BOLD, BaseColor.DARK_GRAY));
           parrafo.add("Fecha: " + fechaHoy + "\n\n" );
           
      
          
          Paragraph tituloTabla = new Paragraph();
          tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
          tituloTabla.setFont(FontFactory.getFont("Sans",11,Font.BOLD, BaseColor.GRAY));
     
           tituloTabla.add("\n\n Albarán de Productos \n\n");
             
            documento.add(parrafo);        
            documento.add(tituloTabla); 
    
    }
    
    public void escribirTotales() throws DocumentException {
     
       
          Paragraph tituloTabla = new Paragraph();
          tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
          tituloTabla.setFont(FontFactory.getFont("Sans",11,Font.BOLD, BaseColor.GRAY));
     
          tituloTabla.add("\n\n TOTALES \n\n");
             
              
           documento.add(tituloTabla);  
        
           PdfPTable tableT = new PdfPTable(4);  
        
      
           
           PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total Costes"));
           celdaFinal.setColspan(3);
           celdaFinal.setBorderColor(BaseColor.BLACK);
        
            
            Paragraph importeTotal = new Paragraph( contadorTotalCosto +  " €");
            importeTotal.setFont(FontFactory.getFont("Century",Font.BOLD));
            PdfPCell celdaImporteTotal = new PdfPCell(importeTotal);
           
            tableT.addCell(celdaFinal);
            tableT.addCell(celdaImporteTotal);
            
            
           PdfPCell celdaFinalProductos = new PdfPCell(new Paragraph("Total existencias"));
           celdaFinalProductos.setColspan(3);
           celdaFinalProductos.setBorderColor(BaseColor.BLACK);
            
            Paragraph importeTotalProductos = new Paragraph( contadorTotalStock +  " unids");
            importeTotalProductos.setFont(FontFactory.getFont("Century",Font.BOLD));
            PdfPCell celdaImporteTotalP = new PdfPCell(importeTotalProductos);
            
            tableT.addCell(celdaFinalProductos);
            tableT.addCell(celdaImporteTotalP);
        
            documento.add(tableT);
    
    
    }
    
    
    
    public void escribirRegistros(List<Producto> listaProductos, String tituloCategoria) throws DocumentException {

          Paragraph tituloTabla = new Paragraph();
          tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
          tituloTabla.setFont(FontFactory.getFont("Sans",14,Font.BOLD, BaseColor.GRAY));
     
          tituloTabla.add(" \n\n " + tituloCategoria + "\n\n");
          PdfPTable table = new PdfPTable(4);  
          float[] medidaCeldas = {9.0f, 50.00f, 9.0f, 9.0f};
          table.setWidths(medidaCeldas);
          table.setWidthPercentage(100);
        
           table.addCell("Id");
           table.addCell("Producto");
           table.addCell("Precio costo");  
           table.addCell("Cantidad en Stock");

            Iterator<Producto> itProductos = listaProductos.iterator();
                       
                           while (itProductos.hasNext())  {
                                    Producto producto = itProductos.next();
                                    Paragraph idProducto = new Paragraph();
                                    idProducto.add(producto.getId().toString());      
                                    PdfPCell celdaidProducto = new PdfPCell(idProducto);
                                    celdaidProducto.setBorderColor(BaseColor.GRAY);


                                    Paragraph nombreProducto = new Paragraph();
                                    nombreProducto.add(producto.getNombre());      
                                    PdfPCell celdaNombreProducto = new PdfPCell(nombreProducto);
                                    celdaNombreProducto.setBorderColor(BaseColor.GRAY);

                                    Paragraph costo = new Paragraph();
                                    costo.add(producto.getPrecioCompra().toString());
                                    costo.setAlignment(Paragraph.ALIGN_RIGHT);
                                    PdfPCell celdaCosto = new PdfPCell(costo);
                                    celdaCosto.setBorderColor(BaseColor.GRAY);
                                    contadorTotalCosto  += producto.getPrecioCompra();
                                    System.out.println("le sumo" +  producto.getPrecioCompra() +  " y va por " + contadorTotalCosto);

                                    Paragraph cantidad = new Paragraph();
                                    cantidad.add(producto.getStock().toString());
                                    cantidad.setAlignment(Paragraph.ALIGN_RIGHT);
                                    PdfPCell celdaCantidad = new PdfPCell(cantidad);
                                    celdaCantidad.setBorderColor(BaseColor.GRAY);
                                    contadorTotalStock  += producto.getStock();
                                    System.out.println("le sumo" +   producto.getStock()+  " y va por " + contadorTotalStock);

                                    table.addCell(celdaidProducto);
                                    table.addCell(celdaNombreProducto);
                                    table.addCell(celdaCosto);
                                    table.addCell(celdaCantidad);
                           }
                
                           
            documento.add(tituloTabla);
            documento.add(table);
  }
    
    
}
    
    
       
           
