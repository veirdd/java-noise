package vd.ui.std;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Style
{
    public static final Color background_color = new Color(225, 225, 225);
    public static final Color enabled_color = new Color(255, 255, 255);
    public static final Color disabled_color = new Color(200, 200, 200);
    public static final Color good_color = new Color(200, 255, 200);
    public static final Color bad_color = new Color(255, 200, 200);

    public static final int text_field_size = 16;

    public static final int empty_border_thickness = 10;
    public static final Border empty_border = BorderFactory.createEmptyBorder(
        empty_border_thickness, empty_border_thickness, empty_border_thickness, empty_border_thickness);
    public static final Border etched_border = BorderFactory.createEtchedBorder();
}