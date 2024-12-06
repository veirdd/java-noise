package vrd.ui.gen_dialog.property;

import vrd.alg.property.FloatProperty;
import vrd.ui.std.FloatField;
import vrd.ui.std.Style;
import vrd.alg.property.Property;

public class FloatPropertyCell extends PropertyCell
{
    public FloatPropertyCell(FloatProperty property)
    {
        super(property);

        this.value_field = new FloatField(property.value);
            this.value_field.setBackground(Style.enabled_color);
        
        add(value_field);
    }

    public Property getProperty()
    { return new FloatProperty(((FloatField)this.value_field).getValue(), this.name_label.getName()); }

    public boolean validateInput()
    {
        boolean valid = ((FloatField)this.value_field).validateInput();

        if(valid)
        { this.value_field.setBackground(Style.enabled_color);}
        else
        { this.value_field.setBackground(Style.bad_color);}

        return valid;
    }
}