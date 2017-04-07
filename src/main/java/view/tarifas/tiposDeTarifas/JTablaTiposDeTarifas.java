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






public final class JTablaTiposDeTarifas extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTiposDeTarifasModelo tablaCRUDModelo;
    private CargarTiposDeTarifaCommand cargarTarifasCommand;
    private static JTablaTiposDeTarifas instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaTiposDeTarifas() {
       setTable();
       setRowSorter();
          getColumnModel().getColumn(0).setCellEditor(new EditorCeldaTabla());
 
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
    public TablaTiposDeTarifasModelo getModel() {
        return tablaCRUDModelo;
    }
    

  public void setListeners() {
      
            getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "tipo";
                      valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                      ModificarTipoDeTarifa modificador = new ModificarTipoDeTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       try {
                            modificador.modificar();
                        } catch (IOException ex) {
                            Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     TiposDeTarifaCRUDFrame.getInstance().actualizarTabla();
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });

      }
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaTiposDeTarifasModelo();
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
   
