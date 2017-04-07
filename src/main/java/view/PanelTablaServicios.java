/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import view.servicios.TablaServicios;

/**
 *
 * @author Aaron
 */
public class PanelTablaServicios extends Panel  {
    
    TablaServicios tablaServicios;
  
    public TablaServicios getTablaServicios() {
        return tablaServicios;
    }
 

    public PanelTablaServicios(){
           tablaServicios = new TablaServicios();
           JScrollPane scrollPaneTabla =  new javax.swing.JScrollPane();
           scrollPaneTabla.setViewportView(tablaServicios);
           setLayout(new BorderLayout());
           add(scrollPaneTabla, BorderLayout.CENTER);
           addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                       System.out.print("click");
                       MainFrame.getInstance().validate();
                     }
            });
         

           
    
    }
     
    
    public void actualizar() {
      revalidate();
      repaint();
      
    }



}
