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

public class TablaResumenCellRenderer extends DefaultTableCellRenderer {

    public TablaResumenCellRenderer() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setBackground(new Color(255, 255, 255));
        setForeground(Color.black);
        setHorizontalAlignment(SwingConstants.LEFT);
        setText(value.toString());
        if (column > 1) {//los importes alineados a la izquierda
            setHorizontalAlignment(SwingConstants.RIGHT);
        }

        if ("clasificacion".equals(table.getValueAt(row, 0).toString())) {
            setBackground(Color.yellow);
            setFont(new Font("Arial", Font.BOLD, 11));
        } else if ("concepto".equals(table.getValueAt(row, 0).toString())) {
            setFont(new Font("Arial", Font.PLAIN, 11));
            if (column == 1) {//Los nombres de los conceptos con una tabulación
                setText("    " + value.toString());
            }
        } else {  //Fila total general      
            setFont(new Font("Arial", Font.BOLD, 11));
            setBackground(Color.blue);
            setForeground(Color.white);
        }

        if ("".equals(value.toString())) {
            setText("0.00");
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return this;
    }
}
