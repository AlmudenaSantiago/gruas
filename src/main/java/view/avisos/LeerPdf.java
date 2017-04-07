/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.avisos;

import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aviso;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import process.CRUDAvisos.RegistrarAviso;
import process.limpiador.LimpiarTexto;

public class LeerPdf {
      Aviso aviso;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable()
        {@Override
        public void run(){
        new LeerPdf().lecturaPDF();}
        }

        );
      

    }

    public void lecturaPDF(){
        String ln = System.getProperty("line.separator");
        File dir = new File("C:\\Users\\gruasjoseantonio\\Documents\\Mapfre\\");//CREO UN OBJETO CON TODOS LOS ARCHIVOS QUE CONTIENE LA CARPETA QUE CONTIENE LOS PDFS.
        String[] ficheros = dir.list();//ARREGLO QUE ALMACENARÁ TODOS LOS NOMBRES DE LOS ARCHIVOS QUE ESTAN DENTRO DEL OBJETO.

        if (ficheros == null)//EXCEPCION
              System.out.println("No hay archivos en la carpeta especificada");
        else {
          for (int x=0;x<ficheros.length;x++){//RECORREMOS EL ARREGLO CON LOS NOMBRES DE ARCHIVO
            String ruta = new String();//VARIABLE QUE DETERMINARA LA RUTA DEL ARCHIVO A LEER.
            ruta=("C:\\Users\\gruasjoseantonio\\Documents\\Mapfre\\"+ficheros[x]); //SE ALMACENA LA RUTA DEL ARCHIVO A LEER.

              try {
                  PDDocument pd = PDDocument.load(ruta); //CARGAR EL PDF
                  List l = pd.getDocumentCatalog().getAllPages();//NUMERO LAS PAGINAS DEL ARCHIVO
                  Object[] obj = l.toArray();//METO EN UN OBJETO LA LISTA DE PAGINAS PARA MANIPULARLA
                  PDPage page = (PDPage) obj[0];//PAGE ES LA PAGINA 1 DE LA QUE CONSTA EL ARCHIVO
                  PageFormat pageFormat = pd.getPageFormat(0);//PROPIEDADES DE LA PAGINA (FORMATO)
                  Double d1 = new Double(pageFormat.getHeight());//ALTO
                  Double d2 = new Double(pageFormat.getWidth());//ANCHO
                  int width = d1.intValue();//ANCHO
                  int eigth=1024;//ALTO

                  PDFTextStripperByArea stripper = new PDFTextStripperByArea();//COMPONENTE PARA ACCESO AL TEXTO
                  Rectangle rect = new Rectangle(0, 0, width, eigth);//DEFNIR AREA DONDE SE BUSCARA EL TEXTO
                  stripper.addRegion("area1", rect);//REGISTRAMOS LA REGION CON UN NOMBRE
                  stripper.extractRegions(page);//EXTRAE TEXTO DEL AREA

                  String contenido = new String();//CONTENIDO = A LO QUE CONTENGA EL AREA O REGION
contenido=(rect+stripper.getTextForRegion("area1"));

                  File archivo=new File(ficheros[x]+".txt");//CREAMOS ARCHIVO CON NOMBRE ORIGINAL PERO EN TXT
                  BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));//CREAMOS EL ESCRITOR
                  System.out.println("333333333333333333");
                  writer.write(ruta);//IMPRIMIMOS LA RUTA
                  writer.write(contenido);//IMPRIMIMOS EL CONTENIDO
                  writer.close();//CERRAMOS EL ESCRITOR
System.out.println(archivo.getAbsolutePath());
                  pd.close();//CERRAMOS OBJETO ACROBAT
                  leeTxt(archivo.getAbsolutePath());
              } catch (IOException e) {
                  if(e.toString()!=null){
                    File archivo=new File("dañado_"+ficheros[x]+".txt");//SEPARA LOS DAÑADOS
                  }
                  System.out.println("Archivo dañado "+ficheros[x]);// INDICA EN CONSOLA CUALES SON LOS DAÑADOS
                  e.printStackTrace();
              }//CATCH
          }//FOR
        }//ELSE
    }//LECTURAPDF()
    public void leeTxt(String ruta){

    String codigoProveedor="";
    String fecha="";
    String expediente="";
    String compa="";
    String marca="";
    boolean expedienteb =false;
    boolean gruab =false;
     boolean conductorb =false;
     boolean tipob=false;
     String tipo="";
    String grua="";
    String conductor="";
    String telefono1="";
    String telefono2="";
    String matricula="";
    String modelo="";
    String color="";
    String codigoAveria="";
    String localidadrecogida="";
    String localidadentrega="";
    String callerecogida="";
    String calleentrega="";

    String insitu="";
    boolean insitub=false;

    try {
      FileReader fr = new FileReader(ruta);
      BufferedReader br = new BufferedReader(fr);

      String linea;
      while((linea = br.readLine()) != null){

      if (linea.contains("Código Proveedor")){
       codigoProveedor= linea.substring(17,23);
       System.out.println(codigoProveedor);
       fecha= linea.substring(30,46);
       System.out.println(fecha);
    }


    if (linea.contains("Expediente")){
      expediente= linea.substring(11,linea.indexOf("/"));

      System.out.println(expediente);

    }
              if(gruab==true){
         grua = linea.substring(2, 4);
        gruab=false;
           System.out.println(grua);
    }

        if (linea.contains("Grúa")){
    gruab=true;



    }

    if (linea.contains("Compa")){
      compa= linea.substring(9,linea.length());
      compa= compa.substring(0, compa.indexOf(" "));
      compa= new LimpiarTexto().limpiar(compa);
       System.out.println(compa);


    }
        if (linea.contains("Teléfono1")){
      telefono1= linea.substring(10,linea.length());
       System.out.println(telefono1);

    }
         if (linea.contains("Localidad")){
             if (localidadrecogida.equals("")){
                localidadrecogida= linea.substring(10,linea.length());
                System.out.println(localidadrecogida);
             }
             else{
localidadentrega=linea.substring(10,linea.length());
                 System.out.println(localidadentrega);
             }

    }




            if (linea.contains("Lugar")){
             if (callerecogida.equals("")){
                callerecogida= linea.substring(6,linea.length());

                if(callerecogida.contains("DE GRAN")){

                    System.out.println("marrr");
                    callerecogida=callerecogida.concat("CANARIA");
                }
             System.out.println(callerecogida);


             }
             else{
                 calleentrega=linea.substring(6,linea.length());
                 System.out.println(calleentrega);

             }

    }







              if (linea.contains("Matrícula")){
      matricula= linea.substring(10,linea.indexOf("Versión"));
       System.out.println(matricula);

    }
                if (linea.contains("Color")){
                    System.out.println("ghjg");
                    String delimitadores= "[ .,;?!¡¿\'\"\\[\\]]+";
String[] palabrasSeparadas = linea.split(delimitadores);
      color = palabrasSeparadas[1];


       System.out.println(color);

    }
           if (linea.contains("Teléfono2")){
               if (linea.length()<10){
                   telefono2="";
               }else{
      telefono2= linea.substring(10,linea.length());
       System.out.println(telefono2);
               }

    }

              if(conductorb==true){
         conductor.concat(linea);
        conductorb=false;
           System.out.println(conductor);
    }

        if (linea.contains("Conductor")){
            conductor=linea.substring(10,linea.length());
    conductorb=true;



    }

              if(tipob==true){
         tipo=linea;
        tipob=false;
        tipo= new LimpiarTexto().limpiar(tipo);
           System.out.println("rrrrtipo"+tipo);
    }

        if (linea.contains("Tipo")){

    tipob=true;



    }
        if (insitub==true){
            insitu=linea.substring(0,linea.indexOf("País"));
            insitub=false;
            System.out.println(insitu);
        }
        if (linea.contains("situ")){
            insitub=true;
        }
        if (linea.contains("Marca")){
      marca= linea.substring(6,linea.indexOf("Cod. avería"));
      codigoAveria= linea.substring(linea.indexOf("Cod. avería")+11,linea.length());

String delimitadores= "[ .,;?!¡¿\'\"\\[\\]]+";
String[] palabrasSeparadas = marca.split(delimitadores);
marca=palabrasSeparadas[0];
modelo=palabrasSeparadas[1];
       System.out.println(marca);
       System.out.println(modelo);
       System.out.println(codigoAveria);

    }
    }
        aviso = new Aviso();
    aviso.setAveria(codigoAveria);
    aviso.setCliente(compa);
    aviso.setColor(color);
    aviso.setGrua(Integer.parseInt(grua));
    aviso.setDireccionDestino(calleentrega);
    aviso.setDireccionLocalizacion(callerecogida);
    aviso.setExpediente(expediente);
   aviso.setFechaAsignacion(fecha);
   aviso.setFechaRealizacion(fecha);
    aviso.setMarca(marca);
   aviso.setMatricula(matricula);
   aviso.setModelo(modelo);
   aviso.setNombre(conductor);
   aviso.setMunicipioDestino(localidadentrega);
   aviso.setMunicipioLocalizacion(localidadrecogida);
   aviso.setTelefono(telefono1);
   //aviso.setTipotarifa(tipo);



      fr.close();
    }
    catch(IOException | NumberFormatException e) {
      System.out.println("Excepcion leyendo fichero "+ ruta + ": " + e);
    }

            RegistrarAviso registrar = new RegistrarAviso(aviso);
            registrar.setPdf(1);
          try {
              List<Aviso> e = registrar.registrar();
          } catch (Exception ex) {
Logger.getLogger(LeerPdf.class.getName()).log(Level.SEVERE, null, ex);
          }
    }




}//CLASS