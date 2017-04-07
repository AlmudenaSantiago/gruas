package view.municipios;


import DatosPrecargados.SingletonMunicipios;
import command.municipios.CargarMunicipiosCommand;
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
import model.Municipio;
import process.CRUDMunicipios.ModificarMunicipio;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;
import view.tarifas.porTiempoDeRespuesta.EleccionTipoDeTarifaFrame;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;
import view.tarifas.tiposDeTarifas.JTablaTiposDeTarifasParaFrameEleccion;







public final class JTablaMunicipiosParaEleccion extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDMunicipioModeloParaEleccion tablaCRUDModelo;
    private CargarMunicipiosCommand cargarRecorridosykmCommand;

    private TableRowSorter sorter;
    private ImageFrame image;
    
    
    
    int columnaSeleccionada;
    int filaSeleccionada;
    
    public JTablaMunicipiosParaEleccion() {
       setTable();
   
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       getColumnModel().getColumn(0).setPreferredWidth(1);
       getColumnModel().getColumn(1).setPreferredWidth(250);
           getColumnModel().getColumn(0).setMinWidth(0);
          getColumnModel().getColumn(0).setMaxWidth(0);
     
       setListeners();
     
      }
    


  
   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDMunicipioModeloParaEleccion();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

 
       
   
    

     
     
     
     
    
    public void setListeners() {
       
           addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
           
                
             
                 if (columnAtPoint(e.getPoint()) == 0 || columnAtPoint(e.getPoint()) == 1) {
                     try {

                         FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTabla().modificarTarifa(getValueAt(rowAtPoint(e.getPoint()), 0).toString());
                         EleccionMunicipioFrame.getInstance().setVisible(false);
                     } catch (Exception ex) {
                         Logger.getLogger(JTablaMunicipiosParaEleccion.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                 }
                
               
           }  
         }); 
        
       
        
    }  
        


    
    public void mostrar(List<Municipio> listaMunicipios) {
         tablaCRUDModelo.actualizarListaMunicipios(listaMunicipios);
         tablaCRUDModelo.fireTableDataChanged();
    }

   
    
    
    
    
    
}
   
