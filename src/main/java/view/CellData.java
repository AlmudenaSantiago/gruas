/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JTable;

public class CellData {
    private JTable table;

    public CellData(JTable table) {
        this.table = table;
    }

    public int getColumn() {
        return table.getSelectedColumn();
    }

    public String getValue() {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        return (String) table.getValueAt(row, col);
    }

    public JTable getTable() {
        return table;
    }

}