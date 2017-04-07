package view.basesOperativas;

import view.gruas.*;
import command.gruas.CargarGruasCommand;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import model.Municipio;
import model.gruas.Gruista;





public final class TablaMunicipiosParaEleccion extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaMunicipiosModelo tabla;
    private CargarGruasCommand cargarGruasCommand;
 
 
    
  
   


    public TablaMunicipiosParaEleccion() {
        setTable();
        this.setFont(new java.awt.Font("Arial", 0, 16));
        this.setRowHeight(30);
        this.setDefaultRenderer(Object.class, new FormatoTablaGruas());
        this.getColumnModel().getColumn(0).setMaxWidth(0);
        this.getColumnModel().getColumn(0).setMinWidth(0);
        
        this.setListeners();
         
      }
    
    
    public void setListeners() {
    
       addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                 BasesOperativasCRUDFrame.getInstance().modificarMunicipioBase(id);
                 EleccionMunicipioFrame.getInstance().setVisible(false);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            } 
            
            
        });
    }


  
    public void setTable() {
        tabla = new TablaMunicipiosModelo();
        setModel(tabla);
        
       }
   
  

   

  

    public void mostrar(List<Municipio> lista) {
          tabla.actualizarLista(lista);
          tabla.fireTableDataChanged();
    }

   

  
}
