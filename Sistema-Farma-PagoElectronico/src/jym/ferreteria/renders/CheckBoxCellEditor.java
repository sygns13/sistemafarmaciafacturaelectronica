/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.renders;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Omr
 */
public class CheckBoxCellEditor extends DefaultCellEditor implements TableCellRenderer {

    private JCheckBox checkBox = new JCheckBox();
    private boolean value = false;

    public CheckBoxCellEditor() {
        super(new JCheckBox());
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) {
            return null;
        }

        return checkBox;
    }

    @Override
    public boolean stopCellEditing() {
        value = ((Boolean) getCellEditorValue());
        checkBox.setSelected(value);
        return super.stopCellEditing(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        boolean b = ((Boolean) value);
        checkBox.setSelected(b);
        return checkBox;
    }

}
