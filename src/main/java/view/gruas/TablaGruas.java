package view.gruas;

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
import view.FromTransferHandler;
import view.MainFrame;
import view.TransferHelper;





public final class TablaGruas extends javax.swing.JTable implements MouseMotionListener{

    private JScrollPane jScrollPane1;
    private TablaGruasModelo tablaGruasModelo;
    private CargarGruasCommand cargarGruasCommand;
    private Integer gruaQueSeVaAAsignar;

 
    
  
   


    public TablaGruas() {
        setTable();
        this.setFont(new java.awt.Font("Arial", 0, 16));
        this.setRowHeight(30);
        this.setDefaultRenderer(Object.class, new FormatoTablaGruas());
        this.setRowSorter();
        this.setListeners();
        
        setDragEnabled(true);
        setDropMode(DropMode.USE_SELECTION);
        setTransferHandler(new TransferHelper());
        setRowSelectionAllowed(false);
        setCellSelectionEnabled(true);
        
        
      }
    
    
    public void setListeners() {
       addMouseMotionListener(this);
       addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 gruaQueSeVaAAsignar = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                
             }
            @Override
            public void mouseReleased(MouseEvent e) {
                 //MouseInfo.getPointerInfo();
                 System.out.println("grua que se va a asignar"+gruaQueSeVaAAsignar);
                 System.out.println("posicion del raton 1" + 
                         MouseInfo.getPointerInfo().getLocation());
                Point mouse =  MouseInfo.getPointerInfo().getLocation();
                int row =  MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().rowAtPoint(mouse);
                System.out.println("servicio" +  MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().getServicioAlQueSeVaAAsignar());
                    
//    System.out.println("row index " +  MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().getModel().getValueAt(rowAtPoint(e.getPoint()),1)); 
                
               // System.out.println("posicion del raton" +   MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().getModel().getValueAt(row,1));
           
               //    MainFrame.getInstance().getTablaServiciosPendientes().getServicioAlQueSeVaAAsignar();
               //   System.out.println("Lo asigno" + MainFrame.getInstance().getTablaServiciosPendientes().getServicioAlQueSeVaAAsignar());
            } 
            
            
        });
    }
    
    @Override
     public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       System.out.println("Estoy arrastrando el raton en la posicion"
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " y LA GRUA QUE ESTOY MOVIENDO ES LA    "  + this.gruaQueSeVaAAsignar +  "/n");
       
    }
    
   

    
    public Integer getGruaQueSeVaAAsignar() {
        return gruaQueSeVaAAsignar;
    }
   
  
    public void setTable() {
        tablaGruasModelo = new TablaGruasModelo();
        setModel(tablaGruasModelo);
        
       }
   
    public void setRowSorter () {
     	TableRowSorter sorter = new TableRowSorter(tablaGruasModelo);
        this.setRowSorter(sorter);
        sorter.addRowSorterListener(new RowSorterListener() {
                 private Object jTable;
        @Override
        public void sorterChanged(RowSorterEvent evt) {
            System.out.println("evento");
        }
             });
    }

   

  

    public void mostrarGruas(List<Grua> listaGruas) {
          tablaGruasModelo.actualizarListaGruas(listaGruas);
          tablaGruasModelo.fireTableDataChanged();
    }

    public void setCargarGruasCommand(CargarGruasCommand cargarGruasCommand) {
        this.cargarGruasCommand = cargarGruasCommand;
    }

  
}
