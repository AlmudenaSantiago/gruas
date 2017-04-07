/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.productos;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Aarón
 */
public class SelectorImagenes {
     
    private JFileChooser jFileChooser;

    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }
    
    
    public SelectorImagenes() {
       this.jFileChooser = new JFileChooser();
    }
    
    
    public String selector(){
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
	     File selectedFile = jFileChooser.getSelectedFile();
	     return selectedFile.getAbsolutePath();
        }
        return null;
    }
    
}
