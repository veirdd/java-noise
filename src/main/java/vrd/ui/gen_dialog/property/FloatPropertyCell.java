package vrd.ui.gen_dialog.property;

import vrd.alg.property.FloatProperty;
import vrd.ui.std.FloatField;

public class FloatPropertyCell extends PropertyCell
{
    public FloatPropertyCell(FloatProperty property)
    {
        super(property);

        this.value_field = new FloatField();
        
        add(value_field);
    }
}