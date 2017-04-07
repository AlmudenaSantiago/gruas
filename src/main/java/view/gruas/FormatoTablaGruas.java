/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gruas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaGruas extends DefaultTableCellRenderer{    
   


    @Override 
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    {
       
        setBackground(Color.white);
        table.setFont(new Font( "Arial",Font.PLAIN,14 ));
        table.setForeground(Color.black);
        setHorizontalAlignment(2);
                
   
        if(String.valueOf(table.getValueAt(row,column)).equals("En atasco")){
            setBackground(Color.red); 
        }  
       if(String.valueOf(table.getValueAt(row,column)).equals("Desocupada")){
            setBackground(Color.green); 
        }  
      
        
      
        if(String.valueOf(table.getValueAt(row,column)).equals("plataforma grande")){
            setBackground(Color.yellow);         
        
        }
        
          if(String.valueOf(table.getValueAt(row,column)).equals("basica")){
            setBackground(Color.pink);         
        
        }
         if(String.valueOf(table.getValueAt(row,column)).equals("plataforma")){
            setBackground(Color.orange);         
        
        }
             
                
       
        /*
        if(Integer.valueOf(String.valueOf(table.getValueAt(row,9)))<20){
            setBackground(Color.GREEN); 
        } */     
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        return this;
 }
    


}

