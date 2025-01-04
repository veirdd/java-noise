package vd.ui.gen_dialog.property;

import java.awt.FlowLayout;
import javax.swing.JLabel;

import vd.gen.alg.property.Property;
import vd.ui.std.Panel;
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