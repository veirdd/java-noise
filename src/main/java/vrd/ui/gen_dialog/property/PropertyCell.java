package vrd.ui.gen_dialog.property;

import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vrd.alg.property.Property;
public abstract class PropertyCell extends JPanel
{
    public PropertyCell(Property property)
    {
        this.name_label = new JLabel(property.name);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(name_label);
    }

    protected JLabel name_label;
    protected JComponent value_field;
}