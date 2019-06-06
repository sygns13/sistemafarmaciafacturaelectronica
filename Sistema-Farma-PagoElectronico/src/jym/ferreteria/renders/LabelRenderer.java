/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Omr
 */
public class LabelRenderer implements TableCellRenderer {

    private int horizontalAlign = SwingConstants.LEFT;
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private Font font;
    private Border border;

    public LabelRenderer(int horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    public LabelRenderer() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        renderer.setFont(new Font("Arial", Font.BOLD, 12));
        renderer.setBackground(new JTable().getBackground());
        renderer.setForeground(Color.black);
        renderer.setText(value.toString());

        renderer.setHorizontalAlignment(horizontalAlign);
        if (font != null) {
            renderer.setFont(font);
        }
        if (border != null) {
            renderer.setBorder(border);
        }
        return (Component) renderer;
    }

    public DefaultTableCellRenderer getRenderer() {
        return renderer;
    }

    public LabelRenderer setFont(Font font) {
        this.font = font;
        return this;
    }

    public LabelRenderer setBorder(Border border) {

        this.border = border;
        return this;
    }

}
