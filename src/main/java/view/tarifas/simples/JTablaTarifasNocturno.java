package view.tarifas.simples;

import command.tarifas.CargarTarifasCommand;
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
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;
import process.CRUDTarifas.ModificarTarifa;
import process.CRUDTarifas.ModificarTarifaNocturno;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;

public final class JTablaTarifasNocturno extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTarifasNocturnoModelo tablaCRUDModelo;
    private CargarTarifasCommand cargarTarifasCommand;
    private static JTablaTarifasNocturno instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    EditorCeldaTabla editor;
    int columna;
    int fila;

    public JTablaTarifasNocturno() {
        setTable();
    

        getColumnModel().getColumn(0).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(8).setCellEditor(new EditorCeldaTabla());

        setListeners();

        this.setForeground(new Color(153, 0, 51));
        this.setFont(new java.awt.Font("Arial", 0, 16));
        this.setRowHeight(30);

        // setListenerModificaCelda();
        setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void habilitarEdicionTabla() {
        tablaCRUDModelo.setEditable(true);

    }

    public void deshabilitarEdicionTabla() {
        tablaCRUDModelo.setEditable(false);
    }

    public void setTable() {
        tablaCRUDModelo = new TablaTarifasNocturnoModelo();
        setModel(tablaCRUDModelo);
    }

    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
     */
 
    public void setListeners() {

        getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            @Override
            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Suplemento";
                valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                fila = getSelectedRow();
                try {
                    modificador.modificarTarifa();
                  
                  //urbano 
                    Double valorUrbano = (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,1).toString()))/100;
              
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Urbano", valorUrbano.toString());
                    modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                    // salida
                    Double valorSalida = (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,2).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Salida", valorSalida.toString());
                      modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                    // km
                    Double valorkm = (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,3).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Km", valorkm.toString());
                      modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                   // rescate
                    Double valorrescate = (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,4).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Rescate", valorrescate.toString());
                      modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                   // dsi
                    Double valordsi= (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,5).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Dsi", valordsi.toString());
                      modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                    
                    // sar
                    Double valorsar= (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,6).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Sar", valorsar.toString());
                      modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                   
                    // espera
                    Double valorespera= (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,7).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Espera", valorespera.toString());
                    modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                  
                    //custodia
                    Double valorcustodia= (Double.parseDouble(valor)*Double.parseDouble(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).getTablaDiurno().getValueAt(fila,8).toString()))/100;
                    modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(fila).getId(), "Custodia", valorcustodia.toString());
                    modificador.setTabla(JTablaTarifasNocturno.this);
               
                    modificador.modificarTarifa();
                  
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            @Override
            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Urbano";
                valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Salida";
                valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Km";
                valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(4).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Rescate";
                valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(5).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Dsi";
                valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });
        getColumnModel().getColumn(6).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Sar";
                valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(7).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Espera";
                valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

        getColumnModel().getColumn(8).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
            String variableAModificar = "";

            public void editingStopped(ChangeEvent e) {
                variableAModificar = "Custodia";
                valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                ModificarTarifaNocturno modificador = new ModificarTarifaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                modificador.setTabla(JTablaTarifasNocturno.this);
                try {
                    modificador.modificarTarifa();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaTarifasNocturno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void editingCanceled(ChangeEvent e) {

            }
        });

    }

    public void mostrarTarifas(List<TarifaNocturno> lista) {
        tablaCRUDModelo.actualizarLista(lista);
        tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarTarifasCommand cargarTarifasCommand) {
        this.cargarTarifasCommand = cargarTarifasCommand;
    }
}
