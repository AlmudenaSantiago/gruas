/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import process.CRUDServicios.ModificarServicio;

/**
 *
 * @author Aaron
 */
public class PopUpActualizarEstadosTablaServicios extends JPopupMenu implements ActionListener  {
    
   
    private  JMenuItem menuItemEstadoRechazado;
    private  JMenuItem menuItemEstadoAceptado;
    private  JMenuItem menuItemEstadoFinalizado;
    private  JMenuItem menuItemEstadoReparando;
    private  JMenuItem menuItemEstadoLocalizado;
    private  JMenuItem menuItemEstadoIniciado;
    private  JMenuItem menuItemEstadoRecibido;
    private  JMenuItem menuItemEstadoAnulado;
    private  JMenuItem menuItemEstadoTrasladando;
    private  String nuevoEstado;
    private  Integer idServicio;
    
    
    public PopUpActualizarEstadosTablaServicios (Integer idServicio) {
            
            this.idServicio = idServicio;
  
            menuItemEstadoRecibido = new JMenuItem("Recibido");
            menuItemEstadoAceptado = new JMenuItem("Aceptado") ;
            menuItemEstadoRechazado = new JMenuItem("Rechazado");
            menuItemEstadoIniciado = new JMenuItem("Iniciado") ;
            menuItemEstadoAnulado = new JMenuItem("Anulado");
            menuItemEstadoLocalizado= new JMenuItem("Localizado");
            menuItemEstadoReparando = new JMenuItem("Reparando");
            menuItemEstadoTrasladando = new JMenuItem("Trasladando");
            menuItemEstadoFinalizado = new JMenuItem("Finalizado");

            add(menuItemEstadoRechazado);
            add(menuItemEstadoAceptado);
            add(menuItemEstadoFinalizado);
            add(menuItemEstadoReparando);
            add(menuItemEstadoLocalizado);
            add(menuItemEstadoIniciado);
            add(menuItemEstadoRecibido);
            add(menuItemEstadoAnulado);
            add(menuItemEstadoTrasladando);

            
            menuItemEstadoAceptado.addActionListener(this);
            menuItemEstadoFinalizado.addActionListener(this);
            menuItemEstadoReparando.addActionListener(this);
            menuItemEstadoLocalizado.addActionListener(this);
            menuItemEstadoIniciado.addActionListener(this);
            menuItemEstadoRecibido.addActionListener(this);
            menuItemEstadoAnulado.addActionListener(this);
            menuItemEstadoTrasladando.addActionListener(this);
        
    }

  
     
     @Override
      public void actionPerformed(ActionEvent e) {
          
          if (e.getSource() == menuItemEstadoRecibido)        nuevoEstado = "Recibido";
          if (e.getSource() == menuItemEstadoAceptado)        nuevoEstado = "Aceptado";
          if (e.getSource() == menuItemEstadoRechazado)       nuevoEstado = "Rechazado";
          if (e.getSource() == menuItemEstadoIniciado)        nuevoEstado = "Iniciado";
          if (e.getSource() == menuItemEstadoAnulado)         nuevoEstado = "Anulado";
          if (e.getSource() == menuItemEstadoLocalizado)      nuevoEstado = "Localizado";
          if (e.getSource() == menuItemEstadoReparando)       nuevoEstado = "Reparando";
          if (e.getSource() == menuItemEstadoTrasladando)     nuevoEstado = "Trasladando";
          if (e.getSource() == menuItemEstadoFinalizado)      nuevoEstado = "Finalizado";
          
          ModificarServicio actualizador = new ModificarServicio(idServicio,"estado",nuevoEstado);
            try {
                System.out.println("llega a modificar");
                actualizador.modificarServicio();
            } catch (IOException ex) {
                Logger.getLogger(PopUpActualizarEstadosTablaServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        
          
        }
          
          
    
    
}
