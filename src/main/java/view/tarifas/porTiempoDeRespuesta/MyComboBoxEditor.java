/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tarifas.porTiempoDeRespuesta;

import java.io.IOException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

  
    public class MyComboBoxEditor extends DefaultCellEditor {
           public MyComboBoxEditor(String[] items) throws IOException {
           super(new JComboBox(items));
           
            
            
            
        }
    }
    
