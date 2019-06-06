/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jym.ferreteria.skin;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author Omr
 */
public class DefinicionColor {
    public static final Color TITLE_DIALOG_FONT_COLOR = Color.WHITE;
	public static final Color WARNING_COLOR = Color.RED;	

	/**
	 * Inits the colors.
	 */
	public static void initColors() {
//		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.BLACK));
		UIManager.put("Table.selectionBackground", (Color.BLACK));
	
//		UIManager.put("ToolTip.background", new ColorUIResource(Color.WHITE));
//		UIManager.put("ToolTip.foreground", new ColorUIResource(Color.BLACK));



                
	}

}
