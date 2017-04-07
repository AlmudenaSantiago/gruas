package view.servicios;

import command.servicios.CargarServiciosCommand;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Servicio;
import java.util.List;
import javax.swing.DropMode;
import javax.swing.JPopupMenu;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableRowSorter;
import view.MainFrame;
import view.PopUpActualizarEstadosTablaServicios;

import view.TransferHelper;


/**
 *
 * @author Aaron
 */
public final class TablaServicios extends javax.swing.JTable  {
    
    private TablaServiciosModelo modelo;
    private CargarServiciosCommand cargarServiciosCommand;
    private String servicioAlQueSeVaAAsignar;
           
    private JPopupMenu popup;
    
    
    public TablaServicios () {
      
        setTable();  
        setFont(new java.awt.Font("Arial", 0, 16));
        setRowHeight(25);
        //  setDefaultRenderer(Object.class, new FormatoTablaServicios());
        setRowSorter(); 
        setListeners();
        setDragEnabled(true);
        setDropMode(DropMode.USE_SELECTION);
        setTransferHandler(new TransferHelper());
        setRowSelectionAllowed(false);
        setCellSelectionEnabled(true);
     
      
    }
    
    
        
    public void setTable() {
        modelo = new TablaServiciosModelo();
        setModel(modelo);
        
       }
   
    public void setRowSorter () {
     	TableRowSorter sorter = new TableRowSorter(modelo);
        this.setRowSorter(sorter);
        sorter.addRowSorterListener(new RowSorterListener() {
                                         private Object jTable;
                                            @Override
                                            public void sorterChanged(RowSorterEvent evt) {
                                                System.out.println("evento");
                                            }
                                            });
    }

    @Override
    public TablaServiciosModelo getModel() {
        return modelo;
    }
    
    
    
     public void mostrarServicios(List<Servicio> listaServicio) {
         modelo.actualizarLista(listaServicio);
         modelo.fireTableDataChanged();
    }

    public void setCargarPedidosCommand(CargarServiciosCommand cargarServiciosCommand) {
        this.cargarServiciosCommand = cargarServiciosCommand;
    }

    public void setListeners() {
 
    
      addMouseListener(new MouseAdapter() {
            
             @Override
             public void mouseEntered(MouseEvent e) {
                servicioAlQueSeVaAAsignar =  (String) getModel().getValueAt(rowAtPoint(e.getPoint()),2);
             }
         
             @Override
             public void mouseClicked(MouseEvent e) {
                 Integer idServicio = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                 if (columnAtPoint(e.getPoint()) == 16) {
                    // Click derecho  en la columna de estado
                    if (e.isMetaDown() ){
                               PopUpActualizarEstadosTablaServicios popup = new PopUpActualizarEstadosTablaServicios(idServicio);
                               popup.show(e.getComponent(),e.getX(), e.getY()); //... mostramos el menu en la ubicacion del raton
                               MainFrame.getInstance().validate();
                               System.out.println("fila" + getModel().getValueAt(rowAtPoint(e.getPoint()),1));
                               
                    } 
               }
              
            }
             
       });
             
       
    }

   
    
    
    public void finalizar() {
     System.out.println("finalizar" +  servicioAlQueSeVaAAsignar);
      
    }
   

    public String getServicioAlQueSeVaAAsignar() {
       System.out.println("RETURNNNNNNNNN " + servicioAlQueSeVaAAsignar);
        return this.servicioAlQueSeVaAAsignar;
    }

    
    
  
    
    
}
