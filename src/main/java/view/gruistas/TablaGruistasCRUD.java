package view.gruistas;

import view.gruas.*;
import command.gruas.CargarGruasCommand;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.List;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import javax.swing.table.TableRowSorter;
import model.gruas.Grua;
import model.gruas.Gruista;
import view.FromTransferHandler;
import view.MainFrame;
import view.TransferHelper;





public final class TablaGruistasCRUD extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaGruistasModelo tablaGruistasModelo;
    private CargarGruasCommand cargarGruasCommand;
 
 
    
  
   


    public TablaGruistasCRUD() {
        setTable();
        this.setFont(new java.awt.Font("Arial", 0, 16));
        this.setRowHeight(30);
        this.setDefaultRenderer(Object.class, new FormatoTablaGruas());
      
        this.setListeners();
        
    
        
        
      }
    
    
    public void setListeners() {
    
       addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 Integer numGrua = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                 
                  
             
                 if ( columnAtPoint(e.getPoint()) == 2) {
                    
                     
                 
                 
                 }

                 
                
             }
            @Override
            public void mouseReleased(MouseEvent e) {
            } 
            
            
        });
    }


  
    public void setTable() {
        tablaGruistasModelo = new TablaGruistasModelo();
        setModel(tablaGruistasModelo);
        
       }
   
  

   

  

    public void mostrarGruistas(List<Gruista> listaGruistas) {
          tablaGruistasModelo.actualizarListaGruas(listaGruistas);
          tablaGruistasModelo.fireTableDataChanged();
    }

   

  
}
