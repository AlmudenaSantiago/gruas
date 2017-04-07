
package process.GeneradorCodigosBarra;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.FileOutputStream;
import view.SelectorDeRuta;
 

public class GeneradorCodigoBarras {
   public static void main(String[] args) {
        String nombrePDF = "SampleCodes.pdf";
        String nombreProducto = "Prueba";
        Double precio = 30.50;
        GeneradorCodigoBarras generador = new GeneradorCodigoBarras();
        generador.createPDF(nombrePDF,nombreProducto,precio);

    }

 private void createPDF (String nombrePDF, String nombreProducto, Double precio){

        Document documento = new Document();
        PdfWriter docWriter = null;

        try {
            String path = new SelectorDeRuta().seleccionDeRuta().getPath() + "\\" +  nombrePDF;
            System.out.println(path);
            docWriter = PdfWriter.getInstance(documento , new FileOutputStream(path));
            documento.addAuthor("Loquat Solutions");
            documento.addCreationDate();
            documento.addProducer();
            documento.addCreator("www.loquatsolutions.com");
            documento.addTitle("Codigo de barras simplee y QR");
            documento.setPageSize(PageSize.LETTER);

            documento.open();
            PdfContentByte contenido = docWriter.getDirectContent();

            String datos = "123548";


            Barcode128 codigo128 = new Barcode128();
            codigo128.setCode(datos.trim());
            codigo128.setCodeType(Barcode128.CODE128);
            com.itextpdf.text.Image imagenCodigo128 = codigo128.createImageWithBarcode(contenido, null, null);
            imagenCodigo128.setAbsolutePosition(10,700);
            imagenCodigo128.scalePercent(80);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setFont(FontFactory.getFont("Century",8, BaseColor.BLACK));
            parrafo.add("Art. " + nombreProducto + ". PVP. " + precio + "€\n" );
            documento.add(parrafo);
            documento.add(imagenCodigo128);

        /*    codigo128.setCodeType(Barcode128.CODE128_UCC);
            imagenCodigo128 = codigo128.createImageWithBarcode(contenido, null, null);
            imagenCodigo128.setAbsolutePosition(10,650);
            imagenCodigo128.scalePercent(100);
            documento.add(parrafo);
            documento.add(imagenCodigo128);

            BarcodeEAN codigoEAN = new BarcodeEAN();
            codigoEAN.setCode(datos.trim());
            codigoEAN.setCodeType(BarcodeEAN.EAN13);
            com.itextpdf.text.Image imagenCodigoEAN = codigo128.createImageWithBarcode(contenido, null, null);
            imagenCodigoEAN.setAbsolutePosition(10,600);
            imagenCodigoEAN.scalePercent(50);
            documento.add(parrafo);
            documento.add(imagenCodigoEAN);

            BarcodeQRCode codigoQR = new BarcodeQRCode(datos.trim(), 1, 1, null);
            com.itextpdf.text.Image qrcodeImage = codigoQR.getImage();
            qrcodeImage.setAbsolutePosition(10,500);
            qrcodeImage.scalePercent(100);
            documento.add(parrafo);
            documento.add(qrcodeImage);*/

  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (documento != null)
   {
    documento.close();
   }
   if (docWriter != null)
   {
    docWriter.close();
   }
  }
 }
}