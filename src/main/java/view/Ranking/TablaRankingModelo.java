
package view.Ranking;

import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.table.AbstractTableModel;



public class TablaRankingModelo extends AbstractTableModel {

    private String listaPlatos;
    private String fechaOrdenada;
    private StringTokenizer st;   
    private ArrayList<String> arrayResultadoTokenizer;
    protected String[] columnNames = new String[] {
            "Producto", "NºVentas"};

    protected Class[] columnClasses = new Class[] {
           String.class, Integer.class };

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
        return (arrayResultadoTokenizer != null) ? arrayResultadoTokenizer.size(): 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        switch (columnIndex){
            case 0:
                if (rowIndex%2 == 0) {
                  return arrayResultadoTokenizer.get(rowIndex);
               }
            
            case 1:
              if (rowIndex%2 == 0) {
                if (arrayResultadoTokenizer.get(rowIndex+1).equals("0")){return Integer.parseInt(arrayResultadoTokenizer.get(rowIndex+1));}
                return Integer.parseInt(arrayResultadoTokenizer.get(rowIndex+1));}
           }     
        
        return null;
    }

    
    
      public void actualizarListaPlatos(String listaPlatos){
          
         this.listaPlatos = listaPlatos;
         arrayResultadoTokenizer  = new ArrayList<String> ();
         st = new StringTokenizer(listaPlatos,",",false);
         
         while (st.hasMoreTokens()) {
              arrayResultadoTokenizer.add(st.nextToken().replaceAll("\"", "").replaceAll("\\[","").replaceAll("\\]",""));
         }
         System.out.println("esta es la lista de platos " + listaPlatos );
         
        
            //quitar la " y la ] del principio y final de la cadena devuelta de la url 
         //arrayResultadoTokenizer.set(0, arrayResultadoTokenizer.get(0).substring(1,arrayResultadoTokenizer.get(0).length()));
         //arrayResultadoTokenizer.set(arrayResultadoTokenizer.size()-1,arrayResultadoTokenizer.get(arrayResultadoTokenizer.size()-1).substring(0,arrayResultadoTokenizer.get(arrayResultadoTokenizer.size()-1).length()-1));
         
         
         
      } 



}