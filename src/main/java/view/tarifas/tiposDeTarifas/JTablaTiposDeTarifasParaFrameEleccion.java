package view.tarifas.tiposDeTarifas;


import view.tarifas.simples.*;
import command.tarifas.CargarRecorridosyKmCommand;
import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import command.tarifas.CargarTiposDeTarifaCommand;
import view.productos.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableRowSorter;
import model.RecorridosyKm;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import model.Tarifas.TipoDeTarifa;
import process.CRUDTiposDeTarifa.ModificarTipoDeTarifa;
import view.basesOperativas.BasesOperativasCRUDFrame;
import view.basesOperativas.JTablaBasesOperativasCRUD;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;
import view.tarifas.porTiempoDeRespuesta.EleccionTipoDeTarifaFrame;






public final class JTablaTiposDeTarifasParaFrameEleccion extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTiposDeTarifasModelo1 tablaCRUDModelo;
    private CargarTiposDeTarifaCommand cargarTarifasCommand;
    private static JTablaTiposDeTarifasParaFrameEleccion instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaTiposDeTarifasParaFrameEleccion() {
       setTable();
       setRowSorter();
       
       setListeners();

       this.setFont(new java.awt.Font("Arial", Font.BOLD, 17));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
   
       
    }

    @Override
    public TablaTiposDeTarifasModelo1 getModel() {
        return tablaCRUDModelo;
    }
    

  public void setListeners() {
      
         addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
           
                
             
                 if (columnAtPoint(e.getPoint()) == 0) {
                     try {
                         FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaTR().modificarTarifa(getValueAt(rowAtPoint(e.getPoint()), 0).toString());
                         EleccionTipoDeTarifaFrame.getInstance().setVisible(false);
                     } catch (Exception ex) {
                         Logger.getLogger(JTablaTiposDeTarifasParaFrameEleccion.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                
               
           }  
         }); 
     }
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaTiposDeTarifasModelo1();
        setModel(tablaCRUDModelo);
       
       
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

    public TableRowSorter getSorter() {
        return sorter;
    }
    

     public void setRowSorter () {
     	  sorter = new TableRowSorter(tablaCRUDModelo);
          this.setRowSorter(sorter);
         //sorter.setRowFilter (RowFilter.regexFilter(".",0)); 
      
     }
    
   


    
    public void mostrarTarifas(List<TipoDeTarifa> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarTiposDeTarifaCommand cargarTarifasCommand) {
        this.cargarTarifasCommand = cargarTarifasCommand;
    }
}
   
