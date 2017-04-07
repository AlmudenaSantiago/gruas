package view.avisos;



import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Aviso;



public class TablaAvisosModelo extends AbstractTableModel {

    private List<Aviso> listaAvisos;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
         "Id", "Fecha realización", "Cliente","Matricula","Expediente","Marca","Nombre","Poliza"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class 

    
     };

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return (listaAvisos != null) ? listaAvisos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
 
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Aviso aviso = listaAvisos.get(rowIndex);
        switch (columnIndex){
            case 0:
                 return aviso.getId();
            case 1:
                 return aviso.getFechaRealizacion();
            case 2:
                 return aviso.getIdCliente() + " (" + aviso.getCliente() + ")";
            case 3:
                 return aviso.getMatricula();
            case 4:
                 return aviso.getExpediente();
            case 5:
                 return aviso.getMarca() + ". " + aviso.getModelo();
            case 6:
                 return aviso.getNombre();
            case 7: 
                 return aviso.getPoliza();
              }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Aviso> lista){
          if (lista == null) {
           List<Aviso> list = new ArrayList<>();
           this.listaAvisos = list;
         } else{
           this.listaAvisos = lista;
         }
       
    }

}



