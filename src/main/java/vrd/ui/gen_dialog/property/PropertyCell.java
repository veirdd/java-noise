package vrd.ui.gen_dialog.property;

import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import vrd.ui.std.Panel;

import vrd.alg.property.Property;
public abstract class PropertyCell extends Panel
{
    public PropertyCell(Property property)
    {
        this.name_label = new JLabel(property.name);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(name_label);
    }

    public abstract Property getProperty();

    public abstract boolean validateInput();

    protected JLabel name_label;
    protected JComponent value_field;
}