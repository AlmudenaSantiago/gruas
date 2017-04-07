/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tarifas.simples;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class EditorCeldaTabla extends AbstractCellEditor implements TableCellEditor {

  JComponent component = new JTextField();

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {

    ((JTextField) component).setText( value.toString() );

     return component;
  }

  @Override
  public Object getCellEditorValue() {
    return ((JTextField) component).getText();
  }
}
