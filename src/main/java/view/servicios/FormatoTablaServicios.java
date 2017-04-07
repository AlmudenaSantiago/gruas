/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servicios;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class FormatoTablaServicios extends DefaultTableCellRenderer{    
   


    @Override 
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    {
        table.setFont(new Font( "Arial",Font.PLAIN,14 ));
        table.setForeground(Color.black);
        setHorizontalAlignment(2);
        TableColumn columnaEstado =  table.getColumnModel().getColumn(15);

        String estados[] =  {"En curso","Aceptado","Localizado","Rechazado","Iniciado","Reparando","Finalizado"};
        JComboBox comboEstados = new JComboBox(estados); //creo el objeto combobo como un vector
        columnaEstado.setCellEditor(new DefaultCellEditor(comboEstados));
       
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        return this;
 }
    


}

