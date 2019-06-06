/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jym.ferreteria.renders;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Omr
 */
public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer{
private JCheckBox checkBox= new JCheckBox();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       checkBox.setSelected(value.toString().equals("1"));
       return checkBox;
    }
    
}
