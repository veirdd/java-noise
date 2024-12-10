package vrd.ui.gen_dialog.property;

import vrd.alg.property.UIntProperty;
import vrd.ui.std.UIntSpinner;
import vrd.ui.std.Style;
import vrd.alg.property.Property;

public class UIntPropertyCell extends PropertyCell
{
    public UIntPropertyCell(UIntProperty property)
    {
        super(property);

        this.value_field = new UIntSpinner(property.value);
            this.value_field.setBackground(Style.enabled_color);
        
        add(value_field);
    }

    @Override
    public Property getProperty()
    { return new UIntProperty((int)((UIntSpinner)this.value_field).getValue(), this.name_label.getName()); } // todo: the casts are cancerous

    @Override
    public boolean validateInput()
    { return true; }
}