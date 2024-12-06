package vrd.ui.std;

import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T>
{
    public ComboBox(T[] items)
    {
        super(items);

        setBackground(Style.enabled_color);
    }
}