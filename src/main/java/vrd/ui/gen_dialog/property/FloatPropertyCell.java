package vrd.ui.gen_dialog.property;

import vrd.gen.alg.property.FloatProperty;
import vrd.gen.alg.property.Property;
import vrd.ui.std.FloatField;
import vrd.ui.std.Style;

public class FloatPropertyCell extends PropertyCell
{
    public FloatPropertyCell(FloatProperty property)
    {
        super(property);

        this.field = new FloatField(property.value);
            this.field.setBackground(Style.enabled_color);
        
        add(field);
    }

    @Override
    public Property getProperty()
    { return new FloatProperty(this.name_label.getName(), this.field.getValue()); }

    @Override
    public boolean validateInput()
    {
        boolean valid = this.field.validateInput();

        if(valid)
        { this.field.setBackground(Style.enabled_color);}
        else
        { this.field.setBackground(Style.bad_color);}

        return valid;
    }

    private FloatField field;
}