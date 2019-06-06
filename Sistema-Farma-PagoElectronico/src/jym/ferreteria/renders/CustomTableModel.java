/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.renders;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel
 */
public class CustomTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {

        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (super.getValueAt(row, column) == null) {
            return "";
        }
        return super.getValueAt(row, column);
    }

}
