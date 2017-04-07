/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Aarón
 */
public class SelectorDeRuta {
    
      
    JFileChooser chooser;
    File rutaAnterior;
    
        public SelectorDeRuta() {
            chooser = new JFileChooser();
        }
        
        public File seleccionDeRuta()  {
               if (rutaAnterior == null) {
                  chooser.setCurrentDirectory(new java.io.File("."));
                } else {
                  chooser.setCurrentDirectory(rutaAnterior);
                }
               
               chooser.setDialogTitle("Seleccion la ubicación de la factura");
               chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               chooser.setAcceptAllFileFilterUsed(false);
               chooser.setApproveButtonText("Guardar");
               if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){ 
                   rutaAnterior = chooser.getSelectedFile();
                   return chooser.getSelectedFile();
               }
               else return null;
            }
    
 
        
        
}
