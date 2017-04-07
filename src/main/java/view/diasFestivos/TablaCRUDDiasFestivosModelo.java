package view.diasFestivos;


import view.productos.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;
import model.DiaFestivo;

import model.Producto;





public class TablaCRUDDiasFestivosModelo extends AbstractTableModel {

    private List<DiaFestivo> lista;
   
 
    protected String[] columnNames = new String[] {
          "Id","Fecha","Eliminar"
    };

    protected Class[] columnClasses = new Class[] {
           Integer.class , String.class, JButton.class};

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
        return (lista != null) ? lista.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public List<DiaFestivo> getLista() {
        return lista;
    }
    
 
    
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        DiaFestivo dia = lista.get(rowIndex);
        switch (columnIndex){
            case 0: 
                 return dia.getId();
                   
            case 1:
                    String fechaOrdenada = dia.getDiaFestivoString();
                    String year  = fechaOrdenada.substring(0,4);
                    
                    String month = fechaOrdenada.substring(5,7);
                    String mes = "";
                    switch (month) {
                        case "01": mes = "Enero"; break;
                        case "02": mes = "Febrero"; break;
                        case "03": mes = "Marzo"; break;
                        case "04": mes = "Abril"; break;
                        case "05": mes = "Mayo"; break;
                        case "06": mes = "Junio"; break;
                        case "07": mes = "Julio"; break;
                        case "08": mes = "Agosto"; break;
                        case "09": mes = "Septiembre"; break;
                        case "10": mes = "Octubre"; break;
                        case "11": mes = "Noviembre"; break;
                        case "12": mes = "Diciembre"; break;
                    }
                    
                    String day   = fechaOrdenada.substring(8,10);
                    String fecha = day + " de " + mes + " del " + year;
                    return fecha; 

            case 2:
                JButton botonEliminar = new JButton("Eliminar");
                botonEliminar.setFont(new java.awt.Font("Arial", 0, 14));
                return botonEliminar;
           
        }
        
        return null;
    }

   
    public void actualizarLista(List<DiaFestivo> lista){
        this.lista = lista;
    
    }

}
