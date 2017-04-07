package view.distancias;


import view.municipios.*;
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
import javax.swing.table.TableRowSorter;
import model.Municipio;
import process.CRUDDistancia.ModificarDistancia;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;







public final class JTablaMunicipiosParaEleccionDistancias extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDMunicipioModeloParaEleccion tablaCRUDModelo;
    private CargarMunicipiosCommand cargarRecorridosykmCommand;

    private TableRowSorter sorter;
    private ImageFrame image;
    
    
    
    int columnaSeleccionada;
    int filaSeleccionada;
    
    public JTablaMunicipiosParaEleccionDistancias() {
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
                      Integer id = Integer.parseInt(TablaDistancias.getInstance().getValueAt(TablaDistancias.getInstance().getSelectedRow(),0).toString());
                       ModificarDistancia  modificar;
                      if (EleccionMunicipioFrameParaDistancias.getInstance().getOrigenODestino().equals("origen")) {
                         modificar = new ModificarDistancia(id,"municipioOrigen", getValueAt(rowAtPoint(e.getPoint()), 0).toString());
                      }
                      else {
                        modificar = new ModificarDistancia(id,"municipioDestino", getValueAt(rowAtPoint(e.getPoint()), 0).toString());
                      }
                      try {
                         modificar.modificar();
                     } catch (IOException ex) {
                         Logger.getLogger(JTablaMunicipiosParaEleccionDistancias.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     EleccionMunicipioFrameParaDistancias.getInstance().setVisible(false);
                     DistanciasFrame.getInstance().actualizarTabla();
                 }
                
               
           }  
         }); 
        
       
        
    }  
        


    
    public void mostrar(List<Municipio> listaMunicipios) {
         tablaCRUDModelo.actualizarListaMunicipios(listaMunicipios);
         tablaCRUDModelo.fireTableDataChanged();
    }

   
    
    
    
    
    
}
   
