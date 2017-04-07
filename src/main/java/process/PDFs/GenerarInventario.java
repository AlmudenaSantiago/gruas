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
import command.categorias.CargarCategorias2Command;
import command.categorias.CargarCategorias3Command;
import command.categorias.CargarCategorias4Command;
import command.categorias.CargarCategoriasCommand;

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
import process.cargador.CargadorListaCategoria;
import process.cargador.CargadorListaCategoria2;
import process.cargador.CargadorListaCategoria3;
import process.cargador.CargadorListaCategoria4;
import process.cargador.CargadorListaProducto;
import process.parser.categorias.CategoriaParserJson;

import process.parser.productos.ProductoParserJson;


public class GenerarInventario {

    File path;
    List<Categoria> lista;
    
    double contadorTotalCosto;
    int contadorTotalStock;
    int id1;
    int id2;
    int id3;
    int id4;
    String nombre1 = "";
    String nombre2 = "";
    String nombre3 = "";
    String nombre4 = "";
    int cont = 0;
    private PdfPTable tableP;
    List<Categoria> lista3;
    List<Categoria> lista4;
    List<Categoria> lista2;
    Document documento; 
    Date hoy = new Date();
    DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG); 
    String fechaHoy = formato.format(hoy);
      
  
    public GenerarInventario (File path) {
        this.path = path;
    }
    
    public void createPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException{
      
           documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
           FileOutputStream ficheroPdf;
           ficheroPdf = new FileOutputStream(path + "/Inventario_" + fechaHoy + "_oc.pdf");
           PdfWriter writer = PdfWriter.getInstance(documento,ficheroPdf);
           writer.setInitialLeading(20);
           escribirCabecera();
      
                      
           CargarCategoriasCommand cargar = new CargarCategoriasCommand(new CargadorListaCategoria(), new CategoriaParserJson());
           List<Categoria> listaCategorias = cargar.execute();
     
        
            while (listaCategorias.size()>=1) {
               if (!listaCategorias.get(0).getNombre().equals("Todos")){
                    
                        // CARGAMOS LAS CATEGORIAS DE CADA UNO
                       CargarCategorias2Command cargar2 = new CargarCategorias2Command(new CargadorListaCategoria2(), new CategoriaParserJson());
                       List<Categoria> listaCategorias2 = cargar2.execute(listaCategorias.get(0).getId());

                       while (listaCategorias2.size()>=1) {
                            if (!listaCategorias2.get(0).getNombre().equals("Todos")){
                             
                                 CargarCategorias3Command cargar3 = new CargarCategorias3Command(new CargadorListaCategoria3(), new CategoriaParserJson());
                                 List<Categoria> listaCategorias3 = cargar3.execute(listaCategorias2.get(0).getId2());
                                
                                 while (listaCategorias3.size()>=1) {
                                     if (!listaCategorias3.get(0).getNombre().equals("Todos")){
                                   
                                         CargarCategorias4Command cargar4 = new CargarCategorias4Command(new CargadorListaCategoria4(), new CategoriaParserJson());
                                         List<Categoria> listaCategorias4 = cargar4.execute(listaCategorias3.get(0).getId3());
                                         while (listaCategorias4.size()>=1) {
                                             
                                             if(!listaCategorias4.get(0).getNombre().equals("Todos")){
                                               
                                                  List<Producto> listaProductos = new ProductoParserJson().parsear(
                                                                                                                    new CargadorListaProducto().cargarConFiltroNivel4(
                                                                                                                            listaCategorias.get(0).getNombre(),
                                                                                                                            listaCategorias2.get(0).getNombre(),
                                                                                                                            listaCategorias3.get(0).getNombre(),
                                                                                                                            listaCategorias4.get(0).getNombre()                                                                         
                                                          
                                                                                                  ));   
                                           
                                        

                                                
                                                 escribirRegistros(listaProductos, listaCategorias.get(0).getNombre()+ " ==> " + listaCategorias2.get(0).getNombre()+  " ==> "+ listaCategorias3.get(0).getNombre()+ " ==> "+ listaCategorias4.get(0).getNombre());
                                             
                                             }
                                             
                                             
                                             listaCategorias4.remove(0);
                                             
                                         }
                                       
                                           
                                                                                   
                                            List<Producto> listaProductos2 = new ProductoParserJson().parsear(new CargadorListaProducto().cargarConFiltroNivel4(
                                                                                                                            listaCategorias.get(0).getNombre(),
                                                                                                                            listaCategorias2.get(0).getNombre(),
                                                                                                                            listaCategorias3.get(0).getNombre(),
                                                                                                                            " "
                                                          
                                                                                                             )); 
                                            escribirRegistros(listaProductos2, listaCategorias.get(0).getNombre()+ " ==> " + listaCategorias2.get(0).getNombre()+ " ==> " + listaCategorias3.get(0).getNombre());
                                    

                                     }
                                     listaCategorias3.remove(0);
                                 }
                            
                                           
                                         
                                               List<Producto> listaProductos3 = new ProductoParserJson().parsear(
                                                                                                                    new CargadorListaProducto().cargarConFiltroNivel4(
                                                                                                                            listaCategorias.get(0).getNombre(),
                                                                                                                            listaCategorias2.get(0).getNombre(),
                                                                                                                            " "," "
                                                          
                                                                                                             )); 
                                               escribirRegistros(listaProductos3, listaCategorias.get(0).getNombre()+ " ==> " + listaCategorias2.get(0).getNombre());
                                         


                            
                            }
                        listaCategorias2.remove(0);
                       }
                                                                      List<Producto> listaProductos4 = new ProductoParserJson().parsear(
                                                                                                                    new CargadorListaProducto().cargarConFiltroNivel4(
                                                                                                                            listaCategorias.get(0).getNombre()," "," "," "                                                                                                                                                                                                                                     
                                                                                                                               
                                                                                                             )); 
                                                                      
                                               escribirRegistros(listaProductos4, listaCategorias.get(0).getNombre());
                       
               }
                     
             
                                             
               listaCategorias.remove(0);
                     
                
               
           
           }
            escribirTotales();
            documento.close(); 
}
    
    
    public void escribirRegistros(List<Producto> listaProductos, String tituloCategoria) throws DocumentException {

          Paragraph tituloTabla = new Paragraph();
          tituloTabla.setAlignment(Paragraph.ALIGN_LEFT);
          tituloTabla.setFont(FontFactory.getFont("Sans",14,Font.BOLD, BaseColor.GRAY));
     
          tituloTabla.add(" \n\n " + tituloCategoria + "\n\n");
          PdfPTable table = new PdfPTable(4);  
        
        
            table.addCell("Id");
            table.addCell("Producto");
            table.addCell("Precio costo");  
            table.addCell("Stock");

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

                                    Paragraph cantidad = new Paragraph();
                                    cantidad.add(producto.getStock().toString());
                                    cantidad.setAlignment(Paragraph.ALIGN_RIGHT);
                                    PdfPCell celdaCantidad = new PdfPCell(cantidad);
                                    celdaCantidad.setBorderColor(BaseColor.GRAY);
                                    contadorTotalStock  += producto.getStock();

                                    table.addCell(celdaidProducto);
                                    table.addCell(celdaNombreProducto);
                                    table.addCell(celdaCosto);
                                    table.addCell(celdaCantidad);
                           }
                
                           
            documento.add(tituloTabla);
            documento.add(table);
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
    
    
}
    
    
       
           
