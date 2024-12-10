package vrd.ui.std;

import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T>
{
    public ComboBox(T[] items)
    {
        super(items);

        setFocusable(false);
        setBackground(Style.enabled_color);
    }
}