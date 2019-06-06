/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jym.ferreteria.skin;

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Barreto Flores Omar
 */
public class Fonts {

    public static Font ABOUT_BIG_FONT;
    public static Font APP_VERSION_TITLE_FONT;
    public static Font BUTTON_FONT;
    public static Font GENERAL_FONT_BOLD;
    public static Font SMALL_FONT;
    public static Font PROPERTIES_DIALOG_BIG_FONT;
    public static Font CHART_TITLE_FONT;
    public static Font CHART_TICK_LABEL_FONT;
    private static Font font;

    public static void initializeFonts() {
        /*
         * establece el fuente por defecto
         * 
         */

        font = new Font(Font.SANS_SERIF, Font.BOLD, 12);

//        font = SubstanceLookAndFeel.getFontPolicy().getFontSet("Substance", UIManager.getDefaults()).getControlFont();

        setUIFont(new FontUIResource(font));

        ABOUT_BIG_FONT = font.deriveFont(font.getSize() + 8f);
        APP_VERSION_TITLE_FONT = font.deriveFont(font.getSize() + 2f);
        BUTTON_FONT = font.deriveFont((float) font.getSize());
        GENERAL_FONT_BOLD = font.deriveFont(Font.BOLD, font.getSize() + 1);
        SMALL_FONT = font.deriveFont(font.getSize() - 1f);
        PROPERTIES_DIALOG_BIG_FONT = font.deriveFont(font.getSize() + 4f);
        CHART_TITLE_FONT = font.deriveFont(font.getSize() - 1f);
        SMALL_FONT = font.deriveFont(font.getSize() - 1f);
        CHART_TICK_LABEL_FONT = font.deriveFont(font.getSize() - 2f);
    }

    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void setFontSmoothing() {

        //usa suavizado de fuente
        System.setProperty("awt.useSystemAAFontSettings", "lcd");
        //no usa suavizado de fuente
//        System.setProperty("awt.useSystemAAFontSettings", "false");

    }

}
