package vrd.ui.gen_dialog.property;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import vrd.gen.alg.property.Property;
import vrd.ui.std.Panel;
public abstract class PropertyCell extends Panel
{
    public PropertyCell(Property property)
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        this.name_label = new JLabel(property.name);

        add(name_label);
    }

    public abstract Property getProperty();

    public abstract boolean validateInput();

    protected JLabel name_label;
}