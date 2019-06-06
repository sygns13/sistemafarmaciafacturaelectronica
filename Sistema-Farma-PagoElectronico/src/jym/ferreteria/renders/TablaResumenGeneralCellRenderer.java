/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaResumenGeneralCellRenderer extends DefaultTableCellRenderer {

    public TablaResumenGeneralCellRenderer() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setBackground(new Color(255, 255, 255));
        setForeground(Color.black);
        setHorizontalAlignment(SwingConstants.LEFT);
        if(column==1){
            setText("    " + value.toString());
        }
        if (column > 1) {//los importes alineados a la izquierda
            setHorizontalAlignment(SwingConstants.RIGHT);
            setText(value.toString());
        }

        if ("egresos".equals(table.getValueAt(row, 0).toString())) {
            setForeground(Color.red);
            setText(value.toString());
        } else if ("ingresos".equals(table.getValueAt(row, 0).toString())) {
            setForeground(Color.green);
            setText(value.toString());
           
        } else {  //Fila total general      
            setFont(new Font("Arial", Font.BOLD, 11));
            setForeground(Color.blue);
            setText(value.toString());
//            setForeground(Color.white);
        }

        if ("".equals(value.toString())) {
            setText("0.00");
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return this;
    }
}
