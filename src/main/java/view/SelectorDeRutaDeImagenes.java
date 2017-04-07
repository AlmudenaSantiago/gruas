/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gruasjoseantonio
 */
public class SelectorDeRutaDeImagenes {
   
  
      
    JFileChooser chooser;
    File rutaAnterior;
    
        public SelectorDeRutaDeImagenes() {
            chooser = new JFileChooser();
        }
        
        public File seleccionDeRuta()  {
               if (rutaAnterior == null) {
                  chooser.setCurrentDirectory(new java.io.File("."));
                } else {
                  chooser.setCurrentDirectory(rutaAnterior);
                }
               
               chooser.setDialogTitle("Seleccion la ubicación de la imagen");
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);
               FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
               chooser.setFileFilter(filtro);

               chooser.setApproveButtonText("Aceptar");
               if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){ 
                   rutaAnterior = chooser.getSelectedFile();
                   return chooser.getSelectedFile();
               }
               else return null;
            }
    
 
        
        
}

    
    

